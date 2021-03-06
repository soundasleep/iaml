
var stored_ajax_monitor = 0;
var stored_counter = { 'set_session' : 0,
	'set_application_value' : 0 };

/**
 * If we are calling Ajax calls before the frame has even completely
 * loaded, we need to either (1) store them until the frame has loaded,
 * or (2) execute them and keep a track of outstanding calls.
 *
 * In this implementation, we execute them and keep a track, populating
 * the monitor once it has been loaded.
 */
function initAjaxMonitor() {
	var monitor = $('ajax_monitor');
	if (monitor == null)
		throw new IamlJavascriptException("ajax_monitor ID was null (init)");

	monitor.innerHTML = stored_ajax_monitor;
	stored_ajax_monitor = null;

	$('counter_set_session').innerHTML = stored_counter['set_session'];
	$('counter_set_application_value').innerHTML = stored_counter['set_application_value'];
	stored_counter = null;
}

function ajaxIncrement() {
	var monitor = $('ajax_monitor');
	if (monitor == null) {
		stored_ajax_monitor++;
	} else {
		monitor.innerHTML = (1 * monitor.innerHTML) + 1;
	}
}

function ajaxDecrement() {
	var monitor = $('ajax_monitor');
	if (monitor == null) {
		stored_ajax_monitor--;
	} else {
		monitor.innerHTML = (1 * monitor.innerHTML) - 1;
	}
}

function ajaxFailed(fail) {
	debug("something went wrong: " + transport.responseText);
	throw new IamlJavascriptException("AJAX called failed: " + fail);
}

function counterIncrement(id) {
	var counter = $('counter_' + id);
	if (counter == null) {
		stored_counter[id]++;
	} else {
		counter.innerHTML = (1 * counter.innerHTML) + 1;
	}
}

/**
 * To prevent store_event queues becoming out of order, we
 * do a client-side fix of creating a client-side queue. When more
 * than one request is active, they are queued up, and
 * then called later in succession after each request returns.
 * queued_store_events is an array of functions to call.
 */
var queued_store_events = new Array();
var store_event_queued = false;

/**
 * Currently there is an issue where if a TextField is changed, it will execute the 'onChange' event remotely, and also
 * update the server-side value (which also executes 'onChange', except on the server). Both are necessary, but as a result,
 * alert messages (which cannot repeat) are being provided twice, failing test cases. This is a quick solution that should
 * be improved in the future, perhaps with 'transaction ID's or hashes being generated and passed along to the
 * server to synchronise client-side and server-side events.
 *
 * @implementation PrimitiveOperation
 *		If two 'alert' operations are called in the same client-side session with the same message,
 *		only the first alert message will be displayed.
 */
var last_alert_message = null;

function alert_without_repeat(message) {
	if (last_alert_message === message) {
		debug("[instruction] alert: ignoring duplicate message");
	} else {
		alert(message);
	}
	last_alert_message = message;
}

/**
 * We refactor the queued functionality so that _any_ remote call
 * will be queued up properly.
 *
 * Update 0.4.2: will <em>always</em> execute instructions
 * @param ignore_updates if true, will ignore on-screen updates
 */
