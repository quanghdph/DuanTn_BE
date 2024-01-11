package com.fpt.duantn.io.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table(name = "users")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, unique = true)
    String username;
    String email;
    @Column(nullable = false)
    String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    public User covertFormEmployee(EmployeeEntity employee){
        return User.builder().email(employee.getEmail()).id(employee.getId()).password(employee.getEncryptedPassword()).roles(null).build();
    }
    public User covertFormCustomer(CustomerEntity customer){
        return User.builder().email(customer.getEmail()).id(customer.getId()).password(customer.getEncryptedPassword()).roles(null).build();
}}
