package com.hrms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hrms.model.Benefits;
@Service
public interface BenefitsService {

    List<Benefits> findAll();

    Benefits save(Benefits benefit);

    Benefits update(Long id,Benefits benefit);

    Benefits findById(Long id);

    void deleteBenefit(Long id);

    void deleteAll();
}
