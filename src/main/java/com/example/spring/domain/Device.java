package com.example.spring.domain;

import java.util.Calendar;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(name = "device.all", query = "Select d from Device d"),
		@NamedQuery(name = "device.byName", query = "Select d from Device d where d.deviceName = :name"),
		//@NamedQuery(name = "device.byScreen", query = "Select d from Device d where d.screenSize = :screenSize"),
		@NamedQuery(name = "device.byDate", query = "Select d from Device d where d.dateOfRelease = :date")
})

public class Device {
	
	private Long id;
	private String deviceName;
	//private double screenSize;
	private Calendar dateOfRelease;
	private Exterior exterior;
	private List<Component> components;
	private List<Review> reviews;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(unique = true, nullable = false)
	public String getDeviceName() {
		return deviceName;
	}
	
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	/*@Column(unique = false, nullable = false)
	public double getScreenSize() {
		return screenSize;
	}
	
	public void setScreenSize(double screenSize) {
		this.screenSize = screenSize;
	}*/
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	public Calendar getDateOfRelease() {
		return dateOfRelease;
	}
	
	public void setDateOfRelease(Calendar dateOfRelease) {
		this.dateOfRelease = dateOfRelease;
	}

	@OneToOne(cascade = CascadeType.ALL)
	public Exterior getExterior() {
		return exterior;
	}

	public void setExterior(Exterior exterior) {
		this.exterior = exterior;
	}

	@OneToMany(mappedBy = "device",
			cascade = CascadeType.ALL)
	public List<Component> getComponents() {
		return components;
	}

	public void setComponents(List<Component> components) {
		this.components = components;
	}

	@ManyToMany
	@JoinTable(
			name = "Device_Review",
			joinColumns = {@JoinColumn(name = "devices_id")},
			inverseJoinColumns = {@JoinColumn(name = "reviews_id")}
	)
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
}
