package com.nagarro.javaMiniAssignment2.validators;

import com.nagarro.javaMiniAssignment2.exceptions.CustomException;

public class EnglishAlphabetsValidator implements Validator {
	private static final EnglishAlphabetsValidator instance = new EnglishAlphabetsValidator();

	private EnglishAlphabetsValidator() {
	}

	public static EnglishAlphabetsValidator getInstance() {
		return instance;
	}

	public boolean validate(String input, String type) {
		if (type.equalsIgnoreCase("sortType")) {
			if (input.equalsIgnoreCase("name") || input.equalsIgnoreCase("age")) {
				return true;
			}
			throw new CustomException("Invalid type for parameter sort type", 400);
		} else if (type.equalsIgnoreCase("sortorder")) {
			if (input.equalsIgnoreCase("even") || input.equalsIgnoreCase("odd")) {
				return true;
			}
			throw new CustomException("Invalid type for parameter sort order", 400);
		}
		return false;
	}
}
