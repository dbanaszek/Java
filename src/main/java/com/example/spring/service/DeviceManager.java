package com.example.spring.service;

import java.util.List;

import com.example.spring.domain.Exterior;
import com.example.spring.domain.Hardware;
import com.example.spring.domain.Device;
import com.example.spring.domain.Review;

interface DeviceManager {

	//Device table methods
	List<Device> getAllDevices();
	List<Device> findDevicesByName(String name);
	void addDevices(List<Device> devices);
	void updateDevices(List<Device> devices, List<Device> newDevices);
	void deleteDevices(List<Device> devices);

	//Hardware table methods
	void addHardware(List<Hardware> hardware);
	void updateHardware(List<Hardware> oldHardware, List<Hardware> hardware);
	void deleteHardware(List<Hardware> hardware);
	List<Hardware> getAllHardware();
	void addNewHardware(Hardware hardware, Long deviceId); //Adds new Hardware to Device


	//Exterior table methods
	void addExteriors(List<Exterior> exteriors);
	void updateExteriors(List<Exterior> oldExteriors, List<Exterior> newExteriors);
	void deleteExteriors(List<Exterior> exteriors);
	List<Exterior> getAllExterior();

	//Review table methods
	void addReviews(List<Review> reviews);
	void updateReviews(List<Review> oldReviews, List<Review> newReviews);
	void deleteReviews(List<Review> reviews);
	List<Review> getAllReviews();
}
