package org.ndissandea.adminassist.service;

import org.ndissandea.adminassist.model.Employee;

import java.util.List;

public interface EmployeeService {
    public Employee add(Employee employee);
    public void delete(long id);
    public Employee update(Employee employee, long id);
    public List<Employee> getAllEmployees();
    public Employee getEmployeeById(long id);
}
