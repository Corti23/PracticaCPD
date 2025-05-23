package com.cpd.api.repository;

import com.cpd.api.entity.InstanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface InstanceRepository extends JpaRepository<InstanceEntity, String> {
    Page<InstanceEntity> findByStatusIgnoreCase(String status, Pageable pageable);

    Optional<InstanceEntity> findByDisk_Id(String diskId);
}