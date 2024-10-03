package com.hrms;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.hrms.Controller.EmployeeController;
import com.hrms.Exceptions.BenefitsNotFoundException;
import com.hrms.Exceptions.EmployeeNotFoundException;
import com.hrms.model.Benefits;
import com.hrms.model.Employee;
import com.hrms.model.EmployeeDTO;
import com.hrms.service.EmployeeService;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class EmployeeControllerTests {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @After(value = "")
    public void tearDown() { 
    	verifyNoMoreInteractions(employeeService); 
    }

    @Test
    public void testGetAllEmployees() {
        EmployeeDTO employee1 = new EmployeeDTO(1L, "rrrrrrr", 1234567890L, 50000L, 1L, "HR", null);
        EmployeeDTO employee2 = new EmployeeDTO(2L, "hhhhhh", 9876543210L, 60000L, 2L, "Finance", null);
        List<EmployeeDTO> employeeList = Arrays.asList(employee1, employee2);

        when(employeeService.findAll()).thenReturn(employeeList);

        List<EmployeeDTO> result = employeeController.getAllEmployees();

        assertEquals(2, result.size());
        assertEquals("rrrrrrr", result.get(0).getName());
        assertEquals("hhhhhh", result.get(1).getName());
    }

    @Test
    public void testGetEmployeeById() {
        EmployeeDTO employeeDTO = new EmployeeDTO(1L, "ggggg", 1234567890L, 50000L, 1L, "HR", null);
        when(employeeService.findById(1L)).thenReturn(employeeDTO);

        EmployeeDTO result = employeeController.getEmployeeById(1L);

        assertEquals("ggggg", result.getName());
    }

    @Test
    public void testGetEmployeeByIdNotFound() {
        when(employeeService.findById(1L)).thenThrow(new EmployeeNotFoundException("No such EMployee"));

        Exception exception = assertThrows(EmployeeNotFoundException.class, () -> {
            employeeController.getEmployeeById(1L);
        });

        assertEquals("No such EMployee", exception.getMessage());
    }

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setName("John Doe");

        EmployeeDTO employeeDTO = new EmployeeDTO(1L, "yyyyyy", 1234567890L, 50000L, 1L, "HR", null);
        when(employeeService.save(any(Employee.class))).thenReturn(employeeDTO);

        EmployeeDTO result = employeeController.createEmployee(employee);

        assertEquals("yyyyyy", result.getName());
    }

    @Test
    public void testUpdateEmployee() {
        Employee updatedEmployee = new Employee();
        updatedEmployee.setName("rrrrr");

        EmployeeDTO updatedEmployeeDTO = new EmployeeDTO(1L, "rrrrr", 1234567890L, 50000L, 1L, "HR", null);
        when(employeeService.updateEmployee(1L, updatedEmployee)).thenReturn(updatedEmployeeDTO);

        EmployeeDTO result = employeeController.updateEmployee(1L, updatedEmployee);

        assertEquals("rrrrr", result.getName());
    }

    @Test
    public void testDeleteEmployee() {
        assertDoesNotThrow(() -> employeeController.deleteEmployee(1L));
        verify(employeeService, times(1)).deleteEmployee(1L);
    }
    
   

    @Test
    void testGetBenefitsWithEmployeeId_Success() {
        Long employeeId = 1L;

        List<Benefits> benefits = Arrays.asList(new Benefits("Health Insurance","insurance benefits"), new Benefits("Retirement Plan","retirement plan "));
        
        when(employeeService.getBenefitsWithEmployeeId(employeeId)).thenReturn(benefits);

        List<Benefits> result = employeeController.getBenefitsWithEmployeeId(employeeId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Health Insurance", result.get(0).getBenefitName());

        verify(employeeService, times(1)).getBenefitsWithEmployeeId(employeeId);
    }

    @Test
    void testGetBenefitsWithEmployeeId_BenefitsNotFound() {
        Long employeeId = 2L;

        when(employeeService.getBenefitsWithEmployeeId(employeeId)).thenThrow(new BenefitsNotFoundException("Benefits for employee not found"));

        BenefitsNotFoundException exception = assertThrows(BenefitsNotFoundException.class, () -> {
            employeeController.getBenefitsWithEmployeeId(employeeId);
        });

        assertEquals("Benefits for employee not found", exception.getMessage());
        verify(employeeService, times(1)).getBenefitsWithEmployeeId(employeeId);
    }

    @Test
    void testGetBenefitsWithEmployeeId_EmployeeNotFound() {
        Long employeeId = 3L;

        when(employeeService.getBenefitsWithEmployeeId(employeeId)).thenThrow(new EmployeeNotFoundException("Employee not found"));

        EmployeeNotFoundException exception = assertThrows(EmployeeNotFoundException.class, () -> {
            employeeController.getBenefitsWithEmployeeId(employeeId);
        });

        assertEquals("Employee not found", exception.getMessage());
        verify(employeeService, times(1)).getBenefitsWithEmployeeId(employeeId);
    }
}

