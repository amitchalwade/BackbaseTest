package com.backbase.bean;

public class AtmObject {

	private static Address address;
	private static GeoLocation geolocation;
	private static int distance;
	private static String type;

	public static Address getAddress() {
		return address;
	}

	
	public static void setAddress(Address address) {
		AtmObject.address = address;
	}

	public static GeoLocation getGeolocation() {
		return geolocation;
	}

	public static void setGeolocation(GeoLocation geolocation) {
		AtmObject.geolocation = geolocation;
	}

	public static int getDistance() {
		return distance;
	}

	public static void setDistance(int distance) {
		AtmObject.distance = distance;
	}

	public static String getType() {
		return type;
	}

	public static void setType(String type) {
		AtmObject.type = type;
	}

}
