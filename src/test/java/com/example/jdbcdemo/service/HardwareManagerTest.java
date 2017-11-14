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

    Device deviceOne = new Device(DEVICENAME_1, SCREENSIZE_1, DATEOFRELEASE_1);
    Hardware hardwareOne = new Hardware(DEVICENAME_1, STORAGE_1, MEMORY_1, PROCESSOR_1);

    @Before
    public void initDevice(){

        deviceManager.clearDevices();

        deviceManager.addDevice(deviceOne);
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
}
