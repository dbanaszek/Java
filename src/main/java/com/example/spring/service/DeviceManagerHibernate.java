package com.example.spring.service;

import java.util.ArrayList;
import java.util.List;

import com.example.spring.domain.Exterior;
import com.example.spring.domain.Hardware;
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

	//Device methods

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

	@Override
	public void addDevices(List<Device> devices) {

		for(Device device : devices) {
			sessionFactory.getCurrentSession().persist(device);
		}
		
	}

	@Override
	public void updateDevices(List<Device> devices, List<Device> newDevices) {

		int i = 0;
		for(Device device : devices) {
			Device oldDevice = (Device) sessionFactory.getCurrentSession().get(Device.class, device.getId());
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

	//Hardware methods

	@Override
	public void addHardware(List<Hardware> hardware) {
		for(Hardware parts : hardware) {
			parts.setId(null);
			sessionFactory.getCurrentSession().persist(parts);
		}
	}

	@Override
	public void updateHardware(List<Hardware> oldHardware ,List<Hardware> newHardware) {
		
		int i = 0;
		for(Hardware hardware : oldHardware) {
			hardware.setProcessor(newHardware.get(i).getProcessor());
			hardware.setRam(newHardware.get(i).getRam());
			hardware.setStorage(newHardware.get(i).getStorage());
			hardware.setDevice(newHardware.get(i).getDevice());
			sessionFactory.getCurrentSession().update(hardware);
			i++;
		}
	}

	@Override
	public void deleteHardware(List<Hardware> hardware) {

		for (Hardware deleted : hardware){
			sessionFactory.getCurrentSession().delete(deleted);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Hardware> getAllHardware(){
		return sessionFactory.getCurrentSession().getNamedQuery("hardware.all").list();
	}

	@Override
	public void addNewHardware(Hardware hardware, Long deviceId) {
		Device device = (Device) sessionFactory.getCurrentSession().get(Device.class, deviceId);
		hardware.setDevice(device);
		sessionFactory.getCurrentSession().persist(hardware);
	}

	//Exterior methods
	@Override
	public void addExteriors(List<Exterior> exteriors) {
		for(Exterior exterior : exteriors) {
			exterior.setId(null);
			sessionFactory.getCurrentSession().persist(exterior);
		}
	}

	@Override
	public void updateExteriors(List<Exterior> oldExteriors, List<Exterior> newExteriors) {

		int i = 0;
		for(Exterior exterior : oldExteriors) {
			exterior.setDevice(newExteriors.get(i).getDevice());
			exterior.setPhysicalButtons(newExteriors.get(i).getPhysicalButtons());
			exterior.setScreenSize(newExteriors.get(i).getScreenSize());
			sessionFactory.getCurrentSession().update(exterior);
			i++;
		}
	}

	@Override
	public void deleteExteriors(List<Exterior> exteriors) {
		for(Exterior exterior : exteriors) {
			sessionFactory.getCurrentSession().delete(exterior);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Exterior> getAllExterior() {
		return sessionFactory.getCurrentSession().getNamedQuery("exterior.all").list();
	}


	//Review methods
	@Override
	public void addReviews(List<Review> reviews){
		for(Review review : reviews) {
			review.setId(null);
			sessionFactory.getCurrentSession().persist(review);
		}
	}

	@Override
	public void updateReviews(List<Review> oldReviews, List<Review> newReviews) {

		int i = 0;
		for(Review review : oldReviews) {
			review.setDevices(newReviews.get(i).getDevices());
			review.setDeviceScore(newReviews.get(i).getDeviceScore());
			review.setMaxScore(newReviews.get(i).getMaxScore());
			review.setTitle(newReviews.get(i).getTitle());
			sessionFactory.getCurrentSession().update(review);
			i++;
		}
	}

	@Override
	public void deleteReviews(List<Review> reviews) {
		for(Review review : reviews) {
			sessionFactory.getCurrentSession().delete(review);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Review> getAllReviews() {
		return sessionFactory.getCurrentSession().getNamedQuery("review.all").list();
	}

	@Override
	public void addReviewToDevice(Review review, Long deviceId) {
		Device device = (Device) sessionFactory.getCurrentSession().get(Device.class, deviceId);
		List<Review> reviews = new ArrayList<Review>();
		List<Device> devices = new ArrayList<Device>();

		try{
			review.getDevices().add(device);
		}catch (Exception e){
			devices.add(device);
		}

		try{
			device.getReviews().add(review);
		}catch (Exception e) {
			reviews.add(review);
			device.setReviews(reviews);
		}
		sessionFactory.getCurrentSession().persist(review);
		sessionFactory.getCurrentSession().update(device);
	}

}
