package com.cpd.api.controller;

import com.cpd.api.entity.DiskEntity;
import com.cpd.api.repository.DiskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/disks")
public class DiskController {
    
    private final DiskRepository diskRepository;

    public DiskController(DiskRepository diskRepository) {
        this.diskRepository = diskRepository;
    }

    @PostMapping
    public ResponseEntity<?> createDisk(@RequestBody DiskEntity newDisk) {
        if (newDisk.getSize() <= 0 || newDisk.getType() == null) {
            return ResponseEntity.badRequest().body("Size and type are mandatory.");
        }

        String id = UUID.randomUUID().toString();

        newDisk.setId(id);
        newDisk.setStatus("REQUESTED");
        diskRepository.save(newDisk);

        URI location = URI.create("/disks/" + id);

        return ResponseEntity.created(location).body(newDisk);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDisk(@PathVariable String id) {
        Optional<DiskEntity> optionalDisk = diskRepository.findById(id);

        if (optionalDisk.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        DiskEntity disk = optionalDisk.get();

        if ("ASSIGNED".equalsIgnoreCase(disk.getStatus())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("It is not possible to delete an ASSIGNED disk.");
        }

        diskRepository.deleteById(id);

        return ResponseEntity.ok("Disk " + id + " deleted.");
    }

    @GetMapping
    public Page<DiskEntity> getAllDisks(@RequestParam(required = false) String status, Pageable pageable) {
        if (status != null) {
            return diskRepository.findByStatusIgnoreCase(status, pageable);
        }

        return diskRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiskEntity> getDiskById(@PathVariable String id) {
        return diskRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}