function execute_queued_url(url, counter, function_queue) {
	store_event_function = function() {
		counterIncrement(counter);
		debug("creating ajax request to url: " + url);
		ajaxIncrement();
		new Ajax.Request(url,
	  {
	    method:'get',
	    onSuccess: function(transport){
	    	try {
		      	var response = transport.responseText || "no response text";
		      	debug("success: " + response);
		      	document.getElementById('response').innerHTML = response;
		      	// run BEFORE decrementing, so counter will never be 0

		      	// execute the function queue (if any)
		      	if (function_queue) {
		      		debug('executing function queue');
		      		function_queue(response); /* provide response as parameter to callback function, even if not requested */
		      		debug('function queue complete');
		      	}

		      	// are there any instructions in here?
		      	if (true /* 0.4.2: always allow instructions */ || allow_instructions) {
		      		debug('executing instructions');
		      		var inst_list = response.split("\n");
		      		for (var i = 0; i < inst_list.length; i++) {
		      			var inst = inst_list[i]; /* TODO don't trim, otherwise empty strings may be lost */
		      			if (inst) {
		      				var bits = inst.split(" ");
		      				switch (bits[0]) {
		      					case "ok":
		      						if (bits.length != 1) {
		      							throw new IamlJavascriptException("'ok' instruction called with incorrect number of arguments: expected 1, found " + bits.length);
		      						}
		      						debug("[instruction] ok");
		      						break;		// nothing

		      					case "set_value":
		      						if (bits.length != 3) {
		      							throw new IamlJavascriptException("'set_value' instruction called with incorrect number of arguments: expected 3, found " + bits.length);
		      						}
		      						var element_id = decodeURIComponent(bits[1]);
		      						var value = decodeURIComponent(bits[2]);
		      						debug("[instruction] set value(" + element_id + ")");
		      						if (!document.getElementById(element_id))
		      							throw new IamlJavascriptException("No such element '" + element_id + "'");
		      						document.getElementById(element_id).value = value;
		      						break;

		      					case "set_html":
		      						if (bits.length != 3) {
		      							throw new IamlJavascriptException("'set_html' instruction called with incorrect number of arguments: expected 3, found " + bits.length);
		      						}
		      						var element_id = decodeURIComponent(bits[1]);
		      						var value = decodeURIComponent(bits[2]);
		      						debug("[instruction] set html(" + element_id + ")");
		      						if (!document.getElementById(element_id))
		      							throw new IamlJavascriptException("No such element '" + element_id + "'");
		      						document.getElementById(element_id).innerHTML = value;
		      						break;

		      					case "set_visibility":
		      						if (bits.length != 3) {
		      							throw new IamlJavascriptException("'set_visibility' instruction called with incorrect number of arguments: expected 3, found " + bits.length);
		      						}
		      						var element_id = decodeURIComponent(bits[1]);
		      						var value = make_into_boolean(decodeURIComponent(bits[2]));
		      						debug("[instruction] set visibility(" + element_id + ")");
		      						if (!document.getElementById(element_id))
		      							throw new IamlJavascriptException("No such element '" + element_id + "'");
		      						if (value) {
		      							document.getElementById(element_id).style.display = "";
		      						} else if (!value) {
		      							document.getElementById(element_id).style.display = "none";
		      						} else {
		      							throw new IamlJavascriptException("Unexpected visibility '" + value + "'");
		      						}
		      						break;

		      					case "set_map_point":
		      						if (bits.length != 3) {
		      							throw new IamlJavascriptException("'set_map_point' instruction called with incorrect number of arguments: expected 3, found " + bits.length);
		      						}
		      						var element_id = decodeURIComponent(bits[1]);
		      						var value = decodeURIComponent(bits[2]);
		      						debug("[instruction] set_map_point(" + element_id + ")");

									if (!set_map_point_handler) {
		      							throw new IamlJavascriptException("No set_map_point_handler registered");
		      						}

		      						set_map_point_handler(element_id, value);
		      						break;

		      					case "iterator_list_rows":
		      						// the given number of rows are visible to the iterator list
		      						if (bits.length != 3) {
		      							throw new IamlJavascriptException("'iterator_list_rows' instruction called with incorrect number of arguments: expected 3, found " + bits.length);
		      						}
		      						var element_id = decodeURIComponent(bits[1]);
		      						var value = decodeURIComponent(bits[2]);
		      						debug("[instruction] iterator_list_rows(" + element_id + ") : " + value);

		      						for (var j = 0; j < 102; j++) {
			      						// max 101 rows
		      							if (j > 101) {
		      								throw new IamlJavascriptException("Too many rows visible through 'iterator_list_rows': " + j);
		      							}

		      							var e = document.getElementById(element_id + "_row_" + j);
		      							if (!e) {
		      								// no more rows to process
		      								break;
		      							}

		      							if (j < value) {
		      								// show
		      								e.style.display = "";
		      							} else {
		      								// hide
		      								e.style.display = "none";
		      							}
		      						}
		      						break;

		      					case "redirect":
		      						if (bits.length != 2) {
		      							throw new IamlJavascriptException("'redirect' instruction called with incorrect number of arguments: expected 2, found " + bits.length);
		      						}
		      						var url = decodeURIComponent(bits[1]);
		      						debug("[instruction] redirect(" + url + ")");
									window.location = url;
									ajaxIncrement();	// prevent other events from executing
									return;		// cancel any other instructions

		      					case "alert":
		      						if (bits.length != 2) {
		      							throw new IamlJavascriptException("'alert' instruction called with incorrect number of arguments: expected 2, found " + bits.length);
		      						}
		      						var message = decodeURIComponent(bits[1]);
		      						debug("[instruction] alert(" + message + ")");
		      						alert_without_repeat(message);
		      						break;

		      					case "call_operation":
		      						// only up to 10 arguments
		      						if (bits.length < 2 || bits.length > 12) {
		      							throw new IamlJavascriptException("'call operation' instruction called with incorrect number of arguments: expected 2 <= n <= 12, found " + bits.length);
		      						}

		      						if (!get_operation_handler()) {
		      							throw new IamlJavascriptException("No operation handler registered");
		      						}

		      						var op = decodeURIComponent(bits[1]);
		      						var args = new Array();

		      						// any arguments (up to 10)
		      						for (var j = 0; j < 10; j++) {
		      							// TODO should the bits downloaded into 'args' be cleaned through decodeURIComponent?
		      							args.push(bits.length > (j + 2) ? bits[j + 2] : null);
		      						}

		      						// call the operation via the operation handler
		      						debug("[instruction] operation '" + op + ": " + args[0] + ", " + args[1] + ", ...");
		      						get_operation_handler().execute(op, args);
		      						debug("[instruction] operation complete");
		      						break;

		      					case "openid_fail":
		      						if (bits.length != 3) {
		      							throw new IamlJavascriptException("'openid_fail' instruction called with incorrect number of arguments: expected 3, found " + bits.length);
		      						}
		      						var element_id = decodeURIComponent(bits[1]) + "_status";
		      						var message = decodeURIComponent(bits[2]);
		      						debug("[instruction] openid fail(" + element_id + ")");
		      						if (!document.getElementById(element_id))
		      							throw new IamlJavascriptException("No such element '" + element_id + "'");
		      						document.getElementById(element_id).innerHTML = message;
		      						document.getElementById(element_id).style.display = '';
		      						break;

		      					case "openid_pass":
		      						if (bits.length != 2) {
		      							throw new IamlJavascriptException("'openid_pass' instruction called with incorrect number of arguments: expected 2, found " + bits.length);
		      						}
		      						var element_id = decodeURIComponent(bits[1]) + "_status";
		      						debug("[instruction] openid fail(" + element_id + ")");
		      						if (!document.getElementById(element_id))
		      							throw new IamlJavascriptException("No such element '" + element_id + "'");
		      						document.getElementById(element_id).style.display = 'none';
		      						break;

		      					default:
		      						throw new IamlJavascriptException("Unknown instruction '" + inst + "'");
		      				}
		      			}
		      		}
		      	}
		    } catch (e) {
		    	if (e instanceof IamlJavascriptException) {
			    	add_runtime_error("Exception within AJAX: " + e);
			    	debug("Exception within AJAX: " + e);
			    	// alert("Exception within AJAX: " + e);
		    	} else {
		    		throw e;	// rethrow
		    	}
		    }

	      	next_store_event();
	      	ajaxDecrement();
	    },
	    onFailure: function(transport){
			var fail = 'Ajax failed at ' + url + ': ' + transport.responseText || "no response text";
			debug("FAILURE: " + fail);
			//alert(fail);
			root_alert(fail);
	      	next_store_event();
			ajaxFailed(fail);
	     }
	  });
  };

  if (store_event_queued) {
  	// we're currently in a queue
  	debug(" -- adding to queue: " + url);
  	queued_store_events.push(store_event_function); // add it to the queue
  } else {
    // otherwise, run it now
    store_event_queued = true;	// enable queueing
    debug(" -- running now: " + url);
    store_event_function();
  }
}

