package org.ndissandea.adminassist.model;

import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@AllArgsConstructor

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(nullable = false)
    private String taskName;
    @Column(nullable = false)
    private String taskDescription;
    @Column(nullable = false)
    private String taskStatus;
    @Column(nullable = false)
    private String priority;

    private String dueDate;
    private String addDate;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee assignedTo;



}
