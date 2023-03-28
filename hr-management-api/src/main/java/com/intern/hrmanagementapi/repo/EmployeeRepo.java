package com.intern.hrmanagementapi.repo;

import com.intern.hrmanagementapi.entity.Employee;

import org.springframework.data.domain.Page;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository

public interface EmployeeRepo extends JpaRepository<Employee, UUID> {
    @Query("SELECT e FROM Employee e WHERE e.firstName LIKE %:name% OR e.lastName LIKE %:name%")
    List<Employee> findByName(@Param("name") String name);
    List<Employee> findAllByOrderByFirstNameAsc();

    @Query("SELECT e FROM Employee e WHERE e.firstName LIKE %:name% OR e.lastName LIKE %:name%")
    Page<Employee> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);



//    Page<Employee> findByNameContainingIgnoreCase(String name, Pageable pageable);
//    default Page<Employee> findEmployees(String name, int pageNumber, int pageSize) {
//        Specification<Employee> spec = (root, query, criteriaBuilder) -> {
//            Predicate firstNamePredicate = criteriaBuilder.like(root.get("firstName"), "%" + name + "%");
//            Predicate lastNamePredicate = criteriaBuilder.like(root.get("lastName"), "%" + name + "%");
//            return criteriaBuilder.or(firstNamePredicate, lastNamePredicate);
//        };
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//        return findAll(spec, pageable);
//    }


    Page<Employee> findAll(Specification<Employee> spec, Pageable pageable);
}