function root_alert(a) {
	alert(a);
}

function add_runtime_error(message) {
	var src = document.getElementById("runtime_errors");
	if (!src) {
		debug("warning: add_runtime_error called without runtime_errors existing");
	} else {
		src.innerHTML += "<li>Runtime error: " + message + "</li>";
	}
}

/**
 * Get a store_event event from the queue to execute, or
 * do nothing.
 */
function next_store_event() {
	debug("next store event..");
	if (store_event_queued) {
		if (queued_store_events.length != 0) {
			var queued_function = queued_store_events.shift();	// get first off queue
			debug(" -- -- running next in queue");
			queued_function();
		} else {
			store_event_queued = false;		// disable queueing when empty
			debug(" -- -- queue empty");
		}
	} else {
		// if the queue isn't empty, we should run it anyway
		if (queued_store_events.length != 0) {
			store_event_queued = true;
			var queued_function = queued_store_events.shift();
			debug(" -- -- found orphan queue event");
			queued_function();
		} else {
			debug(" -- nothing found in queue");
		}
	}
}

function create_stacktrace_parameter() {
	return "&trace=" + encodeURIComponent(printStackTrace().join("\n"));
}

/* save a session variable (only one attribute) */
function set_session(id, arg0, function_queue, ignore_updates) {
	var url = 'set_session.php?id=' + encodeURIComponent(id) + '&frame=' + encodeURIComponent(frame_id) + '&arg0=' + encodeURIComponent(arg0);
	if (ignore_updates) {
		url += '&ignore_updates=true';
	}
	url += create_stacktrace_parameter();
	execute_queued_url(url, 'set_session', function_queue);
}

