package com.example.jdbcdemo.domain;

public class DeviceComponent {

    private long id;



    private String devicename;
    private int storage;
    private int memory;
    private String processor;

    public DeviceComponent(){

    }
    public DeviceComponent(String devicename, int storage, int memory, String processor){
        super();
        this.devicename = devicename;
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
    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
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
