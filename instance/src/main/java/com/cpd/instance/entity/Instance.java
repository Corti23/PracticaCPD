package com.cpd.instance.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "instances")
public class Instance {

    @Id
    private String id; // UUID brought from the API

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int memory;

    @Column(nullable = false)
    private int cores;
    
    @Column
    private String ip;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String diskId;

    public Instance(String id, String name, int memory, int cores, String ip, String status, String diskId) {
        this.id = id;
        this.name = name;
        this.memory = memory;
        this.cores = cores;
        this.ip = ip;
        this.status = status;
        this.diskId = diskId;
    }

    public Instance() {
        
    }
}