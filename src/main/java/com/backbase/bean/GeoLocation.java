package com.backbase.bean;

public class GeoLocation {

	private static String lat;
	private static String lng;
	public static String getLat() {
		return lat;
	}
	
	public static void setLat(String lat) {
		GeoLocation.lat = lat;
	}
	public static String getLng() {
		return lng;
	}
	public static void setLng(String lng) {
		GeoLocation.lng = lng;
	} 
	
	
	
}
