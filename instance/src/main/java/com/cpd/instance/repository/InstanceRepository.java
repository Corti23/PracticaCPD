package com.cpd.instance.repository;

import com.cpd.instance.entity.Instance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstanceRepository extends JpaRepository<Instance, String> {

    Optional<Instance> findByStatus(String status);

    Optional<Instance> findByIp(String ip);

    Optional<Instance> findByDiskId(String diskId);
}