package com.cpd.api.dto;

import com.cpd.api.model.Disk;

public class InstanceResponse {
    
    private String id;
    private String name;
    private int memory;
    private int cores;
    private String ip;
    private String status;
    private Disk disk;

    public InstanceResponse() {
        
    }

    public InstanceResponse(String id, String name, int memory, int cores, String ip, String status, Disk disk) {
        this.id = id;
        this.name = name;
        this.memory = memory;
        this.cores = cores;
        this.ip = ip;
        this.status = status;
        this.disk = disk;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Disk getDisk() {
        return disk;
    }

    public void setDisk(Disk disk) {
        this.disk = disk;
    }
}
