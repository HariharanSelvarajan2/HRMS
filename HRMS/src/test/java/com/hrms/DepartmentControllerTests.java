package com.hrms;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hrms.Controller.DepartmentController;
import com.hrms.Exceptions.DepartmentNotFoundException;
import com.hrms.Exceptions.EmployeeNotFoundException;
import com.hrms.model.Department;
import com.hrms.service.DepartmentService;

public class DepartmentControllerTests {

    @InjectMocks
    private DepartmentController departmentController;

    @Mock
    private DepartmentService departmentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @After(value = "")
    public void tearDown() { 
    	verifyNoMoreInteractions(departmentService); 
    }

    @Test
    public void testGetAllDepartments() {
        Department department1 = new Department("HR");
        Department department2 = new Department("Finance");
        List<Department> departments = Arrays.asList(department1, department2);

        when(departmentService.findAll()).thenReturn(departments);

        List<Department> result = departmentController.getAllDepartments();

        assertEquals(2, result.size());
        assertEquals("HR", result.get(0).getName());
        assertEquals("Finance", result.get(1).getName());
    }

    @Test
    public void testGetDepartmentById() {
        Department department = new Department("HR");
        when(departmentService.findById(1L)).thenReturn(department);

        Department result = departmentController.getDepartmentById(1L);

        assertEquals("HR", result.getName());
    }

    @Test
    public void testGetDepartmentByIdNotFound() {
        when(departmentService.findById(1L)).thenThrow(new EmployeeNotFoundException("Department not found"));

        Exception exception = assertThrows(DepartmentNotFoundException.class, () -> {
            departmentController.getDepartmentById(1L);
        });

        assertEquals("Department not found", exception.getMessage());
    }

    @Test
    public void testCreateDepartment() {
        Department department = new Department("HR");
        when(departmentService.save(any(Department.class))).thenReturn(department);

        Department result = departmentController.createDepartment(department);

        assertEquals("HR", result.getName());
    }

    @Test
    public void testCreateDepartmentException() {
        Department department = new Department("HR");
        when(departmentService.save(any(Department.class))).thenThrow(new RuntimeException("Error saving department"));

        Exception exception = assertThrows(DepartmentNotFoundException.class, () -> {
            departmentController.createDepartment(department);
        });

        assertEquals("Department not found", exception.getMessage());
    }


    @Test
    public void testUpdateDepartmentNotFound() {
        Department updatedDepartment = new Department("HR Updated");
        when(departmentService.updateDepartment(1L, updatedDepartment)).thenThrow(new RuntimeException("Department not found"));

        Exception exception = assertThrows(DepartmentNotFoundException.class, () -> {
            departmentController.updateDepartment(1L, updatedDepartment);
        });

        assertEquals("Department not found", exception.getMessage());
    }

    @Test
    public void testDeleteDepartment() {
        assertDoesNotThrow(() -> departmentController.deleteDepartment(1L));
        verify(departmentService, times(1)).deleteDepartment(1L);
    }
}

