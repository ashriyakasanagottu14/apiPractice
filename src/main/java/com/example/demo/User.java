package com.example.demo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

public class User{
    @Min(value = 1, message = "ID must be between 1 and 2147483647")
    @Max(value = 2147483647, message = "ID must be between 1 and 2147483647")
    private Integer id;
    @NotBlank
    private String name;

    @NotBlank
    @jakarta.validation.constraints.Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[a-z]+\\.[a-z]+$",
            message = "Email must be a valid gmail.com address"
        )
    @Schema(type = "string", format = "email", description = "User's email address (must end with @gmail.com)")
    private String email;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}