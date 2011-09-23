<h1>View Event</h1>

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

<div id="map_canvas" style="width: 40%; height: 320px;"></div>
<form class="view_event view">
	<h2>View Event</h2>

	<label class="label_title"><?php echo $Event->getTitle(); ?></label>
	<label class="label_description"><?php echo $Event->getDescription(); ?></label>
	<label class="label_venue"><?php echo $Event->getVenue(); ?></label>
	<label class="label_event_date"><?php echo $Event->getDate('r'); ?></label>
	<label class="label_tickets_left"><?php echo $Event->getTicketsLeft(); ?></label>

</form>

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

<script type="text/javascript">add_map_location("<?php echo htmlspecialchars($Event->getVenue()); ?>", "<?php echo htmlspecialchars($Event->getTitle()); ?>");</script>

<?php if ($sf_user->hasCredential('manager')) { ?>
<a href="<?php echo url_for('event/edit?id='.$Event->getId()) ?>">Edit</a>
&nbsp;
<?php } ?>
