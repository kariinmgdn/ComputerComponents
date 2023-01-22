package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;

public record AddComponentRequest(@NotBlank String name, @NotBlank String parameters, @NotBlank String reason) {

    public AddComponentRequest(String name, String parameters, String reason) {
        this.name = name;
        this.parameters = parameters;
        this.reason = reason;
    }
}
