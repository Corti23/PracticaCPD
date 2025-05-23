package com.cpd.api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "disks")
public class DiskEntity {
    
    @Id
    private String id;

    @Column(nullable = false)
    private int size;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String status;

    public DiskEntity() {
        
    }

    public DiskEntity(String id, int size, String type, String status) {
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