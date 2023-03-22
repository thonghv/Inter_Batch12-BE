package com.intern.hrmanagementapi.controller;

import com.intern.hrmanagementapi.entity.Department;
import com.intern.hrmanagementapi.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/Department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public Object getAll() {
        return departmentService.getAllDepartment();
    }

    @GetMapping("{id}")
    public ResponseEntity<Department> getOne(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok().body(departmentService.findOne(id));
    }

    @PostMapping("/add")
    public String create(@RequestBody Department newDepartment) {
        try {
            departmentService.add(newDepartment);
            return "Add Department Successfully";
        } catch (Exception e) {
            return "ERROR!!";
        }
    }

    @PutMapping(value = {"{id}", "{id}/"})
    public String update(@RequestBody Department departmentReq) {
        if (departmentService.update(departmentReq)) {
            return "successfully";
        }
        return "ERROR";
    }
    @PatchMapping("{id}")
    public String update(@PathVariable("id") int id, @RequestBody Map<String, Object> fields) {
        if (departmentService.updateFields(id, fields)) {
            return "successfully";
        }
        return "ERROR";
    }
}