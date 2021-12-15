package com.example.demo.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class UserSignUpRequest {

    @NotBlank
    @Size(min = 3, max =30)
    private String username;

    @NotBlank
    @Size(min = 8)
    private String password;

    @NotBlank
    @Size(max =50)
    @Email
    private String email;

    private Set<String> authRole;

    public UserSignUpRequest() {
    }
    public UserSignUpRequest(String username, String password, String email, Set<String> authRole) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.authRole = authRole;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Set<String> getAuthRole() {
        return authRole;
    }
    public void setAuthRole(Set<String> authRole) {
        this.authRole = authRole;
    }
}
