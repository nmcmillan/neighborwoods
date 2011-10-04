package com.woods.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.location.Address;
import android.location.Location;

public class Restaurants implements Data {

	private int _id;
	private String name;
	private Location location;
	private Address address;
	private int yelpStars;
	private int localRecommendations;
	private enum COST {
		CHEAP,
		MODERATE,
		PRICEY,
		BREAK_BANK
	};
	
	
	@Override
	public Data fromCursor(Cursor c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContentValues toContentValues() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the yelpStars
	 */
	public int getYelpStars() {
		return yelpStars;
	}

	/**
	 * @param yelpStars the yelpStars to set
	 */
	public void setYelpStars(int yelpStars) {
		this.yelpStars = yelpStars;
	}

	/**
	 * @return the localRecommendations
	 */
	public int getLocalRecommendations() {
		return localRecommendations;
	}

	/**
	 * @param localRecommendations the localRecommendations to set
	 */
	public void setLocalRecommendations(int localRecommendations) {
		this.localRecommendations = localRecommendations;
	}

}
