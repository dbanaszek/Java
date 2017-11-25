package com.example.jdbcdemo.service;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

import com.example.jdbcdemo.domain.Device;

public class DeviceManagerTest {
	
	
	DeviceManagerJDBC deviceManager = new DeviceManagerJDBC();
	HardwareManagerJDBC hardwareManager = new HardwareManagerJDBC();
	ConnectionManagerJDBC conn = new ConnectionManagerJDBC();
	
	private final static String DEVICENAME_1 = "Moto X gen.2";
	private final static double SCREENSIZE_1 = 5.2;
	private final static Calendar DATEOFRELEASE_1 = new GregorianCalendar(2014, 9,5);

	private final static int STORAGE_1 = 16;
	private final static int MEMORY_1 = 2;
	private final static String PROCESSOR_1 = "Snapdragon 801";

	private final static String DEVICENAME_2 = "Galaxy S5";
	private final static double SCREENSIZE_2 = 5.1;
	private final static Calendar DATEOFRELEASE_2 = new GregorianCalendar(2014, 4,11);

	private final static int STORAGE_2 = 16;
	private final static int MEMORY_2 = 2;
	private final static String PROCESSOR_2 = "Snapdragon 801";

	private final static String DEVICENAME_3 =  "G2";
	private final static double SCREENSIZE_3 = 5.2;
	private final static Calendar DATEOFRELEASE_3 = new GregorianCalendar(2013, 9,13);

	private final static int STORAGE_3 = 16;
	private final static int MEMORY_3 = 2;
	private final static String PROCESSOR_3 = "Snapdragon 800";

	private final static String DEVICENAME_4 = "Iphone 7";
	private final static double SCREENSIZE_4 = 4.7;
	private final static Calendar DATEOFRELEASE_4 = new GregorianCalendar(2016, 9,7);

	private final static String DEVICENAME_5 = "G3";
	private final static double SCREENSIZE_5 = 5.5;
	private final static Calendar DATEOFRELEASE_5 = new GregorianCalendar(2014, 5,27);

	private final static String DEVICENAME_6 = "G4";
	private final static double SCREENSIZE_6 = 5.5;
	private final static Calendar DATEOFRELEASE_6 = new GregorianCalendar(2015, 4,28);

	Device deviceOne = new Device(DEVICENAME_1, SCREENSIZE_1, DATEOFRELEASE_1);
	Device deviceTwo = new Device(DEVICENAME_2, SCREENSIZE_2, DATEOFRELEASE_2);
	Device deviceThree = new Device(DEVICENAME_3, SCREENSIZE_3, DATEOFRELEASE_3);
	Device deviceFour = new Device(DEVICENAME_4, SCREENSIZE_4, DATEOFRELEASE_4);
	Device deviceFive = new Device(DEVICENAME_5, SCREENSIZE_5, DATEOFRELEASE_5);
	Device deviceSix = new Device(DEVICENAME_6, SCREENSIZE_6, DATEOFRELEASE_6);
	
	@Test
	public void checkConnection(){
		assertNotNull(conn.getConnection());
	}
	
	@Test
	public void checkAdding() {

		deviceManager.clearDevices();

		assertEquals(1, deviceManager.addDevice(deviceOne));

		List<Device> devices = deviceManager.getAllDevices();
		Device deviceRetrieved = devices.get(0);

		assertEquals(DEVICENAME_1, deviceRetrieved.getDeviceName());
		assertEquals(SCREENSIZE_1, deviceRetrieved.getScreenSize(), 0.00001);
		assertEquals(DATEOFRELEASE_1, deviceRetrieved.getDateOfRelease());
	}
	
	@Test
	public void checkAddDevices(){
		
		int size;
		
		deviceManager.clearDevices();

		List<Device> devices = new ArrayList<>();
		devices.add(deviceOne);
		devices.add(deviceTwo);
		devices.add(deviceThree);
		devices.add(deviceFour);
		
		deviceManager.addDevices(devices);
		
		size = deviceManager.getAllDevices().size();
		
		assertThat(size, either(is(4)).or(is(0)));
	}
	
