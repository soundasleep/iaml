<?php

class TestPluginInformation extends GmfTestCase {

	public function run() {
		$files = FileFinder::instance()->match(GMF_ROOT, ".gmfgen");

		foreach ($files as $k => $f) {

			$xml = XmlLoader::instance()->load($f);
			$plugin = $xml->xpath("//plugin");
			$this->assert($plugin, "//plugin does not exist");

			$this_provider = $plugin[0]["provider"];
			$this_version = $plugin[0]["version"];

			$this->testProvider($k, $this_provider);
			$this->testVersion($k, $this_version);

			$this->count++;

		}

	}


	var $provider = false;
	var $version = false;
	var $count = 0;

	protected function testProvider($k, $this_provider) {
		if ($this->provider) {
			$this->assertEquals($this->provider, $this_provider, "[$k] providers are not equal: '$this->provider', '$this_provider'");
		} else {
			if ($this->count == 0) {
				$this->provider = $this_provider;
			} else {
				$this->fail("[$k] no provider found in previous");
			}
		}
	}

	protected function testVersion($k, $this_version) {
		if ($this->version) {
			$this->assertEquals($this->version, $this_version, "[$k] versions are not equal: '$this->version', '$this_version'");
		} else {
			if ($this->count == 0) {
				$this->version = $this_version;
			} else {
				$this->fail("[$k] no version found in previous");
			}
		}

	}
}
