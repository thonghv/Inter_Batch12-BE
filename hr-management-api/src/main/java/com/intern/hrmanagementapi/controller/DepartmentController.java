package com.intern.hrmanagementapi.controller;

import com.intern.hrmanagementapi.entity.DepartmentEntity;
import com.intern.hrmanagementapi.exception.ResourceAlreadyExistsException;
import com.intern.hrmanagementapi.exception.ResourceNotFoundException;
import com.intern.hrmanagementapi.service.DepartmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
@Tag(name = "Department", description = "The department API")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<?> getAllDepartment(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) throws ResourceNotFoundException {
        List<DepartmentEntity> departmentEntityList = departmentService.getAllDepartment(name,orderBy,page,size);
        return new ResponseEntity<>(departmentEntityList, HttpStatus.OK);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<String> handleResourceAlreadyExistsException(ResourceAlreadyExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentEntity> getDepartment(@PathVariable int id) throws ResourceNotFoundException {
        DepartmentEntity departmentEntity = departmentService.getDepartment(id);
        return new ResponseEntity<>(departmentEntity, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addDepartment(@Valid @RequestBody DepartmentEntity newDepartment) throws ResourceAlreadyExistsException {
        departmentService.addDepartment(newDepartment);
        return new ResponseEntity<>(newDepartment, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable int id, @Valid @RequestBody DepartmentEntity departmentReq) throws ResourceNotFoundException, ResourceAlreadyExistsException {
        departmentReq.setId(id);
        departmentService.updateDepartment(departmentReq);
        return new ResponseEntity<>(departmentReq, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable int id) throws ResourceNotFoundException {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>("Department has been deleted successfully", HttpStatus.OK);
    }

}
