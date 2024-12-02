package org.ndissandea.adminassist.service;

import org.ndissandea.adminassist.exception.departmentNotFoundException;
import org.ndissandea.adminassist.model.Department;
import org.ndissandea.adminassist.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;
    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
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
            throw new departmentNotFoundException("Department not found");

        }
        departmentRepository.deleteById(id);
    }
}
