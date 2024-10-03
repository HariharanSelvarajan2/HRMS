package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Benefits;
import com.hrms.repository.BenefitsRepository;

@Service
public class BenefitsService {

	@Autowired
	private BenefitsRepository benefitRepository;

	public List<Benefits> findAll() {
		return benefitRepository.findAll();
	}

	public Benefits save(Benefits benefit) {
		return benefitRepository.save(benefit);
	}
	
	public Benefits update(Benefits benefit) {
		if(benefitRepository.findById(benefit.getId())!=null) {
			Benefits benefits = benefitRepository.findById(benefit.getId()).orElse(null);
			benefits.setBenefitName(benefit.getBenefitName());
			benefits.setDescription(benefit.getDescription());
			return benefitRepository.save(benefits); 
		}else {
			return null;
		}
		
	}

	public Benefits findById(Long id) {
		return benefitRepository.findById(id).orElse(null);
	}

	public void deleteBenefit(Long id) {
		benefitRepository.deleteById(id);
	}
	
	public void deleteAll() {
		benefitRepository.deleteAll(); 
	}
}
