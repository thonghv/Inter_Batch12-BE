package com.intern.hrmanagementapi.service;

import com.intern.hrmanagementapi.entity.Employee;
import com.intern.hrmanagementapi.repo.EmployeeRepo;

import com.intern.hrmanagementapi.specification.EmployeeSpecification;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Date;
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
     * @return the employee with the specified id or exception if not found
     */
    public Employee getEmployeeById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee with id " + id + " not found"));
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
        Employee existingEmployee = repository.findById(employee.getId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        if (employee.getFirstName() != null) {
            existingEmployee.setFirstName(employee.getFirstName());
        }
        if (employee.getLastName() != null) {
            existingEmployee.setLastName(employee.getLastName());
        }
        Integer gender = employee.getGender();
        if (gender != null) {
            existingEmployee.setGender(gender);
        }
        if (employee.getAddress() != null) {
            existingEmployee.setAddress(employee.getAddress());
        }
        if (employee.getDob() != null) {
            existingEmployee.setDob(employee.getDob());
        }
        Integer departmentId = employee.getDepartmentId();
        if (departmentId != null) {
            existingEmployee.setDepartmentId(employee.getDepartmentId());
        }
        Integer positionId = employee.getPositionId();
        if (positionId != null) {
            existingEmployee.setPositionId(employee.getPositionId());
        }
        Integer contractId = employee.getContractId();
        if (contractId != null) {
            existingEmployee.setContractId(employee.getContractId());
        }
        Integer educationId = employee.getEducationId();
        if (educationId != null) {
            existingEmployee.setEducationId(employee.getEducationId());
        }
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

    public Page<Employee> list(UUID id, String firstName, String lastName, Integer gender, String address, LocalDateTime dob,
                               Integer departmentId, Integer positionId,Integer contractId,Integer educationId,
                               int pageNumber,int pageSize){
        Specification<Employee> specification = EmployeeSpecification.getSpec(id,firstName,lastName,gender,address,dob,departmentId,positionId,contractId,educationId);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return repository.findAll(specification,pageable);


    }

    public List<Employee> listAll() {
        return repository.findAll(Sort.by("id").ascending());
    }


}
