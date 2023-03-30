package com.intern.hrmanagementapi.service;

import com.intern.hrmanagementapi.entity.Employee;
import com.intern.hrmanagementapi.exception.EmployeeNotFoundException;
import com.intern.hrmanagementapi.repo.EmployeeRepo;

//import com.intern.hrmanagementapi.specification.SearchRequest;
//import com.intern.hrmanagementapi.specification.SearchSpecification;

import jakarta.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.domain.Specification;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo repository;

    /**
     * Save a new Employee object to the database.
     *
     * @param employee the Employee object to be saved
     * @return the saved Employee object
     */
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    /**
     * Save a list of employees to the database.
     *
     * @param employees The list of employees to save.
     * @return The list of saved employees.
     */
    public List<Employee> saveEmployees(List<Employee> employees) {
        return repository.saveAll(employees);
    }

    /**
     * Get a list of all employees.
     *
     * @return A list of all employees in the database.
     */
    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    /**
     * Get an employee by id
     *
     * @param id the id of the employee to retrieve
     * @return the employee with the specified id or null if not found
     */
    public Employee getEmployeeById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Deletes the employee with the given ID from the database.
     *
     * @param id the ID of the employee to be deleted
     * @return a message indicating that the employee has been removed
     */
    public void deleteEmployee(UUID id) {
        Employee e = repository.findById(id).orElse(null);
        if (e==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }else{
            repository.deleteById(id);
        }
    }



    /**
     * Updates the information of an existing employee.
     *
     * @param employee The employee object to be updated.
     * @return The updated employee object.
     */
    public Employee updateEmployee(Employee employee) {
        Employee existingEmployee = repository.findById(employee.getId()).orElse(null);
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setGender(employee.getGender());
        existingEmployee.setAddress(employee.getAddress());
        existingEmployee.setDob(employee.getDob());
        existingEmployee.setDepartmentId(employee.getDepartmentId());
        existingEmployee.setPositionId(employee.getPositionId());
        existingEmployee.setContractId(employee.getContractId());
        existingEmployee.setEducationId(employee.getEducationId());
        return repository.save(existingEmployee);
    }

    /**
     * Retrieves a paginated list of employees sorted by a given field.
     *
     * @param orderBy the field to sort by (optional)
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of employees per page
     * @return a Page object containing the requested employees
     */
    public Page<Employee> getEmployeesByPageAndSort(String orderBy, int pageNumber, int pageSize) {
        Pageable pageable;
        if (orderBy != null) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(orderBy));
        } else {
            pageable = PageRequest.of(pageNumber, pageSize);
        }
        return repository.findAll(pageable);
    }

    /**
     * Retrieves a page of employees matching the given name, sorted by name, and with pagination.
     *
     * @param name The name to search for.
     * @param pageNumber The page number to retrieve (starting from 0).
     * @param pageSize The number of elements to retrieve per page.
     * @return A page of employees matching the given name.
     */

//    public Page<Employee> getEmployeeByName(String name, int pageNumber, int pageSize) {
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//        return repository.findByNameContainingIgnoreCase(name, pageable);
//    }
//    public Page<Employee> getEmployeeByName(String name, int pageNumber, int pageSize) {
//        Specification<Employee> spec = (root, query, criteriaBuilder) -> {
//            Predicate firstNamePredicate = criteriaBuilder.like(root.get("firstName"), "%" + name + "%");
//            Predicate lastNamePredicate = criteriaBuilder.like(root.get("lastName"), "%" + name + "%");
//            return criteriaBuilder.or(firstNamePredicate, lastNamePredicate);
//        };
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//        return repository.findAll(spec, pageable);
//    }

//    public Page<Employee> searchOperatingSystem(SearchRequest request) {
//        SearchSpecification<Employee> specification = new SearchSpecification<>(request);
//        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
//        return repository.findAll(specification, pageable);

//    }
}
