package org.ndissandea.adminassist.controller;

import org.ndissandea.adminassist.model.Task;
import org.ndissandea.adminassist.service.TaskService;
import org.ndissandea.adminassist.exception.taskNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Create a new task
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.add(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    // Get all tasks
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // Get a specific task by ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable long id) {
        return taskService.getTasks().stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .map(task -> new ResponseEntity<>(task, HttpStatus.OK))
                .orElseThrow(() -> new taskNotFoundException("Task with ID " + id + " not found"));
    }

    // Update a task
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable long id, @RequestBody Task updatedTask) {
        Task task = taskService.update(updatedTask, id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    // Delete a task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable long id) {
        taskService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}