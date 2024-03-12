package org.n11.util.helper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.n11.utilities.helper.BusinessRules.RegularExpression;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@ExtendWith(MockitoExtension.class)
public class RegularExpressionTest {
    @Test
    public void testControlFirstNameAndLastName_ValidNames() {
        // Given
        String firstName = "John";
        String lastName = "Doe";

        // When
        boolean result = org.n11.utilities.helper.BusinessRules.RegularExpression.controlFirstNameAndLastName(firstName, lastName);

        // Then
        assertTrue(result);
    }

    @Test
    public void testControlFirstNameAndLastName_InvalidFirstName() {
        // Given
        String firstName = "John123";
        String lastName = "Doe";

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> org.n11.utilities.helper.BusinessRules.RegularExpression.controlFirstNameAndLastName(firstName, lastName));
    }

    @Test
    public void testControlFirstNameAndLastName_InvalidLastName() {
        // Given
        String firstName = "John";
        String lastName = "Doe123";

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> org.n11.utilities.helper.BusinessRules.RegularExpression.controlFirstNameAndLastName(firstName, lastName));
    }

    @Test
    public void testControlEmail_ValidEmail() {
        // Given
        String email = "john.doe@example.com";

        // When
        boolean result = RegularExpression.controlEmail(email);

        // Then
        assertTrue(result);
    }

    @Test
    public void testControlEmail_InvalidEmail() {
        // Given
        String email = "john.doe@example";

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> RegularExpression.controlEmail(email));
    }

    @Test
    public void testControlEmail_EmptyEmail() {
        // Given
        String email = "";

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> RegularExpression.controlEmail(email));
    }

    @Test
    public void testControlEmail_NullEmail() {
        // Given
        String email = null;

        // When & Then
        assertThrows(NullPointerException.class, () -> RegularExpression.controlEmail(email));
    }
}
