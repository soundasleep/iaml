<?php

class TestShortcutsSynchronisation extends GmfTestCase {

	public function test() {
		$files = FileFinder::instance()->match(GMF_ROOT, ".gmfgen");

		$containsShortcutsTo = false;
		{
			$result = array();
			$complete = array();
			foreach ($files as $k => $f) {
				$x = $this->toArray( XmlLoader::instance()->load($f)->xpath("//diagram/containsShortcutsTo") );
				// add to summary
				$result[$f] = $x;
				// merge into complete
				$complete = array_flip(array_merge(array_flip($complete), array_flip($x)));
			}

			// now check each $result instance to make sure they have them all
			foreach ($result as $file => $content) {
				foreach ($complete as $value) {
					$this->assertInArray($value, $content, "'$file' is missing //diagram/containsShortcutsTo['$value']");
				}
			}

			$containsShortcutsTo = $complete;
		}

		// now work out shortcutsProvidedFor and make sure they are also the same
		$shortcutsProvidedFor = false;
		{
			$result = array();
			$complete = array();
			foreach ($files as $k => $f) {
				$x = $this->toArray( XmlLoader::instance()->load($f)->xpath("//diagram/shortcutsProvidedFor") );
				// add to summary
				$result[$f] = $x;
				// merge into complete
				$complete = array_flip(array_merge(array_flip($complete), array_flip($x)));
			}

			// now check each $result instance to make sure they have them all
			foreach ($result as $file => $content) {
				foreach ($complete as $value) {
					$this->assertInArray($value, $content, "'$file' is missing //diagram/shortcutsProvidedFor['$value']");
				}
			}

			$shortcutsProvidedFor = $complete;
		}

		// finally, make sure they are both in sync
		$this->assertEquals(count($containsShortcutsTo), count($shortcutsProvidedFor), "containsShortcutsTo is not the same length as shortcutsProvidedFor");
		foreach ($containsShortcutsTo as $value) {
			$this->assertInArray($value, $shortcutsProvidedFor);
		}

	}
}
