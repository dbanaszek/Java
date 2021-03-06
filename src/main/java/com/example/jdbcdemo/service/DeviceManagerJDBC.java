package com.example.jdbcdemo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.*;

import com.example.jdbcdemo.domain.Device;

public class DeviceManagerJDBC implements DeviceManager {

	private Connection connection;

	private PreparedStatement addDeviceStmt;
	private PreparedStatement getAllDevicesStmt;
	private PreparedStatement deleteAllDevicesStmt;
	private PreparedStatement deleteDeviceByNameStmt;
	private PreparedStatement findDeviceByNameStmt;
	private PreparedStatement findDeviceByScreenSizeStmt;
	private PreparedStatement findDeviceByDate;
	private PreparedStatement updateDeviceByNameStmt;

	ConnectionManagerJDBC conn = new ConnectionManagerJDBC();

	public DeviceManagerJDBC() {
		try {
			connection = conn.getConnection();

			addDeviceStmt = connection
					.prepareStatement("INSERT INTO Device (deviceName, screenSize, dateOfRelease) VALUES (?, ?, ?)");
			deleteAllDevicesStmt = connection
					.prepareStatement("DELETE FROM Device");
			getAllDevicesStmt = connection
					.prepareStatement("SELECT id, deviceName, screenSize, dateOfRelease FROM Device");
			deleteDeviceByNameStmt = connection
					.prepareStatement("DELETE FROM Device WHERE deviceName = ?");
			findDeviceByNameStmt = connection
					.prepareStatement("SELECT id, deviceName, screenSize, dateOfRelease FROM Device WHERE deviceName = ?");
			findDeviceByScreenSizeStmt = connection
					.prepareStatement("SELECT id, deviceName, screenSize, dateOfRelease FROM Device WHERE ScreenSize = ?");
			findDeviceByDate = connection
					.prepareStatement("SELECT id, deviceName, screenSize, dateOfRelease FROM Device WHERE dateOfRelease = ?");
			updateDeviceByNameStmt = connection
					.prepareStatement("UPDATE Device SET deviceName = ?, screenSize = ?, dateOfRelease = ? WHERE deviceName = ?");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void clearDevices() {
		try {
			deleteAllDevicesStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int addDevice(Device device) {
		int count = 0;

		try {
			addDeviceStmt.setString(1, device.getDeviceName());
			addDeviceStmt.setDouble(2, device.getScreenSize());
			addDeviceStmt.setDate(3, new Date(device.getDateOfRelease().getTimeInMillis()));

			count = addDeviceStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<Device> getAllDevices() {
		List<Device> devices = new ArrayList<>();

		try {
			ResultSet rs = getAllDevicesStmt.executeQuery();
			devices = getFromResultSet(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return devices;
	}

	@Override
	public List<Device> findDevicesByName(Device device){
		List<Device> devices = new ArrayList<>();

		try{
			findDeviceByNameStmt.setString(1, device.getDeviceName());
			devices = getFromResultSet(findDeviceByNameStmt.executeQuery());

		}catch (SQLException e){
			e.printStackTrace();
		}
		return devices;
	}

	@Override
	public  List<Device> findDevicesByScreenSize(Device device){
		List<Device> devices = new ArrayList<>();

		try{
			findDeviceByScreenSizeStmt.setDouble(1, device.getScreenSize());
			devices = getFromResultSet(findDeviceByScreenSizeStmt.executeQuery());

		}catch (SQLException e){
			e.printStackTrace();
		}
		return devices;
	}

	@Override
	public List<Device> findDevicesByDate(Device device){
		List<Device> devices = new ArrayList<>();

		try{
			findDeviceByDate.setDate(1, new Date(device.getDateOfRelease().getTimeInMillis()));
			devices = getFromResultSet(findDeviceByDate.executeQuery());

		}catch (SQLException e){
			e.printStackTrace();
		}
		return  devices;
	}

	@Override
	public int updateDevice(Device device, Device newDevice){
		int count = 0;

		try{
			updateDeviceByNameStmt.setString(1, newDevice.getDeviceName());
			updateDeviceByNameStmt.setDouble(2, newDevice.getScreenSize());
			updateDeviceByNameStmt.setDate(3, new Date(newDevice.getDateOfRelease().getTimeInMillis()));
			updateDeviceByNameStmt.setString(4, device.getDeviceName());
			count = updateDeviceByNameStmt.executeUpdate();

		}catch (SQLException e){
			e.printStackTrace();
		}

		return count;
	}

	@Override
	public int removeDevicesByName(Device device){
		int count = 0;

		try {
			deleteDeviceByNameStmt.setString(1, device.getDeviceName());
			count = deleteDeviceByNameStmt.executeUpdate();

		}catch (SQLException e){
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public void addDevices(List<Device> devices){

		try {
			connection.setAutoCommit(false);
			for (Device device : devices) {
				addDeviceStmt.setString(1, device.getDeviceName());
				addDeviceStmt.setDouble(2, device.getScreenSize());
				addDeviceStmt.setDate(3, new Date(device.getDateOfRelease().getTimeInMillis()));
				addDeviceStmt.executeUpdate();
			}
			connection.commit();
						
			} catch (SQLException exception) {
						
				try {
					connection.rollback();
					} catch (SQLException e) {
						e.printStackTrace();
						//!!!! ALARM
					}
			}
	}

	@Override
	public void updateDevices(List<Device> devices, List<Device> newDevices){

		try{
			int i = -1;

			connection.setAutoCommit(false);
			if(devices.size() != newDevices.size())
				throw new SQLException();

			for(Device device : newDevices){
				updateDeviceByNameStmt.setString(1, device.getDeviceName());
				updateDeviceByNameStmt.setDouble(2, device.getScreenSize());
				updateDeviceByNameStmt.setDate(3, new Date(device.getDateOfRelease().getTimeInMillis()));
				updateDeviceByNameStmt.setString(4, devices.get(++i).getDeviceName());
				updateDeviceByNameStmt.executeUpdate();
			}
			connection.commit();

		}catch (SQLException exception){

			try{
				connection.rollback();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public int deleteDevices(List<Device> devices){
		int count = 0;

		try{

			connection.setAutoCommit(false);

			for(Device device : devices){
				deleteDeviceByNameStmt.setString(1, device.getDeviceName());
				count += deleteDeviceByNameStmt.executeUpdate();

			}
			connection.commit();

		}catch (SQLException exception){

			try{
				connection.rollback();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		return count;
	}

	private List<Device> getFromResultSet (ResultSet rs){
		List<Device> devices = new ArrayList<>();

		try {

			while (rs.next()) {
				Device d = new Device();
				Calendar c = new GregorianCalendar();
				d.setId(rs.getInt("id"));
				d.setDeviceName(rs.getString("deviceName"));
				d.setScreenSize(rs.getDouble("screenSize"));
				c.setTime(rs.getDate("dateOfRelease"));
				d.setDateOfRelease(c);
				devices.add(d);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}

		return devices;
	}

}
