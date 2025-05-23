package com.cpd.api.model;

public class Instance {
    
    private String id;
    private String name;
    private int memory;      // GB
    private int cores;
    private String ip;       // assigns automatically
    private Disk disk;       // asociated disk
    private String status;   // BUILDING_DISK, STARTING, ...

    public Instance() {
    }

    public Instance(String id, String name, int memory, int cores, String ip, Disk disk, String status) {

        this.id = id;
        this.name = name;
        this.memory = memory;
        this.cores = cores;
        this.ip = ip;
        this.disk = disk;
        this.status = status;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Disk getDisk() {
        return disk;
    }

    public void setDisk(Disk disk) {
        this.disk = disk;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}