package com.cpd.disk.listener;

import com.cpd.disk.service.DiskService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DiskRequestListener {
    
    private final DiskService diskService;

    public DiskRequestListener(DiskService diskService) {
        this.diskService = diskService;
    }

    @RabbitListener(queues = "disk-requests")
    public void handleDiskRequest(Map<String, Object> message) {
        String diskId = (String) message.get("diskId");
        int size = (int) message.get("size");
        String type = (String) message.get("type");

        System.out.println("[disk-requests] Creating disk " + diskId + " (" + type + ", " + size + "GB)");
        diskService.createDiskWithDelays(diskId, size, type);
    }
}