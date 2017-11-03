package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.example.jdbcdemo.domain.Person;

public class PersonManagerTest {
	
	
	PersonManagerJDBC personManager = new PersonManagerJDBC();
	
	private final static String DEVICENAME_1 = "Moto X gen.2";
	private final static int 	YEAROFRELEASE_1 = 2014;
	private final static int    MONTHOFRELEASE_1 = 9;
	private final static int 	DAYOFRELEASE_1 = 5;
	private final static double SCREENSIZE_1 = 5.2;
	
	@Test
	public void checkConnection(){
		assertNotNull(personManager.getConnection());
	}
	
	@Test
	public void checkAdding(){
		
		Person person = new Person(DEVICENAME_1, SCREENSIZE_1, YEAROFRELEASE_1, MONTHOFRELEASE_1, DAYOFRELEASE_1);
		
		personManager.clearPersons();
		assertEquals(1,personManager.addPerson(person));
		
		List<Person> persons = personManager.getAllPersons();
		Person personRetrieved = persons.get(0);
		
		assertEquals(DEVICENAME_1, personRetrieved.getDeviceName());
//		assertEquals(YOB_1, personRetrieved.getYob());
		
	}

}
