package org.n11.entity.enums;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public enum Status {
    OPEN("OPEN"),
    CLOSE("CLOSE");

    private String context;

    Status(String context){
        this.context=context;
    }

    public String getContext() {
        return context;
    }
}
