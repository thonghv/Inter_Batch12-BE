package com.intern.hrmanagementapi.service;

import com.intern.hrmanagementapi.entity.Department;
import com.intern.hrmanagementapi.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepo departmentRepo;

    public Object getAllDepartment() {
        return departmentRepo.findAll();
    }

    public Department findOne(int id) {
        return (Department) departmentRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid department id: " + id));
    }

    public void add(Department department) {
        departmentRepo.save(department);
    }

    public boolean update(Department department) {
        if (!departmentRepo.existsById(department.getId())) {
            return false;
        }
        Department updatedepartment = (Department) departmentRepo.findById(department.getId()).get();
        departmentRepo.save(updatedepartment);
        return true;
    }

    public boolean updateFields(int id, Map<String, Object> fields) {
        Optional<Department> updatedepartment = departmentRepo.findById(id);

        if (!updatedepartment.isPresent()) {
            return false;
        }
        fields.forEach(
                (key, value) -> {
                    Field field = ReflectionUtils.findField(Department.class, key);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, updatedepartment.get(), value);
                });
        departmentRepo.save(updatedepartment.get());
        return true;
    }

    public void deleteOne(int departmentId) {
        departmentRepo.deleteById(departmentId);
    }

    public void deleteAll() {
        departmentRepo.deleteAll();
    }
}
