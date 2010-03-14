<?php

/**
 * This is the root include for server-side library files.
 *
 */

/**
 * We need to be able to use SID in the URL; remote URL calls require it 
 * (see call_remote_event.php for example).
 * The default setting was changed in PHP 5.3.0.
 */
ini_set("session.use_only_cookies", false);

session_start();
ob_start('ob_error_handler');		/* we will implicitly flush at the end (needed for redirections when input_text_field values cannot be found) */
set_exception_handler('default_exception_handler');			/* default exception handler */

/**
 * If a fatal error occurs in PHP, we cannot catch it with a user-defined
 * exception handler or error handler. Using this, we can catch these errors
 * anyway.
 *
 * We need to do this so that fatal errors return a 500 header, not just a
 * 200 etc. This solves the failing test case runtime.server.PhpMethodsTest#testMissingFunction().
 *
 * If a fatal error occurs, we throw an exception.
 */
function ob_error_handler($output) {
	if ($error = error_get_last()) {
		switch ($error['type']) {
			case E_ERROR: 
			case E_CORE_ERROR:
			case E_COMPILE_ERROR:
			case E_USER_ERROR:
				header("HTTP/1.0 500 Fatal error");
				return "<b>A fatal error occured:</b> $error[message] at $error[file]:$error[line]";
		}
	}
	
	// otherwise, return the output as normal
	return $output;
}

require("databases.php");
require("first_class_types.php");

$log_unique_id = sprintf("%04x", rand(0,0xffff)) . "-" . session_id();
function log_message($msg, $also_debug = true) {
	global $log_unique_id;
	$msg = "[$log_unique_id] $msg";		// append a unique ID to help us track requests

	$fp = fopen("php.log", "a");
	$msg_indent = str_replace("\n", "\n\t", $msg);
	fwrite($fp, date("Y-m-d H:i:s") . " $msg_indent\n");
	fclose($fp);

	// also echo to debug
	if ($also_debug) {
		global $enable_queue_log_messages;
		global $queue_log_messages;
		$message = "\$('response').innerHTML = \"" . htmlentities(str_replace("\n", "", str_replace("\r", "", $msg))) . "\";\n";
		if ($enable_queue_log_messages) {
			$queue_log_messages .= $message;
		} else {
			echo $message;
		}
	}
}

/**
 * For some reason, we can't have '$x = $a or throw new Ex()'
 * so we need to wrap it in a function.
 */
function throw_new_IamlRuntimeException($message) {
	/* re-enable log messages, if they were disabled */
	queue_log_messages(false);

	/* throw an exception */
	throw new IamlRuntimeException($message);
}

$enable_queue_log_messages = true;		// on by default
$queue_log_messages = "";
/**
 * If we output _after_ session_start(), but _before_ <html>, it's
 * likely we will end up making the resulting frame invalid (which means
 * JWebUnit cannot access the <title> etc). So we allow log
 * messages to be queued up.
 *
 * @see log_message()
 */
function queue_log_messages($on) {
	global $enable_queue_log_messages;
	global $queue_log_messages;

	if ($on) {
		$enable_queue_log_messages = true;
		$queue_log_messages = "";
	} else {
		$enable_queue_log_messages = false;
		echo $queue_log_messages;
	}
}

// make sure that the db exists for stored_events
try {
	$db = new PDO('sqlite:stored_events.db') or throw_new_IamlRuntimeException("could not open db");
	$s = $db->prepare("SELECT * FROM stored_events");
	if (!$s) {
		// create the table
		$q = $db->query("CREATE TABLE stored_events (
				id INTEGER PRIMARY KEY AUTOINCREMENT,
				frame_id VARCHAR(64) NOT NULL,
				event_name VARCHAR(64) NOT NULL,
				arg0 BLOB
			);") or throw_new_IamlRuntimeException("could not create table: " . print_r($db->errorInfo(), true));
		log_message("table for stored_events created", false);
	}
	$db = null;
} catch (PDOException $pe) {
	// this should never occur, unless PDO has a serious error (e.g. the database file cannot be read)
	header("HTTP/1.0 500 Fatal error");
	echo "<b>A fatal error occured:</b> " . $pe->getMessage();
	echo "<br>While trying to access <code>stored_events.db</code>";
	die;
}

