package com.hrms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Benefits;
import com.hrms.model.Department;
import com.hrms.model.Employee;
import com.hrms.model.EmployeeDTO;
import com.hrms.repository.BenefitsRepository;
import com.hrms.repository.DepartmentRepository;
import com.hrms.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private BenefitsRepository benefitsRepository;

	public List<EmployeeDTO> findAll() {
		List<Employee> employee = employeeRepository.findAll();
		List<EmployeeDTO> employeeDTOs = new ArrayList<EmployeeDTO>();

		for (Employee e : employee) {
			EmployeeDTO employeeDTO = new EmployeeDTO(e.getId(), e.getName(), e.getContactNumber(), e.getSalary(),
					e.getDepartment().getId(), e.getDepartment().getName(), e.getBenefits());
			employeeDTOs.add(employeeDTO);
		}
		return employeeDTOs;
	}

	public EmployeeDTO save(Employee employee) {
		Department department = departmentRepository.findById(employee.getDepartment().getId()).orElse(null);
		employee.setDepartment(department);
		List<Benefits> fetchedBenefits = new ArrayList<>();

		for (Benefits benefit : employee.getBenefits()) {
			Benefits fetchedBenefit = benefitsRepository.findById(benefit.getId()).orElse(null);
			if (fetchedBenefit != null) {
				fetchedBenefits.add(fetchedBenefit);
			}
		}
		employee.setBenefits(fetchedBenefits);
		employeeRepository.save(employee);

		EmployeeDTO employeeDTO = new EmployeeDTO(employee.getId(), employee.getName(), employee.getContactNumber(),
				employee.getSalary(), employee.getDepartment().getId(), employee.getDepartment().getName(),
				employee.getBenefits());
		return employeeDTO;

	}

	public EmployeeDTO findById(Long id) {
		Employee employee = employeeRepository.findById(id).orElse(null);
		EmployeeDTO employeeDTO = new EmployeeDTO(employee.getId(), employee.getName(), employee.getContactNumber(),
				employee.getSalary(), employee.getDepartment().getId(), employee.getDepartment().getName(),
				employee.getBenefits());
		return employeeDTO;

	}

	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
	}

	public EmployeeDTO updateEmployee(Long id, Employee updatedEmployee) {
		Employee existingEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Employee not found"));

		existingEmployee.setName(updatedEmployee.getName());
		existingEmployee.setContactNumber(updatedEmployee.getContactNumber());
		existingEmployee.setSalary(updatedEmployee.getSalary());
		employeeRepository.save(existingEmployee);

		Employee employee = employeeRepository.findById(id).orElse(null);
		EmployeeDTO employeeDTO = new EmployeeDTO(employee.getId(), employee.getName(), employee.getContactNumber(),
				employee.getSalary(), employee.getDepartment().getId(), employee.getDepartment().getName(),
				employee.getBenefits());
		return employeeDTO;

	}
	public void deleteAll() {
		employeeRepository.deleteAll(); 
	}
	
	public List<Benefits> getBenefitsWithEmployeeId(Long employeeId) { 
		
		Employee employee = employeeRepository.findById(employeeId).orElse(null);
            return employee.getBenefits();
	
        
	}

	
}
