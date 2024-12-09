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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller // Marks this class as a Spring MVC controller, capable of handling HTTP requests.
@RequestMapping("/employees") // Maps all requests starting with "/employees" to this controller.
public class EmployeeViewController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    // Injects the required dependencies into the constructor.
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
    public String addEmployee(@ModelAttribute("employee") Employee employee, RedirectAttributes redirectAttributes) {
        employeeService.add(employee); // Save the employee to the database
        redirectAttributes.addFlashAttribute("message", "Employee added successfully!");
        return "redirect:/employees"; // Redirect after successful submission
    }

    // Display Employee Details
    @GetMapping("/details/{id}")
    public String showEmployeeDetails(@PathVariable ("id")long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employee_details"; // Name of the Thymeleaf HTML file for details
    }

    //Display the specified employee
    @GetMapping("/edit/{id}")
    public String showEditEmployeeForm(@PathVariable ("id") long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("department", departmentService.getAllDepartments());
        return "edit_employee";
    }

    // Process Edit Employee Form
    @PostMapping("/edit/{id}") //Handle the GET request to "/edit/{id}"
    public String updateEmployee(@PathVariable ("id")long id, @ModelAttribute("employee") Employee employee, RedirectAttributes redirectAttributes) {
        employeeService.update(employee, id); //Update in the database
        redirectAttributes.addFlashAttribute("message", "Successfully employee updated!");
        return "redirect:/employees";
    }

    // Confirm Delete Employee
    @GetMapping("/delete/{id}")
    public String confirmDeleteEmployee(@PathVariable ("id") long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "delete_employee";
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        employeeService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Employee deleted successfully!");  // Adds a flash message for the user.
        return "redirect:/employees"; //Return to the employee list after deletion
    }
}

