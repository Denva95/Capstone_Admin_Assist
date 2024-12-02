package org.ndissandea.adminassist.controller;

import org.ndissandea.adminassist.exception.employeeNotFoundException;
import org.ndissandea.adminassist.model.Employee;
import org.ndissandea.adminassist.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*@RestController
@RequestMapping("/api/employees") // Base URL for employee-related endpoints
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Get all employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable long id) {
         return employeeService.getEmployeeById(id);

    }

    // Add a new employee
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.add(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    // Update an existing employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable long id,
            @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.update(employee, id);
        if (updatedEmployee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEmployee);
    }

    // Delete an employee
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
        try {
            employeeService.delete(id);
            return ResponseEntity.ok("Employee deleted successfully.");
        } catch (employeeNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }
}

*/