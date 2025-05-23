package com.cpd.instance.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;

@Service
public class InstanceService {

    public void createInstanceWithDelays(String id, String name, int memory, int cores, String diskId) {
        sendStatus(id, name, memory, cores, null, "BUILDING_DISK", diskId, 0);
        sendStatus(id, name, memory, cores, null, "STARTING", diskId, 5);
        sendStatus(id, name, memory, cores, null, "INITIALIZING", diskId, 10);
        sendStatus(id, name, memory, cores, null, "ASSIGNING_IP", diskId, 15);
        assignIpAndRun(id, name, memory, cores, null, "ASSIGNING_IP", diskId, 20);
    }

    private void sendStatus(String id, String name, int memory, int cores, String ip, String status, String diskId, int delaySeconds) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        executor.schedule(() -> {
            Map<String, Object> msg = new HashMap<>();
            msg.put("instanceId", id);
            msg.put("name", name);
            msg.put("memory", memory);
            msg.put("cores", cores);
            msg.put("ip", ip);
            msg.put("status", status);
            msg.put("diskId", diskId);

            System.out.println("[instance-statuses] Status " + status + " for instance " + id);
        }, delaySeconds, TimeUnit.SECONDS);
    }

    private void assignIpAndRun(String id, String name, int memory, int cores, String ip, String status, String diskId, int delaySeconds) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        executor.schedule(() -> {
            String ipRandom = "192.168.1." + (2 + new Random().nextInt(253));

            Map<String, Object> msg = new HashMap<>();
            msg.put("instanceId", id);
            msg.put("name", name);
            msg.put("memory", memory);
            msg.put("cores", cores);
            msg.put("ip", ipRandom);
            msg.put("status", "RUNNING");
            msg.put("diskId", diskId);

            System.out.println("[instance-statuses] Instance " + id + " in status RUNNING with IP " + ip);
        }, delaySeconds, TimeUnit.SECONDS);
    }
}