package org.n11.constant;

import org.n11.utilities.general.messageService.BaseErrorMessage;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public enum ErrorMessages implements BaseErrorMessage {
    NOT_FOUND_USER("Not Found User!"),
    NOT_VALID_FIRST_NAME("Not Valid User First Name!"),
    NOT_VALID_LAST_NAME("Not Valid User Last Name!"),
    NOT_VALID_PHONE_NUMBER("Not Valid Phone Number!"),
    NOT_START_ZERO_PHONE_NUMBER("Phone number does not start 0(zero)!"),

    NOT_NULL_PHONE_NUMBER("Phone number cannot be null ");

    private String message;

    ErrorMessages(String message) {
        this.message = message;
    }


    @Override
    public String getMessage() {
        return null;
    }
}
