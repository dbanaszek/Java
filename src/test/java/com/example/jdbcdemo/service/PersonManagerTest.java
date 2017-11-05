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

	private final static String DEVICENAME_2 = "Samsung Galaxy S5";
	private final static double SCREENSIZE_2 = 5.1;
	private final static Calendar DATEOFRELEASE_2 = new GregorianCalendar(2014, 4,11);

	private final static String DEVICENAME_3 = "LG G2";
	private final static double SCREENSIZE_3 = 5.2;
	private final static Calendar DATEOFRELEASE_3 = new GregorianCalendar(2013, 9,13);
	
	@Test
	public void checkConnection(){
		assertNotNull(personManager.getConnection());
	}
	
	@Test
	public void checkAdding() {

		Person person = new Person(DEVICENAME_1, SCREENSIZE_1, DATEOFRELEASE_1);
		Person personTwo = new Person(DEVICENAME_2, SCREENSIZE_2, DATEOFRELEASE_2);

		personManager.clearDevices();
		assertEquals(1, personManager.addDevice(person));
		assertEquals(1, personManager.addDevice((personTwo)));

		List<Person> persons = personManager.getAllDevices();
		Person personRetrieved = persons.get(0);

		assertEquals(DEVICENAME_1, personRetrieved.getDeviceName());
		assertEquals(SCREENSIZE_1, personRetrieved.getScreenSize(), 0.00001);
		assertEquals(DATEOFRELEASE_1, personRetrieved.getDateOfRelease());

		personRetrieved = persons.get(1);

		assertEquals(DEVICENAME_2, personRetrieved.getDeviceName());
		assertEquals(SCREENSIZE_2, personRetrieved.getScreenSize(), 0.00001);
		assertEquals(DATEOFRELEASE_2, personRetrieved.getDateOfRelease());

	}
	@Test
	public  void checkDeleting(){

		Person personThree = new Person(DEVICENAME_3, SCREENSIZE_3, DATEOFRELEASE_3);

		assertEquals(1, personManager.addDevice(personThree));
		assertEquals(1, personManager.removeDevicesByName(personThree));
	}

	@Test
	public void checkFindByName(){
		Person personTwo = new Person(DEVICENAME_2, SCREENSIZE_2, DATEOFRELEASE_2);

		List<Person> persons = personManager.findDevicesByName(personTwo);
		Person personRetrieved = persons.get(0);

		assertEquals(DEVICENAME_2, personRetrieved.getDeviceName());
		assertEquals(SCREENSIZE_2, personRetrieved.getScreenSize(), 0.00001);
		assertEquals(DATEOFRELEASE_2, personRetrieved.getDateOfRelease());
	}

}
