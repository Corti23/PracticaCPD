package com.cpd.disk.service;

import com.cpd.disk.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.*;

@Service
public class DiskService {

    private final RabbitTemplate rabbitTemplate;

    public DiskService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void createDiskWithDelays(String diskId, int size, String type) {
        sendStatus(diskId, size, type, "REQUESTED", 0);
        sendStatus(diskId, size, type, "INITIALIZING", 5);
        sendStatus(diskId, size, type, "ASSIGNED", 15);
    }

    private void sendStatus(String diskId, int size, String type, String status, int delaySeconds) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    
        executor.schedule(() -> {
            Map<String, Object> message = new HashMap<>();
            message.put("id", diskId);
            message.put("size", size);
            message.put("type", type);
            message.put("status", status);

            System.out.println("[disk-statuses] Status " + status + " send for disk " + diskId);
            rabbitTemplate.convertAndSend(RabbitMQConfig.DISK_STATUS_QUEUE, message);
        }, delaySeconds, TimeUnit.SECONDS);
    }
}