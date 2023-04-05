package com.intern.hrmanagementapi.specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


import com.intern.hrmanagementapi.entity.Employee;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import jakarta.persistence.criteria.Predicate;



@Component
public class EmployeeSpecification {

    public static Specification<Employee> getSpec(UUID id, String firstName, String lastName, Integer gender, String address, LocalDateTime dob,
                                                  Integer departmentId, Integer positionId,Integer contractId,Integer educationId) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (id != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), id));
            }
            if (firstName != null && !firstName.isEmpty() ) {
                predicates.add(criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%"));
            }
            if (lastName != null && !lastName.isEmpty() ) {
                predicates.add(criteriaBuilder.like(root.get("lastName"), "%" + lastName + "%"));
            }
            if (gender != null) {
                predicates.add(criteriaBuilder.equal(root.get("gender"), gender));
            }
            if (address != null && !address.isEmpty() ) {
                predicates.add(criteriaBuilder.like(root.get("address"), "%" + address + "%"));
            }
            if (dob != null ) {
                predicates.add(criteriaBuilder.equal(root.get("dob"), dob));
            }
            if (departmentId != null) {
                predicates.add(criteriaBuilder.equal(root.get("departmentId"), departmentId));
            }
            if (positionId != null) {
                predicates.add(criteriaBuilder.equal(root.get("positionId"), positionId));
            }
            if (contractId != null) {
                predicates.add(criteriaBuilder.equal(root.get("contractId"), contractId));
            }
            if (educationId != null) {
                predicates.add(criteriaBuilder.equal(root.get("educationId"), educationId));
            }

            query.orderBy(criteriaBuilder.desc(root.get("id")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };

    }
}