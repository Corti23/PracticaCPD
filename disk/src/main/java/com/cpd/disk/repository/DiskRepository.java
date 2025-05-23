package com.cpd.disk.repository;

import com.cpd.disk.entity.Disk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiskRepository extends JpaRepository<Disk, Long> {

    Optional<Disk> findByStatus(String status);

    Optional<Disk> findByName(String name);
}