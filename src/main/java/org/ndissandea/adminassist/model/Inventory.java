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
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private String status; //"In Stock", "Out of Stock", "In Use"
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String assignTo;
    @Column(nullable = false)
    private String addedDate;

    // Many Inventory items can belong to one Department
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

   /* public Inventory(String name, int quantity, String status, String description, String assignTo, String addedDate) {
        this.name = name;
        this.quantity = quantity;
        this.status = status;
        this.description = description;
        this.assignTo = assignTo;
        this.addedDate = addedDate;
    }
*/

}


