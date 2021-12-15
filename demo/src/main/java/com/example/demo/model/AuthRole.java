package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "Auth_roles")
public class AuthRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authRoleId;

    @Enumerated(EnumType.STRING)
    @Column(length = 25)
    private EAuthRole roleName;

    public Long getAuthRoleId() {
        return authRoleId;
    }
    public void setAuthRoleId(Long authRoleId) {
        this.authRoleId = authRoleId;
    }
    public EAuthRole getRoleName() {
        return roleName;
    }
    public void setRoleName(EAuthRole roleName) {
        this.roleName = roleName;
    }
}
