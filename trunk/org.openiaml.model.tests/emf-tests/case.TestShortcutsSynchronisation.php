<?php

class TestShortcutsSynchronisation extends GmfTestCase {
  public function run() {
    $xml = XmlLoader::instance()->load("root.gmfgen")->xpath("//diagram/containsShortcutsTo");
    print_r($xml);
  }
}
