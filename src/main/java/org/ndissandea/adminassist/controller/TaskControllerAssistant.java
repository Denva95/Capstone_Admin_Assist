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
@RequestMapping("/taskA")
public class TaskControllerAssistant {
    private final TaskService taskService;
    private final EmployeeService employeeService;
    @Autowired
    public TaskControllerAssistant(TaskService taskService, EmployeeService employeeService) {
        this.taskService = taskService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String showTask(Model model) {
        List<Task> task = taskService.getTasksList();
        model.addAttribute("task", task);
        return "tasks_management";  // Thymeleaf template to task home page
    }

    // Display Task Details
    @GetMapping("/details/{id}")
    public String showTaskDetails(@PathVariable("id")long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        model.addAttribute("employee", employeeService.getAllEmployees());
        return "task_details"; // Name of the Thymeleaf HTML file for details
    }

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


}
