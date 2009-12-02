
var stored_ajax_monitor = 0;
var stored_counter = { 'store_db' : 0, 
	'store_event' : 0, 
	'set_session' : 0, 
	'set_application_value' : 0 };

/**
 * If we are calling Ajax calls before the page has even completely
 * loaded, we need to either (1) store them until the page has loaded,
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
	
	$('counter_store_db').innerHTML = stored_counter['store_db'];
	$('counter_store_event').innerHTML = stored_counter['store_event'];
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

/* approach #2: use event queues stored on the server, if the function is
   not available on the current page */
function store_event(page_id, event_name, arg0) {
	var url = 'store_event.php?page_id=' + escape(page_id) + '&event_name=' + escape(event_name) + "&arg0=" + escape(arg0);
	execute_queued_url(url, 'store_event', false);
}

/**
 * We refactor the queued functionality so that _any_ remote call
 * will be queued up properly.
 */
function execute_queued_url(url, counter, function_queue, allow_instructions) {
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
		      	if (allow_instructions) {
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
		      						document.getElementById(element_id).value = value;
		      						break;
		      					
		      					case "redirect":
		      						if (bits.length != 2) {
		      							throw new IamlJavascriptException("'redirect' instruction called with incorrect number of arguments: expected 2, found " + bits.length);
		      						}
		      						var url = decodeURIComponent(bits[1]);
		      						debug("[instruction] redirect(" + url + ")");
									window.location = url;
									ajaxIncrement();	// prevent other events from executing
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
		      							args.push(bits.length > (j + 2) ? bits[j + 2] : null); 
		      						}
		      						
		      						// call the operation via the operation handler
		      						debug("[instruction] operation '" + op + ": " + args[0] + ", " + args[1] + ", ...");
		      						get_operation_handler().execute(op, args);
		      						debug("[instruction] operation complete");
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
  debug("store_event called");
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

/* save directly to database (only one attribute) */
function store_db(attribute_id, arg0) {
	var url = 'store_db.php?attribute_id=' + escape(attribute_id) + '&page=' + escape(page_id) + '&arg0=' + escape(arg0);
	execute_queued_url(url, 'store_db', false, false);
}

/* save a session variable (only one attribute) */
function set_session(id, arg0, function_queue) {
	var url = 'set_session.php?id=' + escape(id) + '&page=' + escape(page_id) + '&arg0=' + escape(arg0);
	execute_queued_url(url, 'set_session', function_queue, false);
}

/* save a server variable (only one attribute) */
function set_application_value(id, arg0, function_queue) {
	var url = 'set_application_value.php?id=' + escape(id) + '&page=' + escape(page_id) + '&arg0=' + escape(arg0);
	execute_queued_url(url, 'set_application_value', function_queue, true /* allow instructions */);
}

/* save a domain instance variable */
function set_domain_attribute(id, arg0, function_queue) {
	var url = 'set_domain_attribute.php?id=' + escape(id) + '&page=' + escape(page_id) + '&arg0=' + escape(arg0);
	execute_queued_url(url, 'set_domain_attribute', function_queue, false);
}

function queued_new_domain_instance(id, function_queue) {
	var url = 'new_domain_instance.php?id=' + escape(id) + '&page=' + escape(page_id);
	execute_queued_url(url, 'new_domain_instance', function_queue, true /* allow instructions */);
}

function save_queued_store_domain_attribute(id, function_queue) {
	var url = 'save_queued_attribute.php?id=' + escape(id) + '&page=' + escape(page_id);
	execute_queued_url(url, 'queued_store_attribute', function_queue, true /* allow instructions */);
}

function save_queued_store_domain_object(id, function_queue) {
	var url = 'save_queued_store_domain_object.php?id=' + escape(id) + '&page=' + escape(page_id);
	execute_queued_url(url, 'queued_store_object', function_queue, true /* allow instructions */);
}

function queued_add_role(role_id, instance_id, function_queue) {
	var url = 'add_role.php?role_id=' + escape(role_id) + '&instance_id=' + escape(instance_id) + '&page=' + escape(page_id);
	execute_queued_url(url, 'set_domain_attribute', function_queue, true /* allow instructions */);
}

function queued_add_permission(permission_id, instance_id, function_queue) {
	var url = 'add_permission.php?permission_id=' + escape(permission_id) + '&instance_id=' + escape(instance_id) + '&page=' + escape(page_id);
	execute_queued_url(url, 'set_domain_attribute', function_queue, true /* allow instructions */);
}

function queued_remove_role(role_id, instance_id, function_queue) {
	var url = 'remove_role.php?role_id=' + escape(role_id) + '&instance_id=' + escape(instance_id) + '&page=' + escape(page_id);
	execute_queued_url(url, 'set_domain_attribute', function_queue, true /* allow instructions */);
}

function queued_remove_permission(permission_id, instance_id, function_queue) {
	var url = 'remove_permission.php?permission_id=' + escape(permission_id) + '&instance_id=' + escape(instance_id) + '&page=' + escape(page_id);
	execute_queued_url(url, 'set_domain_attribute', function_queue, true /* allow instructions */);
}

/* call a remote operation (only one attribute) */
function call_remote_event(container, operation_name, arg0, arg1, function_queue) {
	var url = 'call_remote_event.php?container=' + escape(container) 
		+ '&page=' + escape(page_id) 
		+ '&operation_name=' + escape(operation_name)
		+ '&arg0=' + escape(arg0)
		+ '&arg1=' + escape(arg1);
	execute_queued_url(url, 'remote_event', function_queue, false);
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
 * To (hopefully) prevent various XSS attacks, we let each page register
 * a function which actually lists all the operations that are available
 * within the given page. This function handler will then execute
 * the function as necessary, and acts as a "bridge" between this
 * library script and the generated page.
 */
function get_operation_handler() {
	return operation_handler;
}

function set_operation_handler(handler) {
	operation_handler = handler;
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
 * Trim whitespace.
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
	if (a instanceof Visual_Page) {
		id = a.getID();
	}
	if (b instanceof Visual_Page) {
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
 * Comparison function: emailAddressMatch(a)
 * Returns true if 'a' is an e-mail address.
 */
function emailAddressMatch(a) {
	if (a == null) {
		throw new IamlJavascriptException("Cannot emailAddressMatch a null address.");
	}
	
	var re = new RegExp("([A-Za-z0-9\._\-]+)@([A-Za-z0-9_\-]+)(\.[A-Za-z0-9_\-]+)+");
	return re.exec(a) ? true : false;
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
		if (this.stack.length == 0)
			throw new IamlJavascriptException("Ran out of stack: " + this.history);
		
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
 * Represents a Page: by ID and name
 */
function Visual_Page(id, name) {
	this.id = id;
	this.name = name;
	
	this.getID = function() { return id; };
	this.getName = function() { return name; };
	this.toString = function() { return "Page: id=" + id + " name=" + name; };
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
