package com.example.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Table(name = "components")
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String parameters;
    private String reason;
    @Enumerated(EnumType.STRING)
    private Status status;
    @JsonFormat(pattern = "HH:mm dd/MM/yy")
    private LocalDateTime time;

    public Component(String name, String parameters, String reason) {
        this.name = name;
        this.parameters = parameters;
        this.reason = reason;
        this.status = Status.CREATED;
        this.time = LocalDateTime.now();
    }
}
