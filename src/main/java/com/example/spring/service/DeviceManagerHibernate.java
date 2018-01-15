package com.example.spring.service;

import java.util.List;

import com.example.spring.domain.Review;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.domain.Device;

@Component
@Transactional
public class DeviceManagerHibernate implements DeviceManager {

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addDevice(Device device) {
		device.setId(null);
		sessionFactory.getCurrentSession().persist(device);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Device> getAllDevices() {
		return sessionFactory.getCurrentSession()
				.getNamedQuery("device.all").list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Device> findDevicesByName(String name) {
		return sessionFactory.getCurrentSession()
				.getNamedQuery("device.byName").setString("name", name).list();
	}

	/*@Override
	@SuppressWarnings("unchecked")
	public List<Device> findDevicesByScreenSize(double screenSize) {
		return sessionFactory.getCurrentSession()
				.getNamedQuery("device.byScreen").setDouble("screenSize", screenSize).list();
	}*/

	@Override
	public void updateDevice(Long deviceId, Device newDevice) {
		Device device = (Device) sessionFactory.getCurrentSession().get(
				Device.class, deviceId);
		device.setDateOfRelease(newDevice.getDateOfRelease());
		device.setDeviceName(newDevice.getDeviceName());
		//device.setScreenSize(newDevice.getScreenSize());

		sessionFactory.getCurrentSession().update(device);
	}

	@Override
	public void addDevices(List<Device> devices) {

		for(Device device : devices) {
			device.setId(null);
			sessionFactory.getCurrentSession().persist(device);
		}
		
	}

	@Override
	public void updateDevices(List<Device> devices, List<Device> newDevices) {

		int i = 0;
		for(Device device : devices) {
			Device oldDevice = (Device) sessionFactory.getCurrentSession().get(Device.class, device.getId());
			//oldDevice.setScreenSize(newDevices.get(i).getScreenSize());
			oldDevice.setDeviceName(newDevices.get(i).getDeviceName());
			oldDevice.setDateOfRelease(newDevices.get(i).getDateOfRelease());

			sessionFactory.getCurrentSession().update(oldDevice);
			i++;
		}
		
	}

	@Override
	public void deleteDevices(List<Device> devices) {

		for(Device device : devices) {
			sessionFactory.getCurrentSession().delete(device);
		}
	}

	@Override
	public void addReviews(List<Review> reviews){
		for(Review review : reviews) {
			review.setId(null);
			sessionFactory.getCurrentSession().persist(review);
		}
	}

	

}
