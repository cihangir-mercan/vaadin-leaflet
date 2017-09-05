package com.cihangirmercan.leaflet;

public class Marker {

	private double lat;
	private double lng;
	private String popUpText;

	public Marker(double lat, double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
		popUpText = "Lat: " + lat + " Lng: " + lng; // default
	}
	
	public Marker(double lat, double lng, String popUpText) {
		super();
		this.lat = lat;
		this.lng = lng;
		this.popUpText = popUpText;
	}
	
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public String getPopUpText() {
		return popUpText;
	}
	public void setPopUpText(String popUpText) {
		this.popUpText = popUpText;
	}

	@Override
	public String toString() {
		return "Marker [lat=" + lat + ", lng=" + lng + ", popUpText=" + popUpText + "]";
	}
}
