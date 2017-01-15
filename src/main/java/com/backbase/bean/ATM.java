package com.backbase.bean;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "address", "distance", "type" })
public class ATM {

	@JsonProperty("address")
	private Address address;
	@JsonProperty("distance")
	private Integer distance;
	@JsonProperty("type")
	private String type;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("address")
	public Address getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(Address address) {
		this.address = address;
	}

	public ATM withAddress(Address address) {
		this.address = address;
		return this;
	}

	@JsonProperty("distance")
	public Integer getDistance() {
		return distance;
	}

	@JsonProperty("distance")
	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public ATM withDistance(Integer distance) {
		this.distance = distance;
		return this;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	public ATM withType(String type) {
		this.type = type;
		return this;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public ATM withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}