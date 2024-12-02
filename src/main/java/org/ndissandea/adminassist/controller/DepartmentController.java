package org.ndissandea.adminassist.controller;

import org.ndissandea.adminassist.exception.departmentNotFoundException;
import org.ndissandea.adminassist.model.Department;
import org.ndissandea.adminassist.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

    @RestController
    @RequestMapping("/api/departments") // Base URL for department-related endpoints
    public class DepartmentController {

        private DepartmentService departmentService;

        @Autowired
        public DepartmentController(DepartmentService departmentService) {
            this.departmentService = departmentService;
        }

        // Get all departments
        @GetMapping
        public ResponseEntity<List<Department>> getAllDepartments() {
            List<Department> departments = departmentService.getAllDepartments();
            return ResponseEntity.ok(departments);
        }

        // Add a new department
        @PostMapping
        public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
            departmentService.addDepartment(department);
            return ResponseEntity.ok(department);
        }

        // Update an existing department
        @PutMapping("/{id}")
        public ResponseEntity<Department> updateDepartment(
                @PathVariable long id,
                @RequestBody Department department) {
            Department updatedDepartment = departmentService.updateDepartment(department, id);
            if (updatedDepartment == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedDepartment);
        }

        // Delete a department
        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteDepartment(@PathVariable long id) {
            try {
                departmentService.deleteDepartment(id);
                return ResponseEntity.ok("Department deleted successfully.");
            } catch (departmentNotFoundException ex) {
                return ResponseEntity.status(404).body(ex.getMessage());
            }
        }
    }
