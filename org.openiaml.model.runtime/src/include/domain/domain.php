<?php

/**
 * Domain modelling classes and operations.
 *
 */

/**
 * The structure of a collection of attributes.
 */ 
abstract class DomainSchema {
	// the list of DomainAttributes
	var $attributes;
	
	// the table name
	var $table_name;
	
	// the ID of this schema
	var $source_id;

	public function getAttributes() {
		return $this->attributes;
	}
	
	public function getTableName() {
		return $this->table_name;
	}
	public function getSourceID() { return $this->source_id; }

}

/**
 * The actual attribute definition.
 */
abstract class DomainAttribute {
	// the containing Schema
	var $schema;
	
	// boolean: is this key a primary key?
	var $isPrimaryKey;
	
	// the XSD type URI
	var $type;

	public function getSchema() { return $this->schema; }
	public function getType() { return $this->type; }
	public function isPrimaryKey() { return $this->isPrimaryKey; }
	
}

abstract class DomainSource {
	// the DomainSchema
	var $schema;
	
	// the type of database
	var $type;
	
	// the file
	var $file;
	
	public function getSchema() { return $this->schema; }
	public function getType() { return $this->type; }
	public function getFile() { return $this->file; }
	
	public function getTableName() { 
		return $this->schema->getTableName(); 
	}

}

/**
 * Represents only one instance of a domain object.
 */
abstract class DomainIterator {
	
	// the DomainSchema of the instance
	var $schema;
	
	// the source of the data
	var $source;
	
	// the query
	var $query;
	
	// the limit
	var $limit;
	
	// the order
	var $order_by;
	var $order_ascending;
	
	// the current result array
	var $current_result = null;
	
	// is this autosave?
	var $autosave = false;
	
	// is this a new instance?
	var $is_new = false;
	
	public function isAutosave() { return $this->autosave; }
	public function isNew() { return $this->is_new; }
	
	/**
	 * Get the DomainAttributeInstance of the given name.
	 */
	public function getAttribute($name) {
		// get the current object instance
		$obj = $this->toArray();
		
		// and return the attribute
		if (!isset($obj[$name])) {
			throw new IamlDomainException("Could not find a DomainAttribute named '$name'");
		}
		
		// this is already an object
		return $obj[$name];
	}
	
	/**
	 * Return an associative array of the current instance.
	 */
	public function toArray() {
		if ($this->current_result === null) {
			$this->reload();
		}
		return $this->current_result;
	}
	
	/**
	 * Reload the instance; updates $current_result.
	 */
	public function reload() {
		$type = $this->source->getType();
		
		if ($this->isNew()) {
			if ($this->getNewInstanceID() === null) {
				$this->current_result = array();
				foreach ($this->source->getSchema()->getAttributes() as $key => $attr) {
					$value = null;	// empty
					$o2 = new DomainAttributeInstance(
						$this, $key, $value
					);
					$this->current_result[$key] = $o2;
				}
			
				return;
			} else {
				if ($type == 'RELATIONAL_DB') {
					/*
					 * evaluate_select_wire($db_name, $source_id, $source_class, 
					 *		$query, $args, $offset = 0, $order_by = "", 
					 *		$order_ascending = true)
					 */
					
					// for new instances, we select by ID
					$query = $this->getPKName() . " = ?";
					$args = array($this->getNewInstanceID());
		
					$obj = evaluate_select_wire(
						"sqlite:" . $this->source->getFile(),
						$this->source->getSchema()->getSourceID(),
						$this->source->getSchema()->getTableName(),
						$query,
						$args
					);
					
					// translate the array(key=>value) into array(key=>DomainAttributeInstance)
					$this->current_result = array();
					foreach ($this->source->getSchema()->getAttributes() as $key => $attr) {
						$o2 = new DomainAttributeInstance(
							$this, $key, $obj[$key]
						);
						$this->current_result[$key] = $o2;
					}
					
					return;
				} else {
					throw new IamlDomainException("Unknown source type $type");
				}
			}
		}

		if ($type == 'RELATIONAL_DB') {
			/*
			 * evaluate_select_wire($db_name, $source_id, $source_class, 
			 *		$query, $args, $offset = 0, $order_by = "", 
			 *		$order_ascending = true)
			 */
			
			$query = $this->query;
			$args = $this->constructArgs();

			$obj = evaluate_select_wire(
				"sqlite:" . $this->source->getFile(),
				$this->source->getSchema()->getSourceID(),
				$this->source->getSchema()->getTableName(),
				$query,
				$args,
				$this->getOffset(),
				$this->order_by,
				$this->order_ascending
			);
			
			// translate the array(key=>value) into array(key=>DomainAttributeInstance)
			$this->current_result = array();
			foreach ($this->source->getSchema()->getAttributes() as $key => $attr) {
				$o2 = new DomainAttributeInstance(
					$this, $key, $obj[$key]
				);
				$this->current_result[$key] = $o2;
			}
			
		} else {
			throw new IamlDomainException("Unknown source type $type");
		}
	}

	/**
	 * Manually save the current instance.
	 */
	public function save() {
		foreach ($this->current_result as $key => $value) {
			// save this attribute manually
			$this->saveAttribute($value, $value->getValue());
		}
	}
	
