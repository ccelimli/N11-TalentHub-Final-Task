package org.n11.constant;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public enum Messages
{
    USER_DELETED("Removed User from database");
    private String message;

     Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
