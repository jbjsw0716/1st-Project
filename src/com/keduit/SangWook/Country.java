package com.keduit.SangWook;

public class Country {
	protected int country_id;
	protected String countryName;
	
	protected Country(String countryName) {
		super();
		this.countryName = countryName;
	}
	
	protected Country(int country_id) {
		super();
		this.country_id = country_id;
	}
	
	protected int getCountry_id() {
		return country_id;
	}
	protected void setCountry_id(int country_id) {
		this.country_id = country_id;
	}
	protected String getCountryName() {
		return countryName;
	}
	public void setCountryName(String nationNm) {
		this.countryName = nationNm;
	}

	@Override
	public String toString() {
		return "Country [country_id=" + country_id + ", countryName=" + countryName + "]";
	}
}
