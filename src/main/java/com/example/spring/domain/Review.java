package com.example.spring.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Review {

    private Long id;
    private String title;
    private int maxScore;
    private int deviceScore;
    private List<Device> devices;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column//(nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column
    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    @Column
    public int getDeviceScore() {
        return deviceScore;
    }

    public void setDeviceScore(int deviceScore) {
        this.deviceScore = deviceScore;
    }

    @ManyToMany(mappedBy = "reviews")
    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
