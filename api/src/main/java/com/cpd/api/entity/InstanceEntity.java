package com.cpd.api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "instances")
public class InstanceEntity {
    
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int memory;

    @Column(nullable = false)
    private int cores;

    private String ip;

    @Column(nullable = false)
    private String status;

    // Relation with disk
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "disk_id", nullable = false)
    private DiskEntity disk;

    public InstanceEntity() {

    }

    public InstanceEntity(String id, String name, int memory, int cores, String ip, String status, DiskEntity disk) {
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

    public DiskEntity getDisk() {
        return disk;
    }

    public void setDisk(DiskEntity disk) {
        this.disk = disk;
    }
}