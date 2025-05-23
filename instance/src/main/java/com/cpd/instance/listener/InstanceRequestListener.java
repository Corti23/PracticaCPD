package com.cpd.instance.listener;

import com.cpd.instance.service.InstanceService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InstanceRequestListener {
    
    private final InstanceService instanceService;

    public InstanceRequestListener(InstanceService instanceService) {
        this.instanceService = instanceService;
    }

    @RabbitListener(queues = "instance-requests")
    public void handleInstanceRequest(Map<String, Object> message) {
        String id = (String) message.get("instanceId");
        String name = (String) message.get("name");
        int memory = (int) message.get("memory");
        int cores = (int) message.get("cores");
        String diskId = (String) message.get("diskId");

        System.out.println("[instance-requests] Request for instance recieved: " + id);
        instanceService.createInstanceWithDelays(id, name, memory, cores, diskId);
    }
}