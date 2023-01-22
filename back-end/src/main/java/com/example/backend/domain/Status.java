package com.example.backend.domain;

import lombok.Getter;

@Getter
public enum Status {

    CREATED("Izveidots"),
    APPROVED("Apstiprināts"),
    REJECTED("Noraidīts");

    private final String nameInLatvian;

    Status(String nameInLatvian) {
        this.nameInLatvian = nameInLatvian;
    }


}
