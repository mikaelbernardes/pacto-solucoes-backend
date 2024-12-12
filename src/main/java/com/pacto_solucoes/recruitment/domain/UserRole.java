package com.pacto_solucoes.recruitment.domain;

public enum UserRole {
    RECRUITER("recruiter"),
    CANDIDATE("candidate");

    private final String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