/* save a server variable (only one attribute) */
function set_application_value(id, arg0, function_queue, ignore_updates) {
	var url = 'set_application_value.php?id=' + encodeURIComponent(id) + '&frame=' + encodeURIComponent(frame_id) + '&arg0=' + encodeURIComponent(arg0);
	if (ignore_updates) {
		url += '&ignore_updates=true';
	}
	url += create_stacktrace_parameter();
	execute_queued_url(url, 'set_application_value', function_queue);
}

/* save a domain instance variable */
function set_domain_attribute(id, arg0, function_queue) {
	var url = 'set_domain_attribute.php?id=' + encodeURIComponent(id) + '&frame=' + encodeURIComponent(frame_id) + '&arg0=' + encodeURIComponent(arg0);
	url += create_stacktrace_parameter();
	execute_queued_url(url, 'set_domain_attribute', function_queue);
}

function queue_store_domain_attribute(id, arg0, function_queue) {
	var url = 'queue_store_domain_attribute.php?id=' + encodeURIComponent(id) + '&frame=' + encodeURIComponent(frame_id) + '&arg0=' + encodeURIComponent(arg0);
	url += create_stacktrace_parameter();
	execute_queued_url(url, 'set_domain_attribute', function_queue);
}

function queued_new_domain_instance(id, function_queue) {
	var url = 'new_domain_instance.php?id=' + encodeURIComponent(id) + '&frame=' + encodeURIComponent(frame_id);
	url += create_stacktrace_parameter();
	execute_queued_url(url, 'new_domain_instance', function_queue);
}

function save_queued_store_domain_attribute(id, function_queue) {
	var url = 'save_queued_attribute.php?id=' + encodeURIComponent(id) + '&frame=' + encodeURIComponent(frame_id);
	url += create_stacktrace_parameter();
	execute_queued_url(url, 'queued_store_attribute', function_queue);
}

function save_queued_store_domain_object(id, function_queue) {
	var url = 'save_queued_store_domain_object.php?id=' + encodeURIComponent(id) + '&frame=' + encodeURIComponent(frame_id);
	url += create_stacktrace_parameter();
	execute_queued_url(url, 'queued_store_object', function_queue);
}

function queued_add_role(role_id, instance_id, function_queue) {
	var url = 'add_role.php?role_id=' + encodeURIComponent(role_id) + '&instance_id=' + encodeURIComponent(instance_id) + '&frame=' + encodeURIComponent(frame_id);
	url += create_stacktrace_parameter();
	execute_queued_url(url, 'set_domain_attribute', function_queue);
}

function queued_add_permission(permission_id, instance_id, function_queue) {
	var url = 'add_permission.php?permission_id=' + encodeURIComponent(permission_id) + '&instance_id=' + encodeURIComponent(instance_id) + '&frame=' + encodeURIComponent(frame_id);
	url += create_stacktrace_parameter();
	execute_queued_url(url, 'set_domain_attribute', function_queue);
}

function queued_remove_role(role_id, instance_id, function_queue) {
	var url = 'remove_role.php?role_id=' + encodeURIComponent(role_id) + '&instance_id=' + encodeURIComponent(instance_id) + '&frame=' + encodeURIComponent(frame_id);
	url += create_stacktrace_parameter();
	execute_queued_url(url, 'set_domain_attribute', function_queue);
}

function queued_remove_permission(permission_id, instance_id, function_queue) {
	var url = 'remove_permission.php?permission_id=' + encodeURIComponent(permission_id) + '&instance_id=' + encodeURIComponent(instance_id) + '&frame=' + encodeURIComponent(frame_id);
	url += create_stacktrace_parameter();
	execute_queued_url(url, 'set_domain_attribute', function_queue);
}

