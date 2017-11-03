package com.example.jdbcdemo.domain;

public class Person {
	
	private long id;
	
	private String deviceName;
	private double screenSize;
	private int yearOfRelease;
	private int dayOfRelease;
	private int monthOfRelease;
	
	public Person() {
	}
	
	public Person(String deviceName, double screenSize, int yearOfRelease, int monthOfRelease, int dayOfRelease) {
		super();
		this.deviceName = deviceName;
		this.screenSize = screenSize;
		this.yearOfRelease = yearOfRelease;
		this.monthOfRelease = monthOfRelease;
		this.dayOfRelease = dayOfRelease;
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

	public int getYearOfRelease() {
		return yearOfRelease;
	}

	public void setYearOfRelease(int yearOfRelease) {
		this.yearOfRelease = yearOfRelease;
	}

	public int getDayOfRelease() {
		return dayOfRelease;
	}

	public void setDayOfRelease(int dayOfRelease) {
		this.dayOfRelease = dayOfRelease;
	}

	public int getMonthOfRelease() {
		return monthOfRelease;
	}

	public void setMonthOfRelease(int monthOfRelease) {
		this.monthOfRelease = monthOfRelease;
	}
}
