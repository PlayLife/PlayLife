package com.playlife.utility.exceptions;


public class PersistenceLayerException extends RuntimeException  {
	private static final long serialVersionUID = 1L;
	public int errorCode = -1;
	public Exception exception = null;
	
	public PersistenceLayerException(int errorCode, Exception exception){
		this.exception = exception;
		this.errorCode = errorCode;
	}
	public PersistenceLayerException(int errorCode){
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
		String s_return;
		switch (this.getErrorCode()){
			case -20000:
				s_return = "Session Factory Exception in Hibernate Utility.";
				break;
			case -20001:
				s_return = "More than one access token";
				break;	
			case -20002:
				s_return = "Hibernate cannot start operation";
				break;	
			case -20003:
				s_return = "Hibernate cannot perform flush operation";
				break;	
			case -20004:
				s_return = "Hibernate cannot perform clear operation";
				break;
			case -20005:
				s_return = "Hibernate cannot perform merge operation";
				break;
			case -20006:
				s_return = "Hibernate cannot perform save or update operation";
				break;	
			case -20007:
				s_return = "Hibernate cannot perform delete operation";
				break;
			case -20008:
				s_return = "Hibernate cannot perform get operation";
				break;	
			case -20009:
				s_return = "Hibernate cannot perform find operation";
				break;	
			case -20010:
				s_return = "Hibernate cannot perform find all operation";
				break;	
			case -20011:
				s_return = "Hibernate cannot perform find by name parameter operation";
				break;	
			case -20012:
				s_return = "Hibernate cannot perform find by name parameter with unknown type";
				break;
			case -20013:
				s_return = "Hibernate cannot perform find with unknown type";
				break;	
			case -20014:
				s_return = "Hibernate cannot perform execute by name parameter with unknown type";
				break;
			case -20015:
				s_return = "Hibernate cannot perform execute by name parameter operation";
				break;	
			case -20016:
				s_return = "Hibernate cannot perform execute SQL by name parameter operation";
				break;					
			default:
				s_return = "Unknown Error.";
				break;
		}
		return s_return;
	}
}
