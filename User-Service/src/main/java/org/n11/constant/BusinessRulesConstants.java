package org.n11.constant;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public enum BusinessRulesConstants {

    EMAIL_REGEX("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"),
    FIRST_NAME_LAST_NAME_REGEX("^[a-zA-ZçÇğĞıİöÖşŞüÜ]+$"),
    PHONE_NUMBER_REGEX("^\\+(?:[0-9] ?){6,14}[0-9]$");

    private String context;

    BusinessRulesConstants(String context) {
        this.context = context;
    }

    public String getContext() {
        return context;
    }
    }
