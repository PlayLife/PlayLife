package com.playlife.utility.validators;

import java.util.regex.Pattern;

import com.playlife.utility.exceptions.ValidationException;

public class StringValidator {
	public static void validateRegx_email(String email) throws ValidationException{
		Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");
		if (!pattern.matcher(email).matches())
			throw new ValidationException(-50001);
	}
	public static void validateLength_min(String string, int len, String field) throws ValidationException{
		if (string.length() < len){
			throw new ValidationException(-50003, Integer.toString(len), field);
		}
	}
	public static void validateLength_max(String string, int len, String field) throws ValidationException{
		if (string.length() > len){
			throw new ValidationException(-50003, Integer.toString(len), field);
		}
	}
	public static void validateEmpty(String string, String field) throws ValidationException{
		if (string.trim().isEmpty())
			throw new ValidationException(-50004, field);
	}
}
