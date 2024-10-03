package com.hrms.Exceptions;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends RuntimeException { 

	public EmployeeNotFoundException(String string) {
        super(string);
    }
}
