package org.n11.utilities.helper;

import org.n11.constant.BusinessRulesConstants;
import org.n11.constant.ErrorMessages;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@Service
public class BusinessRules {
    public static boolean controlFirstNameAndLAstName(String firstName, String lastName) {
        Pattern pattern = Pattern.compile(BusinessRulesConstants.FIRST_NAME_LAST_NAME_REGEX.getContext());
        if (!pattern.matcher(firstName).matches()) {
            throw new IllegalArgumentException(ErrorMessages.NOT_VALID_FIRST_NAME.getMessage());
        }

        else if (!pattern.matcher(lastName).matches()){
            throw new IllegalArgumentException(ErrorMessages.NOT_VALID_LAST_NAME.getMessage());
        }
        else{
            return true;
        }
    }

    public static boolean controlEmail(String email) {
        Pattern pattern = Pattern.compile(BusinessRulesConstants.EMAIL_REGEX.getContext());
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException(ErrorMessages.NOT_VALID_FIRST_NAME.getMessage());
        }
        else{
            return true;
        }
    }

    public static boolean controlPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile(BusinessRulesConstants.PHONE_NUMBER_REGEX.getContext());
        if (phoneNumber.startsWith("0")){
            throw new IllegalArgumentException(ErrorMessages.NOT_START_ZERO_PHONE_NUMBER.getMessage());
        }
        if (!pattern.matcher(phoneNumber).matches()) {
            throw new IllegalArgumentException(ErrorMessages.NOT_VALID_PHONE_NUMBER.getMessage());
        }
        else{
            return true;
        }
    }
}
