package com.hrms.model;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	@NotNull(message = "name cannot be null")
	private String name;

	@Column(name = "contactNumber")
	@NotNull(message = "Contact number cannot be null")
    @Min(value = 1000000000L, message = "Contact number must be at least 10 digits")
	private Long contactNumber;

	@Column(name = "salary")
	@NotNull(message = "Salary cannot be null")
    @Min(value = 0, message = "Salary must be a positive number")
	private Long salary;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id")
	@JsonBackReference //to avoid recursion
	private Department department;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
	@JoinColumn(name = "employee_id")
	private List<Benefits> benefits;

	public Employee() {

	}

	public Employee(Long id, String name, Long contactNumber, Long salary, Department department,
			List<Benefits> benefits) {
		super();
		this.id = id;
		this.name = name;
		this.contactNumber = contactNumber;
		this.salary = salary;
		this.department = department;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Benefits> getBenefits() {
		return benefits;
	}

	public void setBenefits(List<Benefits> benefits) {
		this.benefits = benefits;
	}

}
