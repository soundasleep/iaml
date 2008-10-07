<?php

// find all files in a directory that match a certain pattern
class FileFinder {
  var $_loaded = array();

  public function match($dir, $extension, $prefix = "") {
    $result = array();
    if ($handle = opendir($dir)) {
      while (false !== ($file = readdir($handle))) {
        if ($file != "." && $file != ".." && substr(strtolower($file), -strlen($extension)) == $extension && substr(strtolower($file), 0, strlen($prefix)) == $prefix) {
          $className = substr(substr($file, 0, strpos($file, $extension)), strlen($prefix));
          $result[$className] = $dir . $file;
        }
      }
      closedir($handle);
    }

    return $result;
  }

  static public function instance() {
    global $_singleton_instances;
    if (!isset($_singleton_instances["FileFinder"])) {
      $_singleton_instances["FileFinder"] = new FileFinder();
    }
    return $_singleton_instances["FileFinder"];
  }
}
