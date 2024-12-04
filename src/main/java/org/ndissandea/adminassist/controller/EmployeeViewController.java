package org.ndissandea.adminassist.controller;

import jakarta.validation.Valid;
import org.ndissandea.adminassist.model.Employee;
import org.ndissandea.adminassist.service.DepartmentService;
import org.ndissandea.adminassist.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeViewController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @Autowired
    public EmployeeViewController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }



    // Display Employee List
    @GetMapping
    public String getAllEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees); // Pass employee data to Thymeleaf
        return "employees_profile"; // Name of the Thymeleaf HTML file
    }

    // Display Add Employee Form
    @GetMapping("/add")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("department", departmentService.getAllDepartments());
        return "add_employee"; // Thymeleaf template
    }

    // Process Add Employee Form
    @PostMapping("/add")
    public String addEmployee(@ModelAttribute("employee") @Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "add_employee"; // Return to the form with errors
        }
        employeeService.add(employee); // Save the employee to the database
        return "redirect:/employees"; // Redirect after successful submission
    }

    // Display Employee Details
    @GetMapping("/details/{id}")
    public String showEmployeeDetails(@PathVariable ("id")long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employee_details"; // Name of the Thymeleaf HTML file for details
    }

    @GetMapping("/edit/{id}")
    public String showEditEmployeeForm(@PathVariable ("id") long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("department", departmentService.getAllDepartments());
        return "edit_employee";
    }

    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable ("id")long id, @ModelAttribute("employee") Employee employee) {
        employeeService.update(employee, id);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String confirmDeleteEmployee(@PathVariable ("id") long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "delete_employee";
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") long id) {
        employeeService.delete(id);
        return "redirect:/employees";
    }
}

