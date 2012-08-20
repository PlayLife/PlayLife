package com.playlife.utility.exceptions;

import java.util.Locale;

import org.springframework.context.MessageSource;


public class PresentationException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public int errorCode = -1;
	public Exception exception = null; 
	public String[] s_para;
	public String msg;
	
	public PresentationException (int errorCode, Exception exception, String... s_para){
		this.exception = exception;
		this.errorCode = errorCode;
		this.s_para = s_para;
	}
	public PresentationException (int errorCode, String... s_para){
		this.errorCode = errorCode;
		this.s_para = s_para;
	}
	public PresentationException (int errorCode, Exception exception){
		this.exception = exception;
		this.errorCode = errorCode;
	}
	public PresentationException (int errorCode){
		this.errorCode = errorCode;
	}
	public PresentationException (int errorCode, String message){
		this.errorCode = errorCode;
		this.msg = message;
	}
	public PresentationException (){
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
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getErrorMessage(MessageSource messageSource, Locale locale){
		try {
			String errorName = "validation." + Math.abs(errorCode);
			return messageSource.getMessage(errorName, null, locale) + " (Error Code : " + errorCode + ")";
		} catch (Exception ex){
			return messageSource.getMessage("error.unknown", null, locale) + " (Error Code : " + errorCode + ")";
		}
	}
}
