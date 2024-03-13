package org.n11.constant;

import org.n11.utilities.general.messageService.BaseErrorMessage;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public enum ErrorMessages implements BaseErrorMessage {
    NOT_FOUND_RESTAURANT("Not Found Restaurant"),
    EMPTY_LIST("Empty List!");
    private String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