function queued_email_send(email_id, function_queue) {
	var url = 'email_callback_send.php?email_id=' + encodeURIComponent(email_id) + '&frame=' + encodeURIComponent(frame_id);
	url += create_stacktrace_parameter();
	execute_queued_url(url, 'set_domain_attribute', function_queue);
}

function queued_instance_next(instance_id, function_queue) {
	var url = 'instance_next.php?instance_id=' + encodeURIComponent(instance_id)
		+ '&frame_id=' + encodeURIComponent(frame_id);
	url += create_stacktrace_parameter();
	execute_queued_url(url, 'set_domain_attribute', function_queue);
}

function queued_instance_previous(instance_id, function_queue) {
	var url = 'instance_previous.php?instance_id=' + encodeURIComponent(instance_id)
		+ '&frame_id=' + encodeURIComponent(frame_id);
	url += create_stacktrace_parameter();
	execute_queued_url(url, 'set_domain_attribute', function_queue);
}

function queued_instance_reset(instance_id, function_queue) {
	var url = 'instance_reset.php?instance_id=' + encodeURIComponent(instance_id)
		+ '&frame_id=' + encodeURIComponent(frame_id);
	url += create_stacktrace_parameter();
	execute_queued_url(url, 'set_domain_attribute', function_queue);
}

function queued_instance_skip(instance_id, arg0, function_queue) {
	var url = 'instance_skip.php?instance_id=' + encodeURIComponent(instance_id)
		+ '&arg0=' + encodeURIComponent(arg0)
		+ '&frame_id=' + encodeURIComponent(frame_id);
	url += create_stacktrace_parameter();
	execute_queued_url(url, 'set_domain_attribute', function_queue);
}

function queued_instance_jump(instance_id, arg0, function_queue) {
	var url = 'instance_jump.php?instance_id=' + encodeURIComponent(instance_id)
		+ '&arg0=' + encodeURIComponent(arg0)
		+ '&frame_id=' + encodeURIComponent(frame_id);
	url += create_stacktrace_parameter();
	execute_queued_url(url, 'set_domain_attribute', function_queue);
}

/**
 * Call a remote operation.
 *
 * @implementation Operation
 *		Remote {@model Operation} calls can only support up to six arguments.
 */
function call_remote_event(container, operation_name, arg0, arg1, arg2, arg3, arg4, arg5, function_queue) {
	var url = 'call_remote_event.php?container=' + encodeURIComponent(container)
		+ '&frame=' + encodeURIComponent(frame_id)
		+ '&operation_name=' + encodeURIComponent(operation_name)
		+ '&arg0=' + encodeURIComponent(arg0)
		+ '&arg1=' + encodeURIComponent(arg1)
		+ '&arg2=' + encodeURIComponent(arg2)
		+ '&arg3=' + encodeURIComponent(arg3)
		+ '&arg4=' + encodeURIComponent(arg4)
		+ '&arg5=' + encodeURIComponent(arg5);
	url += create_stacktrace_parameter();
	execute_queued_url(url, 'remote_event', function_queue);
}

var debug_message_saved = "";
var debug_message_cleared = false;
function debug(msg) {
	var debug_string = "<li>" + msg + "\n";
	if (document.getElementById('debug')) {
		document.getElementById('debug').innerHTML += debug_message_saved + debug_string;
		debug_message_saved = "";
		debug_message_cleared = true;
	} else {
		if (debug_message_cleared) {
			// how could we have lost the element?
			throw new IamlJavascriptException("Unexpectedly lost the debug element");
		}
		debug_message_saved += debug_string;
	}
}

/**
 * Save the value of a particular field
 */
function setField(field) {
	debug("saving cookie for " + field.id);
	createCookie("field_" + field.id, field.value, 30);
}

var operation_handler = null;

/**
 * To (hopefully) prevent various XSS attacks, we let each frame register
 * a function which actually lists all the operations that are available
 * within the given frame. This function handler will then execute
 * the function as necessary, and acts as a "bridge" between this
 * library script and the generated frame.
 */
function get_operation_handler() {
	return operation_handler;
}

function set_operation_handler(handler) {
	operation_handler = handler;
}

/**
 * Allows an exception to be thrown as part of a value assignment.
 * c.f. the PHP runtime's <code>throw_new_IamlRuntimeException()</code>
 */
function throw_new_IamlJavascriptException(message) {
	throw new IamlJavascriptException(message);
}

