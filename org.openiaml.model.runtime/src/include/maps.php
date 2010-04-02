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
	<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=<?php echo urlencode(GOOGLE_MAPS_API_KEY); ?>&sensor=false"
            type="text/javascript"></script>
	<div id="map_canvas_<?php echo $id; ?>" style="width: 500px; height: 300px"></div>
	
	<script language="Javascript" type="application/javascript">
	/* <![CDATA[ */
		function initialise_map_<?php echo $id; ?>() {
			if (GBrowserIsCompatible()) {
				var map = new GMap2(document.getElementById("map_canvas_<?php echo $id; ?>"));
				// default location: google HQ
				map.setCenter(new GLatLng(37.4419, -122.1419), 13);
				map.setUIToDefault();
			}
		}
	/* ]]> */
	</script>
<?php
}

function echo_google_map_point($id) {
?>
	google map point
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

function echo_mock_map_point($id) {
?>
<div id="mock_map_point_<?php echo $id; ?>_address">(empty)</div>

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
