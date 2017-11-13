package com.example.jdbcdemo.service;

import java.util.List;

import com.example.jdbcdemo.domain.Person;

public interface PersonManager {
	
	public int addDevice(Person person);
	public List<Person> getAllDevices();
	public List<Person> findDevicesByName(Person person);
	public List<Person> findDevicesByScreenSize(Person person);
	public List<Person> findDevicesByDate(Person person);
	public int updateDevice(Person person, Person newPerson);
	public int removeDevicesByName(Person person);
	public void addDevices(List<Person> persons);
	public void updateDevices(List<Person> persons, List<Person> newPersons);
	public int deleteDevices(List<Person> persons);
	

}
