package com.project.travello_backend.RequestEntity;

import com.project.travello_backend.Entity.Role;

public class AuthenticationResponse {
    private String token;

    private Integer customerId;

    private Role role;



    public AuthenticationResponse() {
    }


    public AuthenticationResponse(String token, Integer customerId, Role role) {
        this.token = token;
        this.customerId = customerId;
        this.role = role;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "token='" + token + '\'' +
                ", customerId=" + customerId +
                ", role='" + role + '\'' +
                '}';
    }
}
