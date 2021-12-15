package com.example.demo.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserLoginRequest {

    @NotEmpty
    private String username;

    @NotEmpty
    @Size(min = 8)
    private String password;

    public UserLoginRequest() {
    }

    public UserLoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
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
}
