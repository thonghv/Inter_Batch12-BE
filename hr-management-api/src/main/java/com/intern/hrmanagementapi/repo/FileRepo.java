package com.intern.hrmanagementapi.repo;

import com.intern.hrmanagementapi.entity.FileEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepo extends JpaRepository<FileEntity, UUID> {

  Optional<FileEntity> findByName(String name);

  Boolean existsByName(String name);
}
