package org.ndissandea.adminassist.model;

import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity

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
    @Column(nullable = false)
    private String assignedTo;

    public Task(String taskName, String taskDescription, String taskStatus, String priority, String assignedTo) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
        this.priority = priority;
        this.assignedTo = assignedTo;
    }

}
