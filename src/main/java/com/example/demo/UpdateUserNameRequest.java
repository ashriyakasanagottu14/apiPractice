package com.example.demo;

import jakarta.validation.constraints.NotBlank;

public class UpdateUserNameRequest {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
