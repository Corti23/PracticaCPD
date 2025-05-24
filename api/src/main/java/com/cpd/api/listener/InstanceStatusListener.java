package com.cpd.api.listener;

import com.cpd.api.entity.InstanceEntity;
import com.cpd.api.repository.InstanceRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InstanceStatusListener {
    
    private final InstanceRepository instanceRepository;

    public InstanceStatusListener(InstanceRepository instanceRepository) {
        this.instanceRepository = instanceRepository;
    }

    @RabbitListener(queues = "instance-statuses")
    public void handleInstanceStatusMessage(Map<String, Object> message) {
        String instanceId = (String) message.get("instanceId");
        String status = (String) message.get("status");
        String ip = (String) message.get("ip"); // puede ser null

        System.out.println("[instance-statuses] Status recieved: " + status + " for instance " + instanceId);

        instanceRepository.findById(instanceId).ifPresentOrElse(
            instance -> {
                instance.setStatus(status);
                if (ip != null) {
                    instance.setIp(ip);
                }
                instanceRepository.save(instance);
            },
            () -> {
                System.err.println("Instance " + instanceId + " not found in database.");
            }
        );
    }
}