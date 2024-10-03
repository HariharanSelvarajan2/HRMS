package com.hrms.model;

import java.util.List;

public class EmployeeDTO {

	private Long id;

	private String name;

	private Long contactNumber;

	private Long salary;

	private Long department_id;

	private String department_name;

	private List<Benefits> benefits;

	public EmployeeDTO() {

	}

	public EmployeeDTO(Long id, String name, Long contactNumber, Long salary, Long department_id,
			String department_name, List<Benefits> benefits) {
		super();
		this.id = id;
		this.name = name;
		this.contactNumber = contactNumber;
		this.salary = salary;
		this.department_id = department_id;
		this.department_name = department_name;
		this.benefits = benefits;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public Long getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Long department_id) {
		this.department_id = department_id;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public List<Benefits> getBenefits() {
		return benefits;
	}

	public void setBenefits(List<Benefits> benefits) {
		this.benefits = benefits;
	}

}
