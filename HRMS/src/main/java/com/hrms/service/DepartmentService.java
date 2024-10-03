package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Department;
import com.hrms.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	public List<Department> findAll() {
		return departmentRepository.findAll();
	}

	public Department save(Department department) {
		return departmentRepository.save(department);
	}

	public Department findById(Long id) {
		return departmentRepository.findById(id).orElseGet(null);
	}

	public void deleteDepartment(Long id) {
		departmentRepository.deleteById(id);
	}

	public Department updateDepartment(Long id, Department updatedDepartment) {
		Department existingDepartment = departmentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Department not found"));
		existingDepartment.setName(updatedDepartment.getName());
		return departmentRepository.save(existingDepartment);
	}
	public void deleteAll() {
		departmentRepository.deleteAll(); 
	}
}
