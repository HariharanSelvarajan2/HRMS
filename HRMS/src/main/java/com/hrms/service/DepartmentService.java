package com.hrms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hrms.model.Department;
@Service
public interface DepartmentService {

	List<Department> findAll();

	Department save(Department department);

	Department findById(Long id);

	void deleteDepartment(Long id);

	Department updateDepartment(Long id, Department updatedDepartment);

	void deleteAll();
}
