package com.playlife.utility.validators;

import java.util.ArrayList;
import java.util.regex.Pattern;

import com.playlife.utility.exceptions.ValidatorException;

public class StringValidator {
	public static void validateRegx_email(String email) throws ValidatorException{
		Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");
		if (!pattern.matcher(email).matches())
			throw new ValidatorException(-50001);
	}
	public static void validateLength_min(String string, int len, String field) throws ValidatorException{
		if (string.length() < len){
			ArrayList<String> paras = new ArrayList<String>();
			paras.add(Integer.toString(len)); paras.add(field);
			throw new ValidatorException(-50002, paras);
		}
	}
	public static void validateLength_max(String string, int len, String field) throws ValidatorException{
		if (string.length() > len){
			ArrayList<String> paras = new ArrayList<String>();
			paras.add(Integer.toString(len)); paras.add(field);
			throw new ValidatorException(-50003, paras);
		}
	}
	public static void validateEmpty(String string, String field) throws ValidatorException{
		if (string.trim().isEmpty())
			throw new ValidatorException(-50004, field);
	}
}
