package com.tts.NonProfitApp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Email(message = "Please proved a valid email")
    @NotEmpty(message = "Please provide an email")
    private String email;

    @Length(min = 3, message = "Your Non-Profit must have at least 3 characters")
    @Length(max = 15, message = "Your Non-Profit cannot have more than 15 characters")
    @NotEmpty(message = "Please provide a Non-Profit Name")
    private String username;

    @NotEmpty(message = "Please provide a password with at least 5 characters.")
    @Length(min = 5, message = "Your password must have at least 5 characters")
    private String password;

    private int active;

//    @Length(min = 5, message = "Your zipcode must have 5 numbers")
//    @Length(max = 5, message = "Your zipcode cannot have more than 5 numbers")
    private int zipcode;

    private String nonProfitName;
    private String address;
    private String hours;
    private String needs;
    private String logo;
    private String city;

    @CreationTimestamp
    private Date createdAt;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @ElementCollection
    private Map<Product, Integer> cart;
}
