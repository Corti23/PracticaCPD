package com.cpd.api.controller;

import com.cpd.api.dto.CreateInstanceRequest;
import com.cpd.api.dto.InstanceResponse;
import com.cpd.api.services.InstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/instances")
public class InstanceController {
    
    @Autowired
    private InstanceService instanceService;

    @PostMapping
    public ResponseEntity<String> createInstance(@RequestBody CreateInstanceRequest request) {
        String instanceId = instanceService.createInstance(request);
        
        return ResponseEntity.ok("Instance request received. ID: " + instanceId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstanceResponse> getInstance(@PathVariable String id) {
        InstanceResponse instance = instanceService.getInstance(id);

        if (instance != null) {
            return ResponseEntity.ok(instance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public Page<InstanceResponse> getAllInstances(@RequestParam(required = false) String status, Pageable pageable) {
        return instanceService.getAllInstancesFiltered(status, pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInstance(@PathVariable String id) {
        boolean deleted = instanceService.deleteInstance(id);

        if (deleted) {
            return ResponseEntity.ok("Instance " + id + " deleted.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}