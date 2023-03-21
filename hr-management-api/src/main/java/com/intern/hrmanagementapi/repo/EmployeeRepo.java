package com.intern.hrmanagementapi.repo;

import com.intern.hrmanagementapi.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
    @Query("SELECT e FROM Employee e WHERE e.firstName LIKE %:name% OR e.lastName LIKE %:name%")
    List<Employee> findByName(@Param("name") String name);
    List<Employee> findAllByOrderByFirstNameAsc();
    Page<Employee> findAll(Pageable pageable);
}
