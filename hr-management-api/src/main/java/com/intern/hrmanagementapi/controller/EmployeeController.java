package com.intern.hrmanagementapi.controller;

import com.intern.hrmanagementapi.constant.EndpointConst;
import com.intern.hrmanagementapi.entity.Employee;
import com.intern.hrmanagementapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = {EndpointConst.BASE_PATH})
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return service.saveEmployee(employee);
    }

    @PostMapping(value = {EndpointConst.EMPLOYEE_LIST})
    public List<Employee> addEmployees(@RequestBody List<Employee> employees) {
        return service.saveEmployees(employees);
    }

    @GetMapping()
    public Page<Employee> getEmployees(@RequestParam(required = false) String orderBy,
                                       @RequestParam int pageNumber,
                                       @RequestParam int pageSize) {
        return service.getEmployeesByPageAndSort(orderBy, pageNumber, pageSize);
    }

    @GetMapping()
    public Employee findEmployeeById(@RequestParam int id) {
        return service.getEmployeeById(id);
    }

    @PutMapping()
    public Employee updateEmployee(@RequestParam int id, @RequestBody Employee employee) {
        employee.setId(id);
        return service.updateEmployee(employee);
    }

    @DeleteMapping()
    public String deleteEmployee(@RequestParam int id) {
        return service.deleteEmployee(id);
    }

    @GetMapping(value = {EndpointConst.SEARCH})
    public Page<Employee> findEmployeeByName(@RequestParam String name, @RequestParam int pageNumber, @RequestParam int pageSize) {
        return service.getEmployeeByName(name, pageNumber, pageSize);
    }
}