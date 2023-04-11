package com.intern.hrmanagementapi.controller;

import com.intern.hrmanagementapi.constant.EndpointConst;
import com.intern.hrmanagementapi.constant.ExcelExporter;
import com.intern.hrmanagementapi.constant.MessageConst;
import com.intern.hrmanagementapi.entity.Employee;
import com.intern.hrmanagementapi.exception.EmployeeNotFoundException;
import com.intern.hrmanagementapi.model.DataResponseDto;
import com.intern.hrmanagementapi.service.EmployeeService;

import com.intern.hrmanagementapi.specification.EmployeeSpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController

@RequestMapping(value = {EndpointConst.EMPLOYEE_BASE_PATH})

public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @PostMapping
    public ResponseEntity<?> addEmployee(@Valid @RequestBody Employee employee) {

        employee.setCreateDate(LocalDateTime.now());
        employee.setUpdateDate(LocalDateTime.now());

        var response =  service.saveEmployee(employee);
        return ResponseEntity.ok(DataResponseDto.success(HttpStatus.OK.value(), MessageConst.SUCCESS, response));
    }


    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @PostMapping(value = {EndpointConst.EMPLOYEE_LIST})
    public ResponseEntity<?> addEmployees(@Valid  @RequestBody List<Employee> employees) {
        var response =  service.saveEmployees(employees);
        return ResponseEntity.ok(DataResponseDto.success(HttpStatus.OK.value(), MessageConst.SUCCESS, response));

    }


    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @GetMapping("/{id}")
    public ResponseEntity<?> findEmployeeById(@PathVariable UUID id) {
        var response =  service.getEmployeeById(id);
        return ResponseEntity.ok(DataResponseDto.success(HttpStatus.OK.value(), MessageConst.SUCCESS, response));
    }

    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @PutMapping()
    public ResponseEntity<?> updateEmployee(@RequestParam UUID id, @RequestBody Employee employee) {
        employee.setId(id);
        var response =  service.updateEmployee(employee);
        return ResponseEntity.ok(DataResponseDto.success(HttpStatus.OK.value(), MessageConst.SUCCESS, response));
    }

    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @DeleteMapping()
    public ResponseEntity<?> deleteEmployee(@RequestParam UUID id) {
        try {
            service.deleteEmployee(id);
            return ResponseEntity.ok(DataResponseDto.success(HttpStatus.OK.value(), MessageConst.SUCCESS, "success"));
        }catch (EmployeeNotFoundException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DataResponseDto.error(HttpStatus.NOT_FOUND.value(), MessageConst.Employee.NOT_EXIST, ex.getMessage()));
            }
    }

    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @PostMapping("/filter")
    public ResponseEntity<?> list(@RequestParam(required = false) UUID id,
                               @RequestParam(required = false) String firstName,
                               @RequestParam(required = false) String lastName,
                               @RequestParam(required = false) Integer gender,
                               @RequestParam(required = false) String address,
                               @RequestParam(required = false) LocalDateTime dob,
                               @RequestParam(required = false) Integer departmentId,
                               @RequestParam(required = false) Integer positionId,
                               @RequestParam(required = false) Integer contractId,
                               @RequestParam(required = false) Integer educationId,

                               @RequestParam int pageNumber,
                               @RequestParam int pageSize){
        var response =  service.list(id,firstName,lastName,gender,address,dob,departmentId,positionId,contractId,educationId,pageNumber,pageSize);
        return ResponseEntity.ok(DataResponseDto.success(HttpStatus.OK.value(), MessageConst.SUCCESS, response));

    }
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @GetMapping("/export")
    public ResponseEntity<?>  exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=List_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Employee> list = service.listAll();

        ExcelExporter excelExporter = new ExcelExporter(list);

        excelExporter.export(response);
        return ResponseEntity.ok(DataResponseDto.success(HttpStatus.OK.value(), MessageConst.SUCCESS, response));

    }

}