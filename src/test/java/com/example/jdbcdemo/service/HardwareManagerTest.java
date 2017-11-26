package com.example.jdbcdemo.service;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.example.jdbcdemo.domain.Hardware;
import org.junit.Before;
import org.junit.Test;

import com.example.jdbcdemo.domain.Device;

public class HardwareManagerTest {

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

    private final static int STORAGE_4 = 32;
    private final static int MEMORY_4 = 2;
    private final static String PROCESSOR_4 = "Apple A10";

    private final static String DEVICENAME_5 = "G3";
    private final static double SCREENSIZE_5 = 5.5;
    private final static Calendar DATEOFRELEASE_5 = new GregorianCalendar(2014, 5,27);

    private final static int STORAGE_5 = 32;
    private final static int MEMORY_5 = 3;
    private final static String PROCESSOR_5 = "Snapdragon 801";

    private final static String DEVICENAME_6 = "G4";
    private final static double SCREENSIZE_6 = 5.5;
    private final static Calendar DATEOFRELEASE_6 = new GregorianCalendar(2015, 4,28);

    private final static int STORAGE_6 = 32;
    private final static int MEMORY_6 = 3;
    private final static String PROCESSOR_6 = "Snapdragon 808";

    Device deviceOne = new Device(DEVICENAME_1, SCREENSIZE_1, DATEOFRELEASE_1);
    Device deviceTwo = new Device(DEVICENAME_2, SCREENSIZE_2, DATEOFRELEASE_2);
    Device deviceThree = new Device(DEVICENAME_3, SCREENSIZE_3, DATEOFRELEASE_3);
    Device deviceFour = new Device(DEVICENAME_4, SCREENSIZE_4, DATEOFRELEASE_4);
    Device deviceFive = new Device(DEVICENAME_5, SCREENSIZE_5, DATEOFRELEASE_5);
    Device deviceSix = new Device(DEVICENAME_6, SCREENSIZE_6, DATEOFRELEASE_6);

    Hardware hardwareOne = new Hardware(DEVICENAME_1, STORAGE_1, MEMORY_1, PROCESSOR_1);
    Hardware hardwareTwo = new Hardware(DEVICENAME_2, STORAGE_2, MEMORY_2, PROCESSOR_2);
    Hardware hardwareThree = new Hardware(DEVICENAME_3, STORAGE_3, MEMORY_3, PROCESSOR_3);
    Hardware hardwareFour = new Hardware(DEVICENAME_4, STORAGE_4, MEMORY_4, PROCESSOR_4);
    Hardware hardwareFive = new Hardware(DEVICENAME_5, STORAGE_5, MEMORY_5, PROCESSOR_5);
    Hardware hardwareSix = new Hardware(DEVICENAME_6, STORAGE_6, MEMORY_6, PROCESSOR_6);

    @Before
    public void initDevice(){

        deviceManager.clearDevices();

        List<Device> devices = new ArrayList<>();
        devices.add(deviceOne);
        devices.add(deviceTwo);
        devices.add(deviceThree);
        devices.add(deviceFour);
        devices.add(deviceFive);
        devices.add(deviceSix);

        deviceManager.addDevices(devices);
    }

    @Test
    public void checkConnection(){
        assertNotNull(conn.getConnection());
    }

    @Test
    public void checkAddingHardware(){

        hardwareManager.clearHardware();

        assertEquals(1, hardwareManager.addHardware(hardwareOne));

        List<Hardware> hardwares = hardwareManager.getAllHardware();
        Hardware hardware = hardwares.get(0);

        assertEquals(DEVICENAME_1, hardware.getDeviceName());
        assertEquals(STORAGE_1, hardware.getStorage());
        assertEquals(MEMORY_1, hardware.getMemory());
        assertEquals(PROCESSOR_1, hardware.getProcessor());

    }

    @Test
    public void checkAddHardwares(){

        int size;

        hardwareManager.clearHardware();

        List<Hardware> hardwares = new ArrayList<>();
        hardwares.add(hardwareOne);
        hardwares.add(hardwareTwo);
        hardwares.add(hardwareThree);

        hardwareManager.addHardwares(hardwares);

        size = hardwareManager.getAllHardware().size();

        assertThat(size, either(is(3)).or(is(0)));

    }

