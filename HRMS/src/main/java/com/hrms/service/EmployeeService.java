package com.hrms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hrms.model.Benefits;
import com.hrms.model.Employee;
import com.hrms.model.EmployeeDTO;

@Service
public interface EmployeeService {

	List<EmployeeDTO> findAll();
	EmployeeDTO findById(Long id); 
	EmployeeDTO save(Employee employee); 
	void deleteEmployee(Long id); 
	EmployeeDTO updateEmployee(Long id, Employee updatedEmployee) ; 
	void deleteAll();
	List<Benefits> getBenefitsWithEmployeeId(Long employeeId); 
}
