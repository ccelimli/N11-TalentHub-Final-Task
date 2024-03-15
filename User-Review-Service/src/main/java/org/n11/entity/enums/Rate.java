package org.n11.entity.enums;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public enum Rate {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private final int value;

    Rate(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Rate fromValue(int value) {
        for (Rate rate : Rate.values()) {
            if (rate.getValue() == value) {
                return rate;
            }
        }
        throw new IllegalArgumentException("Invalid rate value: " + value);
    }
}
