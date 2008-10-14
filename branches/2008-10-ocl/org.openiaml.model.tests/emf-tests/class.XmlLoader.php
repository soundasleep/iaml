<?php

$_singleton_instances = array();
class XmlLoader {
	var $_loaded = array();

	public function load($url) {
		if (!isset($this->_loaded[$url])) {
			$this->_loaded[$url] = simplexml_load_file($url);
		}

		return $this->_loaded[$url];
	}

	static public function instance() {
		global $_singleton_instances;
		if (!isset($_singleton_instances["XmlLoader"])) {
			$_singleton_instances["XmlLoader"] = new XmlLoader();
		}
		return $_singleton_instances["XmlLoader"];
	}
}