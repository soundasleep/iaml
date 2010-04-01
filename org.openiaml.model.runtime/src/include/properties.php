<?php
/**
 * Wrapper to access .properties files.
 *
 * Tries to have the same semantics as Java's properties: 
 * http://java.sun.com/j2se/1.4.2/docs/api/java/util/Properties.html#load%28java.io.InputStream%29
 */
 
function load_properties($db_name) {
	// properties file doesn't exist
	if (!file_exists($db_name)) {
		// log_message("properties file $db_name doesn't exist.");
		return array();	
	}

	// load the properties file
	$f = file($db_name);
	
	// escape out \ escape characters temporarily
	foreach ($f as $i => $line) {
		$line = str_replace("\\\\", "&p", $line);
		$line = str_replace("\\ ", "&s", $line);
		$line = str_replace("\\n", "&n", $line);
		$line = str_replace("&", "&&", $line);
		$f[$i] = $line;
	}
	
	$properties = array();
	for ($i = 0; $i < count($f); $i++) {
		// strip comments
		if (strpos($f[$i], "#") !== false) {
			$f[$i] = substr($f[$i], 0, strpos($f[$i], "#"));
		}
	
		// skip blank lines
		if (trim($f[$i]) == "")
			continue; 
		
		// split the property line
		$split = explode("=", $f[$i], 2);
		if (count($split) != 2) {
			throw new IamlRuntimeException("Malformed properties line: " . $f[$i]);
		}
		
		$key = trim($split[0]);
		$value = trim($split[1]);
		if (substr(trim($split[1]), -1) == "\\") {
			// remove end \
			$value = substr($value, 0, strlen($value) - 1);
		
			// continues on the next couple of lines
			while ($i < count($f)) {
				$i++;
				// strip comments
				if (strpos($f[$i], "#") !== false) {
					$f[$i] = substr($f[$i], 0, strpos($f[$i], "#"));
				}
				
				// skip blank lines
				if (ltrim($f[$i]) != "") {
					$trimmed = trim($f[$i]);
					// remove end \
					if (substr(trim($f[$i]), -1) == "\\") {
						$trimmed = substr($trimmed, 0, strlen($trimmed) - 1);
					}
					$value .= $trimmed;
				}
				
				// break out of continuation
				if (substr(trim($f[$i]), -1) != "\\") {
					break;
				}
			}
		}

		// save to properties array
		$properties[$key] = $value;
	}
	
	$new_prop = array();
	// now unescape everything
	foreach ($properties as $key => $value) {
		// in reverse order
		$key = str_replace("&&", "&", $key);
		$key = str_replace("&n", "\n", $key);
		$key = str_replace("&s", " ", $key);
		$key = str_replace("&p", "\\", $key);

		// in reverse order
		$value = str_replace("&&", "&", $value);
		$value = str_replace("&n", "\n", $value);
		$value = str_replace("&s", " ", $value);
		$value = str_replace("&p", "\\", $value);
	
		$new_prop[$key] = $value;
	} 
	
	return $new_prop;
}

function get_property($properties, $row_name, $default = false) {
	return isset($properties[$row_name]) ? $properties[$row_name] : $default;
}

/**
 * Set a property.
 * TODO this probably mangles up properties files when a value with a 
 * newline is specified: issue 166.
 */
function set_property($db_name, $properties, $key, $value) {
	$properties[$key] = $value;
	
	// write new properties file
	$s = array();
	foreach ($properties as $key => $value) {
		// escape out special characters
		// we don't need to split out the value line into multiple lines, as we had to deal with when reading
		$value = str_replace("\\", "\\\\", $value);
		$value = str_replace("\n", "\\n", $value);
		if (substr($value, 0, 1) == " ") {
			// need to prefix with '\ '
			$value = "\\ " . substr($value, 1);
		}
		if (substr($value, -1) == " ") {
			// need to suffix with '\ '
			$value = substr($value, 0, strlen($value) - 1) . "\\ ";
		}
	
		$s[] = "$key=$value";
	}
	file_put_contents($db_name, implode("\n", $s));

	// return new properties
	return $properties;
}
