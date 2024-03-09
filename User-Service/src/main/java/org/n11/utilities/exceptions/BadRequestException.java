package org.n11.utilities.exceptions;

import org.n11.utilities.general.messageService.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)

public class BadRequestException extends BusinessException{
    public BadRequestException(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }
}
