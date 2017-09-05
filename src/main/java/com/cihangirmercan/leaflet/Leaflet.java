package com.cihangirmercan.leaflet;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.AbstractJavaScriptComponent;

@SuppressWarnings("serial")
@StyleSheet({"https://unpkg.com/leaflet@1.2.0/dist/leaflet.css", 
	"https://unpkg.com/leaflet.markercluster@1.0.6/dist/MarkerCluster.css",
	"https://unpkg.com/leaflet.markercluster@1.0.6/dist/MarkerCluster.Default.css"})
@JavaScript({"https://unpkg.com/leaflet@1.2.0/dist/leaflet.js", 
	"https://unpkg.com/leaflet.markercluster@1.0.6/dist/leaflet.markercluster.js", 
	"leaflet-connector.js"})
public class Leaflet extends AbstractJavaScriptComponent {
	
    private List<LeafletClickListener> listeners = new ArrayList<LeafletClickListener>();
    
	public Leaflet() {
		registerRpc(new LeafletServerRpc() {
			@Override
			public void leafletClicked(double lat, double lng) {
				// notify every listener
		        for (LeafletClickListener listener : listeners) {
		        	listener.onClick(new LeafletClickEvent(lat, lng));
		        }	           
			}	
		});
	}

	@Override
	protected LeafletState getState() {
		return (LeafletState) super.getState();
	}
	
	// call javascript function
	public void setView(double lat, double lng, int zoomLevel) {
        getRpcProxy(LeafletClientRpc.class).setView(lat, lng, zoomLevel);
    }
	
	// call javascript function
	public void flyTo(double lat, double lng) {
		getRpcProxy(LeafletClientRpc.class).flyTo(lat, lng);
	}
	
	public void flyTo(Marker marker) {
		getRpcProxy(LeafletClientRpc.class).flyTo(marker.getLat(), marker.getLng());
	}

	// onStateChange will be triggered
	public void addMarker(Marker marker) {	
		getState().addMarker(marker);
	}
	
	// onStateChange will be triggered
	public void removeMarker(Marker marker) {
		getState().removeMarker(marker);
	}
	
	// helper method
	public Marker getClosestMarker(double lat, double lng) {
		List<Marker> markers = getState().getMarkers();
		int R = 6371; // radius of earth in km
	    double[] distances = new double[markers.size()];
	    int closest = -1;
	    for( int i=0;i<markers.size(); i++ ) {
	        double mlat = markers.get(i).getLat();
	        double mlng = markers.get(i).getLng();
	        double dLat  = (mlat - lat) * Math.PI/180;
	        double dLong = (mlng - lng) * Math.PI/180;
	        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	            Math.cos((lat) * Math.PI/180) * Math.cos((lat)*Math.PI/180) * Math.sin(dLong/2) * Math.sin(dLong/2);
	        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	        double d = R * c;
	        distances[i] = d;
	        if ( closest == -1 || d < distances[closest] ) {
	            closest = i;
	        }
	    }
	    return markers.get(closest);
	}
	
	// helper method
	public List<Marker> getMarkers() {
		return getState().getMarkers();
	}
	
	public void addClickListener(LeafletClickListener listener) {
		listeners.add(listener);
	}
}