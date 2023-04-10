package com.intern.hrmanagementapi.service;

import com.intern.hrmanagementapi.entity.DepartmentEntity;
import com.intern.hrmanagementapi.exception.ResourceAlreadyExistsException;
import com.intern.hrmanagementapi.exception.ResourceNotFoundException;
import com.intern.hrmanagementapi.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class DepartmentService {
    @Autowired
    DepartmentRepo departmentRepo;
    /**

     Retrieves a list of DepartmentEntities based on search criteria
     @param name The name to search for in DepartmentEntity name
     @param orderBy The field to order the results by
     @param page The page number to retrieve
     @param size The number of items to retrieve per page
     @return A list of DepartmentEntities that match the search criteria
     @throws ResourceNotFoundException if no matching DepartmentEntities are found
     */
    public List<DepartmentEntity> getAllDepartment(String name, String orderBy, int page, int size) throws ResourceNotFoundException {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy).descending());
        List<DepartmentEntity> userEntityList;
        if (name != null) {
            userEntityList = departmentRepo.findBynameContaining(name, pageable);
        } else {
            userEntityList = departmentRepo.findAll(pageable).getContent();
        }
        if (userEntityList.isEmpty()) {
            throw new ResourceNotFoundException("No user found");
        }
        return userEntityList;
    }
    /**

     Retrieves a single DepartmentEntity by ID
     @param id The ID of the DepartmentEntity to retrieve
     @return The DepartmentEntity with the specified ID
     @throws ResourceNotFoundException if no DepartmentEntity with the specified ID exists
     */
    public DepartmentEntity getDepartment(int id) throws ResourceNotFoundException {
        return departmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id:" + id));
    }
    /**

     Adds a new DepartmentEntity to the database
     @param department The DepartmentEntity to add
     @return The added DepartmentEntity
     @throws ResourceAlreadyExistsException if a DepartmentEntity with the same name already exists
     */
    public DepartmentEntity addDepartment(DepartmentEntity department) throws ResourceAlreadyExistsException {
        if (departmentRepo.findByname(department.getName()).isPresent()) {
            throw new ResourceAlreadyExistsException("Department already exists with name: " + department.getName());
        }
        return departmentRepo.save(department);
    }
    /**

     Updates an existing DepartmentEntity in the database
     @param department The DepartmentEntity to update
     @return The updated DepartmentEntity
     @throws ResourceNotFoundException if no DepartmentEntity with the specified ID exists
     @throws ResourceAlreadyExistsException if a DepartmentEntity with the same name already exists
     */
    public DepartmentEntity updateDepartment(DepartmentEntity department) throws ResourceNotFoundException, ResourceAlreadyExistsException {
        Optional<DepartmentEntity> departmentEntity = departmentRepo.findById(department.getId());
        Optional<DepartmentEntity> departmentEntity1 = departmentRepo.findByname(department.getName());
        if (!departmentEntity.isPresent()) {
            throw new ResourceNotFoundException("Department not found with id: " + department.getId());
        }
        if (departmentEntity1.isPresent()) {
            throw new ResourceAlreadyExistsException("name already exists");
        }
        department.setUpdateDate(new Date());
        return departmentRepo.save(department);

    }
    /**

     Deletes a DepartmentEntity from the database
     @param id The ID of the DepartmentEntity to delete
     @throws ResourceNotFoundException if no DepartmentEntity with the specified ID exists
     */
    public void deleteDepartment(int id) throws ResourceNotFoundException {
        Optional<DepartmentEntity> department = departmentRepo.findById(id);
        if (department.isPresent()) {
            departmentRepo.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Department not found with id: " + id);
        }
    }
}
