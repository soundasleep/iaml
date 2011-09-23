<h1>Browse Events</h1>

<!-- search input will go here -->
<div class="event_search">
<form action="<?php echo url_for("event/index"); ?>" method="GET">
	<label class="event_search">Event Search
		<?php
		echo $searchForm["q"]->render(array(
			'id' => 'event_search_q',
			'class' => 'event_search_query',
		));
?>
	</label>
</form>
</div>

<script type="text/javascript"
    src="http://maps.googleapis.com/maps/api/js?sensor=false">
</script>
<script type="text/javascript">
	$(document).ready(function() {
		geocoder = new google.maps.Geocoder();
		var latlng = new google.maps.LatLng(-34.397, 150.644);
		var myOptions = {
		  zoom: 13,
		  center: latlng,
		  mapTypeId: google.maps.MapTypeId.ROADMAP
		}
		map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
	});
</script>

<?php include_partial("results", array("Events" => $Events)) ?>

<div id="map_canvas" style="width: 30%; height: 320px;"></div>
