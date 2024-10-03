## HRMS Application

- Java 17 and higher.
- Spring Boot
- Spring Data JPA.
- PostGreSQL
- Maven
- Junit and Mockito

- A Monolith Application.

## EndPoints

- Employee endpoints

  GET- AllEmployees => http://localhost:<port>/employees
  
  POST- Employee => http://localhost:<port>/employees
  
  PUT- Employee => http://localhost:<port>/employees/id
  
  Delete- Employee => http://localhost:<port>/employees/id
  
  DELETE - AllEmployees => http://localhost:<port>/employees
  
  GET - BenefitsWithEmployeeId => http:localhost:<port>/employees/benefits/{employeeId}
  
  
- Department endpoints

  GET- AllDepartments => http://localhost:<port>/departments
  
  POST- Department => http://localhost:<port>/departments
  
  
  PUT- Department => http://localhost:<port>/departments/id
  
  Delete- Department => http://localhost:<port>/departments/id
  
  DELETE - AllDepartments => http://localhost:<port>/departments
  
  
- Benefits endpoints

  GET- AllBenefits => http://localhost:<port>/benefits
  
  POST- Benefit => http://localhost:<port>/benefits
  
  PUT- Benefit => http://localhost:<port>/benefits/id
  
  Delete- Benefit => http://localhost:<port>/benefits/id
  
  DELETE - AllBenefits => http://localhost:<port>/benefits
  
- Before Running the application,
 
  set the port number in application properties. 
  
  Run the tests.
 
 - After running the Application,

- hit the Post department endpoint, post the following parameters.
  (employee list will be null, employee objects will be added later) 
  
      {
       "name":"finance"
      }
     
  Result in JSON-
  
     {
        "id": 1,
        "name": "finance",
        "employees": []
     }
 
 - hit the post benefit endpoint and post the following parameters, add as much.
  
     {
      "benefitName": "Health Insurance",
      "description": "Comprehensive health coverage"
     }
    
     {   
       "benefitName": "Retirement Plan",
       "description": "Retirement benefits"
     }
    
     {    
       "benefitName": "Complemenary meals",
       "description": "free meals"
     }
    
   result in JSON-
   
        {
            "id": 1,
            "benefitName": "Health Insurance",
            "description": "Comprehensive health coverage"
        },
        {
            "id": 2,
            "benefitName": "Retirement Plan",
            "description": "Retirement benefits"
        },
        {
            "id": 3,
            "benefitName": "Complemenary meals",
            "description": "free meals"
        }
  
- hit the post employee endpoint and post the following parameters.
  (Refer the employee service, the department id,benefit id is got, using DTO object, resultant is mapped)
  
  
       {
        "name": "rrrrr",
        "contactNumber": 9888666666,
        "salary": 98888,
        "department": {
            "id": 1
        },
        "benefits": [
            {
                "id": 1  
            },
            {
                "id": 2
            },
            {
                "id": 3
            }
        ]
        }
     
   Result in Json-
   
        {
        "id": 1,
        "name": "dra",
        "contactNumber": 9888666666,
        "salary": 98888,
        "department_id": 1,
        "department_name": "finance",
        "benefits": [
        {
            "id": 1,
            "benefitName": "Health Insurance",
            "description": "Comprehensive health coverage"
        },
        {
            "id": 2,
            "benefitName": "Retirement Plan",
            "description": "Retirement benefits"
        },
        {
            "id": 3,
            "benefitName": "Complemenary meals",
            "description": "free meals"
        }
       ]
       }
  
  