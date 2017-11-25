package com.example.jdbcdemo.service;

import java.util.List;

import com.example.jdbcdemo.domain.Hardware;

public interface HardwareManager {
    public int addHardware(Hardware hardware);
    public List<Hardware> getAllHardware();
    public List<Hardware> findHardwareByDeviceName(Hardware hardware);
    public List<Hardware> findHardwareByProcessor(Hardware hardware);
    public int updateHardware(Hardware hardware, Hardware newHardwares);
    public int removeHardwaresByName(Hardware hardware);
    public void addHardwares(List<Hardware> hardwares);
    public void updateHardwares(List<Hardware> hardwares, List<Hardware> newHardwares);
    public int deleteHardwares(List<Hardware> hardware);
}
