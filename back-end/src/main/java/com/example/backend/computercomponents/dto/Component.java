package com.example.backend.computercomponents.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String name;

    @NotBlank
    private String parameters;

    @NotBlank
    private String reason;

    private String status;

    private String time;

    public Component(String name, String parameters, String reason) {
        this.name = name;
        this.parameters = parameters;
        this.reason = reason;
        this.status = "Izveidots";
        this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public Component(long id, String name, String parameters, String reason, String status, String time) {
        this.id = id;
        this.name = name;
        this.parameters = parameters;
        this.reason = reason;
        this.status = status;
        this.time = time;
    }

    public Component() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getParameters() {
        return parameters;
    }

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Component{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parameters='" + parameters + '\'' +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
