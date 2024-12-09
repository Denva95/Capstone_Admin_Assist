package org.ndissandea.adminassist.service;

import org.ndissandea.adminassist.exception.EmployeeNotFoundException;
import org.ndissandea.adminassist.model.Employee;
import org.ndissandea.adminassist.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //Marks this class as a service the candidate for the data Spring Service, making it a candidate for component scanning and enabling dependency injection.
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    //Injects the EmployeeRepository dependency into this service implementation
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

    }


    @Override
    public Employee add(Employee employee) {
        // Saves the given employee entity to the database.
       return employeeRepository.save(employee);

    }

    //Retrieve all employee records from the database.
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    //Fetches an employee by ID. If not found, throws a custom EmployeeNotFoundException.
    @Override
    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException("Missing Id"));
    }

    @Override
    // Updates an existing employee record with new values. If not found, returns null.
    public Employee update(Employee employee, long id) {
        // Map function used to update fields of the existing employee.
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
    // Deletes an employee by ID. Throws an exception if the ID does not exist.
    public void delete(long id) {
       if(!employeeRepository.existsById(id)) {
           throw new EmployeeNotFoundException("Employee not found");
       }
       employeeRepository.deleteById(id);
    }


}
