<?php

/**
 * Generic class templates for instantating various first-class-ish
 * model element types.
 *
 */

/**
 * Represents a Page: by ID and name
 */
class Visual_Page {
	var $id;
	var $name;

	public function __construct($id, $name) {
		$this->id = $id;
		$this->name = $name;
	}

	public function getID() {
		return $this->id;
	}

	public function getName() {
		return $this->name;
	}
}

/**
 * Represents a Role: by ID and name
 */
class Users_Role {
	var $id;
	var $name;

	public function __construct($id, $name) {
		$this->id = $id;
		$this->name = $name;
	}

	public function getID() {
		return $this->id;
	}

	public function getName() {
		return $this->name;
	}
}

/**
 * Represents a Permission: by ID and name
 */
class Users_Permission {
	var $id;
	var $name;

	public function __construct($id, $name) {
		$this->id = $id;
		$this->name = $name;
	}

	public function getID() {
		return $this->id;
	}

	public function getName() {
		return $this->name;
	}
}


