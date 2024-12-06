package org.ndissandea.adminassist.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column
    private int quantity;
    @Column(nullable = false)
    private String status; //"In Stock", "Out of Stock", "In Use"
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String addedDate;
    private String AssignedDate;

    // Many Inventory items can belong to one Department
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department AssignTo;


}


