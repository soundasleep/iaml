
var map_point_functions = new Array();
var map_point_elements = new Array();

/**
 * Look through the list of registered 'set_map_point' functions for the
 * handler for the given element_id. Once found, call the corresponding
 * function to update the map point.
 */
function set_map_point_handler(element_id, value) {
	if (map_point_elements.length != map_point_functions.length) {
		throw new IamlJavascriptException("Inconsistent map point handler state");
	}

	for (var i = 0; i < map_point_elements.length; i++) {
		if (map_point_elements[i] == element_id) {
			map_point_functions[i](value);
			return;
		}
	}
	
	throw new IamlJavascriptException("set_map_point_handler: No handler found for element '" + element_id + "'");
}

/**
 * Add a 'set_map_point' function to the handler for the given map point element ID.
 *
 * @param element_id the element ID of the map point
 * @param f the function, which takes one single attribute ('value')
 */
function add_map_point_handler(element_id, f) {
	map_point_elements.push(element_id);
	map_point_functions.push(f);
}
