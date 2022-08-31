package com.svartberg.springbootrest.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusType {
    INPROGRESS("IN PROGRESS"), UPLOADED("UPLOADED"), COMPLETED("COMPLETED");

    StatusType(String value) {
    }

    @Override
    public String toString() {
        return name();
    }
}
