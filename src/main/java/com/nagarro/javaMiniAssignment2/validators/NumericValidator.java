package com.nagarro.javaMiniAssignment2.validators;

import com.nagarro.javaMiniAssignment2.exceptions.CustomException;

//NumericValidator.java
public class NumericValidator implements Validator {
	private static final NumericValidator instance = new NumericValidator();

	private NumericValidator() {
	}

	public static NumericValidator getInstance() {
		return instance;
	}

	public boolean validate(String input, String type) {
		try {
			Integer intInput = Integer.parseInt(input);
			if (type.equalsIgnoreCase("limit")) {
				if (intInput > 0 && intInput <= 5) {
					return true;
				}
				throw new CustomException("Invalid value for parameter Limit", 400);
			} else if (type.equalsIgnoreCase("offset")) {
				if (intInput >= 0) {
					return true;
				}
				throw new CustomException("Invalid value for parameter Offset ", 400);
			}
		} catch (NumberFormatException e) {
			throw new CustomException("Invalid value for parameter " + type, 400);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), 400);
		}
		return false;
	}
}
