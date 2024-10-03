package com.hrms;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.hrms.Controller.BenefitsController;
import com.hrms.Exceptions.BenefitsNotFoundException;
import com.hrms.model.Benefits;
import com.hrms.service.BenefitsService;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class BenefitsControllerTest {

    @InjectMocks
    private BenefitsController benefitsController;

    @Mock
    private BenefitsService benefitsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @After(value = "")
    public void tearDown() {
        verifyNoMoreInteractions(benefitsService);
    } 

    @Test
    public void testGetAllBenefits() {
        Benefits benefit1 = new Benefits("insurance", "Covers medical expenses");
        Benefits benefit2 = new Benefits("Retirement Plan", "retirement savings");
        List<Benefits> benefitsList = Arrays.asList(benefit1, benefit2);

        when(benefitsService.findAll()).thenReturn(benefitsList);

        List<Benefits> result = benefitsController.getAllBenefits();

        assertEquals(2, result.size());
        assertEquals("insurance", result.get(0).getBenefitName());
        assertEquals("Retirement Plan", result.get(1).getBenefitName());
    }

    @Test
    public void testGetBenefitById() {
        Benefits benefit = new Benefits("insurance", "Covers medical expense");
        when(benefitsService.findById(1L)).thenReturn(benefit);

        Benefits result = benefitsController.getBenefitById(1L);

        assertEquals("insurance", result.getBenefitName());
    }

    @Test
    public void testGetBenefitByIdNotFound() {
        when(benefitsService.findById(1L)).thenThrow(new BenefitsNotFoundException("Benefits not found"));

        Exception exception = assertThrows(BenefitsNotFoundException.class, () -> {
            benefitsController.getBenefitById(1L);
        });

        assertEquals("Benefits not found", exception.getMessage());
    }

    @Test
    public void testCreateBenefit() {
        Benefits benefit = new Benefits("insurance", "Covers medical expenses");
        when(benefitsService.save(any(Benefits.class))).thenReturn(benefit);

        Benefits result = benefitsController.createBenefit(benefit);

        assertEquals("insurance", result.getBenefitName());
    }

    

    @Test
    public void testDeleteBenefit() {
        assertDoesNotThrow(() -> benefitsController.deleteBenefit(1L));
        verify(benefitsService, times(1)).deleteBenefit(1L);
    }
}

