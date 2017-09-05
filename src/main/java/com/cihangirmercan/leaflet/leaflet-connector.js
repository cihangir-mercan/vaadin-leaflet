window.com_cihangirmercan_leaflet_Leaflet = function() {
	var element = this.getElement();
	var rpcProxy = this.getRpcProxy();
	
	// initialize map div
	element.innerHTML = "<div id='my-map'></div>";

	// style it
	element.getElementsByTagName("div")[0].style.width='100%';
	element.getElementsByTagName("div")[0].style.height='100%';
	
	// initially san fransisco is chosen
	var myMap = L.map('my-map');
    var mapBoxAccessToken = "sk.eyJ1IjoiZnVua3kyNDQyNiIsImEiOiJjajY2cHd2NGoyNjV3MnF0ODlsdnpzaDNrIn0.FW9lgf3JWaidVtEA324nJQ";
    L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=' + mapBoxAccessToken, {
        attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
        id: 'mapbox.streets',
        accessToken: mapBoxAccessToken
    }).addTo(myMap);
    
    // init markerClusterGroup on the map
    var markerClusterGroup = L.markerClusterGroup();
    myMap.addLayer(markerClusterGroup);
    
 	// initialize markers and their properties that will come from state;
	var markers;
	var latlng;
	var popUpText;
    // it is called when list in state is changed
	this.onStateChange = () => {
		// info
		console.log("state changed");

		// get latest markers
		markers = this.getState().markers;
		
		// clear old markerClusterGroup
		markerClusterGroup.clearLayers();

		// update with new ones
		for (var i = 0; i < markers.length; i++) {
			// get each latlng and popUp at our property
			latlng = [markers[i].lat, markers[i].lng];
			popUpText = markers[i].popUpText;

			// mark them on the leaflet map
			markerClusterGroup.addLayer(L.marker(latlng).bindPopup(popUpText));
		}	
	}

	// my own functions, they will be called from java
	this.registerRpc({
		setView: function(lat, lng, zoomLevel) {
			myMap.setView([lat, lng], zoomLevel);
		},
		flyTo: function(lat, lng) {
			myMap.flyTo([lat, lng], myMap.getZoom());
		}
	});
	
	// click events on leaflet
	myMap.on('click', e => {   
        // call java method 
		rpcProxy.leafletClicked(e.latlng.lat, e.latlng.lng);
    });
}