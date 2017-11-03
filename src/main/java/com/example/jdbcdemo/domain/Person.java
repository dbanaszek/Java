package com.example.jdbcdemo.domain;

import java.util.Calendar;

public class Person {
	
	private long id;
	
	private String deviceName;
	private double screenSize;
	private Calendar dateOfRelease;
	
	public Person() {
	}
	
	public Person(String deviceName, double screenSize, Calendar dateOfRelease) {
		super();
		this.deviceName = deviceName;
		this.screenSize = screenSize;
		this.dateOfRelease = dateOfRelease;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public double getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(double screenSize) {
		this.screenSize = screenSize;
	}


	public Calendar getDateOfRelease() {
		return dateOfRelease;
	}

	public void setDateOfRelease(Calendar dateOfRelease) {
		this.dateOfRelease = dateOfRelease;
	}
}
