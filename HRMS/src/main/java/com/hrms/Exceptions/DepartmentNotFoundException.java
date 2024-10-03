package com.hrms.Exceptions;

@SuppressWarnings("serial")
public class DepartmentNotFoundException extends RuntimeException { 

	public DepartmentNotFoundException(String string) {
        super(string);
    }
}
