package com.backbase.bean;

public class Address {

	private static String street;
	private static int housenumber;
	public static String getStreet() {
		return street;
	}
	public static void setStreet(String street) {
		Address.street = street;
	}
	
	public static int getHousenumber() {
		return housenumber;
	}
	public static void setHousenumber(int housenumber) {
		Address.housenumber = housenumber;
	}
	public static String getPostalcode() {
		return postalcode;
	}
	public static void setPostalcode(String postalcode) {
		Address.postalcode = postalcode;
	}
	public static String getCity() {
		return city;
	}
	public static void setCity(String city) {
		Address.city = city;
	}
	private static String postalcode;
	private static String city; 
	
}
