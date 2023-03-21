package com.intern.hrmanagementapi.controller;

import com.intern.hrmanagementapi.entity.Employee;
import com.intern.hrmanagementapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return service.saveEmployee(employee);
    }

    @PostMapping("/batch")
    public List<Employee> addEmployees(@RequestBody List<Employee> employees) {
        return service.saveEmployees(employees);
    }

    @GetMapping()
    public List<Employee> findAllEmployees(@RequestParam(required = false) String orderBy) {
        if (orderBy != null) {
            return service.sort(orderBy);
        }
        return service.getEmployees();
    }

    @GetMapping("/{id}")
    public Employee findEmployeeById(@PathVariable int id) {
        return service.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        employee.setId(id);
        return service.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {
        return service.deleteEmployee(id);
    }

    @GetMapping("/search/{name}")
    public List<Employee> findEmployeeByName(@PathVariable String name) {
        return service.getEmployeeByName(name);
    }
    @GetMapping("/pagination")
    public Page<Employee> getEmployeesByPage(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return service.getEmployeesByPage(pageNumber, pageSize);
    }

}