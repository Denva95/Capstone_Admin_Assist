package org.ndissandea.adminassist.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String userName;

    @NotEmpty(message = "Required")
    private String password;

    @Column(nullable = false, unique = true)
    private String email;
@Enumerated(EnumType.STRING)
   private  Role role;

}