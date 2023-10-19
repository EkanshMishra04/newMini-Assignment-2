package com.nagarro.javaMiniAssignment2.validators.factory;

import com.nagarro.javaMiniAssignment2.exceptions.CustomException;

import com.nagarro.javaMiniAssignment2.validators.Validator;
import com.nagarro.javaMiniAssignment2.validators.impl.EnglishAlphabetsValidator;
import com.nagarro.javaMiniAssignment2.validators.impl.NumericValidator;
import org.springframework.http.HttpStatus;

public class ValidatorFactory {
	
	public static Validator getValidator(Object input) {
		if (input instanceof String) {
	        String strInput = (String) input;
	        if (strInput.matches("^\\d+$")) {
	            return NumericValidator.getInstance();
	        } else {
	            return EnglishAlphabetsValidator.getInstance();
	        }
	    } else if (input instanceof Integer) {
	        return NumericValidator.getInstance();
	    } else {
	        throw new CustomException("Unsupported input type", HttpStatus.BAD_REQUEST.value());
	    }
	}
	
}
