package org.ndissandea.adminassist.controller;

import org.ndissandea.adminassist.model.Inventory;
import org.ndissandea.adminassist.model.Task;
import org.ndissandea.adminassist.service.InventoryService;
import org.ndissandea.adminassist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskViewController {
        @Autowired
        private TaskService taskService;

        // Show all inventory items
        @GetMapping
        public String showInventory(Model model) {
            List<Task> task = taskService.getTasksList();
            model.addAttribute("task", task);
            return "task_management";  // Thymeleaf template to task home page
        }

        // Show form to add new inventory item
        @GetMapping("/add")
        public String showAddTaskForm(Model model) {
            model.addAttribute("task", new Task());  // Empty task object for the form
            return "add_task";  // Thymeleaf template for adding new task
        }

        // Add a new task
        @PostMapping("/add")
        public String addTask(@ModelAttribute("task") Task task, RedirectAttributes redirectAttributes) {
            taskService.addTask(task);
            redirectAttributes.addFlashAttribute("message", "Task add successfully!");
            return "redirect:/task";  // Redirect to the task list
        }

        // Show form to edit an existing task item
        @GetMapping("/edit/{id}")
        public String showEditTaskForm(@PathVariable long id, Model model) {
            Task task = taskService.getTasksList().stream()
                    .filter(i -> i.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid task Id:" + id));
            model.addAttribute("task", task);
            return "edit_task";  // Thymeleaf template for editing task
        }

        // Update an existing inventory item
        @PostMapping("/edit/{id}")
        public String updateTask(@PathVariable long id, @ModelAttribute("task") Task task, RedirectAttributes redirectAttributes) {
            taskService.updateTask(task, id);
            redirectAttributes.addFlashAttribute("message", "Task updated successfully!");
            return "redirect:/Task";  // Redirect to the task home
        }

        // Delete an inventory item
        @GetMapping("/delete/{id}")
        public String deleteTask(@PathVariable long id, RedirectAttributes redirectAttributes) {
            Task task = new Task();
            task.setId(id);  // Set the id to delete the corresponding item
            taskService.removeTask(id);
            redirectAttributes.addFlashAttribute("message", "Task deleted successfully!");
            return "redirect:/Task";  // Redirect to the inventory list
        }

        //
    }

