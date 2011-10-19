package com.woods.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.location.Location;
import android.provider.ContactsContract.CommonDataKinds.Email;

public class User implements Data {

	private String firstName;
	private String lastName;
	private Email email;
	private Location gpsHome;
	private Location gpsWork;
	private Location gpsOther;
	private NeighborwoodsLocation nwHome;
	private NeighborwoodsLocation nwWork;
	private NeighborwoodsLocation nwOther;
	
	public User(String fName,String lName,Email email,Location h,Location w,Location o){
		this.firstName = fName;
		this.lastName = lName;
		this.email = email;
		this.gpsHome = h;
		this.gpsWork = w;
		this.gpsOther = o;
	}
	
	public User(String fName, Email email){
		this(fName,"",email,null,null,null);
	}
	
	
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public Email getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(Email email) {
		this.email = email;
	}

	/**
	 * @return the home
	 */
	public Location getHome() {
		return gpsHome;
	}

	/**
	 * @param home the home to set
	 */
	public void setHome(Location home) {
		this.gpsHome = home;
	}

	/**
	 * @return the work
	 */
	public Location getWork() {
		return gpsWork;
	}

	/**
	 * @param work the work to set
	 */
	public void setWork(Location work) {
		this.gpsWork = work;
	}

	/**
	 * @return the other
	 */
	public Location getOther() {
		return gpsOther;
	}

	/**
	 * @param other the other to set
	 */
	public void setOther(Location other) {
		this.gpsOther = other;
	}

	/**
	 * @return the nwHome
	 */
	public NeighborwoodsLocation getNwHome() {
		return nwHome;
	}

	/**
	 * @param nwHome the nwHome to set
	 */
	public void setNwHome(NeighborwoodsLocation nwHome) {
		this.nwHome = nwHome;
	}

	/**
	 * @return the nwWork
	 */
	public NeighborwoodsLocation getNwWork() {
		return nwWork;
	}

	/**
	 * @param nwWork the nwWork to set
	 */
	public void setNwWork(NeighborwoodsLocation nwWork) {
		this.nwWork = nwWork;
	}

	/**
	 * @return the nwOther
	 */
	public NeighborwoodsLocation getNwOther() {
		return nwOther;
	}

	/**
	 * @param nwOther the nwOther to set
	 */
	public void setNwOther(NeighborwoodsLocation nwOther) {
		this.nwOther = nwOther;
	}

}
