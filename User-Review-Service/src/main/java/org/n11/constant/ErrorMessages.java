package org.n11.constant;

import org.n11.utilities.general.messageService.BaseErrorMessage;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public enum ErrorMessages implements BaseErrorMessage {
    NOT_FOUND_ITEM("Not Found Item");

    private String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
