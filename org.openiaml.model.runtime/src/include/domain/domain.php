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
	
	/**
	 * Get the DomainAttributeInstance of the given name.
	 */
	public function getAttribute($name) {
		// get the current object instance
		$obj = $this->fetchInstance();
		
		// and return the attribute
		if (!isset($obj[$name])) {
			throw new IamlDomainException("Could not find a DomainAttribute named '$name'");
		}
		
		$attribute = new DomainAttributeInstance(
			$this, $name, $obj[$name]
		);
		
		return $attribute;
	}
	
	/**
	 * Return an associative array of the current instance.
	 */
	public function fetchInstance() {
		$type = $this->source->getType();

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
			
			return $obj;
	
		} else {
			throw new IamlDomainException("Unknown source type $type");
		}

	}

	/**
	 * We want to update the given attribute instance with the new value.
	 */	
	public function setAttribute($attrinst, $value) {
		$type = $this->source->getType();

		if ($type == 'RELATIONAL_DB') {
			$source = "sqlite:" . $this->source->getFile();
			$db = new DatabaseQuery($source);
			
			// find the primary key
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
	protected abstract function constructArgs();
	
	/**
	 * Get the offset of the current query.
	 */
	protected abstract function getOffset();
	
	/**
	 * Set the offset to the current value.
	 */
	protected abstract function setOffset($value);
	
	public function reset() {
		$this->setOffset(0);
	}
	
	public function hasNext() {
		return ($this->count() > 0) && ($this->getOffset() < $this->count());
	}
	
	public function hasPrevious() {
		return ($this->count() > 0) && ($this->getOffset() > 0);
	}
	
	/**
	 * Move the offset forward and return the new object instance.
	 */
	public function next() {
		$this->setOffset($this->getOffset() + 1);
		return $this->fetchInstance();
	}

	/**
	 * Move the offset backwards and return the new object instance.
	 */
	public function previous() {
		$this->setOffset($this->getOffset() - 1);
		return $this->fetchInstance();
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
		$this->iterator->setAttribute($this, $value);
		$this->value = $value;
	}
	
}
