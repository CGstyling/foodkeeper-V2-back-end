package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "Auth_roles")
public class AuthRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorityRoleId;

    @Enumerated(EnumType.STRING) //make string from the roles
    @Column(length = 25)
    private EAuthRole roleName;

}