	@Test
	public void checkFindByName(){

		deviceManager.clearDevices();
		deviceManager.addDevice(deviceOne);

		List<Device> devices = deviceManager.findDevicesByName(deviceOne);
		Device deviceRetrieved = devices.get(0);

		assertEquals(DEVICENAME_1, deviceRetrieved.getDeviceName());
		assertEquals(SCREENSIZE_1, deviceRetrieved.getScreenSize(), 0.00001);
		assertEquals(DATEOFRELEASE_1, deviceRetrieved.getDateOfRelease());
	}

	@Test
	public void checkFindByScreenSize(){

		deviceManager.clearDevices();
		deviceManager.addDevice(deviceOne);

		List<Device> devices = deviceManager.findDevicesByScreenSize(deviceThree);
		Device deviceRetrieved = devices.get(0);

		assertEquals(DEVICENAME_1, deviceRetrieved.getDeviceName());
		assertEquals(SCREENSIZE_1, deviceRetrieved.getScreenSize(), 0.00001);
		assertEquals(DATEOFRELEASE_1, deviceRetrieved.getDateOfRelease());
	}
	
	@Test
	public void checkFindByDate(){

		deviceManager.clearDevices();
		deviceManager.addDevice(deviceOne);

		List<Device> devices = deviceManager.findDevicesByDate(deviceOne);
		Device deviceRetrieved = devices.get(0);

		assertEquals(DEVICENAME_1, deviceRetrieved.getDeviceName());
		assertEquals(SCREENSIZE_1, deviceRetrieved.getScreenSize(), 0.00001);
		assertEquals(DATEOFRELEASE_1, deviceRetrieved.getDateOfRelease());
	}

	@Test
	public void checkDeleteDevice(){

		deviceManager.clearDevices();

		assertEquals(1, deviceManager.addDevice(deviceFour));
		assertEquals(1, deviceManager.removeDevicesByName(deviceFour));
	}

	@Test
	public void checkUpdateDevice(){

		deviceManager.clearDevices();

		assertEquals(1, deviceManager.addDevice(deviceFive));
		assertEquals(1, deviceManager.updateDevice(deviceFive, deviceSix));

		List<Device> devices = deviceManager.findDevicesByDate(deviceSix);
		Device deviceRetrieved = devices.get(0);

		assertEquals(DEVICENAME_6, deviceRetrieved.getDeviceName());
		assertEquals(SCREENSIZE_6, deviceRetrieved.getScreenSize(), 0.00001);
		assertEquals(DATEOFRELEASE_6, deviceRetrieved.getDateOfRelease());

		assertEquals(1, deviceManager.removeDevicesByName(deviceSix));
	}

	@Test
	public void checkUpdateDevices(){

		int size;

		deviceManager.clearDevices();
		List<Device> devices = new ArrayList<>();
		List<Device> newDevices = new ArrayList<>();

		devices.add(deviceOne);
		devices.add(deviceTwo);
		devices.add(deviceThree);

		deviceManager.addDevices(devices);

		newDevices.add(deviceFour);
		newDevices.add(deviceFive);
		newDevices.add(deviceSix);

		deviceManager.updateDevices(devices, newDevices);

		List<Device> devicesRetrieved = deviceManager.getAllDevices();
		size = devicesRetrieved.size();

		Device deviceRetrieved = devicesRetrieved.get(0);

		assertEquals(DEVICENAME_4, deviceRetrieved.getDeviceName());
		assertEquals(SCREENSIZE_4, deviceRetrieved.getScreenSize(), 0.00001);
		assertEquals(DATEOFRELEASE_4, deviceRetrieved.getDateOfRelease());
		assertThat(size, either(is(3)).or(is(0)));
	}

	@Test
	public void checkDeleteDevices(){

		int size;

		deviceManager.clearDevices();
		List<Device> devices = new ArrayList<>();

		devices.add(deviceOne);
		devices.add(deviceTwo);
		devices.add(deviceThree);

		deviceManager.addDevices(devices);

		size = deviceManager.deleteDevices(devices);

		assertThat(size, either(is(3)).or(is(0)));
	}

	@Test
	public void checkDeleteDevicesForeignKey(){
		deviceManager.clearDevices();
		List<Device> devices = new ArrayList<>();

		devices.add(deviceOne);
		devices.add(deviceTwo);
		devices.add(deviceThree);

		deviceManager.addDevices(devices);



	}
}