/**
 * Is every element in the array equal?
 */
function is_array_equal(a) {
	if (a.length <= 1)
		return true;

	for (var i = 1; i < a.length; i++) {
		if (a[0] != a[i])
			return false;
	}

	return true;
}

/**
 * Is every element in the array true?
 */
function is_array_true(a) {
	for (var i = 0; i < a.length; i++) {
		if (!a[i])
			return false;
	}

	return true;
}

/**
 * Make the given value into a boolean, according to a set of
 * specified conversions: http://code.google.com/p/iaml/wiki/IamlPrimitiveOperations#true?
 */
function make_into_boolean(value) {
	if (value === "0" || value === "false" || value === "" || value === null)
		return false;
	if (value === 0 || value === 0.0 || value === false)
		return false;
	return true;
}

/**
 * Trim whitespace.
 * trim() is part of ECMAScript 5, but only implemented in Firefox 3.6 (not HtmlUnit 4.7).
 */
function trim(str) {
	return str.replace(/^\s\s*/, "").replace(/\s\s*$/, "");
}

/**
 * Translate the given string into a floating point.
 * If the given string is empty, return '0'.
 */
function parse_float_or_zero(str) {
	if (str == "")
		return 0;
	return parseFloat(str);
}

/**
 * Convert any non-finite number (e.g. Infinity, NaN)
 * into NaN. Otherwise, return the same number.
 */
function convert_to_nan(n) {
	if (isFinite(n))
		return n;
	return Number.NaN;
}

/**
 * Load a string of XML as an XML document using an XML parser.
 *
 * Based on http://www.w3schools.com/Xml/xml_parser.asp.
 *
 * The Firefox parser does not catch all exceptions, so we can
 * try to catch any parsing exceptions.
 */
function load_xml(text) {
	try {
		// Internet Explorer
		var xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
		xmlDoc.async = "false";
		return xmlDoc.loadXML(text);
	} catch (e) { }

	// Firefox
	var parser = new DOMParser();
	return parser.parseFromString(text, "text/xml");

	// nothing
	// throw new IamlJavascriptException("Could not load XML, no XML loader found.");
}

/**
 * Comparison function: xpathMatch(a, b)
 * Returns true if 'a' matches the xpath of 'b', or 'b' matches the
 * xpath of 'a' (order doesn't matter).
 */
function xpathMatch(a, b) {
	var id = null;
	var ids = null;

	if (a instanceof Array) {
		ids = a;
	}
	if (b instanceof Array) {
		ids = b;
	}
	if (a instanceof Visual_Frame) {
		id = a.getID();
	}
	if (b instanceof Visual_Frame) {
		id = b.getID();
	}

	// make sure we have id and path
	if (id == null) {
		throw new IamlJavascriptException("Cannot compare xpath: ID is null. (" + a + ", " + b + ")");
	}
	if (ids == null) {
		throw new IamlJavascriptException("Cannot compare xpath: List of results is null.");
	}

	debug("Querying XML XPath for ID: '" + id + "'");

	// is the ID in this list?
	for (var i = 0; i < ids.length; i++) {
		if (ids[i] == id) {
			return true;	// found it
		}
	}

	return false;	// not found
}

/**
 * Define an exception class.
 */
function IamlJavascriptException(message) {
	this.message = message;

	this.getMessage = function() { return message; };
	this.toString = function() { return "IamlJavascriptException: " + message; };
}

/**
 * Throw an exception, but implemented as a function so we can
 * throw exceptions when passing parameters.
 */
function throwJavascriptException(message) {
	throw new IamlJavascriptException(message);
	return false;
}

/**
 * Represents a call stack.
 */
function CallStack() {
	this.stack = new Array();

	this.history = new Array();

	this.pop = function() {
		if (this.stack.length == 0) {
			ajaxIncrement(); // prevent other events from executing
			alert("Ran out of stack: " + this.history);
			throw new IamlJavascriptException("Ran out of stack: " + this.history);
		}

		var s = this.stack.pop();
		this.history.push(s);
		return s;
	}

	this.push = function(s) {
		this.stack.push(s);
	}
}

// ----- IAML domain concepts

/**
 * Represents a Frame: by ID and name
 */
function Visual_Frame(id, name) {
	this.id = id;
	this.name = name;

	this.getID = function() { return id; };
	this.getName = function() { return name; };
	this.toString = function() { return "Frame: id=" + id + " name=" + name; };
}

