package com.intern.hrmanagementapi.repo;

import com.intern.hrmanagementapi.entity.DepartmentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepo extends JpaRepository<DepartmentEntity, Integer> {
    Optional<DepartmentEntity> findByname(String name);
    @Query(value = "SELECT * FROM department WHERE name LIKE %:name%",nativeQuery = true)
    List<DepartmentEntity> findBynameContaining(@Param("name") String name, Pageable pageable);
}
