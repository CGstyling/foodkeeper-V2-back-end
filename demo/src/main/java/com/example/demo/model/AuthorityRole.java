package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "Autority_roles")
public class AuthorityRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorityRoleId;

    @Enumerated(EnumType.STRING) //make string from the roles
    @Column(length = 25)
    private EAuthorityRole roleName;

}
