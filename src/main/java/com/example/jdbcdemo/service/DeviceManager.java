package com.example.jdbcdemo.service;

import java.util.List;

import com.example.jdbcdemo.domain.Device;

public interface DeviceManager {
	
	public int addDevice(Device device);
	public List<Device> getAllDevices();
	public List<Device> findDevicesByName(Device device);
	public List<Device> findDevicesByScreenSize(Device device);
	public List<Device> findDevicesByDate(Device device);
	public int updateDevice(Device device, Device newDevice);
	public int removeDevicesByName(Device device);
	public void addDevices(List<Device> devices);
	public void updateDevices(List<Device> devices, List<Device> newDevices);
	public int deleteDevices(List<Device> devices);
	

}
