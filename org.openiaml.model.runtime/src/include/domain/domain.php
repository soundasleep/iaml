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
	
	public function initDirectJoins($source) {
		$joins = array();
		// go over all attributes
		foreach ($this->attributes as $attribute) {
			// does this attribute extend?
			if ($attribute->getExtends() !== null) {
				$extends = $attribute->getExtends();

				// find a Schema in this source that can match it
				$schema = $source->findSchemaForAttribute($extends);
				
				if ($schema === null) {
					// we couldn't find a schema in the given source
					throw new IamlDomainException("Could not find a schema in the source " . $source->toString() . " for attribute " . $extends->toString());
				}
			
				$joins[$schema->getSourceID()] = 
					" JOIN " . $schema->getTableName() . " ON " .
					$this->getTableName() . "." . $attribute->getName() .
					" = " . $schema->getTableName() . "." . $extends->getName();
			}
		}
	
		AllDirectJoins::getInstance()->add($this->source_id, $joins);
	}
	
	/**
	 * NOTE this is not the PHP toString() method
	 */
	public function toString() {
		return "Schema '" . $this->getTableName() . "' [source_id='" . $this->getSourceID() . "']";
	}
	
	/**
	 * Find the primary key DomainAttribute in this schema.
	 */
	public function getPrimaryKey() {
		foreach ($this->attributes as $a) {
			if ($a->isPrimaryKey()) {
				return $a;
			}
		}
		throw new IamlDomainException("Could not find a primary key for schema " . $this->toString());
	}

}

/**
 * The actual attribute definition.
 */
abstract class DomainAttribute {

	// boolean: is this key a primary key?
	var $isPrimaryKey;
	
	// the XSD type URI
	var $type;
	
	// a DomainAttribute this attribute extends, or <code>null</code>
	var $extends = null;
	
	// the name of the attribute
	var $name = null;

	public function getType() { return $this->type; }
	public function getName() { return $this->name; }
	public function isPrimaryKey() { return $this->isPrimaryKey; }
	public function getExtends() { return $this->extends; }
	
	/**
	 * NOTE this is not the PHP toString() method
	 */
	public function toString() {
		return "Attribute '" . $this->getName() . "' [type='" . $this->getType() . "']";
	}
	
}

abstract class DomainSource {
	// the DomainSchemas this provides
	var $schemas;
	
	// the type of database
	var $type;
	
	// the file
	var $file;
	
	public function getSchemas() { return $this->schemas; }
	public function getSchema($name) { return $this->schemas[$name]; }
	public function getType() { return $this->type; }
	public function getFile() { return $this->file; }

	/**
	 * Initialise the attribute extensions. Should not be called
	 * in the constructor, but rather before evaluating a query.
	 */
	public function initExtensions() {
		if ($this->schemas === null) {
			throw new IamlDomainException("No such schemas for " . get_class($this));
		}
	
		foreach ($this->schemas as $schema) {
			$schema->initDirectJoins($this);
		}
	}
	
	/**
	 * Find the schema that contains the given attribute within this source;
	 * or <code>null</code> if none can be found.
	 */
	public function findSchemaForAttribute($attribute) {
		foreach ($this->schemas as $schema) {
			foreach ($schema->getAttributes() as $a) {
				if ($a === $attribute) {
					return $schema;
				}
			}
		}
	
		// couldn't find any
		return null;
	}
		
	/**
	 * NOTE this is not the PHP toString() method
	 */
	public function toString() {
		return "DomainSource " . $this->getType() . "' [file='" . $this->getFile() . "']";
	}
	
	
}

/**
 * Represents only one instance of a domain object.
 */
abstract class DomainIterator {
	