	/**
	 * Get the name of the primary key for this instance. Throws an
	 * exception if none can be found.
	 */
	protected function getPKName() {
		$pk_name = null;
		foreach ($this->source->getSchema()->getAttributes() as $name => $attr) {
			if ($attr->isPrimaryKey()) {
				$pk_name = $name;
				break;
			}
		}
		if ($pk_name === null) {
			throw new IamlDomainException("No primary key found for " . $this->source->getSchema()->getName());
		}
		return $pk_name;
	}
	
	protected function saveAttribute($attrinst, $value) {
		$type = $this->source->getType();

		if ($type == 'RELATIONAL_DB') {
			$source = "sqlite:" . $this->source->getFile();
			$db = new DatabaseQuery($source);
			
			// find the primary key
			$pk_name = $this->getPKName();
			
			// we don't update PKs
			if ($pk_name == $attrinst->getName()) {
				return;
			}
			
			// if this is a NEW object that hasn't been saved yet, we have to
			// insert in default values
			if ($this->isNew() && $this->getNewInstanceID() === null) {
				// create new
				$query = "INSERT INTO " . $this->source->getSchema()->getTableName()
					. " DEFAULT VALUES";
				$db_handle = $db->execute($query, array());
				
				// the newly created ID
				$new_id = $db_handle->lastInsertId($pk_name);
				$this->setNewInstanceID($new_id);
				$this->getAttribute($pk_name)->setValue($new_id);
			}
			
			// get the current PK value
			$pk_value = $this->getAttribute($pk_name)->getValue();
			
			$query = "UPDATE " .
				$this->source->getSchema()->getTableName() .
				" SET " . 
				$attrinst->getName() .
				" = ? WHERE $pk_name = ?";
			$args = array($value, $pk_value);
			
			$db->execute($query, $args);
	
		} else {
			throw new IamlDomainException("Unknown source type $type");
		}
	}
	
	/**
	 * Construct an array of arguments to pass to the query ($this->query).
	 * This can be from session data, for example.
	 */ 
	public abstract function constructArgs();
	
	/**
	 * Get the offset of the current query.
	 */
	public abstract function getOffset();
	
	/**
	 * Set the offset to the current value.
	 */
	public abstract function setOffset($value);
	
	/**
	 * Get the ID of the newly created instance, or <code>null</code> if it
	 * hasn't been created yet.
	 */
	public abstract function getNewInstanceID();

	/**
	 * Set the ID of the newly created instance (called internally).
	 */
	public abstract function setNewInstanceID($id);
	
	public function reset() {
		$this->setOffset(0);
	}
	
	public function hasNext() {
		return ($this->count() > 0) && ($this->getOffset() < $this->count() - 1);
	}
	
	public function hasPrevious() {
		return ($this->count() > 0) && ($this->getOffset() > 0);
	}
	
	/**
	 * Move the offset forward and return the new object instance.
	 * This will lose any unsaved changes.
	 */
	public function next() {
		$this->setOffset($this->getOffset() + 1);
		$this->reload();
		return $this->toArray();
	}

	/**
	 * Move the offset backwards and return the new object instance.
	 * This will lose any unsaved changes.
	 */
	public function previous() {
		$this->setOffset($this->getOffset() - 1);
		$this->reload();
		return $this->toArray();
	}
	
	/**
	 * Get the number of results for this query.
	 */
	public function count() {
		$type = $this->source->getType();

		if ($type == 'RELATIONAL_DB') {
			/*
			 * evaluate_select_wire_count($db_name, $source_id, $source_class, 
			 *		$query, $args)
			 */
			 
			$query = $this->query;
			$args = $this->constructArgs();

			$obj = evaluate_select_wire_count(
				"sqlite:" . $this->source->getFile(),
				$this->source->getSchema()->getSourceID(),
				$this->source->getSchema()->getTableName(),
				$query,
				$args
			);
			
			return $obj;
	
		} else {
			throw new IamlDomainException("Unknown source type $type");
		}
	
	}
	
	/**
	 * Create a new instance of this object. If there are any unsaved
	 * changes, they are lost.
	 */
	public function createNew() {
		$this->setNewInstanceID(null);
		$this->reload();
	}

}

class DomainAttributeInstance {

	// the Iterator that defined this
	var $iterator;

	// the name of the attribute
	var $name;
	
	// the current value
	var $value;
	
	public function __construct($iterator, $name, $value) {
		$this->iterator = $iterator;
		$this->name = $name;
		$this->value = $value;
	}
	
	public function getValue() {
		return $this->value;
	}
	public function getName() { return $this->name; }
	
	/**
	 * We want to update the attribute instance with the new value.
	 */
	public function setValue($value) {
		$this->value = $value;
		
		// do we need to autosave?
		if ($this->iterator->isAutosave()) {
			$this->iterator->save();
		}
	}
	
}

class IamlDomainException extends IamlRuntimeException {
	var $more_info;

	public function __construct($message = "", $more_info = "") {
		parent::__construct($message);
		$this->more_info = $more_info;
	}
}

