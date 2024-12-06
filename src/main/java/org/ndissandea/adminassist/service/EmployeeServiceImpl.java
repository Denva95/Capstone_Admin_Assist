package org.ndissandea.adminassist.service;

import org.ndissandea.adminassist.exception.EmployeeNotFoundException;
import org.ndissandea.adminassist.model.Employee;
import org.ndissandea.adminassist.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

    }


    @Override
    public Employee add(Employee employee) {
       return employeeRepository.save(employee);

    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException("Missing Id"));
    }

    @Override
    public Employee update(Employee employee, long id) {
        return employeeRepository.findById(id).map(em-> {
            em.setFirstName(employee.getFirstName());
            em.setLastName(employee.getLastName());
            em.setEmail(employee.getEmail());
            em.setPhone(employee.getPhone());
            em.setSalary(employee.getSalary());
            em.setPosition(employee.getPosition());
            em.setDepartment(employee.getDepartment());
            em.setPosition(employee.getPosition());
            em.setHolidayBalance(employee.getHolidayBalance());
            return employeeRepository.save(em);
        }).orElse(null) ;


    }


    @Override
    public void delete(long id) {
       if(!employeeRepository.existsById(id)) {
           throw new EmployeeNotFoundException("Employee not found");
       }
       employeeRepository.deleteById(id);
    }


}
