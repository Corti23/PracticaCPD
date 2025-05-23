package com.cpd.api.services;

import com.cpd.api.dto.CreateInstanceRequest;
import com.cpd.api.dto.InstanceResponse;
import com.cpd.api.entity.DiskEntity;
import com.cpd.api.entity.InstanceEntity;
import com.cpd.api.model.Disk;
import com.cpd.api.repository.DiskRepository;
import com.cpd.api.repository.InstanceRepository;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;

@Service
public class InstanceService {

    private final InstanceRepository instanceRepository;
    private final DiskRepository diskRepository;

    public InstanceService(InstanceRepository instanceRepository, DiskRepository diskRepository) {
        this.instanceRepository = instanceRepository;
        this.diskRepository = diskRepository;
    }

    private Disk convertToDiskDto(DiskEntity entity) {
        return new Disk(entity.getId(), entity.getSize(), entity.getType(), entity.getStatus());
    }

    public String createInstance(CreateInstanceRequest request) {
        // create associated disk
        String diskId = UUID.randomUUID().toString();
        DiskEntity disk = new DiskEntity(diskId, request.getDiskSize(), request.getDiskType(), "REQUESTED");
        diskRepository.save(disk);

        // Create instace
        String instanceId = UUID.randomUUID().toString();
        InstanceEntity instance = new InstanceEntity(instanceId, request.getName(), request.getMemory(), request.getCores(), null, "BUILDING_DISK", disk);
        instanceRepository.save(instance);

        // Send to disk queue
        Map<String, Object> diskMsg = new HashMap<>();
        diskMsg.put("diskId", diskId);
        diskMsg.put("size", request.getDiskSize());
        diskMsg.put("type", request.getDiskType());
        diskMsg.put("instanceId", instanceId); // opcional


        return instanceId;
    }

    public boolean deleteInstance(String instanceId) {
        Optional<InstanceEntity> optional = instanceRepository.findById(instanceId);

        if (optional.isEmpty()) {
            return false;
        }

        InstanceEntity instance = optional.get();
        instanceRepository.deleteById(instanceId);

        // Change the associated disk status to UNASSIGNED
        DiskEntity disk = instance.getDisk();
        disk.setStatus("UNASSIGNED");
        diskRepository.save(disk);

        return true;
    }

    public List<InstanceResponse> getAllInstances() {
        List<InstanceEntity> list = instanceRepository.findAll();
        List<InstanceResponse> result = new ArrayList<>();

        for (InstanceEntity i : list) {
            Disk diskDto = convertToDiskDto(i.getDisk());

            result.add(new InstanceResponse(
                    i.getId(),
                    i.getName(),
                    i.getMemory(),
                    i.getCores(),
                    i.getIp(),
                    i.getStatus(),
                    diskDto
            ));
        }

        return result;
    }

    public InstanceResponse getInstance(String id) {
        return instanceRepository.findById(id)
                .map(i -> new InstanceResponse(
                        i.getId(), i.getName(), i.getMemory(), i.getCores(), i.getIp(),
                        i.getStatus(), convertToDiskDto(i.getDisk())))
                .orElse(null);
    }

    public void updateDiskStatus(String diskId, String status) {
        diskRepository.findById(diskId).ifPresent(disk -> {
            disk.setStatus(status);
            diskRepository.save(disk);

            if ("ASSIGNED".equals(status)) {
                // Launch instance if one is associated
                instanceRepository.findByDisk_Id(diskId)
                .ifPresentOrElse(
                    this::startInstanceCreation,
                    () -> System.err.println("No se encontró instancia con disco " + diskId)
                );
            }
        });
    }

    public void updateInstanceStatus(String instanceId, String status, String ip) {
        instanceRepository.findById(instanceId).ifPresent(instance -> {
            instance.setStatus(status);

            if (ip != null) {
                instance.setIp(ip);
            }

            instanceRepository.save(instance);
        });
    }

    private void startInstanceCreation(InstanceEntity instance) {
        Map<String, Object> msg = new HashMap<>();
        msg.put("instanceId", instance.getId());
        msg.put("name", instance.getName());
        msg.put("memory", instance.getMemory());
        msg.put("cores", instance.getCores());
        msg.put("diskId", instance.getDisk().getId());

        instance.setStatus("STARTING");
        instanceRepository.save(instance);
    }

    public Page<InstanceResponse> getAllInstancesFiltered(String statusFilter, Pageable pageable) {
        Page<InstanceEntity> page = (statusFilter == null) ? instanceRepository.findAll(pageable) : instanceRepository.findByStatusIgnoreCase(statusFilter, pageable);
    
        return page.map(i -> new InstanceResponse(
                i.getId(),
                i.getName(),
                i.getMemory(),
                i.getCores(),
                i.getIp(),
                i.getStatus(),
                new Disk(i.getDisk().getId(), i.getDisk().getSize(), i.getDisk().getType(), i.getDisk().getStatus())
        ));
    }
}