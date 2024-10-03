package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.model.Benefits;

@Repository
public interface BenefitsRepository extends JpaRepository<Benefits,Long> {

}
