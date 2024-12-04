package org.ndissandea.adminassist.service;

import org.ndissandea.adminassist.model.Department;

import java.util.List;

public interface DepartmentService {
    public List<Department> getAllDepartments();
    public Department addDepartment(Department department);
    public Department updateDepartment(Department department, long id);
    public  void deleteDepartment(long id);
    public Department getDepartment(long id);

}
