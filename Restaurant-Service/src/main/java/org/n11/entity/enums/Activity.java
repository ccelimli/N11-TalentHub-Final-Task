package org.n11.entity.enums;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public enum Activity {
    ACTIVE("ACTIVE"),
    NOT_ACTIVE("NOT ACTIVE");

    private String context;

    Activity(String context) {
        this.context = context;
    }

    public String getContext() {
        return this.context;
    }
}