/**
 * Represents a Role: by ID and name
 */
function Users_Role(id, name) {
	this.id = id;
	this.name = name;

	this.getID = function() { return id; };
	this.getName = function() { return name; };
	this.toString = function() { return "Role: id=" + id + " name=" + name; };
}

/**
 * Represents a Permission: by ID and name
 */
function Users_Permission(id, name) {
	this.id = id;
	this.name = name;

	this.getID = function() { return id; };
	this.getName = function() { return name; };
	this.toString = function() { return "Permission: id=" + id + " name=" + name; };
}

// ---------

/**
 * Get the regexp to check e-mail types for.
 * TODO this should be synchronised with the XSD type.
 */
function get_email_datatype_regexp() {
	return "^([0-9a-zA-Z]([-.\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\w]*[0-9a-zA-Z]\.)+[a-zA-Z]{2,9})$";
}

/**
 * Returns true if the given value (any type) can be successfully cast into the given
 * XSD type.
 *
 * <p>See also the documentation for {@model CastNode}.
 */
function can_cast(value, type) {
	debug("can_cast('" + value + "', '" + type + "')");
	switch (type) {
		// casting to string
		case "":
		case "http://openiaml.org/model/datatypes#iamlString":
		case "http://openiaml.org/model/datatypes#iamlAddress":
		case "http://openiaml.org/model/datatypes#iamlURL":
		case "http://openiaml.org/model/datatypes#iamlOpenIDURL":
		case "http://www.w3.org/2001/XMLSchema#string":
			// can always convert anything to a String
			return true;

		// casting to email
		case "http://openiaml.org/model/datatypes#iamlEmail":
			// can only convert strings
			if (typeof(value) == "string") {
				if (value == "")
					return true;	// can match empty string

				return new RegExp(get_email_datatype_regexp()).test(value);
			}

			// everything else fails
			return false;

		// casting to dateTime
		case "http://openiaml.org/model/datatypes#iamlDateTime":
			if (value instanceof Date) {
				// already a datetime
				return true;
			}

			if (typeof(value) == "number") {
				// numbers are both int's and float's; make sure it's
				// not one of the maximums on int
				return value >= -2147483648 &&
					value <= 2147483647;
			}

			// a string
			if (typeof(value) == "string") {
				// check it can be converted
				if (isNaN(Date.parse(value))) {
					// failed
					return false;
				}
				return true;
			}

			// everything else fails
			return false;

		// casting to integer
		case "http://openiaml.org/model/datatypes#iamlInteger":
		case "http://www.w3.org/2001/XMLSchema#integer":
			if (typeof(value) == "boolean") {
				return true;	// can always cast boolean to int
			}

			if (typeof(value) == "number") {
				// numbers are both int's and float's; make sure it's
				// not one of the maximums on int
				return value >= -2147483648 &&
					value <= 2147483647;
			}

			// a string
			if (typeof(value) == "string") {
				// "" = 0
				if (value == "") {
					return true;
				}

				var int = parseInt(value);

				// conversion failed?
				if (isNaN(int)) {
					return false;
				}

				// integer is represented the same in string as in int?
				if (value != ("" + int)) {
					return false;
				}

				// numbers are both int's and float's; make sure it's
				// not one of the maximums on int
				return int >= -2147483648 &&
					int <= 2147483647;

			}

			// a date/time (PHP class)
			if (value instanceof Date) {
				var int = value.getTime() / 1000;	// convert from msec to sec

				// numbers are both int's and float's; make sure it's
				// not one of the maximums on int
				return int >= -2147483648 &&
					int <= 2147483647;

			}

			// don't know how to deal with this otherwise
			return false;

		// casting to boolean
		case "http://openiaml.org/model/datatypes#iamlBoolean":
		case "http://www.w3.org/2001/XMLSchema#boolean":
			// all objects can be cast to boolean
			return true;

		default:
			throw new IamlJavascriptException("Unknown cast type '" + type + "'");
	}

	throw new IamlJavascriptException("Unexpectedly fell out of can_cast check");

}

/**
 * Get the given date in RFC 2822 format. This actually converts it to
 * UTC format for consistency (i.e. +0000 timezone).
 *
 * <p>Example: <code>Tue, 19 Jan 2038 03:14:07 +0000</code>
 */
