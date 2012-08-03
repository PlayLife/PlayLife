package com.playlife.utility.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.playlife.utility.exceptions.LogicLayerException;
import com.playlife.utility.exceptions.ValidatorException;

public class DateValidator {
	public static Date validateDate_YYYYMMDD(String s_date) throws ValidatorException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
		if (s_date.trim().length() != dateFormat.toPattern().length())
			/*** TODO ***/
			throw new LogicLayerException(-50005);
		dateFormat.setLenient(false);
		
		Date date = null;
	    try {
	        date = dateFormat.parse(s_date.trim());
	    }
	    catch (ParseException ex) {
			/*** TODO ***/
			throw new LogicLayerException(-50006, ex);
	    }
	    return date;
	}
}
