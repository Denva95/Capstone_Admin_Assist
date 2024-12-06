package org.ndissandea.adminassist.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

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
    @NotBlank
    @Pattern(regexp = "\\d{10,15}")
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
    public Employee(String firstName, String lastName, String email, String position, int holidayBalance, String phone, double salary, String startDate) {
       // this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.position = position;
        this.holidayBalance = holidayBalance;
        this.phone = phone;
        this.salary = salary;
        this.startDate = startDate;

    }


}









