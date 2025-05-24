package com.cpd.api.config;

import com.cpd.api.entity.DiskEntity;
import com.cpd.api.entity.InstanceEntity;
import com.cpd.api.repository.DiskRepository;
import com.cpd.api.repository.InstanceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class DataInitializer {
    
    @Bean
    public CommandLineRunner loadData(DiskRepository diskRepo, InstanceRepository instanceRepo) {
        return args -> {
            if (diskRepo.count() == 0) {
                DiskEntity d1 = new DiskEntity(UUID.randomUUID().toString(), 100, "SSD", "UNASSIGNED");
                DiskEntity d2 = new DiskEntity(UUID.randomUUID().toString(), 250, "HDD", "ASSIGNED");
                
                diskRepo.save(d1);
                diskRepo.save(d2);

                if (instanceRepo.count() == 0) {
                    InstanceEntity i1 = new InstanceEntity(
                            UUID.randomUUID().toString(),
                            "Server-Test",
                            16,
                            4,
                            "192.168.1.100",
                            "RUNNING",
                            d2
                    );
                    instanceRepo.save(i1);
                }

                System.out.println("Sample data loaded into database");
            }
        };
    }
}