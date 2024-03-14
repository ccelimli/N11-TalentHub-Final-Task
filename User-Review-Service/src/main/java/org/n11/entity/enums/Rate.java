package org.n11.entity.enums;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public enum Rate {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5");

    private String context;

    Rate(String context) {
        this.context = context;
    }

    public String getContext() {
        return context;
    }
}
