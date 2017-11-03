package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

import com.example.jdbcdemo.domain.Person;

public class PersonManagerTest {
	
	
	DeviceManagerJDBC personManager = new DeviceManagerJDBC();
	
	private final static String DEVICENAME_1 = "Moto X gen.2";
	private final static double SCREENSIZE_1 = 5.2;
	private final static Calendar DATEOFRELEASE_1 = new GregorianCalendar(2014, 9,5);
	
	@Test
	public void checkConnection(){
		assertNotNull(personManager.getConnection());
	}
	
	@Test
	public void checkAdding() {

		Person person = new Person(DEVICENAME_1, SCREENSIZE_1, DATEOFRELEASE_1);

		personManager.clearDevices();
		assertEquals(1, personManager.addDevice(person));

		List<Person> persons = personManager.getAllDevices();
		Person personRetrieved = persons.get(0);

		assertEquals(DEVICENAME_1, personRetrieved.getDeviceName());
		assertEquals(SCREENSIZE_1, personRetrieved.getScreenSize(), 0.00001);
		assertEquals(DATEOFRELEASE_1, personRetrieved.getDateOfRelease());
	}
	@Test
	public  void checkDeleting(){
		Person person = new Person(DEVICENAME_1, SCREENSIZE_1, DATEOFRELEASE_1);

		assertEquals(1, personManager.removeDevicesByName(person));
	}

}
