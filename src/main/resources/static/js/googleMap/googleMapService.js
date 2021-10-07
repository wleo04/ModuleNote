function createGoogleMap(element, googleMapOptions){
	return new GoogleMapClass(new google.maps.Map(element, googleMapOptions));
}

function createGoogleMapOptions(lat, lng, zoom, mapTypeId){
	const latLng = new google.maps.LatLng(lat, lng);
	const mapOption = {
		zoom : zoom,
		center : latLng,
		mapTypeId : mapTypeId
	}

	return mapOption;
}

function createMarker(lat, lng, title, googleMap){
	const latLng = new google.maps.LatLng(lat, lng);
	const marker = new google.maps.Marker({
		position : latLng,
		map : googleMap,
		title : title
	});
}