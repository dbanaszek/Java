package com.example.spring.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.example.spring.domain.Hardware;
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
    private Hardware exampleHardware = new Hardware();
    private Review exampleReviewOne = new Review();
    private Review exampleReviewTwo = new Review();

    List<Hardware> exampleHardwares = new ArrayList<Hardware>();
    List<Review> exampleReviews = new ArrayList<Review>();
    List<Device> exampleDevices = new ArrayList<Device>();

    {
        exampleExterior.setScreenSize(5.2);
        exampleExterior.setPhysicalButtons(false);
        exampleExterior.setDevice(exampleDeviceOne);

        exampleHardware.setProcessor("Something");
        exampleHardware.setStorage(64);
        exampleHardware.setRam(2);
        exampleHardware.setDevice(exampleDeviceOne);

        exampleHardwares.add(exampleHardware);

        exampleDevices.add(exampleDeviceOne);
        exampleDevices.add(exampleDeviceTwo);

        exampleReviewOne.setTitle("Something");
        exampleReviewOne.setMaxScore(100);
        exampleReviewOne.setDeviceScore(95);

        exampleReviewOne.setTitle("SomethingTwo");
        exampleReviewOne.setMaxScore(50);
        exampleReviewOne.setDeviceScore(49);

        exampleReviews.add(exampleReviewOne);
        exampleReviews.add(exampleReviewTwo);

        exampleDeviceOne.setDeviceName(DEVICENAME_1);
        exampleDeviceOne.setDateOfRelease(DATEOFRELEASE_1);
        exampleDeviceOne.setExterior(exampleExterior);
        exampleDeviceOne.setHardwares(exampleHardwares);
        exampleDeviceOne.setReviews(exampleReviews);

        exampleDeviceTwo.setDeviceName(DEVICENAME_2);
        exampleDeviceTwo.setDateOfRelease(DATEOFRELEASE_2);
        exampleDeviceTwo.setExterior(exampleExterior);
        exampleDeviceTwo.setHardwares(exampleHardwares);
        exampleDeviceTwo.setReviews(exampleReviews);

        exampleDevices.add(exampleDeviceOne);
        exampleDevices.add(exampleDeviceTwo);
    }

    //Device tests

    @Test
    public void checkAddDevices(){


        deviceManager.addDevices(exampleDevices);

        List<Device> retrieved = deviceManager.getAllDevices();

        assertEquals(DEVICENAME_1, retrieved.get(0).getDeviceName());
        assertEquals(DEVICENAME_2, retrieved.get(1).getDeviceName());
    }

    @Test
    public void checkFindByName(){
        deviceManager.addDevices(exampleDevices);

        List<Device> retrieved = deviceManager.findDevicesByName(DEVICENAME_1);

        assertEquals(DEVICENAME_1, retrieved.get(0).getDeviceName());
    }

    @Test
    public void checkUpdateDevices(){

        Device deviceOne = new Device();
        deviceOne.setDeviceName(DEVICENAME_1);
        deviceOne.setDateOfRelease(DATEOFRELEASE_1);

        Device deviceFour = new Device();
        deviceFour.setDeviceName(DEVICENAME_4);
        deviceFour.setDateOfRelease(DATEOFRELEASE_4);

        List<Device> oldDevices = new ArrayList<Device>();
        oldDevices.add(deviceOne);

        List<Device> newDevices = new ArrayList<Device>();
        newDevices.add(deviceFour);

        deviceManager.addDevices(oldDevices);
        deviceManager.updateDevices(oldDevices, newDevices);

        List<Device> retrieved = deviceManager.getAllDevices();

        assertEquals(DEVICENAME_4, retrieved.get(0).getDeviceName());

    }

    @Test
    public void checkDeleteDevices() {

        deviceManager.addDevices(exampleDevices);
        deviceManager.deleteDevices(exampleDevices);

        List<Device> retrieved = deviceManager.getAllDevices();

        assertEquals(0, retrieved.size());
    }

    //Hardware methods
    @Test
    public void checkAddHardware(){

        deviceManager.addHardware(exampleHardwares);
        List<Hardware> hardwares = deviceManager.getAllHardware();

        assertEquals(hardwares.size(), 1);
    }


    @Test
    public void checkUpdateHardware(){

        Hardware hardware = new Hardware();
        Hardware received;
        hardware.setProcessor("three");
        hardware.setStorage(32);
        hardware.setRam(8);

        List<Hardware> oldHardware = new ArrayList<Hardware>();
        List<Hardware> newHardware = new ArrayList<Hardware>();

        oldHardware.add(exampleHardware);
        newHardware.add(hardware);

        deviceManager.addHardware(oldHardware);
        deviceManager.updateHardware(oldHardware, newHardware);
        received = deviceManager.getAllHardware().get(0);

        assertEquals(received.getProcessor(), "three");
    }

    @Test
    public void checkDeleteHardware(){
        deviceManager.addHardware(exampleHardwares);
        deviceManager.deleteHardware(exampleHardwares);

        List<Hardware> retrieved = deviceManager.getAllHardware();

        assertEquals(retrieved.size(), 0);
    }

    @Test
    public void checkAddNewHardware(){
        deviceManager.deleteDevices(exampleDevices);
        deviceManager.addDevices(exampleDevices);
        Hardware hardware = new Hardware();
        hardware.setStorage(64);
        hardware.setRam(2);
        hardware.setProcessor("Snapdragon");

        List<Device> receivedDevice = deviceManager.getAllDevices();
        Long deviceId = receivedDevice.get(0).getId();

        deviceManager.addNewHardware(hardware, deviceId);
        List<Hardware> receivedHardware = deviceManager.getAllHardware();
        assertEquals(receivedHardware.get(0).getDevice().getDeviceName(), receivedDevice.get(0).getDeviceName());
    }

    @Test
    public void checkAddExteriors(){
        Exterior exterior = new Exterior();
        exterior.setScreenSize(5.2);
        exterior.setPhysicalButtons(false);

        List<Exterior> exteriors = new ArrayList<Exterior>();
        exteriors.add(exterior);
        deviceManager.addExteriors(exteriors);

        List<Exterior> retrieved = deviceManager.getAllExterior();
        assertEquals(retrieved.get(0).getScreenSize(), exterior.getScreenSize(),0.001);

    }

    @Test
    public void checkUpdateExteriors(){
        deviceManager.deleteDevices(exampleDevices);
        Exterior exterior = new Exterior();
        exterior.setScreenSize(5.2);
        exterior.setPhysicalButtons(false);
        exterior.setDevice(exampleDeviceOne);

        Exterior newExterior = new Exterior();
        exterior.setPhysicalButtons(true);
        exterior.setScreenSize(6.0);

        List<Exterior> oldExteriors = new ArrayList<Exterior>();
        List<Exterior> newExteriors = new ArrayList<Exterior>();
        List<Exterior> retrieved;
        oldExteriors.add(exterior);
        newExteriors.add(newExterior);

        deviceManager.addExteriors(oldExteriors);
        deviceManager.updateExteriors(oldExteriors, newExteriors);

        retrieved = deviceManager.getAllExterior();

        assertEquals(null, retrieved.get(0).getDevice());
        assertEquals(newExterior.getScreenSize(), retrieved.get(0).getScreenSize(), 0.001);
    }

    @Test
    public void checkDeleteExterior(){
        Exterior exterior = new Exterior();
        exterior.setScreenSize(5.2);
        exterior.setPhysicalButtons(false);
        exterior.setDevice(exampleDeviceOne);

        List<Exterior> exteriors = new ArrayList<Exterior>();
        exteriors.add(exterior);

        deviceManager.addExteriors(exteriors);
        deviceManager.deleteExteriors(exteriors);

        List<Exterior> retrieved = new ArrayList<Exterior>();

        assertEquals(0, retrieved.size());
    }

    @Test
    public void checkAddReview(){
        deviceManager.deleteReviews(deviceManager.getAllReviews());
        exampleDeviceOne.setReviews(null);

        List<Device> devices = new ArrayList<Device>();
        devices.add(exampleDeviceOne);

        exampleReviewOne.setDevices(devices);
        List<Review> reviews = new ArrayList<Review>();
        reviews.add(exampleReviewTwo);
        reviews.add(exampleReviewOne);
        deviceManager.addReviews(reviews);

        List<Review> retrieved;
        retrieved = deviceManager.getAllReviews();

        assertEquals(exampleDeviceOne.getDeviceName(), retrieved.get(1).getDevices().get(0).getDeviceName());
    }

    @Test
    public void checkUpdateReview(){
        deviceManager.deleteReviews(deviceManager.getAllReviews());

        List<Review> oldReview = new ArrayList<Review>();
        List<Review> newReview = new ArrayList<Review>();
        oldReview.add(exampleReviewOne);
        newReview.add(exampleReviewTwo);

        deviceManager.addReviews(oldReview);

        deviceManager.updateReviews(oldReview, newReview);

        Review retrieved = deviceManager.getAllReviews().get(0);

        assertEquals(retrieved.getTitle(), exampleReviewTwo.getTitle());

    }


}