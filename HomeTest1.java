import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class EmployeeService {
    
    public Employee createNewEmployee(String name, int age, int salary) {
        Employee employee = new Employee(name, age, salary);
        Response response = RestAssured.given()
            .contentType(ContentType.JSON)
            .body(employee)
            .when()
            .post("/employees");
        if (response.getStatusCode() == 201) {
            Employee createdEmployee = response.getBody().as(Employee.class);
            return createdEmployee;
        } else {
            throw new RuntimeException("Failed to create new employee. Status code: " + response.getStatusCode());
        }
    }


public class Employee {
    private String name;
    private int age;
    private int salary;
    private int id;
    
    public Employee(String name, int age, int salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
    
}

EmployeeService employeeService = new EmployeeService();
Employee newEmployee = employeeService.createNewEmployee("John Smith", 30, 5000);
int newEmployeeId = newEmployee.getId();
Response response = RestAssured.get("/employees/" + newEmployeeId);
if (response.getStatusCode() == 200) {
    Employee retrievedEmployee = response.getBody().as(Employee.class);
    if (retrievedEmployee.getName().equals("John Smith") &&
        retrievedEmployee.getAge() == 30 &&
        retrievedEmployee.getSalary() == 5000 &&
        retrievedEmployee.getId() == newEmployeeId) {
        System.out.println("Employee created successfully");
    } else {
        System.out.println("Failed to create employee");
    }
} else {
    System.out.println("Failed to retrieve employee");
}


}