function rfc2822(date) {
	function pad(n) {
		return n < 10 ? '0' + n : n;
	}
	function pad4(n) {
		return n < 10 ? '000' + n :
			(n < 100 ? '00' + n :
				(n < 1000 ? '0' + n : n));
	}

	var days = new Array('Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat');
	var months = new Array('Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec');

	return days[date.getUTCDay()] + ", " + pad(date.getUTCDate())
		+ " " + months[date.getUTCMonth()] + " " + date.getUTCFullYear()
		+ " " + pad(date.getUTCHours()) + ":" + pad(date.getUTCMinutes())
		+ ":" + pad(date.getUTCSeconds()) + " +0000";

}

/**
 * Cast the given value to the given type, as best as possible.
 *
 * <p>See also the documentation for {@model CastNode}.
 */
function do_cast(value, type) {
	debug("do_cast('" + value + "', '" + type + "')");
	switch (type) {
		// casting to string
		case "":
		case "http://openiaml.org/model/datatypes#iamlString":
		case "http://openiaml.org/model/datatypes#iamlAddress":
		case "http://openiaml.org/model/datatypes#iamlURL":
		case "http://openiaml.org/model/datatypes#iamlOpenIDURL":
		case "http://www.w3.org/2001/XMLSchema#string":
			// a date?
			if (value instanceof Date) {
				// format as RFC 2822
				return rfc2822(value);
			}

			return "" + value;

		// casting to email
		case "http://openiaml.org/model/datatypes#iamlEmail":
			// can only convert strings
			if (typeof(value) == "string") {
				// a successful e-mail?
				if (can_cast(value, type)) {
					return value;
				}

				// otherwise, fails
				return "";
			}

			// everything else fails
			return "";

		// casting to dateTime
		case "http://openiaml.org/model/datatypes#iamlDateTime":
			if (value instanceof Date) {
				// same thing
				return value;
			}

			if (typeof(value) == "number") {
				// if it can't be returned, just return the unix epoch
				if (!can_cast(value, type))
					return new Date(0);

				// convert timestamp to date
				return new Date(parseInt(value) * 1000);	// convert s to msec
			}

			// a string
			if (typeof(value) == "string") {
				// if it can't be returned, just return the unix epoch
				if (!can_cast(value, type))
					return new Date(0);

				// use DateTime to create object
				return new Date(value);
			}

			// everything else fails
			return new Date("0");

		// casting to integer
		case "http://openiaml.org/model/datatypes#iamlInteger":
		case "http://www.w3.org/2001/XMLSchema#integer":
			if (typeof(value) == "boolean") {
				return value ? 1 : 0;
			}

			if (typeof(value) == "number") {
				// too big?
				if (!(value >= -2147483648 &&
						value <= 2147483647)) {
					return 0;
				}

				return Math.floor(value);	// return copy
			}

			// a string
			if (typeof(value) == "string") {
				// remove all alphanumeric characters
				value = trim(value.replace(new RegExp("[^0-9\.]", "g"), " "));

				// "" = 0
				if (value == "")
					return 0;

				// and select the first non-whitespace, if any
				var splits = value.split(" ", 2);
				if (splits.length > 0) {
					value = splits[0];
				}

				// return best guess
				var int = parseInt(value);
				if (isNaN(int)) {
					// if it's not an integer, return 0
					return 0;
				}

				// too big?
				if (!(int >= -2147483648 &&
						int <= 2147483647)) {
					return 0;
				}

				return int;
			}

			// a date/time (PHP class)
			if (value instanceof Date) {
				// return timestamp
				return Math.floor(value.getTime() / 1000);		// convert msec to sec
			}

			// don't know how to deal with this otherwise
			return 0;

		// casting to boolean
		case "http://openiaml.org/model/datatypes#iamlBoolean":
		case "http://www.w3.org/2001/XMLSchema#boolean":
			return make_into_boolean(value);

		default:
			throw new IamlJavascriptException("Unknown cast type '" + type + "'");
	}

	throw new IamlJavascriptException("Unexpectedly fell out of do_cast");
}

// ---------

/**
 * Copied from http://www.quirksmode.org/js/cookies.html
 */
function createCookie(name,value,days) {
	if (days) {
		var date = new Date();
		date.setTime(date.getTime()+(days*24*60*60*1000));
		var expires = "; expires="+date.toGMTString();
	}
	else var expires = "";
	document.cookie = name+"="+value+expires+"; path=/";
}

function readCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++) {
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1,c.length);
		if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
	}
	return null;
}

function eraseCookie(name) {
	createCookie(name,"",-1);
}
