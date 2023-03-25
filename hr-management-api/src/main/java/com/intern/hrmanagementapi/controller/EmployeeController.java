package com.intern.hrmanagementapi.controller;

import com.intern.hrmanagementapi.constant.EndpointConst;
import com.intern.hrmanagementapi.constant.MessageConst;
import com.intern.hrmanagementapi.entity.Employee;
import com.intern.hrmanagementapi.model.DataResponseDto;
import com.intern.hrmanagementapi.service.EmployeeService;
<<<<<<< HEAD
import com.intern.hrmanagementapi.specification.SearchRequest;
=======
>>>>>>> c13617b2570236640adbb21be0a98fd7981b9b96
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import java.time.LocalDateTime;
=======
>>>>>>> c13617b2570236640adbb21be0a98fd7981b9b96
import java.util.List;
import java.util.UUID;

@RestController
<<<<<<< HEAD
@RequestMapping(value = {EndpointConst.EMPLOYEE_BASE_PATH})
=======
@RequestMapping(value = {EndpointConst.BASE_PATH})
>>>>>>> c13617b2570236640adbb21be0a98fd7981b9b96
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @PostMapping
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
<<<<<<< HEAD
        employee.setCreateDate(LocalDateTime.now());
        employee.setUpdateDate(LocalDateTime.now());
=======
>>>>>>> c13617b2570236640adbb21be0a98fd7981b9b96
        var response =  service.saveEmployee(employee);
        return ResponseEntity.ok(DataResponseDto.success(HttpStatus.OK.value(), MessageConst.SUCCESS, response));
    }

<<<<<<< HEAD
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})

    @PostMapping(value = {EndpointConst.EMPLOYEE_LIST})
    public ResponseEntity<?> addEmployees(@RequestBody List<Employee> employees) {
        var response =  service.saveEmployees(employees);
        return ResponseEntity.ok(DataResponseDto.success(HttpStatus.OK.value(), MessageConst.SUCCESS, response));
=======
    @PostMapping(value = {EndpointConst.EMPLOYEE_LIST})
    public List<Employee> addEmployees(@RequestBody List<Employee> employees) {
        return service.saveEmployees(employees);
>>>>>>> c13617b2570236640adbb21be0a98fd7981b9b96
    }


    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @GetMapping()
    public ResponseEntity<?> getEmployees(@RequestParam(required = false) String orderBy,
                                          @RequestParam(required = false) String name,
                                          @RequestParam int pageNumber,
                                          @RequestParam int pageSize) {
        if (name != null) {
            var response = service.getEmployeeByName(name, pageNumber, pageSize);
            return ResponseEntity.ok(
                    DataResponseDto.success(HttpStatus.OK.value(), MessageConst.SUCCESS, response));
        } else {
            var response = service.getEmployeesByPageAndSort(orderBy, pageNumber, pageSize);
            return ResponseEntity.ok(
                    DataResponseDto.success(HttpStatus.OK.value(), MessageConst.SUCCESS, response));
        }
    }
<<<<<<< HEAD
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
=======
>>>>>>> c13617b2570236640adbb21be0a98fd7981b9b96

    @GetMapping("/id")
    public Employee findEmployeeById(@RequestParam UUID id) {
        return service.getEmployeeById(id);
    }
<<<<<<< HEAD
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
=======
>>>>>>> c13617b2570236640adbb21be0a98fd7981b9b96

    @PutMapping()
    public Employee updateEmployee(@RequestParam UUID id, @RequestBody Employee employee) {
        employee.setId(id);
        return service.updateEmployee(employee);
    }
<<<<<<< HEAD
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
=======
>>>>>>> c13617b2570236640adbb21be0a98fd7981b9b96

    @DeleteMapping()
    public String deleteEmployee(@RequestParam UUID id) {
        return service.deleteEmployee(id);
    }

<<<<<<< HEAD
    @PostMapping("/search")
    public Page<Employee> search(@RequestBody SearchRequest request) {
        return service.searchOperatingSystem(request);
    }

=======
>>>>>>> c13617b2570236640adbb21be0a98fd7981b9b96
}