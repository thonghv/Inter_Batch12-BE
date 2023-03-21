package com.intern.hrmanagementapi.service;

import com.intern.hrmanagementapi.entity.Employee;
import com.intern.hrmanagementapi.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo repository;

    //Add a new employee
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    //Add new employee list
    public List<Employee> saveEmployees(List<Employee> employees) {
        return repository.saveAll(employees);
    }

    //Get all employees
    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    //Get an employee by id
    public Employee getEmployeeById(int id) {
        return repository.findById(id).orElse(null);
    }

    //Delete an employee
    public String deleteEmployee(int id) {
        repository.deleteById(id);
        return "Employee removed !! " + id;
    }

    //Update an employee
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

    //Search employees by name (both first and last name)
    public List<Employee> getEmployeeByName(String name) {
        return repository.findByName(name);
    }

    //Ordered by {orderBy}
    public List<Employee> sort(String orderBy) {
        Sort sort = Sort.by(orderBy);
        return repository.findAll(sort);
    }

    //Pagination
    public Page<Employee> getEmployeesByPage(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return repository.findAll(pageable);
    }
}
