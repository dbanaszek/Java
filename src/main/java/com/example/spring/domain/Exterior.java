package com.example.spring.domain;

import javax.persistence.*;

@Entity
public class Exterior {

    private Long id;
    private double screenSize;
    private boolean physicalButtons;
    private Device device;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    @Column(nullable = false)
    public boolean isPhysicalButtons() {
        return physicalButtons;
    }

    public void setPhysicalButtons(boolean physicalButtons) {
        this.physicalButtons = physicalButtons;
    }

    @OneToOne(mappedBy = "exterior",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}