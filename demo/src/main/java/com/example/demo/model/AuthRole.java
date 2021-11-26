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

    public Long getAuthorityRoleId() {
        return authorityRoleId;
    }

    public void setAuthorityRoleId(Long authorityRoleId) {
        this.authorityRoleId = authorityRoleId;
    }

    public EAuthRole getRoleName() {
        return roleName;
    }

    public void setRoleName(EAuthRole roleName) {
        this.roleName = roleName;
    }
}
