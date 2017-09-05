package com.cihangirmercan.leaflet;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.shared.ui.JavaScriptComponentState;

@SuppressWarnings("serial")
public class LeafletState extends JavaScriptComponentState {
    private List<Marker> markers;

	public LeafletState() {
		markers = new ArrayList<Marker>();
	}
	
	public void addMarker(Marker marker) {
		markers.add(marker);
	}
	
	public void removeMarker(Marker marker) {
		markers.remove(marker);	
	}
	
	public List<Marker> getMarkers() {
		return markers;
	}

	public void setMarkers(List<Marker> markers) {
		this.markers = markers;
	}
}