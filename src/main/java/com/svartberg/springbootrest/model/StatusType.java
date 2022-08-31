package com.svartberg.springbootrest.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusType {
    INPROGRESS, UPLOADED, COMPLETED;

    @Override
    public String toString() {
        return name();
    }
}
