package com.playlife.utility.exceptions;

public class PresentationLayerException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public int errorCode = -1;
	public Exception exception = null;
	public String[] s_para;
	
	public PresentationLayerException(int errorCode, Exception exception, String... s_para){
		this.exception = exception;
		this.errorCode = errorCode;
		this.s_para = s_para;
	}
	public PresentationLayerException(int errorCode, String... s_para){
		this.errorCode = errorCode;
		this.s_para = s_para;
	}
	public PresentationLayerException(int errorCode, Exception exception){
		this.exception = exception;
		this.errorCode = errorCode;
	}
	public PresentationLayerException(int errorCode){
		this.errorCode = errorCode;
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
		switch (errorCode){
			case -40000:
				s_return = s_para + "is missing. I cannot understand.";
				break;		
			case -40001:
				s_return = s_para + "is missing. I cannot understand.";
				break;	
			case -40002:
				s_return = s_para + "is missing. I cannot understand.";
				break;	
			case -40003:
				s_return = s_para + "is missing. I cannot understand.";
				break;		
			case -40004:
				s_return = s_para + "is missing. I cannot understand.";
				break;
			case -40005:
				s_return = s_para + "is missing. I cannot understand.";
				break;	
			case -40006:
				s_return = s_para + "is missing. I cannot understand.";
				break;
			case -40007:
				s_return = s_para + "is missing. I cannot understand.";
				break;		
			case -40008:
				s_return = s_para + "is missing. I cannot understand.";
				break;					
			default:
				s_return = "Unknown Error.";
				break;
		}
		return s_return;
	}
}