function require_session($var, $default = false) {
	if (!isset($_SESSION[$var]) && $default === false) {
		throw new IamlRuntimeException("Required session variable '$var' was not found");
	}
	return isset($_SESSION[$var]) ? $_SESSION[$var] : $default;
}

function require_get($var, $default = false) {
	if (!isset($_GET[$var]) && $default === false) {
		throw new IamlRuntimeException("Required get variable '$var' was not found");
	}
	return isset($_GET[$var]) ? $_GET[$var] : $default;
}

function has_session($var, $default = "") {
	return isset($_SESSION[$var]) ? $_SESSION[$var] : $default;
}

function has_get($var, $default = "") {
	return isset($_GET[$var]) ? $_GET[$var] : $default;
}

/**
 * If the current address is http://foo.com/foo/bar.php, we want to
 * get out http://foo.com/foo
 */
function get_request_base() {
	return "http://" . $_SERVER["HTTP_HOST"] . dirname($_SERVER["REQUEST_URI"]);
}
function get_baseurl() {
	return "http://" . $_SERVER["HTTP_HOST"] . dirname($_SERVER["REQUEST_URI"]);
}

/**
 * Similar to a Java's RuntimeException.
 * We can also pass along additional information.
 */
class IamlRuntimeException extends Exception {
	var $more_info;

	public function __construct($message = "", $more_info = "") {
		parent::__construct($message);
		$this->more_info = $more_info;
	}
}

/**
 * A specific runtime exception to say that data from the current
 * session seems invalid. Perhaps the user needs to reset their
 * session?
 */
class IamlInvalidSessionException extends IamlRuntimeException {
	public function __construct($message = "", $more_info = "") {
		parent::__construct($message, $more_info);
	}
}

/**
 * Set a global application value stored in the database.
 */
function get_application_value($id, $default) {
	global $db;
	$db = new PDO('sqlite:stored_events.db') or throw_new_IamlRuntimeException("could not open db");

	// make sure the table is there
	$s = $db->prepare("SELECT * FROM global_values WHERE value_id=?");
	if (!$s) {
		// create the table
		$q = $db->query("CREATE TABLE global_values (
				id INT AUTO_INCREMENT PRIMARY KEY,
				value_id VARCHAR(64) NOT NULL,
				arg0 BLOB
			);") or throw_new_IamlRuntimeException("could not create table: " . print_r($db->errorInfo(), true));
		log_message("table for global_values created", false);

		return $default;
	}

	// get the value
	$s->execute(array($id)) or throw_new_IamlRuntimeException("could not execute select query: " . print_r($db->errorInfo(), true));
	$row = $s->fetch();

	if (!$row) {
		// empty
		return $default;
	} else {
		return $row["arg0"];
	}
}

/**
 * Set a global application value stored in the database.
 */
