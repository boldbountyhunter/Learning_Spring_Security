package com.example.springSecurity.demo;

public class JWTResponse {
    private String jwt;

    public JWTResponse(String jwt) {
        this.jwt = jwt;
    }

    public JWTResponse() {
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
