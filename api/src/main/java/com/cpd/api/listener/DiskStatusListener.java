package com.cpd.api.listener;

import com.cpd.api.entity.DiskEntity;
import com.cpd.api.repository.DiskRepository;
import com.cpd.api.services.InstanceService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DiskStatusListener {
    
    private final DiskRepository diskRepository;
    private final InstanceService instanceService;

    public DiskStatusListener(DiskRepository diskRepository, InstanceService instanceService) {
        this.diskRepository = diskRepository;
        this.instanceService = instanceService;
    }

    @RabbitListener(queues = "disk-statuses")
    public void handleDiskStatusMessage(Map<String, Object> message) {
        String diskId = (String) message.get("id");
        String status = (String) message.get("status");

        System.out.println("[disk-statuses] Status recieved: " + status + " for disk " + diskId);

        diskRepository.findById(diskId).ifPresentOrElse(
            disk -> {
                disk.setStatus(status);
                diskRepository.save(disk);
            },
            () -> {
                System.err.println("Disk " + diskId + " not found in database.");
            }
        );
        instanceService.updateDiskStatus(diskId, status);
    }
}