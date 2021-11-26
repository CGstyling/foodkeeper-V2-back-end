package com.example.demo.dto.response;

import java.util.List;

public class AuthenticationResponse {

    private String token;
    private String type = "Bearer";
    private Long userId;
    private String username;
    private String email;
    private List<String> authorityRole;

    public AuthenticationResponse(String accessToken, Long userId, String username, String email, List<String> authorityRole) {
        this.token = accessToken;
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.authorityRole = authorityRole;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getAuthorityRole() {
        return authorityRole;
    }

    public void setAuthorityRole(List<String> authorityRole) {
        this.authorityRole = authorityRole;
    }
}
