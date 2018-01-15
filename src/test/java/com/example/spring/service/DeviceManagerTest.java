package com.example.spring.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.example.spring.domain.Component;
import com.example.spring.domain.Exterior;
import com.example.spring.domain.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.domain.Device;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( "classpath:/beans.xml" )
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class DeviceManagerTest {

    @Autowired
    DeviceManager deviceManager;

    private final static String DEVICENAME_1 = "Moto X gen.2";
    //private final static double SCREENSIZE_1 = 5.2;
    private final static Calendar DATEOFRELEASE_1 = new GregorianCalendar(2014, 9, 5);

    private final static String DEVICENAME_2 = "Galaxy S5";
    //private final static double SCREENSIZE_2 = 5.1;
    private final static Calendar DATEOFRELEASE_2 = new GregorianCalendar(2014, 4, 11);

    private final static String DEVICENAME_3 = "G2";
    //private final static double SCREENSIZE_3 = 5.2;
    private final static Calendar DATEOFRELEASE_3 = new GregorianCalendar(2013, 9, 13);

    private final static String DEVICENAME_4 = "Iphone 7";
    //private final static double SCREENSIZE_4 = 4.7;
    private final static Calendar DATEOFRELEASE_4 = new GregorianCalendar(2016, 9, 7);

    private final static String DEVICENAME_5 = "G3";
    //private final static double SCREENSIZE_5 = 5.5;
    private final static Calendar DATEOFRELEASE_5 = new GregorianCalendar(2014, 5, 27);

    private final static String DEVICENAME_6 = "G4";
    //private final static double SCREENSIZE_6 = 5.5;
    private final static Calendar DATEOFRELEASE_6 = new GregorianCalendar(2015, 4, 28);

    private Device exampleDeviceOne = new Device();
    private Device exampleDeviceTwo = new Device();
    private Exterior exampleExterior = new Exterior();
    private Component exampleComponent = new Component();
    private Review exampleReviewOne = new Review();
    private Review exampleReviewTwo = new Review();

    List<Component> exampleComponents = new ArrayList<Component>();
    List<Review> exampleReviews = new ArrayList<Review>();
    List<Device> exampleDevices = new ArrayList<Device>();

    {
        exampleExterior.setScreenSize(5.2);
        exampleExterior.setPhysicalButtons(false);
        exampleExterior.setDevice(exampleDeviceOne);

        exampleComponent.setProcessor("Something");
        exampleComponent.setStorage(64);
        exampleComponent.setRam(2);
        exampleComponent.setDevice(exampleDeviceOne);

        exampleComponents.add(exampleComponent);

        exampleDevices.add(exampleDeviceOne);
        exampleDevices.add(exampleDeviceTwo);

        exampleReviewOne.setTitle("Something");
        exampleReviewOne.setMaxScore(100);
        exampleReviewOne.setDeviceScore(95);
        exampleReviewOne.setDevices(exampleDevices);

        exampleReviewOne.setTitle("SomethingTwo");
        exampleReviewOne.setMaxScore(50);
        exampleReviewOne.setDeviceScore(49);
        exampleReviewOne.setDevices(exampleDevices);

        exampleReviews.add(exampleReviewOne);
        exampleReviews.add(exampleReviewTwo);

        exampleDeviceOne.setDeviceName(DEVICENAME_1);
        exampleDeviceOne.setDateOfRelease(DATEOFRELEASE_1);
        exampleDeviceOne.setExterior(exampleExterior);
        exampleDeviceOne.setComponents(exampleComponents);
        exampleDeviceOne.setReviews(exampleReviews);

        exampleDeviceTwo.setDeviceName(DEVICENAME_2);
        exampleDeviceTwo.setDateOfRelease(DATEOFRELEASE_2);
        exampleDeviceTwo.setExterior(exampleExterior);
        exampleDeviceTwo.setComponents(exampleComponents);
        exampleDeviceTwo.setReviews(exampleReviews);
    }


    @Test
    public void checkAdding() {

        Device device = new Device();
        device.setDeviceName(DEVICENAME_1);
        //exampleDeviceOne.setScreenSize(SCREENSIZE_1);
        device.setDateOfRelease(DATEOFRELEASE_1);

        deviceManager.addReviews(exampleReviews);
        deviceManager.addDevice(exampleDeviceOne);

        List<Device> retrieved = deviceManager.getAllDevices();
        assertEquals(DEVICENAME_1, retrieved.get(0).getDeviceName());
    }

    /*@Test
    public void checkFindByScreen(){

        Device deviceFour = new Device();
        deviceFour.setDeviceName(DEVICENAME_4);
        deviceFour.setScreenSize(SCREENSIZE_4);
        deviceFour.setDateOfRelease(DATEOFRELEASE_4);

        deviceManager.addDevice(deviceFour);

        List<Device> exampleDeviceOne = deviceManager.findDevicesByScreenSize(SCREENSIZE_4);

        assertEquals(DEVICENAME_4, exampleDeviceOne.get(0).getDeviceName());
        assertEquals(DATEOFRELEASE_4, exampleDeviceOne.get(0).getDateOfRelease());
    } */

    @Test
    public void checkUpdate() {

        Device oldDevice = new Device();
        oldDevice.setDeviceName(DEVICENAME_2);
        //oldDevice.setScreenSize(SCREENSIZE_2);
        oldDevice.setDateOfRelease(DATEOFRELEASE_2);

        Device newDevice = new Device();
        newDevice.setDeviceName(DEVICENAME_3);
        //newDevice.setScreenSize(SCREENSIZE_3);
        newDevice.setDateOfRelease(DATEOFRELEASE_3);

        deviceManager.addDevice(oldDevice);

        List<Device> retrieved = deviceManager.findDevicesByName(DEVICENAME_2);

        deviceManager.updateDevice(retrieved.get(0).getId(), newDevice);
    }

    @Test
    public void checkAddDevices(){

        Device deviceOne = new Device();
        deviceOne.setDeviceName(DEVICENAME_1);
        //deviceOne.setScreenSize(SCREENSIZE_1);
        deviceOne.setDateOfRelease(DATEOFRELEASE_1);

        Device deviceTwo = new Device();
        deviceTwo.setDeviceName(DEVICENAME_2);
        //deviceTwo.setScreenSize(SCREENSIZE_2);
        deviceTwo.setDateOfRelease(DATEOFRELEASE_2);

        List<Device> devices = new ArrayList<Device>();

        devices.add(deviceOne);
        devices.add(deviceTwo);


        deviceManager.addDevices(devices);

        List<Device> retrieved = deviceManager.getAllDevices();

        assertEquals(DEVICENAME_1, retrieved.get(0).getDeviceName());
        assertEquals(DEVICENAME_2, retrieved.get(1).getDeviceName());
    }

    @Test
    public void checkUpdateDevices(){

        Device deviceOne = new Device();
        deviceOne.setDeviceName(DEVICENAME_1);
        //deviceOne.setScreenSize(SCREENSIZE_1);
        deviceOne.setDateOfRelease(DATEOFRELEASE_1);

        Device deviceTwo = new Device();
        deviceTwo.setDeviceName(DEVICENAME_2);
        //deviceTwo.setScreenSize(SCREENSIZE_2);
        deviceTwo.setDateOfRelease(DATEOFRELEASE_2);

        Device deviceThree = new Device();
        deviceThree.setDeviceName(DEVICENAME_3);
        //deviceThree.setScreenSize(SCREENSIZE_3);
        deviceThree.setDateOfRelease(DATEOFRELEASE_3);

        Device deviceFour = new Device();
        deviceFour.setDeviceName(DEVICENAME_4);
        //deviceFour.setScreenSize(SCREENSIZE_4);
        deviceFour.setDateOfRelease(DATEOFRELEASE_4);

        List<Device> oldDevices = new ArrayList<Device>();
        oldDevices.add(deviceOne);
        oldDevices.add(deviceTwo);

        List<Device> newDevices = new ArrayList<Device>();
        newDevices.add(deviceThree);
        newDevices.add(deviceFour);

        deviceManager.addDevices(oldDevices);
        deviceManager.updateDevices(oldDevices, newDevices);

        List<Device> retrieved = deviceManager.getAllDevices();

        assertEquals(DEVICENAME_3, retrieved.get(0).getDeviceName());
        assertEquals(DEVICENAME_4, retrieved.get(1).getDeviceName());

    }

    @Test
    public void checkDeleteDevices() {

        Device deviceFour = new Device();
        deviceFour.setDeviceName(DEVICENAME_4);
        //deviceFour.setScreenSize(SCREENSIZE_4);
        deviceFour.setDateOfRelease(DATEOFRELEASE_4);

        Device deviceFive = new Device();
        deviceFive.setDeviceName(DEVICENAME_5);
        //deviceFive.setScreenSize(SCREENSIZE_5);
        deviceFive.setDateOfRelease(DATEOFRELEASE_5);

        Device deviceSix = new Device();
        deviceSix.setDeviceName(DEVICENAME_6);
        //deviceSix.setScreenSize(SCREENSIZE_6);
        deviceSix.setDateOfRelease(DATEOFRELEASE_6);

        List<Device> devices = new ArrayList<Device>();
        devices.add(deviceFour);
        devices.add(deviceFive);
        devices.add(deviceSix);

        deviceManager.addDevices(devices);

        List<Device> retrieved = deviceManager.getAllDevices();

        assertEquals(3, retrieved.size());

        deviceManager.deleteDevices(devices);

        retrieved = deviceManager.getAllDevices();

        assertEquals(0, retrieved.size());
    }
}