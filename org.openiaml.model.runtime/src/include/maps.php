<?php

/**
 * Helper methods for creating maps.
 *
 */

function echo_google_map($id) {
	if (!defined('GOOGLE_MAPS_API_KEY')) {
		throw new IamlRuntimeException("GOOGLE_MAPS_API_KEY not defined");
	}

?>
	<?php /* TODO we should have a runtime property for sensor=true|false */ ?>
	<?php if (!defined('GOOGLE_MAPS_INCLUDED')) { ?>
	<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=<?php echo urlencode(GOOGLE_MAPS_API_KEY); ?>&sensor=false"
            type="text/javascript"></script>
    	<?php define('GOOGLE_MAPS_INCLUDED', true); ?>
    <?php } ?>
	<div id="map_canvas_<?php echo $id; ?>" style="width: 500px; height: 300px"></div>
	
	<script language="Javascript" type="application/javascript">
	/* <![CDATA[ */
		var map_<?php echo $id; ?> = null;
	
		function initialise_map_<?php echo $id; ?>() {
			if (GBrowserIsCompatible()) {
				map_<?php echo $id; ?> = new GMap2(document.getElementById("map_canvas_<?php echo $id; ?>"));
				// default location: google HQ
				map_<?php echo $id; ?>.setCenter(new GLatLng(37.4419, -122.1419), 13);
				map_<?php echo $id; ?>.setUIToDefault();
			}
		}
	/* ]]> */
	</script>
<?php
}

function echo_google_map_point_script($id, $parent_id) {
?>
<script language="Javascript" type="application/javascript">
/* <![CDATA[ */
	// the <label>
	var hidden_label_<?php echo $id; ?> = null;
	
	// the map marker on google maps (if any)
	var map_marker_<?php echo $id; ?> = null;
	
	function show_map_point_<?php echo $id; ?>() {
		if (hidden_label_<?php echo $id; ?> != null) {
			document.getElementById('<?php echo $id; ?>_label').innerHTML = hidden_label_<?php echo $id; ?>;
			hidden_label_<?php echo $id; ?> = null;
		}
		document.getElementById('<?php echo $id; ?>_label').style.display = "";
		
		// and add the point to the google map
		if (map_marker_<?php echo $id; ?> != null) {
			map_marker_<?php echo $id; ?>.show();
		}
	}
	function hide_map_point_<?php echo $id; ?>() {
		hidden_label_<?php echo $id; ?> = document.getElementById('<?php echo $id; ?>_label').innerHTML;
		document.getElementById('<?php echo $id; ?>_label').innerHTML = "";
		document.getElementById('<?php echo $id; ?>_label').style.display = "none";
		
		// and remove the point from the google map
		if (map_marker_<?php echo $id; ?> != null) {
			map_marker_<?php echo $id; ?>.hide();
		}
	}
	function set_map_point_<?php echo $id; ?>(address) {
		debug("set map point: address = " + address);
		if (address) {
			// ask the geocoder to translate it
			var geocoder = new GClientGeocoder();
			
			geocoder.getLatLng(address,
				function(point) {
					if (!point) {
						// could not translate into a point!
						// TODO should throw an onFailure error
						throw new IamlJavascriptException("[google maps] address '" + address + "' could not be converted");
					} else {
						// found it OK
						debug("set map point: async geocoding successful");
						map_<?php echo $parent_id; ?>.setCenter(point, 13);
						
						if (map_marker_<?php echo $id; ?> == null) {
							map_marker_<?php echo $id; ?> = new GMarker(point);
							map_<?php echo $parent_id; ?>.addOverlay(map_marker_<?php echo $id; ?>);
						} else {
							map_marker_<?php echo $id; ?>.setLatLng(point);
						}
			
						// show it
						show_map_point_<?php echo $id; ?>();

						// get the div node from HTML that we want to display
						var info_node = document.getElementById('<?php echo $id; ?>');
						if (info_node == null) {
							throw new IamlJavascriptException("Could not find info node to display: <?php echo $id; ?>");
						}

						document.getElementById("mock_map_point_<?php echo $id; ?>_address")
							.innerHTML = address;
							
						// have we already extracted all of the HTML from the node?
						if (info_node.innerHTML == "") {
							// we need to re-open the info window, since the point has moved
							var info_spy = document.getElementById("map_point_<?php echo $id; ?>_spy");
							if (info_spy == null) {
								throw new IamlJavascriptException("Cannot spy on the info window for map point <?php echo $id; ?>");
							}
							if (info_spy.parentNode == null) {
								throw new IamlJavascriptException("Info window spy parent node was null");
							}
				
							info_node.innerHTML = info_spy.parentNode.innerHTML;
						} 
								
						// refresh the contents
						map_marker_<?php echo $id; ?>.openInfoWindowHtml(info_node.innerHTML);
						
						// and remove the node, to make sure that only one element of
						// each ID exists
						info_node.innerHTML = "";

					}
				}
			);
		} else {
			// get the div node from HTML that we want to display
			var info_node = document.getElementById('<?php echo $id; ?>');
			if (info_node == null) {
				throw new IamlJavascriptException("Could not find info node to display: <?php echo $id; ?>");
			}
			
			// have we already extracted the HTML back out of the info node div?
			if (info_node.innerHTML == "") {
				// it is not possible to get the innerHTML of the info_node window
				// from google maps; so if the inner DOM changes, it is not possible for
				// us to retrieve the new content directly.
				// HOWEVER, we can just create a new known node that we can access; we
				// can then navigate through the DOM (parent) to get the current innerHTML.
				
				var info_spy = document.getElementById("map_point_<?php echo $id; ?>_spy");
				if (info_spy == null) {
					throw new IamlJavascriptException("Cannot spy on the info window for map point <?php echo $id; ?>");
				}
				if (info_spy.parentNode == null) {
					throw new IamlJavascriptException("Info window spy parent node was null");
				}
	
				info_node.innerHTML = info_spy.parentNode.innerHTML;
			}

			// TODO does this ensure that only one copy of [id=XXX_address] is now
			// available to the DOM?
			hide_map_point_<?php echo $id; ?>();
			
			document.getElementById("mock_map_point_<?php echo $id; ?>_address")
				.innerHTML = address;
			
		}
	}
	
	// and register this to the handler, for callbacks to update the map point
	add_map_point_handler("<?php echo $id; ?>", set_map_point_<?php echo $id; ?>);
/* ]]> */
</script>
<?php
}

