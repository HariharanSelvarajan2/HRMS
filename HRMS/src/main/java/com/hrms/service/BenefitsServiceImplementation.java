package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Benefits;
import com.hrms.model.Department;
import com.hrms.repository.BenefitsRepository;
@Service
public class BenefitsServiceImplementation implements BenefitsService {
	@Autowired
	private BenefitsRepository benefitRepository;

	public List<Benefits> findAll() {
		return benefitRepository.findAll();
	}

	public Benefits save(Benefits benefit) {
		return benefitRepository.save(benefit);
	}
	
	
	public Benefits update(Long id,Benefits benefit) {
		Benefits existingBenefit = benefitRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Benefit not found"));
		existingBenefit.setBenefitName(benefit.getBenefitName());
		existingBenefit.setDescription(benefit.getDescription()); 
		return benefitRepository.save(existingBenefit);
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
