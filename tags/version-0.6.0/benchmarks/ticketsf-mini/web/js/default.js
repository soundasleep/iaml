var current_map_markers = [];
var current_map_info = null;

function reset_map_results() {
	if (current_map_info != null) {
		current_map_info.close();
	}
	current_map_info = null;

	for (var i in current_map_markers) {
		var marker = current_map_markers[i];
		marker.setMap(null);
	}
	current_map_markers = [];
}

function add_map_location(address, title) {
	$(document).ready(function() {
		geocoder.geocode( { 'address': address}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				map.setCenter(results[0].geometry.location);
				var marker = new google.maps.Marker({
					map: map,
					position: results[0].geometry.location,
					title: title,
				});

				google.maps.event.addListener(marker, 'click', function() {
					var infowindow = new google.maps.InfoWindow({
						content: title,
						size: new google.maps.Size(50,50),
						position: results[0].geometry.location
					});
					if (current_map_info != null) {
						current_map_info.close();
					}
					infowindow.open(map);
					current_map_info = infowindow;

				});

				current_map_markers[current_map_markers.length] = marker;

			} else {
				alert("Geocode was not successful for the following reason: " + status);
			}
		});

	});
}
