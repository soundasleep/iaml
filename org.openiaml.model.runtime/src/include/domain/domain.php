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
	var $attributes = array();

	// the table name
	var $table_name;

	// the ID of this schema
	var $source_id;

	public function getAttributes() {
		return $this->attributes;
	}

	/**
	 * Add the given DomainAttribute to the Schema.
	 */
	public function addAttribute(DomainAttribute $a) {
		// we can't store as a direct string, otherwise we may overwrite a student_id with another student_id
		$this->attributes[] = $a;
	}

	public function getTableName() {
		return $this->table_name;
	}
	public function getSourceID() { return $this->source_id; }

	public function initDirectJoins($source) {
		$joins = array();
		// go over all attributes
		foreach ($this->getAttributes() as $attribute) {
			// does this attribute extend?
			if ($attribute->getExtends() !== null) {
				$extends = $attribute->getExtends();

				// find a Schema in this source that can match it
				$schema = $source->findSchemaForAttribute($extends);

				if ($schema === null) {
					// we couldn't find a schema in the given source
					throw new IamlDomainException("For foreign key '" . $attribute->toString() . "': Could not find a schema in the source " . $source->toString() . " for attribute " . $extends->toString());
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
		foreach ($this->getAttributes() as $a) {
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
			throw new IamlDomainException("No schemas for source " . get_class($this));
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
		return "DomainSource '" . $this->getType() . "' [file='" . $this->getFile() . "']";
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

	// the limit of the query, default -1 = all
	var $limit = -1;

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
	public function getSource() { return $this->source; }
	public function getLimit() { return $this->limit; }

	/**
	 * Check that either a result exists, or we are new.
	 * Throws a IamlDomainException if calling a get() method would fail.
	 */
	protected function checkResultsExist() {
		if (!$this->isNew() && $this->isEmpty()) {
			throw new IamlDomainException("No results for query '" . $this->query . "'");
		}
	}

	/**
	 * Get the DomainAttributeInstance of the given name. Note that if two attributes
	 * have the same name, this will not return both.
	 *
	 * Checks that a valid result exists; if the query would return no results, throws an exception.
	 *
	 * @see #getAttributeInstance()
	 */
	public function getAttribute($name) {
		// possibly reload
		if ($this->current_result === null) {
			$this->reload();
		}

		// check we have a result
		$this->checkResultsExist();

		// and return the attribute
		foreach ($this->current_result as $value) {
			if ($name == $value->getName()) {
				return $value;
			}
		}

		// could not find any with the given name; fail
		throw new IamlDomainException("Could not find a DomainAttribute named '$name'");
	}

	/**
	 * Get the DomainAttributeInstance of the given DomainAttribute.
	 *
	 * Checks that a valid result exists; if the query would return no results, throws an exception.
	 */
	public function getAttributeInstance(DomainAttribute $attribute) {
		// possibly reload
		if ($this->current_result === null) {
			$this->reload();
		}

		// check we have a result
		$this->checkResultsExist();

		// and return the attribute
		foreach ($this->current_result as $value) {
			if ($attribute === $value->getDefinition()) {
				return $value;
			}
		}

		// could not find any with the given name; fail
		throw new IamlDomainException("Could not find a DomainAttribute for '" . $attribute->toString() . "'");
	}

	public function getAttributeInstances() {
		// possibly reload
		if ($this->current_result === null) {
			$this->reload();
		}

		// check we have a result
		$this->checkResultsExist();

		return $this->current_result;
	}

	/**
	 * Return an associative array of the current instance; e.g.
	 * id => DomainAttributeInstance(id), ...
	 *
	 * NOTE that if two attributes are provided with the same name, one will
	 * be overridden; use {@link #getAttributeInstance()} and {@link #getAttributes()} instead.
	 *
	 * Checks that a valid result exists; if the query would return no results, throws an exception.
	 */
	public function toArray() {
		// possibly reload
		if ($this->current_result === null) {
			$this->reload();
		}

		// check we have a result
		$this->checkResultsExist();

		// now put it into an associative array
		$result = array();
		foreach ($this->current_result as $value) {
			$result[$value->getName()] = $value;
		}

		return $result;
	}

	/**
	 * Check to make sure that the table exists; if it doesn't, it
	 * will be created.
	 *
	 * Checks that a valid result exists; if the query would return no results, throws an exception.
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
			$db = new DatabaseQuery($this->source->getFile());
			$query = "SELECT 1 FROM " . $schema->getTableName();
			if ($db->tableExists($query)) {
				// OK
				return;
			}

			log_message("[domain] Creating new schema '" . $schema->toString() . "'");

			// we need to create the new table
			$db = new DatabaseQuery($this->source->getFile());
			$query = "CREATE TABLE " . $schema->getTableName();
			$bits = array();
			foreach ($schema->getAttributes() as $value) {
				$key = $value->getName();
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
	 * This does not return an associative array of keys anymore.
	 */
	protected function allAttributes($schema) {
		$result = array();
		foreach ($schema->getAttributes() as $value) {
			$key = $value->getName();
			if ($value->getExtends() !== null) {
				// find the schema
				$found = $this->source->findSchemaForAttribute($value->getExtends());
				if ($found === null) {
					throw new IamlDomainException("Could not find any schema for " . $value->getExtends()->toString() . " in source " . $this->source->toString());
				}
				$f2 = $this->allAttributes($found);
				foreach ($f2 as $k => $a) {
					// we only want unique attributes
					if (array_search($a, $result, true) === false) {
						$result[] = $a;
					}
				}
			}

			// we only want unique attributes
			if (array_search($value, $result, true) === false) {
				$result[] = $value;
			}
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
				log_message("[domain reload] creating a new empty instance");
				$this->current_result = array();

				// allAttributes() also includes all parent schemas
				foreach ($this->allAttributes($this->schema) as $attribute) {
					$key = $attribute->getName();
					$value = null;	// empty
					$o2 = new DomainAttributeInstance(
						$this, $key, $value, $attribute
					);
					$this->current_result[] = $o2;
				}

				return;
			} else {
				log_message("[domain reload] reloading a created new instance");
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
						$this->source->getFile(),
						$this->schema->getSourceID(),
						$this->schema->getTableName(),
						$query,
						$args
					);

					// translate the array(key=>value) into array(key=>DomainAttributeInstance)
					log_message("result: " . print_r($obj, true));
					$this->current_result = array();
					foreach ($this->allAttributes($this->schema) as $attribute) {
						$key = $attribute->getName();
						$o2 = new DomainAttributeInstance(
							$this, $key, $obj[$key], $attribute
						);
						$this->current_result[] = $o2;
					}

					return;
				} else {
					throw new IamlDomainException("Unknown source type $type");
				}
			}
		}

		if ($type == 'RELATIONAL_DB') {
			// we might need to create the table first
			if ($this->isAutosave()) {
				$this->possiblyCreateTable();
			}

			// check we aren't going beyond our limit
			if ($this->getLimit() > 1 && $this->getOffset() >= $this->getLimit()) {
				$offset = $this->getOffset();
				$limit = $this->getLimit();
				throw new IamlDomainException("Illegal offset $offset: Cannot select past limit $limit");
			}

			/*
			 * evaluate_select_wire($db_name, $source_id, $source_class,
			 *		$query, $args, $offset = 0, $order_by = "",
			 *		$order_ascending = true)
			 */

			$query = $this->query;
			$args = $this->constructArgs();

			// construct the order_by as TableName.attribute_name
			$order_by = "";
			if ($this->order_by !== null) {
				$container = $this->source->findSchemaForAttribute($this->order_by);
				if ($container === null) {
					throw new IamlDomainException("Cannot find container for order_by attribute '" . $this->order_by->toString() . "'");
				}
				$order_by = $container->getTableName() . "." . $this->order_by->getName();
			}

			$obj = evaluate_select_wire(
				$this->source->getFile(),
				$this->schema->getSourceID(),
				$this->schema->getTableName(),
				$query,
				$args,
				$this->getOffset(),
				$order_by,
				$this->order_ascending
			);

			// translate the array(key=>value) into array(key=>DomainAttributeInstance)
			$this->current_result = array();
			foreach ($this->allAttributes($this->schema) as $attribute) {
				$key = $attribute->getName();
				$o2 = new DomainAttributeInstance(
					$this, $key, $obj[$key], $attribute
				);
				$this->current_result[] = $o2;
			}

		} else {
			throw new IamlDomainException("Unknown source type $type");
		}
	}

	/**
	 * Manually save the current instance.
	 */
	public function save() {
		foreach ($this->current_result as $value) {
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
		foreach ($this->schema->getAttributes() as $attr) {
			$name = $attr->getName();
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
	protected function initialiseInstance($schema) {
		log_message("[domain init] Initialise instance: " . $schema->toString());

		foreach ($schema->getAttributes() as $attribute) {
			// is this attribute extended?
			if ($attribute->getExtends() !== null) {
				$parent = $this->source->findSchemaForAttribute($attribute->getExtends());
				if ($parent === null) {
					throw new IamlDomainException("Could not find any schema for " . $attribute->getExtends()->toString() . " in source " . $this->source->toString());
				}

				if ($this->getNewInstanceID($parent->getTableName()) === null) {
					log_message("[domain init] Initialising parent instance: " . $parent->toString());
					$new_id = $this->initialiseInstance($parent);
					log_message("Attribute " . $attribute->getName() . " set to value $new_id");

					// update the FK
					$this->getAttributeInstance($attribute)->setValueManually($new_id);
				} else {
					// update it to the current value
					$this->getAttributeInstance($attribute)->setValueManually($this->getNewInstanceID($parent->getTableName()));
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

		$source = $this->source->getFile();
		$db = new DatabaseQuery($source);

		// create new
		$query = "INSERT INTO " . $schema->getTableName()
			. " DEFAULT VALUES";
		$db_handle = $db->execute($query, array());

		// the newly created ID
		$new_id = $db_handle->lastInsertId($pk->getName());
		$this->setNewInstanceID($schema->getTableName(), $new_id);

		// update the current attribute instance
		$this->getAttributeInstance($pk)->setValueManually($new_id);

		return $new_id;
	}

	protected function saveAttribute($attrinst, $value) {
		$type = $this->source->getType();

		// if we haven't saved a new object yet, we first have to get the PK of this object
		// otherwise we may create sub-tables that are then overridden later
		if ($this->isNew() && $this->getNewInstanceID($this->schema->getTableName()) === null) {
			// we might need to create the table first
			$this->possiblyCreateTable();

			$this->initialiseInstance($this->schema);
		}

		if ($type == 'RELATIONAL_DB') {
			// we might need to create the table first
			$this->possiblyCreateTable();

			// we don't update direct PKs
			if ($attrinst->getDefinition()->isPrimaryKey()) {
				return;
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
			if ($this->isNew()) {
			 	if ($this->getNewInstanceID($target_schema->getTableName()) === null) {
					$new_id = $this->initialiseInstance($target_schema);
					log_message("[domain] Set '$schema_pk_name' to new ID '$new_id'");
					$this->getAttributeInstance($schema_pk)->setValue($new_id);

					if ($attrinst === $schema_pk) {
						// update the value directly
						$attrinst->setValue($new_id);
					}
				} else {
					// the new ID hasn't been saved yet in the instance; we need to
					// update the attribute manually
					$schema_pk_value = $this->getNewInstanceID($target_schema->getTableName());
					if ($this->getAttributeInstance($schema_pk)->getValue() != $schema_pk_value) {
						$this->getAttributeInstance($schema_pk)->setValue($schema_pk_value);
					}
				}
			}

			// find the value of this primary key for our current instance
			log_message("[domain] Target schema: " . $target_schema->toString());
			$schema_pk_value = $this->getValueForPrimaryKey($schema_pk);
			if ($schema_pk_value === null) {
				throw new IamlDomainException("Cannot use a NULL schema primary key '" . $schema_pk->toString() . "': $schema_pk_value");
			}

			$source = $this->source->getFile();
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
		$this->toArray();
		foreach ($this->current_result as $value) {
			if ($value->getDefinition() === $this->getRootExtends($attribute)) {
				return $value->getValue();
			}
		}

		throw new IamlDomainException("Could not find value for PK attribute " . $attribute->toString());
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
		return ($this->count() > 0) && ($this->getOffset() < $this->count() - 1) &&
			($this->getLimit() <= 1 || $this->getOffset() < ($this->getLimit() - 1));
	}

	public function hasPrevious() {
		return ($this->count() > 0) && ($this->getOffset() > 0) &&
			($this->getLimit() <= 1 || $this->getOffset() > 0);
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
	 * Returns true if count = 0.
	 */
	public function isEmpty() {
		return $this->count() == 0;
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
				$this->source->getFile(),
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
		// make sure that this is a new object
		if (!$this->isNew()) {
			throw new IamlDomainException("Cannot create a new instance of Iterator type '" . get_class($this) . "'");
		}

		$this->resetSchema($this->schema);

		// finally, reload
		// (this will set all new fields to <code>null</code>, etc.)
		$this->reload();
	}

	/**
	 * For all schemas in this instance, set the new instance ID(s) to null
	 */
	private function resetSchema($schema) {
		// set to null
		$this->setNewInstanceID($schema->getTableName(), null);

		// and set all parents to null as well (recursively)
		foreach ($schema->getAttributes() as $attribute) {
			if ($attribute->getExtends() !== null) {
				$parent = $this->source->findSchemaForAttribute($attribute->getExtends());
				if ($parent === null) {
					throw new IamlDomainException("Could not find parent schema for '" . $attribute->getExtends()->toString() . "' in source '" . $this->source->toString() . "'");
				}
				$this->resetSchema($parent);
			}
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

	/**
	 * We want to update the attribute instance value with the new value,
	 * but this does not trigger a save! This is not intended to be used
	 * by clients.
	 */
	public function setValueManually($value) {
		$this->value = $value;
	}

	/**
	 * Get the containing schema for this attribute instance.
	 */
	public function getContainingSchema() {
		return $this->iterator->getSource()->findSchemaForAttribute($this->getDefinition());
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

