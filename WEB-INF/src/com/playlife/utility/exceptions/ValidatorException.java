package com.playlife.utility.exceptions;

import java.util.ArrayList;


public class ValidatorException extends RuntimeException  {
	private static final long serialVersionUID = 1L;
	public int errorCode = -1;
	public Exception exception = null;
	public ArrayList<String> paras = null;
	
	public ValidatorException(int errorCode, Exception exception, ArrayList<String> paras){
		this.exception = exception;
		this.errorCode = errorCode;
		this.paras = paras;
	}
	public ValidatorException(int errorCode, Exception exception){
		this(errorCode, exception, null);
	}
	public ValidatorException(int errorCode){
		this(errorCode, null, null);
	}
	public ValidatorException(int errorCode, ArrayList<String> paras){
		this(errorCode, null, null);
		this.paras = paras;
	}	
	public ValidatorException(int errorCode, String para){
		this(errorCode, null, new ArrayList<String>());
		this.paras.add(para);
	}
	
	public Exception getException() {
		return exception;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorMessage(){
		String s_return = "";
		switch (this.getErrorCode()){
			case -50001:
				s_return = "Email is not in correct format.";
				break;	
			case -50002:
				if (paras != null && paras.get(0) != null && paras.get(1) != null)
					s_return = paras.get(1) + " Length should not be less than " + paras.get(0) + ".";
				break;
			case -50003:
				if (paras != null && paras.get(0) != null && paras.get(1) != null)
					s_return = paras.get(1) + " Length should not be more than " + paras.get(0) + ".";
				break;
			case -50004:
				s_return = paras.get(0) + " should not be empty.";
				break;	
			case -50005:
				s_return = "Date should be in mm/dd/yyyy format.";
				break;
			case -50006:
				s_return = "Date should be in mm/dd/yyyy format.";
				break;			
			default:
				s_return = "Unknown Error.";
				break;
		}
		return s_return;
	}
}
