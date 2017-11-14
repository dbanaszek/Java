package com.example.jdbcdemo.domain;

public class Hardware {

    private long id;



    private String deviceName;
    private int storage;
    private int memory;
    private String processor;

    public Hardware(){

    }
    public Hardware(String deviceName, int storage, int memory, String processor){
        super();
        this.deviceName = deviceName;
        this.storage = storage;
        this.memory = memory;
        this. processor = processor;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }
}
