package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.model.Department;

@Repository 
public interface DepartmentRepository extends JpaRepository<Department,Long>{

}
