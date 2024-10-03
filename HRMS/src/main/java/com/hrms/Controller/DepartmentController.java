package com.hrms.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.Exceptions.DepartmentNotFoundException;
import com.hrms.model.Department;
import com.hrms.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@GetMapping
	public List<Department> getAllDepartments() {
		return departmentService.findAll();
	}

	@GetMapping("/{id}")
	public Department getDepartmentById(@PathVariable Long id) {
		try {
			Department department = departmentService.findById(id);
			return department;
		} catch (Exception e) {
			throw new DepartmentNotFoundException("Department not found");
		}

	}

	@PostMapping
	public Department createDepartment(@RequestBody Department department) {
		try {
			return departmentService.save(department);
		} catch (Exception e) {
			throw new DepartmentNotFoundException("Department not found");
		}

	}

	@PutMapping("/{id}")
	public Department updateDepartment(@PathVariable Long id, @RequestBody Department updatedDepartment) {
		try {
			Department department = departmentService.updateDepartment(id, updatedDepartment);
			return department;
		} catch (Exception e) {
			throw new DepartmentNotFoundException("Department not found");
		}
	}

	@DeleteMapping("/{id}")
	public void deleteDepartment(@PathVariable Long id) {
		try {
		departmentService.deleteDepartment(id);
		}catch (Exception e) { 
			throw new DepartmentNotFoundException("Department not found"); 
		}
	}
	
	@DeleteMapping
	public void deleteDepartments() {
		try {
		departmentService.deleteAll();
		} catch (Exception e) { 
			throw new DepartmentNotFoundException("No department Found"); 
		}
	
	}

}
