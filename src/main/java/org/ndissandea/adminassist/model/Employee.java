package org.ndissandea.adminassist.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity
@Table (name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String position;
    private int holidayBalance = 0;
    @Column(nullable = false, length = 15)
    private String phone;
    @Column(nullable = false)
    private double salary;
    private String startDate;

    // Many Employees can belong to one Department
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;


    // Parameterized Constructor
   /* public Employee(long id, String firstName, String lastName, String email, String position, int holidayBalance, String phone, double salary, LocalDate startDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.position = position;
        this.holidayBalance = holidayBalance;
        this.phone = phone;
        this.salary = salary;
        this.startDate = startDate;

    }
    */

}









