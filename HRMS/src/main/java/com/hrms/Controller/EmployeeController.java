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

import com.hrms.Exceptions.BenefitsNotFoundException;
import com.hrms.Exceptions.EmployeeNotFoundException;
import com.hrms.model.Benefits;
import com.hrms.model.Employee;
import com.hrms.model.EmployeeDTO;
import com.hrms.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public List<EmployeeDTO> getAllEmployees() {
		return employeeService.findAll();
	}

	@GetMapping("/{id}")
	public EmployeeDTO getEmployeeById(@PathVariable Long id) {
		try {
			EmployeeDTO employee = employeeService.findById(id);
			return employee;
		} catch (Exception e) {
			throw new EmployeeNotFoundException("No such EMployee");
		}

	}

	@PostMapping
	public EmployeeDTO createEmployee(@RequestBody Employee employee) {

		try {
			return employeeService.save(employee);
		} catch (Exception e) {
			throw new EmployeeNotFoundException("No such EMployee");
		}

	}

	@PutMapping("/{id}")
	public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
		try {
			EmployeeDTO employee = employeeService.updateEmployee(id, updatedEmployee);
			return employee;
		} catch (RuntimeException e) {
			throw new EmployeeNotFoundException("No employee Found");
		}
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		try {
			employeeService.deleteEmployee(id);
		} catch (Exception e) {
			throw new EmployeeNotFoundException("No employee found");
		}
	}

	@DeleteMapping
	public void deleteEmployee() {
		try {
			employeeService.deleteAll();
		} catch (Exception e) {
			throw new EmployeeNotFoundException("No employees found");
		}

	}

	
	@GetMapping("/benefits/{employeeId}")
	public List<Benefits> getBenefitsWithEmployeeId(@PathVariable Long employeeId) {
		try {
			return employeeService.getBenefitsWithEmployeeId(employeeId);
		} catch (BenefitsNotFoundException e) {
			throw new BenefitsNotFoundException("Benefits for employee not found");
		} catch (EmployeeNotFoundException e) {
			throw new EmployeeNotFoundException("Employee not found");
		}
	}

}
