package org.ndissandea.adminassist.service;

import org.ndissandea.adminassist.exception.DepartmentNotFoundException;
import org.ndissandea.adminassist.model.Department;
import org.ndissandea.adminassist.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;
    private EmployeeService employeeService;
    private InventoryService inventoryService;
    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, EmployeeService employeeService, InventoryService inventoryService) {
        this.departmentRepository = departmentRepository;
        this.employeeService = employeeService;
        this.inventoryService = inventoryService;
    }



    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Department department, long id) {

        return departmentRepository.findById(id).map(st-> {
            st.setName(department.getName());
            return departmentRepository.save(st);
        }).orElse(null);
        }



    @Override
    public void deleteDepartment(long id) {
        if(!departmentRepository.existsById(id)) {
            throw new DepartmentNotFoundException("Department not found");

        }
        departmentRepository.deleteById(id);
    }

    @Override
    public Department getDepartment(long id) {
        return departmentRepository.findById(id)
                .orElseThrow(()-> new DepartmentNotFoundException("Missing Id"));
    }
}
