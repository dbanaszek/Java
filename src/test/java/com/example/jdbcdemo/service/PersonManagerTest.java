package com.example.jdbcdemo.service;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
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

	Person personOne = new Person(DEVICENAME_1, SCREENSIZE_1, DATEOFRELEASE_1);
	Person personTwo = new Person(DEVICENAME_2, SCREENSIZE_2, DATEOFRELEASE_2);
	Person personThree = new Person(DEVICENAME_3, SCREENSIZE_3, DATEOFRELEASE_3);
	Person personFour = new Person(DEVICENAME_4, SCREENSIZE_4, DATEOFRELEASE_4);
	Person personFive = new Person(DEVICENAME_5, SCREENSIZE_5, DATEOFRELEASE_5);
	Person personSix = new Person(DEVICENAME_6, SCREENSIZE_6, DATEOFRELEASE_6);
	
	@Test
	public void checkConnection(){
		assertNotNull(personManager.getConnection());
	}
	
	@Test
	public void checkAdding() {

		personManager.clearDevices();
		assertEquals(1, personManager.addDevice(personOne));

		List<Person> persons = personManager.getAllDevices();
		Person personRetrieved = persons.get(0);

		assertEquals(DEVICENAME_1, personRetrieved.getDeviceName());
		assertEquals(SCREENSIZE_1, personRetrieved.getScreenSize(), 0.00001);
		assertEquals(DATEOFRELEASE_1, personRetrieved.getDateOfRelease());
	}
	
	@Test
	public void checkAddDevices(){
		
		int size;
		
		personManager.clearDevices();
		List<Person> persons = new ArrayList<>();
		persons.add(personOne);
		persons.add(personTwo);
		persons.add(personThree);
		persons.add(personFour);
		
		personManager.addDevices(persons);
		
		size = personManager.getAllDevices().size();
		
		assertThat(size, either(is(4)).or(is(0)));
	}
	
	@Test
	public void checkFindByName(){
		
		List<Person> persons = personManager.findDevicesByName(personOne);
		Person personRetrieved = persons.get(0);

		assertEquals(DEVICENAME_1, personRetrieved.getDeviceName());
		assertEquals(SCREENSIZE_1, personRetrieved.getScreenSize(), 0.00001);
		assertEquals(DATEOFRELEASE_1, personRetrieved.getDateOfRelease());
	}

	@Test
	public void checkFindByScreenSize(){

		List<Person> persons = personManager.findDevicesByScreenSize(personThree);
		Person personRetrieved = persons.get(0);

		assertEquals(DEVICENAME_1, personRetrieved.getDeviceName());
		assertEquals(SCREENSIZE_1, personRetrieved.getScreenSize(), 0.00001);
		assertEquals(DATEOFRELEASE_1, personRetrieved.getDateOfRelease());
	}
	
	@Test
	public void checkFindByDate(){

		List<Person> persons = personManager.findDevicesByDate(personOne);
		Person personRetrieved = persons.get(0);

		assertEquals(DEVICENAME_1, personRetrieved.getDeviceName());
		assertEquals(SCREENSIZE_1, personRetrieved.getScreenSize(), 0.00001);
		assertEquals(DATEOFRELEASE_1, personRetrieved.getDateOfRelease());
	}

	@Test
	public void checkDeleting(){

		assertEquals(1, personManager.addDevice(personFour));
		assertEquals(1, personManager.removeDevicesByName(personFour));
	}

	@Test
	public void checkUpdating(){
		
		assertEquals(1, personManager.addDevice(personFive));
		assertEquals(1, personManager.updateDevice(personFive, personSix));

		List<Person> persons = personManager.findDevicesByDate(personSix);
		Person personRetrieved = persons.get(0);

		assertEquals(DEVICENAME_6, personRetrieved.getDeviceName());
		assertEquals(SCREENSIZE_6, personRetrieved.getScreenSize(), 0.00001);
		assertEquals(DATEOFRELEASE_6, personRetrieved.getDateOfRelease());

		assertEquals(1, personManager.removeDevicesByName(personSix));
	}

}
