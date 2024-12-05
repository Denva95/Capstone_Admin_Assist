package org.ndissandea.adminassist.controller;

import org.ndissandea.adminassist.model.Task;
import org.ndissandea.adminassist.service.EmployeeService;
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
    private EmployeeService employeeService;
    private TaskService taskService;
        @Autowired
       public TaskViewController(EmployeeService employeeService, TaskService taskService) {
            this.employeeService = employeeService;
            this.taskService = taskService;
        }

        // Show all inventory items
        @GetMapping
        public String showTask(Model model) {
            List<Task> task = taskService.getTasksList();
            model.addAttribute("task", task);
            return "tasks_management";  // Thymeleaf template to task home page
        }

        // Show form to add new inventory item
        @GetMapping("/add")
        public String showAddTaskForm(Model model) {
            model.addAttribute("task", new Task());  // Empty task object for the form
            model.addAttribute("employee", employeeService.getAllEmployees());
            return "add_task";  // Thymeleaf template for adding new task
        }

        // Add a new task
        @PostMapping("/add")
        public String addNewTask(@ModelAttribute("task") Task task, RedirectAttributes redirectAttributes) {
            taskService.addTask(task);
            redirectAttributes.addFlashAttribute("message", "Task add successfully!");
            return "redirect:/task";  // Redirect to the task list
        }

        // Show form to edit an existing task item
        @GetMapping("/edit/{id}")
        public String showEditTaskForm(@PathVariable long id, Model model) {
            Task task = taskService.getTaskById(id);
            model.addAttribute("task", task);
            model.addAttribute("employee", employeeService.getAllEmployees());
            return "edit_task";  // Thymeleaf template for editing task
        }

        // Update an existing inventory item
        @PostMapping("/edit/{id}")
        public String updateTask(@PathVariable long id, @ModelAttribute("task") Task task, RedirectAttributes redirectAttributes) {
            taskService.updateTask(task, id);
            redirectAttributes.addFlashAttribute("message", "Task updated successfully!");
            return "redirect:/task";  // Redirect to the task home
        }

        // Delete a task
        @GetMapping("/delete/{id}")
        public String confirmDeleteTask(@PathVariable ("id") long id, Model model) {
            Task task = taskService.getTaskById(id);
            model.addAttribute("task", task);
            return "delete_task";
        }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") long id, Task task, RedirectAttributes redirectAttributes) {
        taskService.removeTask(id);
        redirectAttributes.addFlashAttribute("message", "Inventory item deleted successfully!");
        return "redirect:/task";
    }


        // Display Task Details
        @GetMapping("/details/{id}")
        public String showTaskDetails(@PathVariable ("id")long id, Model model) {
            Task task = taskService.getTaskById(id);
            model.addAttribute("task", task);
            model.addAttribute("employee", employeeService.getAllEmployees());
            return "task_details"; // Name of the Thymeleaf HTML file for details
        }
    }

