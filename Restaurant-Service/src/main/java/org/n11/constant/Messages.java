package org.n11.constant;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public enum Messages {
    SUCCESSFUL_DELETE("Restaurant Deleted!");

    private String context;

    Messages(String context) {
        this.context = context;
    }

    public String getContext() {
        return context;
    }
}
