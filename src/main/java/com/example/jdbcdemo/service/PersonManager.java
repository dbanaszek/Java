package com.example.jdbcdemo.service;

import java.util.List;

import com.example.jdbcdemo.domain.Person;

public interface PersonManager {
	
	public int addDevice(Person person);
	public List<Person> getAllDevices();
//	public void removeDevice(Person person);																			TODO: For later implementation
//	public List<Person> findDevicesByName(Person person);
//	public List<Person> findDevicesByScreenSize(Person person);
//  public List<Person> removeDevices(Person person)
// 	public void removeDevice(Person person);
//	public int updateDevice(Person person);


}
