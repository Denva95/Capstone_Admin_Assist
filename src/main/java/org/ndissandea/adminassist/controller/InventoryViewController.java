package org.ndissandea.adminassist.controller;

import org.ndissandea.adminassist.model.Inventory;
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
    @Autowired
    private InventoryService inventoryService;

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
        Inventory inventory = inventoryService.getInventory().stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid inventory Id:" + id));
        model.addAttribute("inventory", inventory);
        return "edit_item";  // Thymeleaf template for editing inventory
    }

    // Update an existing inventory item
    @PostMapping("/edit/{id}")
    public String updateInventory(@PathVariable long id, @ModelAttribute("inventory") Inventory inventory, RedirectAttributes redirectAttributes) {
        inventoryService.updateInventory(inventory, id);
        redirectAttributes.addFlashAttribute("message", "Inventory item updated successfully!");
        return "redirect:/inventory";  // Redirect to the inventory list
    }

    // Delete an inventory item
    @GetMapping("/delete/{id}")
    public String deleteInventory(@PathVariable long id, RedirectAttributes redirectAttributes) {
        Inventory inventory = new Inventory();
        inventory.setId(id);  // Set the id to delete the corresponding item
        inventoryService.deleteInventory(inventory);
        redirectAttributes.addFlashAttribute("message", "Inventory item deleted successfully!");
        return "redirect:/inventory";  // Redirect to the inventory list
    }
}

