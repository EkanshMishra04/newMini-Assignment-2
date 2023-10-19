package com.nagarro.javaMiniAssignment2.validators.impl;

import com.nagarro.javaMiniAssignment2.exceptions.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class NumericValidatorTest {

    @InjectMocks
    private NumericValidator numericValidator;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validateLimit() {
        assertTrue(numericValidator.validate("3","limit"));
    }

    @Test
    void validateLimitThrowsException() {
        assertThrows(CustomException.class,()->numericValidator.validate("8","limit"));
    }

    @Test
    void validateOffset() {
        assertTrue(numericValidator.validate("6","offset"));
    }

    @Test
    void validateOffsetThrowsException() {
        assertThrows(CustomException.class,()->numericValidator.validate("-1","offset"));
    }

    @Test
    void validateThrowsNumberFormatExceptionException() {
        assertThrows(CustomException.class,()->numericValidator.validate("Number","offset"));
    }

    @Test
    void validateNotLimitNotOffset() {
        assertFalse(numericValidator.validate("10","something different"));
    }
}