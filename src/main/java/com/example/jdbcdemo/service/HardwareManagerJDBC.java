package com.example.jdbcdemo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.jdbcdemo.domain.Hardware;

public class HardwareManagerJDBC implements HardwareManager {

    private Connection connection;

    private PreparedStatement addHardwareStmt;
    private PreparedStatement deleteAllHardwareStmt;
    private PreparedStatement getAllHardwareStmt;
    private PreparedStatement findHardwareByDeviceNameStmt;
    private PreparedStatement findHardwareByProcessorStmt;
    private PreparedStatement updateHardwareStmt;
    private PreparedStatement deleteHardwareByDeviceNameStmt;

    ConnectionManagerJDBC conn = new ConnectionManagerJDBC();

    public HardwareManagerJDBC() {
        try{
            connection = conn.getConnection();

            addHardwareStmt = connection.prepareStatement("INSERT INTO Hardware(deviceName, storage, memory, processor) VALUES (?, ?, ?, ?)");
            deleteAllHardwareStmt = connection.prepareStatement("DELETE FROM Hardware");
            getAllHardwareStmt = connection.prepareStatement("SELECT id, deviceName, storage, memory, processor FROM Hardware");
            findHardwareByDeviceNameStmt = connection.prepareStatement ("SELECT id, deviceName, storage, memory, processor FROM Hardware WHERE deviceName = ?");
            findHardwareByProcessorStmt = connection.prepareStatement("SELECT id, deviceName, storage, memory, processor FROM Hardware WHERE processor = ?");
            updateHardwareStmt = connection.prepareStatement("UPDATE Hardware SET deviceName = ?, storage = ?, memory = ?, processor = ? WHERE deviceName = ?");
            deleteHardwareByDeviceNameStmt = connection.prepareStatement("DELETE FROM Hardware WHERE deviceName = ?");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    void clearHardware(){
        try{
            deleteAllHardwareStmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public int addHardware(Hardware hardware){

        int count = 0;

        try{
            addHardwareStmt.setString(1, hardware.getDeviceName());
            addHardwareStmt.setInt(2, hardware.getStorage());
            addHardwareStmt.setInt(3, hardware.getMemory());
            addHardwareStmt.setString(4, hardware.getProcessor());

            count = addHardwareStmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Hardware> getAllHardware() {

        List<Hardware> hardwares = new ArrayList<>();

        try{
            ResultSet rs = getAllHardwareStmt.executeQuery();
            hardwares = getFromResultSet(rs);

        }catch (SQLException e){
            e.printStackTrace();
        }

        return hardwares;
    }

    @Override
    public List<Hardware> findHardwareByDeviceName(Hardware hardware) {

        List<Hardware> hardwares = new ArrayList<>();

        try {
            findHardwareByDeviceNameStmt.setString(1, hardware.getDeviceName());
            hardwares = getFromResultSet(findHardwareByDeviceNameStmt.executeQuery());

        }catch (SQLException e){
            e.printStackTrace();
        }

        return  hardwares;
    }

    @Override
    public List<Hardware> findHardwareByProcessor(Hardware hardware){

        List<Hardware> hardwares = new ArrayList<>();

        try {
            findHardwareByProcessorStmt.setString(1, hardware.getProcessor());
            hardwares = getFromResultSet(findHardwareByProcessorStmt.executeQuery());

        }catch (SQLException e){
            e.printStackTrace();
        }

        return hardwares;
    }

    @Override
    public int updateHardware(Hardware hardware, Hardware newHardwares) {
        int count = 0;

        try {
            updateHardwareStmt.setString(1, newHardwares.getDeviceName());
            updateHardwareStmt.setInt(2, newHardwares.getStorage());
            updateHardwareStmt.setInt(3, newHardwares.getMemory());
            updateHardwareStmt.setString(4, newHardwares.getProcessor());
            updateHardwareStmt.setString(5, hardware.getDeviceName());
            count = updateHardwareStmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public int removeHardwaresByName(Hardware hardware) {
        int count = 0;

        try {
            deleteHardwareByDeviceNameStmt.setString(1, hardware.getDeviceName());
            count = deleteAllHardwareStmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public void addHardwares(List<Hardware> hardwares) {

        try {
            connection.setAutoCommit(false);
            for(Hardware hardware : hardwares){
                addHardwareStmt.setString(1, hardware.getDeviceName());
                addHardwareStmt.setInt(2, hardware.getStorage());
                addHardwareStmt.setInt(3, hardware.getMemory());
                addHardwareStmt.setString(4, hardware.getProcessor());
                addHardwareStmt.executeUpdate();
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
    public void updateHardwares(List<Hardware> hardwares, List<Hardware> newHardwares){

        try{
            int i = -1;

            if(hardwares.size() != newHardwares.size())
                throw new SQLException();

            connection.setAutoCommit(false);
            for(Hardware hardware : newHardwares){
                updateHardwareStmt.setString(1, hardware.getDeviceName());
                updateHardwareStmt.setInt(2, hardware.getStorage());
                updateHardwareStmt.setInt(3, hardware.getMemory());
                updateHardwareStmt.setString(4, hardware.getProcessor());
                updateHardwareStmt.setString(5, hardwares.get(++i).getDeviceName());
                updateHardwareStmt.executeUpdate();
            }
            connection.commit();

        }catch (SQLException exception){
            try {
                connection.rollback();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public int deleteHardwares(List<Hardware> hardwares) {
        int count = 0;

        try {
            connection.setAutoCommit(false);

            for(Hardware hardware : hardwares){
                deleteHardwareByDeviceNameStmt.setString(1, hardware.getDeviceName());
                count += deleteHardwareByDeviceNameStmt.executeUpdate();
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

    private List<Hardware> getFromResultSet (ResultSet rs){
        List<Hardware> hardwares = new ArrayList<>();

        try {

            while (rs.next()) {
                Hardware h = new Hardware();
                h.setId(rs.getInt("id"));
                h.setDeviceName(rs.getString("deviceName"));
                h.setMemory(rs.getInt("memory"));
                h.setStorage(rs.getInt("storage"));
                h.setProcessor(rs.getString("processor"));
                hardwares.add(h);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return hardwares;
    }
}
