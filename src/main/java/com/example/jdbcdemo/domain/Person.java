package com.example.jdbcdemo.domain;

public class Person {
	
	private long id;
	
	private String deviceName;
	private String dateOfRelease;
	private double screenSize;
	private int yob;
	
	public Person() {
	}
	
	public Person(String deviceName, String dateOfRelease, double screenSize, int yob) {
		super();
		this.deviceName = deviceName;
		this.dateOfRelease = dateOfRelease;
		this.screenSize = screenSize;
		this.yob = yob;
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

	public String getDateOfRelease() {
		return dateOfRelease;
	}

	public void setDateOfRelease(String dateOfRelease) {
		this.dateOfRelease = dateOfRelease;
	}

	public double getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(double screenSize) {
		this.screenSize = screenSize;
	}
	public int getYob() {
		return yob;
	}
	public void setYob(int yob) {
		this.yob = yob;
	}
	
}