	// the DomainSchema of the [main] instance
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
	 * Check to make sure that the table exists; if it doesn't, it
	 * will be created.
	 */
	protected function possiblyCreateTable($schema = null) {
		if ($schema === null) {
			// we will use this schema
			$schema = $this->schema;
		}
		
		// do we need to create any schemas below this current schema?
		// this eventually recurses down to create all tables in the database
		foreach ($schema->getAttributes() as $attribute) {
			if ($attribute->getExtends() !== null) {
				$new_schema = $this->source->findSchemaForAttribute($attribute->getExtends());
				if ($new_schema === null) {
					throw new IamlDomainException("Could not find any schema for " . $attribute->getExtends()->toString() . " in source " . $this->source->toString());
				}
				
				$this->possiblyCreateTable($new_schema);
			}
		}
		
		$type = $this->source->getType();
		if ($type == 'RELATIONAL_DB') {
			
			// try to prepare a query on the table name; if the table doesn't exist,
			// this will not succeed
			$db = new DatabaseQuery("sqlite:" . $this->source->getFile());
			$query = "SELECT 1 FROM " . $schema->getTableName();
			if ($db->tableExists($query)) {
				// OK
				return;	
			}
			
			// we need to create the new table
			$db = new DatabaseQuery("sqlite:" . $this->source->getFile());
			$query = "CREATE TABLE " . $schema->getTableName();
			$bits = array();
			foreach ($schema->getAttributes() as $key => $value) {
				if ($value->isPrimaryKey() && $value->getType() === "iamlInteger") {
					// this needs to be auto increment
					$bits[] = "$key INTEGER PRIMARY KEY AUTOINCREMENT";
				} else {
					// this is a normal key
					switch ($value->getType()) {
						case "iamlString":
						case "iamlEmail":
							$rowtype = "VARCHAR(255)";
							break;

						case "iamlInteger":
							$rowtype = "INTEGER";
							break;
							
						default:
							throw new IamlDomainException("Unknown attribute type " . $value->getType());
					}
					$bits[] = "$key $rowtype";
				}
			}
			if (!$bits) {
				throw new IamlDomainException("Schema " . $schema->getTableName() . " had no attributes"); 
			}
			$query .= "(" . implode(", ", $bits) . ")";
			
			// execute
			$db->execute($query);
			
		} else {
			throw new IamlDomainException("Unknown source type $type");
		}

	}
	
	/**
	 * Get all of the attributes, both contained and not-contained, in the
	 * given schema, and return them as a list of keys.
	 */
	protected function allAttributes($schema) {
		$result = array();
		foreach ($schema->getAttributes() as $key => $value) {
			if ($value->getExtends() !== null) {
				// find the schema
				$found = $this->source->findSchemaForAttribute($value->getExtends());
				if ($found === null) {
					throw new IamlDomainException("Could not find any schema for " . $value->getExtends()->toString() . " in source " . $this->source->toString());
				}
				$f2 = $this->allAttributes($found);
				foreach ($f2 as $k => $a) {
					// don't overwrite existing attributes
					if (!isset($result[$k]))
						$result[$k] = $a;
				}
			}
			
			$result[$key] = $value;
		}
		return $result;
	}
	
