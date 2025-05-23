package com.cpd.api.dto;

public class CreateInstanceRequest {
    
    private String name;
    private int memory;       // GB of RAM
    private int cores;        // CPU nodes
    private int diskSize;     // disk size in GB
    private String diskType;  // HDD or SSD

    public CreateInstanceRequest() {

    }

    public CreateInstanceRequest(String name, int memory, int cores, int diskSize, String diskType) {
        this.name = name;
        this.memory = memory;
        this.cores = cores;
        this.diskSize = diskSize;
        this.diskType = diskType;
    }

    // Getters and Setters
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

    public int getDiskSize() {
        return diskSize;
    }

    public void setDiskSize(int diskSize) {
        this.diskSize = diskSize;
    }

    public String getDiskType() {
        return diskType;
    }

    public void setDiskType(String diskType) {
        this.diskType = diskType;
    }
}