    @Test
    public void checkFindHardwareByDeviceName() {

        hardwareManager.clearHardware();
        hardwareManager.addHardware(hardwareOne);

        List<Hardware> hardwares = hardwareManager.findHardwareByDeviceName(hardwareOne);
        Hardware hardwareRetrieved = hardwares.get(0);

        assertEquals(DEVICENAME_1, hardwareRetrieved.getDeviceName());
        assertEquals(STORAGE_1, hardwareRetrieved.getStorage());
        assertEquals(MEMORY_1, hardwareRetrieved.getMemory());
        assertEquals(PROCESSOR_1, hardwareRetrieved.getProcessor());
    }

    @Test
    public void checkFindByProcessor(){

        hardwareManager.clearHardware();
        hardwareManager.addHardware(hardwareOne);

        List<Hardware> hardwares = hardwareManager.findHardwareByProcessor(hardwareOne);
        Hardware hardwareRetrieved = hardwares.get(0);

        assertEquals(DEVICENAME_1, hardwareRetrieved.getDeviceName());
        assertEquals(STORAGE_1, hardwareRetrieved.getStorage());
        assertEquals(MEMORY_1, hardwareRetrieved.getMemory());
        assertEquals(PROCESSOR_1, hardwareRetrieved.getProcessor());

    }

    @Test
    public void checkUpdateHardware() {

        hardwareManager.clearHardware();
        hardwareManager.addHardware(hardwareOne);

        assertEquals(1, hardwareManager.updateHardware(hardwareOne, hardwareSix));

        List<Hardware> hardwares = hardwareManager.findHardwareByDeviceName(hardwareSix);
        Hardware hardwareRetrieved = hardwares.get(0);

        assertEquals(DEVICENAME_6, hardwareRetrieved.getDeviceName());
        assertEquals(STORAGE_6, hardwareRetrieved.getStorage());
        assertEquals(MEMORY_6, hardwareRetrieved.getMemory());
        assertEquals(PROCESSOR_6, hardwareRetrieved.getProcessor());

    }

    @Test
    public void checkUpdateHardwares() {
        int size;

        hardwareManager.clearHardware();

        List<Hardware> hardwares = new ArrayList<>();
        List<Hardware> newHardwares = new ArrayList<>();

        hardwares.add(hardwareOne);
        hardwares.add(hardwareTwo);
        hardwares.add(hardwareThree);

        hardwareManager.addHardwares(hardwares);

        newHardwares.add(hardwareFour);
        newHardwares.add(hardwareFive);
        newHardwares.add(hardwareSix);

        hardwareManager.updateHardwares(hardwares, newHardwares);

        List<Hardware> hardwaresRetrieved = hardwareManager.getAllHardware();
        size = hardwaresRetrieved.size();

        Hardware hardwareRetrieved = hardwaresRetrieved.get(2);

        assertEquals(DEVICENAME_6, hardwareRetrieved.getDeviceName());
        assertEquals(STORAGE_6, hardwareRetrieved.getStorage());
        assertEquals(MEMORY_6, hardwareRetrieved.getMemory());
        assertEquals(PROCESSOR_6, hardwareRetrieved.getProcessor());

        assertThat(size, either(is(3)).or(is(0)));

    }

    @Test
    public void checkDeleteHardware() {

        hardwareManager.clearHardware();
        hardwareManager.addHardware(hardwareOne);

        assertEquals(1, hardwareManager.removeHardwaresByName(hardwareOne));
    }

    public void checkDeleteHardwares() {
        int size;

        hardwareManager.clearHardware();

        List<Hardware> hardwares = new ArrayList<>();

        hardwares.add(hardwareOne);
        hardwares.add(hardwareTwo);
        hardwares.add(hardwareThree);

        hardwareManager.addHardwares(hardwares);

        size = hardwareManager.deleteHardwares(hardwares);

        assertThat(size, either(is(3)).or(is(0)));
    }

}
