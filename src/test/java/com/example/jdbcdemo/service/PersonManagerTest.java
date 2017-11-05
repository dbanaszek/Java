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

	private final static String DEVICENAME_2 = "Galaxy S5";
	private final static double SCREENSIZE_2 = 5.1;
	private final static Calendar DATEOFRELEASE_2 = new GregorianCalendar(2014, 4,11);

	private final static String DEVICENAME_3 =  "G2";
	private final static double SCREENSIZE_3 = 5.2;
	private final static Calendar DATEOFRELEASE_3 = new GregorianCalendar(2013, 9,13);

	private final static String DEVICENAME_4 = "Iphone 7";
	private final static double SCREENSIZE_4 = 4.7;
	private final static Calendar DATEOFRELEASE_4 = new GregorianCalendar(2016, 9,7);

	private final static String DEVICENAME_5 = "G3";
	private final static double SCREENSIZE_5 = 5.5;
	private final static Calendar DATEOFRELEASE_5 = new GregorianCalendar(2014, 5,27);

	private final static String DEVICENAME_6 = "G4";
	private final static double SCREENSIZE_6 = 5.5;
	private final static Calendar DATEOFRELEASE_6 = new GregorianCalendar(2015, 4,28);


	
	@Test
	public void checkConnection(){
		assertNotNull(personManager.getConnection());
	}
	
	@Test
	public void checkAdding() {

		Person person = new Person(DEVICENAME_1, SCREENSIZE_1, DATEOFRELEASE_1);
		Person personTwo = new Person(DEVICENAME_2, SCREENSIZE_2, DATEOFRELEASE_2);
		Person personThree = new Person(DEVICENAME_3, SCREENSIZE_3, DATEOFRELEASE_3);

		personManager.clearDevices();
		assertEquals(1, personManager.addDevice(person));
		assertEquals(1, personManager.addDevice(personTwo));
		assertEquals(1, personManager.addDevice(personThree));

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
	public void checkFindByName(){
		Person personTwo = new Person(DEVICENAME_2, SCREENSIZE_2, DATEOFRELEASE_2);

		List<Person> persons = personManager.findDevicesByName(personTwo);
		Person personRetrieved = persons.get(0);

		assertEquals(DEVICENAME_2, personRetrieved.getDeviceName());
		assertEquals(SCREENSIZE_2, personRetrieved.getScreenSize(), 0.00001);
		assertEquals(DATEOFRELEASE_2, personRetrieved.getDateOfRelease());
	}

	@Test
	public void checkFindByScreenSize(){
		Person personThree = new Person(DEVICENAME_3, SCREENSIZE_3, DATEOFRELEASE_3);

		//assertEquals(1, personManager.addDevice(personThree));

		List<Person> persons = personManager.findDevicesByScreenSize(personThree);
		Person personRetrieved = persons.get(0);

		assertEquals(DEVICENAME_1, personRetrieved.getDeviceName());
		assertEquals(SCREENSIZE_1, personRetrieved.getScreenSize(), 0.00001);
		assertEquals(DATEOFRELEASE_1, personRetrieved.getDateOfRelease());

		personRetrieved = persons.get(1);

		assertEquals(DEVICENAME_3, personRetrieved.getDeviceName());
		assertEquals(SCREENSIZE_3, personRetrieved.getScreenSize(), 0.00001);
		assertEquals(DATEOFRELEASE_3, personRetrieved.getDateOfRelease());

	}

	@Test
	public void checkDeleting(){

		Person personFour = new Person(DEVICENAME_4, SCREENSIZE_4, DATEOFRELEASE_4);

		assertEquals(1, personManager.addDevice(personFour));
		assertEquals(1, personManager.removeDevicesByName(personFour));
	}

	@Test
	public void checkUpdating(){
		Person personFive = new Person(DEVICENAME_5, SCREENSIZE_5, DATEOFRELEASE_5);
		Person personSix = new Person(DEVICENAME_6, SCREENSIZE_6, DATEOFRELEASE_6);

		assertEquals(1, personManager.addDevice(personFive));
		assertEquals(1, personManager.updateDevice(personFive, personSix));

	}
	
}