function set_application_value($id, $value) {
	global $db;
	$db = new PDO('sqlite:stored_events.db') or throw_new_IamlRuntimeException("could not open db");

	// make sure the table is there
	$s = $db->prepare("SELECT * FROM global_values WHERE value_id=?");
	if (!$s) {
		// create the table
		$q = $db->query("CREATE TABLE global_values (
				id INTEGER PRIMARY KEY AUTOINCREMENT,
				value_id VARCHAR(64) NOT NULL,
				arg0 BLOB
			);") or throw_new_IamlRuntimeException("could not create table: " . print_r($db->errorInfo(), true));
		log_message("table for global_values created", false);

		// re-prepare
		$s = $db->prepare("SELECT * FROM global_values WHERE value_id=?");
	}

	// execute the query with the parameter
	$s->execute(array($id)) or throw_new_IamlRuntimeException("could not execute select query: " . print_r($db->errorInfo(), true));

	// does anything exist?
	if ($s->fetch()) {
		// yes: update all existing
		$s = $db->prepare("UPDATE global_values SET arg0=? WHERE value_id=?") or throw_new_IamlRuntimeException("could not prepare update query: " . print_r($db->errorInfo(), true));
		log_message("Updating existing: " . $id);
	} else {
		// no: insert new
		$s = $db->prepare("INSERT INTO global_values (arg0, value_id) VALUES (?, ?)") or throw_new_IamlRuntimeException("could not prepare insert query: " . print_r($db->errorInfo(), true));
		log_message("Inserting new: " . $id);
	}

	// update all existing
	$s->execute(array($value, $id)) or throw_new_IamlRuntimeException("could not execute query: " . print_r($db->errorInfo(), true));

	// done
	$s = null;

	if (get_application_value($id, "not " . $value) != $value) {
		throw new IamlRuntimeException("The application value '$id' was not saved (value='$value').");
	}

}

/**
 * If the given value ID does not yet exist, insert the give
 * default value into the database.
 */
function initialize_application_value_default($id, $default) {
	if (get_application_value($id, null) === null) {
		log_message("Initializing default '$id' to '$default'");
		set_application_value($id, $default);
	}
}

/**
 * Wraps the URL with functionality to prevent infinite loops, and
 * also copy the session ID.
 */
function call_remote_url($url) {
	// get all running functions
	$url .= "&running=" . urlencode(implode(",", array_keys(get_running_functions())));

	// copy over session parameters
	$url .= "&" . get_sid();		// directly from PHP, so we don't need to set session_id() ourselves

	log_message("[php side] calling remote url: $url");

	// stop current session: the default PHP session handler blocks to prevent concurrent access
	$old_id = session_id();
	session_write_close();

	$result = file_get_contents($url);

	// restart the session once complete
	session_id($old_id);
	session_start();

	log_message("[php side] remote call to $url successful");

	return $result;
}

function call_remote_event($container, $id, $arg0 = "", $arg1 = "") {
	$url = get_request_base() . "/" . 'call_remote_event.php?container=' . urlencode($container)
		. '&operation_name=' . urlencode($id)
		. '&arg0=' . urlencode($arg0)
		. '&arg1=' . urlencode($arg1);

	return call_remote_url($url);
}

function escape_javascript_string($string) {
	$string = htmlspecialchars($string);
	$string = str_replace("\\", "\\\\", $string);
	$string = str_replace("\n", "\\n", $string);
	$string = str_replace("\r", "\\r", $string);
	$string = str_replace("\t", "\\t", $string);
	return $string;
}

function escape_parameter_string($string) {
	return rawurlencode($string);
}

function unescape_parameter_string($string) {
	return rawurldecode($string);
}

/**
 * For preventing infinite loops (hopefully)
 */
$running_functions = array();
function add_running_function($function_id) {
	global $running_functions;
	$running_functions[$function_id] = true;
}
function remove_running_function($function_id) {
	global $running_functions;
	unset($running_functions[$function_id]);
}
function has_running_function($function_id) {
	global $running_functions;
	return isset($running_functions[$function_id]);
}
function get_running_functions() {
	global $running_functions;
	return $running_functions;
}
function register_running_functions() {
	$running = require_get("running");
	if ($running) {
		$r = explode(",", $running);
		foreach ($r as $fun) {
			add_running_function($fun);
		}
	}
}

/**
 * Dynamic application element set stuff.
 */
/**
 * Comparison function: xpathMatch(a, b)
 * Returns true if 'a' matches the xpath of 'b', or 'b' matches the
 * xpath of 'a' (order doesn't matter).
 */
