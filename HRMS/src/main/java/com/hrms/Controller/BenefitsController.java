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
import com.hrms.model.Benefits;
import com.hrms.service.BenefitsService;

@RestController
@RequestMapping("/benefits")
public class BenefitsController {

	@Autowired
	private BenefitsService benefitService;

	@GetMapping
	public List<Benefits> getAllBenefits() {
		return benefitService.findAll();
	}

	@GetMapping("/{id}")
	public Benefits getBenefitById(@PathVariable Long id) {
		try {
			Benefits benefit = benefitService.findById(id);
			return benefit;
		} catch (Exception e) {
			throw new BenefitsNotFoundException("Benefits not found");
		}
	}

	@PostMapping
	public Benefits createBenefit(@RequestBody Benefits benefit) {
		try {
			return benefitService.save(benefit);
		} catch (Exception e) {
			throw new BenefitsNotFoundException("Benefits not found");
		}
	}
	
	@PutMapping("/{id}")
	public Benefits updateBenefit(@PathVariable Long id, @RequestBody Benefits updatedBenefit) {
		try {
			return benefitService.update(id,updatedBenefit);
		} catch (Exception e) { 
			throw new BenefitsNotFoundException("Benefits not found"); 
		}
		
	}

	@DeleteMapping("/{id}")
	public void deleteBenefit(@PathVariable Long id) {
		try {
		benefitService.deleteBenefit(id);
		}catch ( Exception e ) { 
			throw new BenefitsNotFoundException("Benefits not found");
		}
	}
	
	@DeleteMapping
	public void deleteBenefits() {
		try {
		benefitService.deleteAll();
		} catch ( Exception e ) { 
			throw new BenefitsNotFoundException("Benefits not found");
		}
	
	}

}
