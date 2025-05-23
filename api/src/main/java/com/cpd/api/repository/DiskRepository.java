package com.cpd.api.repository;

import com.cpd.api.entity.DiskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DiskRepository extends JpaRepository<DiskEntity, String> {
    List<DiskEntity> findByStatusIgnoreCase(String status);
    
    Page<DiskEntity> findByStatusIgnoreCase(String status, Pageable pageable);
}