package com.cpd.disk.model;

public class Disk {
    
    private String id;
    private int size;
    private String type;
    private String status;

    public Disk() {
        
    }

    public Disk(String id, int size, String type, String status) {
        this.id = id;
        this.size = size;
        this.type = type;
        this.status = status;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}