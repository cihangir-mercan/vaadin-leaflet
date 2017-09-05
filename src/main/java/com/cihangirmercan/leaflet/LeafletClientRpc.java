package com.cihangirmercan.leaflet;

import com.vaadin.shared.communication.ClientRpc;

public interface LeafletClientRpc extends ClientRpc {
	void setView(double lat, double lng, int zoomLevel);
	void flyTo(double lat, double lng);
}