function xpathMatch($a, $b) {
	$id = null;
	$ids = null;

	if (is_array($a)) {
		$ids = $a;
	}
	if (is_array($b)) {
		$ids = $b;
	}
	if ($a instanceof Visual_Frame) {
		$id = $a->getID();
	}
	if ($b instanceof Visual_Frame) {
		$id = $b->getID();
	}

	// make sure we have id and path
	if ($id == null) {
		throw new IamlRuntimeException("Cannot compare xpath: ID is null.");
	}
	if ($ids == null) {
		throw new IamlRuntimeException("Cannot compare xpath: List of results is null.");
	}

	log_message("Querying XML XPath for ID: '" + $id + "'");

	// is the ID in this list?
	if (in_array($id, $ids)) {
		return true; 		// found it
	}

	return false;	// not found
}

/**
 * Comparison function: emailAddressMatch(a)
 * Returns true if 'a' is an e-mail address.
 */
function emailAddressMatch($a) {
	$regexp = "/^([A-Za-z0-9\._\-]+)@([A-Za-z0-9_\-]+)(\.[A-Za-z0-9_\-]+)+$/i";
	return preg_match($regexp, $a);
}

/**
 * For some reason, SID isn't being set.
 * This forces it.
 */
function get_sid() {
	return session_name() . "=" . session_id();
}

function default_exception_handler($e) {
	// no fail handler has caught me yet
	$url = "exception.php?fail=" . urlencode($e->getMessage()) . "&type=" . urlencode(get_class($e)) . "&trace=" . urlencode($e->getTraceAsString());
	log_message("[exception] " . print_r($e, true));
	log_message("[default exception handler] Redirecting to '$url' (fail)");
	header("Location: $url");
	die;
}

/**
 * Make the given value into a boolean, according to a set of
 * specified conversions: http://code.google.com/p/iaml/wiki/IamlPrimitiveOperations#true?
 */
function make_into_boolean($value) {
	if ($value === "0" || $value === "false" || $value === "" || $value === null)
		return false;
	if ($value === 0 || $value === 0.0 || $value === false)
		return false;
	return true;
}

/**
 * Get the regexp to check e-mail types for.
 * TODO this should be synchronised with the XSD type.
 */
function get_email_datatype_regexp() {
	return "#^([0-9a-zA-Z]([-.\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\w]*[0-9a-zA-Z]\.)+[a-zA-Z]{2,9})$#";
}

/**
 * Returns true if the given value (any type) can be successfully cast into the given
 * XSD type.
 *
 * <p>See also the documentation for {@model CastNode}. 
 */
function can_cast($value, $type) {
	log_message("can_cast('" . print_r($value, true) . "', '$type')");
	switch ($type) {
		// casting to string
		case "http://openiaml.org/model/datatypes#iamlString":
			// can always convert anything to a String
			return true;
		
		// casting to email
		case "http://openiaml.org/model/datatypes#iamlEmail":
			// can only convert strings
			if (is_string($value)) {
				if ($value === "")
					return true;	// can match empty string
			
				return preg_match(get_email_datatype_regexp(), $value);
			}
			
			// everything else fails
			return false;
		
		// casting to dateTime
		case "http://openiaml.org/model/datatypes#iamlDateTime":
			if ($value instanceof DateTime) {
				// already a datetime
				return true;
			}
		
			if (is_int($value)) {
				// PHP's ints are always constrained to min/max
				return true;
			}
		
			// a string
			if (is_string($value)) {
				// check it can be converted
				if (strtotime($value) === false) {
					// failed
					return false;
				}
				return true;
			}
			
			// everything else fails
			return false;
		
		// casting to integer
		case "http://openiaml.org/model/datatypes#iamlInteger":
			if (is_int($value))
				return true;	// already an integer
		
			// a string		
			if (is_string($value)) {
				// maximum values
				if ($value === "-2147483648")
					return true;				
				if ($value === "2147483647")
					return true;
				
				// convert it to an int
				$int = intval($value);
				
				// is it one of the maximums? if so, we have an overflow
				if ($int == -2147483648 || $int == 2147483647)
					return false;
					
				// "" = 0
				if ($int == 0 && $value == "") {
					return true;
				}
				
				// is the string representation of this intval equal
				// to the original string?
				if (strval($int) === $value) {
					// successfully converted
					return true;
				}
				
				// otherwise, this string has a floating point (etc), so
				// we fail
				return false;
				
			}
			
			// a date/time (PHP class)
			if ($value instanceof DateTime) {
				// a date/time
				
				$ts = $value->getTimestamp();
				
				// maximum values
				if ($ts == -2147483648) {
					// NOTE as far as i can tell, DateTime->format() always returns values in English
					return $value->format(DateTime::RFC2822) == "Fri, 13 Dec 1901 20:45:52 +0000";
				}
				if ($ts == 2147483647) {
					return $value->format(DateTime::RFC2822) == "Tue, 19 Jan 2038 03:14:07 +0000";
				}
				
				// otherwise, we can definitely return as an integer
				return true;
				
			}
			
			// don't know how to deal with this otherwise
			return false;
	
		default:
			throw new IamlRuntimeException("Unknown cast type '$type'");
	}

	throw new IamlRuntimeException("Unexpectedly fell out of can_cast check");
}

