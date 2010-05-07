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

	/**
	 * Execute the given SQL query with the given arguments.
	 */
	public function executeQueryDirectly($query, $args = array()) {

		if ($this->type == 'RELATIONAL_DB') {

			$db = new DatabaseQuery($this->getFile());

			// execute
			$db->execute($query, $args);

		} else {
			throw new IamlDomainException("Unknown Domain Source type " . $this->type . ": " . $this->toString());
		}

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
	public function getQuery() { return $this->query; }

	public function toString() {
		return "DomainIterator over " . $this->schema->toString() . " on source " . $this->source->toString();
	}

	/**
	 * Check that either a result exists, or we are new.
	 * Throws a IamlDomainException if calling a get() method would fail.
	 */
	protected function checkResultsExist() {
		if (!$this->isNew() && $this->isEmpty()) {
			throw new IamlDomainException("No results found for query '" . $this->query . "'");
		}
	}

	// can no longer getAttribute() by name; too likely to cause an inconsistent database.

	/**
	 * Get the DomainAttributeInstance of the given DomainAttribute.
	 *
	 * Checks that a valid result exists; if the query would return no results, throws an exception.
	 */
	public function getAttributeInstance(DomainAttribute $attribute) {
		// possibly reload
		$this->repopulate();

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
		$this->repopulate();

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
		$this->repopulate();

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
			$this->source->executeQueryDirectly($query);

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
	 * Repopulate the instance; updates $current_result.
	 *
	 * This allows the current instance to be stored across multiple sessions (in $_SESSION, eg.)
	 * and only saved when required.
	 */
	public function repopulate() {
		// if the result is already set, don't overwrite it
		if ($this->current_result !== null) {
			return;
		}

		$needs_reload = false;
		if (!$this->isNew() && $this->argumentsHaveChanged()) {
			// have the query arguments changed? this means that we have to lose our saved values
			// (but only if we are on a Select; New wires are already covered with createNew())
			$needs_reload = true;
			log_message("[domain] Repopulating: Reloading due to changed arguments");
		} elseif (!$this->getStoredValue("initialised", false)) {
			// do we have a defined object already?
			$needs_reload = true;
			log_message("[domain] Repopulating: Reloading as no instance exists");
		}

		if ($needs_reload) {
			log_message("[domain] Repopulating: Reloading");

			// reload it instead
			$this->reload();

			// store it
			foreach ($this->getAttributeInstances() as $inst) {
				$this->updateAttributeInstanceValue($inst);
			}

			// mark this as initialised
			$this->setStoredValue("initialised", true);

			$this->logInstanceToDebug();
			return;
		}

		log_message("[domain] Repopulating: Loading from stored values");

		// otherwise, load from the store
		foreach ($this->allAttributes($this->schema) as $attribute) {
			$schema = $this->source->findSchemaForAttribute($attribute);
			if ($schema === null) {
				throw new IamlDomainException("Cannot find container for attribute '" . $attribute->toString() . "'");
			}

			$key = $attribute->getName();
			$value = $this->getStoredValue($schema->getTableName() . "." . $attribute->getName());
			$o2 = new DomainAttributeInstance(
				$this, $key, $value, $attribute
			);
			$this->current_result[] = $o2;
		}

		$this->logInstanceToDebug();

	}

	/**
	 * Have the arguments to the results of this Iterator changed? If they have, then
	 * we will need to refresh the stored results (potentially losing unsaved changes).
	 */
	protected function argumentsHaveChanged() {
		return $this->getStoredValue("query_argument_cache", "empty") != print_r($this->constructArgs(), true);
	}

	/**
	 * Update the cached arguments. See: {@link #argumentsHaveChanged()}
	 */
	protected function updateCachedArguments() {
		$this->setStoredValue("query_argument_cache", print_r($this->constructArgs(), true));
	}

	/**
	 * Update the stored value of the given attribute instance.
	 */
	public function updateAttributeInstanceValue($attrInst) {
		$attr = $attrInst->getDefinition();
		$schema = $this->source->findSchemaForAttribute($attr);
		if ($schema === null) {
			throw new IamlDomainException("Cannot find container for attribute '" . $attr->toString() . "'");
		}

		log_message("Storing " . $schema->getTableName() . "." . $attr->getName() . " to " . $attrInst->getValue());
		$this->setStoredValue($schema->getTableName() . "." . $attr->getName(), $attrInst->getValue());

		// we also need to save that we've been initialised
		$this->setStoredValue("initialised", true);
	}

	/**
	 * Get a stored value for the given key.
	 */
	public abstract function getStoredValue($key, $default = false);

	/**
	 * Set a stored value for the given key.
	 */
	public abstract function setStoredValue($key, $value);

	/**
	 * Reload the instance from the database; always updates $current_result.
	 * If there are unsaved changes, they are lost.
	 *
	 * If there are no results for the given query, throws a IamlDomainException, and sets
	 * $current_result to <code>null</code>.
	 */
	public function reload() {
		// init joins
		$this->source->initExtensions();

		$type = $this->source->getType();

		// reset current_result
		$this->current_result = null;

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

					// update the stored value
					$this->updateAttributeInstanceValue($o2);
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

					// create a mapping of expected attributes to unique names, so we can retrieve two attributes
					// named 'id', for example
					$m = $this->createMappings();
					$mapping = $m["mapping"];
					$mappingQuery = $m["query"];

					$obj = evaluate_select_wire(
						$this->source->getFile(),
						$this->schema->getSourceID(),
						$this->schema->getTableName(),
						$query,
						$args,
						0, "", true, /* default values */
						$mappingQuery
					);

					// if no results returned, bail
					if ($obj === null) {
						throw new IamlDomainException("No results found for previously created new instance");
					}

					// translate the array(key=>value) into array(key=>DomainAttributeInstance)
					// log_message("result: " . print_r($obj, true));
					$this->current_result = array();
					foreach ($mapping as $mapKey => $attribute) {
						$key = $attribute->getName();
						$o2 = new DomainAttributeInstance(
							$this, $key, $obj[$mapKey], $attribute
						);
						$this->current_result[] = $o2;

						// update the stored value
						$this->updateAttributeInstanceValue($o2);
					}

					return;
				} else {
					throw new IamlDomainException("Unknown source type $type");
				}
			}
		}

		if ($type == 'RELATIONAL_DB') {
			log_message("[domain reload] reloading from the domain source");
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
			$this->updateCachedArguments();

			// construct the order_by as TableName.attribute_name
			$order_by = "";
			if ($this->order_by !== null) {
				$container = $this->source->findSchemaForAttribute($this->order_by);
				if ($container === null) {
					throw new IamlDomainException("Cannot find container for order_by attribute '" . $this->order_by->toString() . "'");
				}
				$order_by = $container->getTableName() . "." . $this->order_by->getName();
			}

			// create a mapping of expected attributes to unique names, so we can retrieve two attributes
			// named 'id', for example
			$m = $this->createMappings();
			$mapping = $m["mapping"];
			$mappingQuery = $m["query"];

			$obj = evaluate_select_wire(
				$this->source->getFile(),
				$this->schema->getSourceID(),
				$this->schema->getTableName(),
				$query,
				$args,
				$this->getOffset(),
				$order_by,
				$this->order_ascending,
				$mappingQuery
			);

			// if no results returned, bail
			if ($obj === null) {
				throw new IamlDomainException("No results found for query '" . $this->getQuery() . "'");
			}

			// translate the array(key=>value) into array(key=>DomainAttributeInstance)
			$this->current_result = array();
			foreach ($mapping as $mapKey => $attribute) {
				$key = $attribute->getName();
				$o2 = new DomainAttributeInstance(
					$this, $key, $obj[$mapKey], $attribute
				);
				$this->current_result[] = $o2;

				// update the stored value
				$this->updateAttributeInstanceValue($o2);
			}
			log_message("Reloaded instance from domain source:");
			$this->logInstanceToDebug();

		} else {
			throw new IamlDomainException("Unknown source type $type");
		}
	}

	/**
	 * Create the necessary mappings for the SELECT (...AS) query.
	 * Returns an associative array: 'mapping' => array (key => DomainAttribute), 'query' => query string.
	 */
	private function createMappings() {
		$mapping = array();
		$mappingQuery = array();
		foreach ($this->allAttributes($this->schema) as $attribute) {
			$key = "a" . count($mapping);
			$mapping[$key] = $attribute;

			$container = $this->source->findSchemaForAttribute($attribute);
			if ($container === null) {
				throw new IamlDomainException("Cannot find container for attribute '" . $attribute->toString() . "'");
			}
			$mappingQuery[] = $container->getTableName() . "." . $attribute->getName() . " AS $key";
		}

		if (!$mapping) {
			throw new IamlDomainException("Could not construct a mapping for an empty schema.");
		}

		return array("mapping" => $mapping, "query" => implode(", ", $mappingQuery));
	}

	/**
	 * Manually save the current instance.
	 */
	public function save() {
		// possibly reload
		$this->repopulate();

		if ($this->current_result === null || (!$this->isNew() && $this->isEmpty())) {
			throw new IamlDomainException("Cannot save " . get_class($this) . ": The current result has not been loaded anywhere.");
		}

		// first, do all PKs/FKs
		foreach ($this->current_result as $value) {
			if ($this->getRootExtends($value->getDefinition())->isPrimaryKey()) {
				$this->saveAttribute($value);
			}
		}

		// then normal data
		foreach ($this->current_result as $value) {
			if (!$this->getRootExtends($value->getDefinition())->isPrimaryKey()) {
				$this->saveAttribute($value);
			}
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
					$attrinst = $this->getAttributeInstance($attribute);
					$attrinst->setValueManually($new_id);

				} else {
					// update it to the current value
					log_message("[domain init] Updating attribute '" . $attribute->toString() . "' manually to '" . $this->getNewInstanceID($parent->getTableName()) . "'");
					$this->getAttributeInstance($attribute)->setValueManually($this->getNewInstanceID($parent->getTableName()));
				}
			}
		}

		// find the primary key
		$pk = null;
		foreach ($schema->getAttributes() as $attribute) {
			if ($attribute->isPrimaryKey()) {
				if ($pk !== null) {
					throw new IamlDomainException("Domain Schema '" . $schema->toString() . "' has more than one primary key attribute");
				}
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

		// and any other attributes that extend this one
		foreach ($this->current_result as $inst) {
			if ($this->getRootExtends($inst->getDefinition()) === $pk) {
				$inst->setValueManually($new_id);
			}
		}

		return $new_id;
	}

	protected function saveAttribute($attrinst) {
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

			log_message("[domain] Saving attribute " . $attrinst->getDefinition()->toString());
			$this->logInstanceToDebug();

			$target_schema = $this->source->findSchemaForAttribute($attrinst->getDefinition());
			if ($target_schema === null) {
				throw new IamlDomainException("Could not find target schema for attribute " . $attrinst->getDefinition()->toString() . " in source " . $this->source);
			}

			// what is the primary key in this schema?
			$schema_pk = $target_schema->getPrimaryKey();
			$schema_pk_name = $schema_pk->getName();

			// if this is a NEW object that hasn't been saved yet, we have to
			// insert in default values
			if ($this->isNew() && $this->getAttributeInstance($schema_pk)->getValue() === null) {
			 	if ($this->getNewInstanceID($target_schema->getTableName()) === null) {
					$new_id = $this->initialiseInstance($target_schema);
					log_message("[domain] Set '$schema_pk_name' to new ID '$new_id'");
					$this->getAttributeInstance($schema_pk)->setValue($new_id);

					if ($attrinst === $schema_pk) {
						// update the value directly
						log_message("[domain] Set attribute instance '" . $attrinst->toString() . "' directly to '$new_id'");
						$attrinst->setValue($new_id);
					}
				} else {
					// the new ID hasn't been saved yet in the instance; we need to
					// update the attribute manually
					$schema_pk_value = $this->getNewInstanceID($target_schema->getTableName());
					if ($this->getAttributeInstance($schema_pk)->getValue() != $schema_pk_value) {
						log_message("[domain] Set attribute instance '" . $schema_pk->toString() . "' to PK value '$schema_pk_value'");
						$this->getAttributeInstance($schema_pk)->setValue($schema_pk_value);
					}
				}
			}

			// find the value of this primary key for our current instance
			log_message("[domain] Target schema: " . $target_schema->toString());
			$this->logInstanceToDebug();
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
			$args = array($attrinst->getValue(), $schema_pk_value);

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
		foreach ($this->current_result as $value) {
			log_message("[domain] Finding primary key: " . $value->getDefinition()->toString() . "=" . $value->getValue());
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

	/**
	 * Start the iterator back at the beginning. Reloads the instance.
	 */
	public function reset() {
		$this->setOffset(0);
		$this->reload();
	}

	/**
	 * Skips the given number of instances. Reloads the instance.
	 */
	public function skip($n) {
		$this->setOffset($this->getOffset() + $n);
		$this->reload();
	}

	/**
	 * Jump to offset $n. Reloads the instance.
	 */
	public function jump($n) {
		$this->setOffset($n);
		$this->reload();
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
	 * If we go beyond the limit of the results, we must throw an IamlDomainException.
	 */
	public function next() {
		if (!$this->hasNext()) {
			throw new IamlDomainException("No results found for query '" . $this->getQuery() . "': Passed beyond end of result set");
		}

		$this->setOffset($this->getOffset() + 1);
		$this->reload();
		return $this->toArray();
	}

	/**
	 * Move the offset backwards and return the new object instance.
	 * This will lose any unsaved changes.
	 * If we go beyond the limit of the results, we must throw an IamlDomainException.
	 */
	public function previous() {
		if (!$this->hasPrevious()) {
			throw new IamlDomainException("No results found for query '" . $this->getQuery() . "': Offset cannot be negative");
		}

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
	 * If the limit is set, and there are more results for this query than the limit, then returns the limit instead.
	 */
	public function count() {
		if ($this->isNew()) {
			// a 'new' object always has one available
			// TODO this is kept for BC. Should this be changed to 1 only if the current instance has been saved?
			return 1;
		}

		// init joins
		$this->source->initExtensions();

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
			// we don't update the cached arguments (updateCachedArguments()) since count() is always up-to-date

			$obj = evaluate_select_wire_count(
				$this->source->getFile(),
				$this->schema->getSourceID(),
				$this->schema->getTableName(),
				$query,
				$args
			);

			// return the maximum of 'actual results' or 'limit'
			if ($this->limit > 0 && $obj > $this->limit)
				return $this->limit;

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
		log_message("[domain] Creating new instance manually");

		// make sure that this is a new object
		if (!$this->isNew()) {
			throw new IamlDomainException("Cannot create a new instance of Iterator type '" . get_class($this) . "'");
		}

		$this->resetSchema($this->schema);

		// we need to lose the previously saved information
		$this->setStoredValue("initialised", false);

		// finally, reload
		// (this will set all new fields to <code>null</code>, etc.)
		$this->reload();
	}

	/**
	 * For all schemas in this instance, set the new instance ID(s) to null
	 */
	private function resetSchema($schema) {
		log_message("[domain] Resetting schema " . $schema->toString());

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

	/**
	 * Log the current values in the instance to log_message.
	 */
	public function logInstanceToDebug() {
		if ($this->current_result === null) {
			log_message("[domain] current_result = null");
			return;
		}

		$result = "[domain] current_result =";
		foreach ($this->current_result as $attrinst) {
			$result .= "\n" . $attrinst->getName() . " = " . $attrinst->getValue();
		}
		log_message($result);
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
		log_message("[domain attribute] Attribute '$this->name' set to '$value'");
		$this->value = $value;

		// do we need to autosave?
		if ($this->iterator->isAutosave()) {
			$this->iterator->save();
		}

		// we need to persist it over sessions
		$this->iterator->updateAttributeInstanceValue($this);
	}

	/**
	 * We want to update the attribute instance value with the new value,
	 * but this does not trigger a save! This is not intended to be used
	 * by clients.
	 */
	public function setValueManually($value) {
		log_message("[domain attribute] Attribute '$this->name' set to '$value' manually");
		$this->value = $value;

		// we need to persist it over sessions
		$this->iterator->updateAttributeInstanceValue($this);
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