function echo_google_map_point($id, $parent_id) {
?>
<div id="mock_map_point_<?php echo $id; ?>_address">(empty)</div>
<div id="map_point_<?php echo $id; ?>_spy"></div>
<?php
}

function echo_mock_map($id) {
?>
	<div>mock map</div>

	<script language="Javascript" type="application/javascript">
	/* <![CDATA[ */
		function initialise_map_<?php echo $id; ?>() {
			// empty
		}
	/* ]]> */
	</script>
<?php
}

function echo_mock_map_point_script($id, $parent_id) {
?>
<script language="Javascript" type="application/javascript">
/* <![CDATA[ */
	var hidden_label_<?php echo $id; ?> = null;
	var hidden_point_<?php echo $id; ?> = null;

	function show_map_point_<?php echo $id; ?>() {
		if (hidden_label_<?php echo $id; ?> != null) {
			document.getElementById('<?php echo $id; ?>_label').innerHTML = hidden_label_<?php echo $id; ?>;
			document.getElementById('mock_map_point_<?php echo $id; ?>_address').innerHTML = hidden_point_<?php echo $id; ?>;
			hidden_label_<?php echo $id; ?> = null;
			hidden_point_<?php echo $id; ?> = null;
		}
		document.getElementById('<?php echo $id; ?>_label').style.display = "";
		document.getElementById('mock_map_point_<?php echo $id; ?>_address').style.display = "";
	}
	function hide_map_point_<?php echo $id; ?>() {
		hidden_label_<?php echo $id; ?> = document.getElementById('<?php echo $id; ?>_label').innerHTML;
		hidden_point_<?php echo $id; ?> = document.getElementById('mock_map_point_<?php echo $id; ?>_address').innerHTML;
		document.getElementById('<?php echo $id; ?>_label').innerHTML = "";
		document.getElementById('mock_map_point_<?php echo $id; ?>_address').innerHTML = "";
		document.getElementById('<?php echo $id; ?>_label').style.display = "none";
		document.getElementById('mock_map_point_<?php echo $id; ?>_address').style.display = "none";
	}
	function set_map_point_<?php echo $id; ?>(address) {
		if (address) {
			// show it
			show_map_point_<?php echo $id; ?>();

			document.getElementById("mock_map_point_<?php echo $id; ?>_address")
				.innerHTML = address;
		} else {
			// hide it
			document.getElementById("mock_map_point_<?php echo $id; ?>_address")
				.innerHTML = address;

			hide_map_point_<?php echo $id; ?>();
		}
	}
	
	// and register this to the handler, for callbacks to update the map point
	add_map_point_handler("<?php echo $id; ?>", set_map_point_<?php echo $id; ?>);
/* ]]> */
</script>
<?php
}

function echo_mock_map_point($id, $parent_id) {
?>
<div id="mock_map_point_<?php echo $id; ?>_address">(empty)</div>
<?php
}
