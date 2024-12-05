package org.ndissandea.adminassist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ndissandea.adminassist.model.Employee;
import org.ndissandea.adminassist.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
public class TestEmployees {
        @Autowired
        private EmployeeRepository employeeRepository;

        private Employee emp;
        private Employee emp2;

        @Transactional
        @BeforeEach
        void setUp() {
            // Create and save employee 1
            emp = new Employee();
            emp.setFirstName("Jae");
            emp.setLastName("Dt");
            emp.setEmail("jt@d.com");
            emp.setStartDate("12/03/2023");
            emp.setPosition("Assistant Manager");
            emp.setHolidayBalance(0);
            emp.setPhone("0707062127");
            emp.setSalary(8000);
            emp = employeeRepository.save(emp);

            // Create and save employee 2
            emp2 = new Employee();
            emp2.setFirstName("Ndissa");
            emp2.setLastName("Dea");
            emp2.setEmail("d@dat.com");
            emp2.setStartDate("12/03/2023");
            emp2.setPosition("Manager");
            emp2.setHolidayBalance(0);
            emp2.setPhone("0707062127");
            emp2.setSalary(9000);
            emp2 = employeeRepository.save(emp2);
        }

        @Test
        void testSaveEmployee() {
            assertNotNull(emp.getId(), "Saved employee ID should not be null");
            assertNotNull(emp2.getId(), "Saved employee 2 ID should not be null");
            assertEquals("Jame", emp.getFirstName());
            assertEquals("Ndissan", emp2.getFirstName());
        }

        @Test
        void testGetEmployeeById() {
            Employee retrievedEmp = employeeRepository.getById(emp.getId());
            assertNotNull(retrievedEmp, "Retrieved employee should not be null");
            assertEquals(emp.getId(), retrievedEmp.getId());

            Employee retrievedEmp2 = employeeRepository.getById(emp2.getId());
            assertNotNull(retrievedEmp2, "Retrieved employee 2 should not be null");
            assertEquals(emp2.getId(), retrievedEmp2.getId());
        }
    }