/**
 * Cast the given value to the given type, as best as possible.
 *
 * <p>See also the documentation for {@model CastNode}. 
 */
function do_cast($value, $type) {
	log_message("do_cast('" . print_r($value, true) . "', '$type')");
	switch ($type) {
		// casting to string
		case "http://openiaml.org/model/datatypes#iamlString":
			// a date?
			if ($value instanceof DateTime) {
				// change to UTC first
				$value->setTimezone(new DateTimeZone("UTC"));
				
				// format as RFC 2822
				return $value->format(DateTime::RFC2822);
			}
			
			return (string) $value;
		
		// casting to email
		case "http://openiaml.org/model/datatypes#iamlEmail":
			// can only convert strings
			if (is_string($value)) {
				// a successful e-mail?
				if (can_cast($value, $type)) {
					return $value;
				}

				// otherwise, fails
				return "";			
			}
			
			// everything else fails
			return "";
		
		// casting to dateTime
		case "http://openiaml.org/model/datatypes#iamlDateTime":
			if ($value instanceof DateTime) {
				// same thing
				return $value;
			}
		
			if (is_int($value)) {
				// if it can't be returned, just return the unix epoch
				if (!can_cast($value, $type))
					return new DateTime("@0");
			
				// convert timestamp to a date, and then convert to an object
				// "to create DateTime from timestamp, use the @ keyword"
				return new DateTime("@$value");
			}
		
			// a string
			if (is_string($value)) {
				// if it can't be returned, just return the unix epoch
				if (!can_cast($value, $type))
					return new DateTime("@0");
			
				// use DateTime to create object
				return new DateTime($value);
			}
			
			// everything else fails
			return new DateTime("@0");
		
		// casting to integer
		case "http://openiaml.org/model/datatypes#iamlInteger":
			if (is_int($value))
				return $value;	// return copy
		
			// a string		
			if (is_string($value)) {
				// return best guess
				return (int) $value;
				
			}
			
			// a date/time (PHP class)
			if ($value instanceof DateTime) {
				// change to UTC first
				$value->setTimezone(new DateTimeZone("UTC"));
			
				// return timestamp
				return $value->getTimestamp();
			}
			
			// don't know how to deal with this otherwise
			return 0;
	
		default:
			throw new IamlRuntimeException("Unknown cast type '$type'");
	}

	throw new IamlRuntimeException("Unexpectedly fell out of do_cast");
}

/**
 * Represents a stack for function calls.
 */
class CallStack {

	private $stack = array();
	
	public function pop() {
		if (count($this->stack) == 0)
			throw new IamlRuntimeException("Ran out of stack");
		
		return array_pop($this->stack);
	}
	
	public function push($s) {
		$this->stack[] = $s;
	}

}
