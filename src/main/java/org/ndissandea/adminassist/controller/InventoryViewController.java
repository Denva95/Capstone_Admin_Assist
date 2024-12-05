package org.ndissandea.adminassist.controller;

import org.ndissandea.adminassist.model.Department;
import org.ndissandea.adminassist.model.Employee;
import org.ndissandea.adminassist.model.Inventory;
import org.ndissandea.adminassist.service.DepartmentService;
import org.ndissandea.adminassist.service.EmployeeService;
import org.ndissandea.adminassist.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/inventory")
public class InventoryViewController {
    private InventoryService inventoryService;
    private DepartmentService departmentService;
    private EmployeeService employeeService;
    @Autowired
    public InventoryViewController(InventoryService inventoryService, DepartmentService departmentService, EmployeeService employeeService) {
        this.inventoryService = inventoryService;
        this.departmentService = departmentService;
        this.employeeService = employeeService;

    };

    // Show all inventory items
    @GetMapping
    public String showInventory(Model model) {
        List<Inventory> inventory = inventoryService.getInventory();
        model.addAttribute("inventory", inventory);
        return "office_supplies";  // Thymeleaf template to list inventory
    }

    // Show form to add new inventory item
    @GetMapping("/add")
    public String showAddInventoryForm(Model model) {
        model.addAttribute("inventory", new Inventory());  // Empty inventory object for the form
        model.addAttribute("department", departmentService.getAllDepartments());
        model.addAttribute("employee", employeeService.getAllEmployees());
        return "add_item";  // Thymeleaf template for adding new inventory
    }

    // Add a new inventory item
    @PostMapping("/add")
    public String addInventory(@ModelAttribute("inventory") Inventory inventory, RedirectAttributes redirectAttributes) {
        inventoryService.addInventory(inventory);
        redirectAttributes.addFlashAttribute("message", "Inventory item added successfully!");
        return "redirect:/inventory";  // Redirect to the inventory list
    }

    // Show form to edit an existing inventory item
    @GetMapping("/edit/{id}")
    public String showEditInventoryForm(@PathVariable long id, Model model) {
        Inventory inventory = inventoryService.getInventoryById(id);
        model.addAttribute("inventory", inventory);
        model.addAttribute("department", departmentService.getAllDepartments());
        model.addAttribute("employee", employeeService.getAllEmployees());
        return "edit_item";  // Thymeleaf template for editing inventory
    }

    // Update inventory item
    @PostMapping("/edit/{id}")
    public String updateInventory(@PathVariable long id, @ModelAttribute("inventory") Inventory inventory, RedirectAttributes redirectAttributes) {
        inventoryService.updateInventory(inventory, id);
        redirectAttributes.addFlashAttribute("message", "Inventory item updated successfully!");
        return "redirect:/inventory";  // Redirect to the inventory list
    }


   @GetMapping("/delete/{id}")
   public String confirmDeleteItem(@PathVariable ("id") long id, Model model) {
       Inventory inventory = inventoryService.getInventoryById(id);
       model.addAttribute("inventory", inventory);
       return "delete_item";
   }

    @PostMapping("/delete/{id}")
    public String deleteInventory(@PathVariable("id") long id, Inventory inventory, RedirectAttributes redirectAttributes) {
        inventoryService.deleteInventory(id);
        redirectAttributes.addFlashAttribute("message", "Inventory item deleted successfully!");
        return "redirect:/inventory";
    }

    // Display Item Details
    @GetMapping("/details/{id}")
    public String showInventoryDetails(@PathVariable ("id")long id, Model model) {
        Inventory inventory = inventoryService.getInventoryById(id);
        model.addAttribute("inventory", inventory);
        return "inventory_details"; // Name of the Thymeleaf HTML file for details
    }
}

