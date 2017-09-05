package com.cihangirmercan.leaflet;

import com.vaadin.shared.communication.ServerRpc;

public interface LeafletServerRpc extends ServerRpc {
	void leafletClicked(double lat, double lng);
}