	/**
	 * Reload the instance; updates $current_result.
	 */
	public function reload() {
		// init joins
		$this->source->initExtensions();
	
		$type = $this->source->getType();
		
		if ($this->isNew()) {
			if ($this->getNewInstanceID($this->schema->getTableName()) === null) {
				$this->current_result = array();
				foreach ($this->allAttributes($this->schema) as $key => $attribute) {
					$value = null;	// empty
					$o2 = new DomainAttributeInstance(
						$this, $key, $value, $attribute
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
					$query = $this->schema->getTableName() . "." . $this->getPKName() . " = ?";
					$args = array($this->getNewInstanceID($this->schema->getTableName()));
		
					$obj = evaluate_select_wire(
						"sqlite:" . $this->source->getFile(),
						$this->schema->getSourceID(),
						$this->schema->getTableName(),
						$query,
						$args
					);

					// translate the array(key=>value) into array(key=>DomainAttributeInstance)
					$this->current_result = array();
					foreach ($this->allAttributes($this->schema) as $key => $attribute) {
						$o2 = new DomainAttributeInstance(
							$this, $key, $obj[$key], $attribute
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
			// we might need to create the table first
			$this->possiblyCreateTable();
		
			/*
			 * evaluate_select_wire($db_name, $source_id, $source_class, 
			 *		$query, $args, $offset = 0, $order_by = "", 
			 *		$order_ascending = true)
			 */

			$query = $this->query;
			$args = $this->constructArgs();

			$obj = evaluate_select_wire(
				"sqlite:" . $this->source->getFile(),
				$this->schema->getSourceID(),
				$this->schema->getTableName(),
				$query,
				$args,
				$this->getOffset(),
				$this->order_by,
				$this->order_ascending
			);
			
			// translate the array(key=>value) into array(key=>DomainAttributeInstance)
			$this->current_result = array();
			foreach ($this->allAttributes($this->schema) as $key => $attribute) {
				$o2 = new DomainAttributeInstance(
					$this, $key, $obj[$key], $attribute
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
		foreach ($this->schema->getAttributes() as $name => $attr) {
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
	
	/**
	 * Create a blank new instance of the given schema.
	 * Return the generated ID.
	 * Recurses to create any extended tables as well.
	 */
	protected function initialiseStructure($schema) {
		foreach ($schema->getAttributes() as $attribute) {
			// is this attribute extended?
			if ($attribute->getExtends() !== null) {
				$parent = $this->source->findSchemaForAttribute($attribute->getExtends());
				if ($parent === null) {
					throw new IamlDomainException("Could not find any schema for " . $attribute->getExtends()->toString() . " in source " . $this->source->toString());
				}
				
				if ($this->getNewInstanceID($parent->getTableName()) === null) {
					$new_id = $this->initialiseStructure($parent);
					$this->getAttribute($attribute->getName())->setValue($new_id);
				}
			}
		}
		
		// find the primary key
		$pk = null;
		foreach ($schema->getAttributes() as $attribute) {
			if ($attribute->isPrimaryKey()) {
				$pk = $attribute;
			}
		}
		if ($pk === null) {
			throw new IamlDomainException("Could not find any primary key for schema " . $schema->toString());
		}

		$source = "sqlite:" . $this->source->getFile();
		$db = new DatabaseQuery($source);
		
		// create new
		$query = "INSERT INTO " . $schema->getTableName()
			. " DEFAULT VALUES";
		$db_handle = $db->execute($query, array());
		
		// the newly created ID
		$new_id = $db_handle->lastInsertId($pk->getName());
		$this->setNewInstanceID($schema->getTableName(), $new_id);
		
		return $new_id;
	}
	
	protected function saveAttribute($attrinst, $value) {
		$type = $this->source->getType();

		if ($type == 'RELATIONAL_DB') {
			// we might need to create the table first
			$this->possiblyCreateTable();

			$attr_pk = $attrinst->getDefinition();
			while ($attr_pk !== null) {
				if ($attr_pk->isPrimaryKey()) {
					// we don't update PKs
					return;
				}
				// see if the parent extend is a PK
				$attr_pk = $attr_pk->getExtends();
			}
			
			$target_schema = $this->source->findSchemaForAttribute($attrinst->getDefinition());
			if ($target_schema === null) {
				throw new IamlDomainException("Could not find target schema for attribute " . $attrinst->getDefinition()->toString() . " in source " . $this->source);
			}

			
			// what is the primary key in this schema?
			$schema_pk = $target_schema->getPrimaryKey();
			$schema_pk_name = $schema_pk->getName();
						
			// if this is a NEW object that hasn't been saved yet, we have to
			// insert in default values
			if ($this->isNew() && $this->getNewInstanceID($target_schema->getTableName()) === null) {
				$new_id = $this->initialiseStructure($target_schema);
				$this->getAttribute($schema_pk_name)->setValue($new_id);
			}

			// find the value of this primary key for our current instance
			$schema_pk_value = $this->getValueForPrimaryKey($schema_pk);
			
			$source = "sqlite:" . $this->source->getFile();
			$db = new DatabaseQuery($source);
			$query = "UPDATE " .
				$target_schema->getTableName() .
				" SET " . 
				$attrinst->getName() .
				" = ? WHERE $schema_pk_name = ?";
			$args = array($value, $schema_pk_value);
			
			$db->execute($query, $args);
	
		} else {
			throw new IamlDomainException("Unknown source type $type");
		}
	}
	
	/**
	 * Find the value in the current instance that refers to the given
	 * PK attribute.
	 */
	protected function getValueForPrimaryKey($attribute) {
		foreach ($this->toArray() as $key => $value) {
			if ($value->getDefinition() === $this->getRootExtends($attribute)) {
				return $value->getValue();
			}
		}
		
		throw new IamlDomainException("Could not find value for PK attribute " . $attribute->getDefinition()->toString());
	}
	
	protected function getRootExtends($attribute) {
		if ($attribute->getExtends() === null) {
			return $attribute;
		}
		return $this->getRootExtends($attribute->getExtends());
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
	public abstract function getNewInstanceID($key);

	/**
	 * Set the ID of the newly created instance (called internally).
	 */
	public abstract function setNewInstanceID($key, $id);
	
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
			// we might need to create the table first
			$this->possiblyCreateTable();

			/*
			 * evaluate_select_wire_count($db_name, $source_id, $source_class, 
			 *		$query, $args)
			 */
			 
			$query = $this->query;
			$args = $this->constructArgs();

			$obj = evaluate_select_wire_count(
				"sqlite:" . $this->source->getFile(),
				$this->schema->getSourceID(),
				$this->schema->getTableName(),
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
		$this->setNewInstanceID($this->schema->getTableName(), null);
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
	
	// the definition of the attribute
	var $def;
	
	public function __construct($iterator, $name, $value, $def) {
		$this->iterator = $iterator;
		$this->name = $name;
		$this->value = $value;
		$this->def = $def;
	}
	
	public function getValue() {
		return $this->value;
	}
	public function getName() { return $this->name; }
	public function getDefinition() { return $this->def; }
	
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

class AllDirectJoins {
	var $all_direct_joins = array();

	private function __construct() { }

	// the current instance
	static $instance = null;
	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new AllDirectJoins();
		}
		return self::$instance;
	}
	
	public function add($key, $value) {
		$this->all_direct_joins[$key] = $value;
	}
	
	public function getJoins() {
		return $this->all_direct_joins;
	}
	
}

function get_all_domain_joins() {
	return AllDirectJoins::getInstance()->getJoins();
}

