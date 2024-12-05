package org.ndissandea.adminassist.controller;

import org.ndissandea.adminassist.model.Department;
import org.ndissandea.adminassist.model.Employee;
import org.ndissandea.adminassist.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentViewController {

    @Autowired
    private DepartmentService departmentService;

    // Show all departments
    @GetMapping
    public String showDepartment(Model model) {
        List<Department> department = departmentService.getAllDepartments();
        model.addAttribute("department", department);  // Corrected the attribute name to match the plural form
        return "department_management";  // Thymeleaf template for department home page
    }

    // Show form to add a new department
    @GetMapping("/add")
    public String showAddDepartmentForm(Model model) {
        model.addAttribute("department", new Department());  // Empty department object for the form
        return "add_department";  // Thymeleaf template for adding new department
    }

    // Add a new department
    @PostMapping("/add")
    public String addDepartment(@ModelAttribute("department") Department department, RedirectAttributes redirectAttributes) {
        departmentService.addDepartment(department);
        redirectAttributes.addFlashAttribute("message", "Department added successfully!");
        return "redirect:/department";  // Redirect to department list
    }

    // Show form to edit an existing department
    @GetMapping("/edit/{id}")
    public String showEditDepartmentForm(@PathVariable long id, Model model) {
        Department department = departmentService.getDepartment(id);
        model.addAttribute("department", department);
        return "edit_department";  // Thymeleaf template for editing department
    }

    // Update an existing department
    @PostMapping("/edit/{id}")
    public String updateDepartment(@PathVariable long id, @ModelAttribute("department") Department department, RedirectAttributes redirectAttributes) {
        // Update department in the service layer
        departmentService.updateDepartment(department, id);  // Assuming you have an updateDepartment method
        redirectAttributes.addFlashAttribute("message", "Department updated successfully!");
        return "redirect:/department";  // Redirect to department list
    }

    // Delete a department

    @PostMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable long id, RedirectAttributes redirectAttributes) {
        departmentService.deleteDepartment(id);
        redirectAttributes.addFlashAttribute("message", "Department deleted successfully!");
        return "redirect:/department";  // Redirect to department list
    }
}



