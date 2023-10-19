package com.nagarro.javaMiniAssignment2.validators.impl;

import com.nagarro.javaMiniAssignment2.exceptions.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class EnglishAlphabetsValidatorTest {

    @InjectMocks
    private EnglishAlphabetsValidator englishAlphabetsValidator;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validateLimitSortTypeName() {
        assertTrue(englishAlphabetsValidator.validate("name","sortType"));
    }

    @Test
    void validateLimitSortTypeAge() {
        assertTrue(englishAlphabetsValidator.validate("age","sortType"));
    }

    @Test
    void validateLimitSortTypeDifferent() {
        assertThrows(CustomException.class,()->englishAlphabetsValidator.validate("different","sortType"));
    }

    @Test
    void validateLimitSortOrderEven() {
        assertTrue(englishAlphabetsValidator.validate("even","sortorder"));
    }

    @Test
    void validateLimitSortOrderOdd() {
        assertTrue(englishAlphabetsValidator.validate("odd","sortorder"));
    }

    @Test
    void validateLimitSortOrderDifferent() {
        assertThrows(CustomException.class,()->englishAlphabetsValidator.validate("different","sortorder"));
    }

    @Test
    void validateDifferent() {
        assertFalse(englishAlphabetsValidator.validate("10","something different"));
    }
}