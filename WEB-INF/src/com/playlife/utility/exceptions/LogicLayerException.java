package com.playlife.utility.exceptions;

public class LogicLayerException extends RuntimeException  {
	private static final long serialVersionUID = 1L;
	public int errorCode = -1;
	public Exception exception = null;
	
	public LogicLayerException(int errorCode, Exception exception){
		this.exception = exception;
		this.errorCode = errorCode;
	}
	public LogicLayerException(int errorCode){
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
			case -30000:
				s_return = "Incorrect Email.";
				break;
			case -30001:
				s_return = "Incorrect Password.";
				break;
			case -30002:
				s_return = "Incorrect Username.";
				break;
			case -30003:
				s_return = "No correct access token.";
				break;
			case -30004:
				s_return = "Access token expired.";
				break;				
			case -30005:
				s_return = "Email already registered.";
				break;	
			case -30006:
				s_return = "Server error. Please contact adminstrator.";
				break;	
			case -30007:
				s_return = "cannot register right now.";
				break;				
			case -30008:
				s_return = "No correct access token.";
				break;
			case -30009:
				s_return = "Access token expired.";
				break;
			case -30010:
				s_return = "Incorrect Username or password.";
				break;	
			case -30011:
				s_return = "Cannot login right now.";
				break;	
			case -30012:
				s_return = "Cannot logout right now.";
				break;		
			case -30013:
				s_return = "Access Token were full. Please contact administrator.";
				break;
			case -30014:
				s_return = "Cannot get access token.";
				break;		
			case -30015:
				s_return = "User does not exist.";
				break;		
			case -30016:
				s_return = "Cannot get user info.";
				break;		
			case -30017:
				s_return = "User does not exist.";
				break;	
			case -30018:
				s_return = "Cannot create book.";
				break;	
			case -30019:
				s_return = "Book not found.";
				break;
			case -30020:
				s_return = "Cannot modify book.";
				break;	
			case -30021:
				s_return = "Cannot delete book.";
				break;	
			case -30022:
				s_return = "Book not found.";
				break;	
			case -30023:
				s_return = "Sheet not found.";
				break;
			case -30024:
				s_return = "Sheet not found.";
				break;
			case -30025:
				s_return = "Cannot create photo content.";
				break;		
			case -30026:
				s_return = "Cannot delete photo content.";
				break;					
			case -30027:
				s_return = "Photo Content not found.";
				break;		
			case -30028:
				s_return = "Book not found.";
				break;
			case -30029:
				s_return = "Book not found.";
				break;	
			case -30030:
				s_return = "Category not found.";
				break;	
			case -30031:
				s_return = "Cannot create sheet.";
				break;		
			case -30032:
				s_return = "Sheet not found.";
				break;
			case -30033:
				s_return = "Category not found.";
				break;
			case -30034:
				s_return = "Cannot modify sheet.";
				break;				
			case -30035:
				s_return = "Cannot delete sheet.";
				break;
			case -30036:
				s_return = "Sheet not found.";
				break;	
			case -30037:
				s_return = "Sheet not found.";
				break;	
			case -30038:
				s_return = "Cannot add comment.";
				break;	
			case -30039:
				s_return = "Rate should be 0-10.";
				break;	
			case -30040:
				s_return = "sheet not found.";
				break;	
			case -30041:
				s_return = "You already rated.";
				break;		
			case -30042:
				s_return = "Cannot rate right now.";
				break;	
			case -30043:
				s_return = "Sheet not found.";
				break;	
			case -30044:
				s_return = "Sheet not found.";
				break;
			case -30045:
				s_return = "Cannot create text content right now.";
				break;		
			case -30046:
				s_return = "text content not found.";
				break;	
			case -30047:
				s_return = "Cannot delete text content right now.";
				break;	
			case -30048:
				s_return = "Cannot modify text content right now.";
				break;	
			case -30049:
				s_return = "text content not found.";
				break;
			case -30050:
				s_return = "cannot create category right now.";
				break;	
			case -30051:
				s_return = "sheet not found.";
				break;
			case -30052:
				s_return = "category not found.";
				break;
			case -30053:
				s_return = "Cannot add category to sheet right now.";
				break;	
			case -30054:
				s_return = "No Category.";
				break;
			case -30055:
				s_return = "Cannot get category right now.";
				break;	
			case -30056:
				s_return = "Cannot create location right now.";
				break;	
			case -30057:
				s_return = "Cannot add location to sheet right now.";
				break;	
			case -30058:
				s_return = "Location not found.";
				break;	
			case -30059:
				s_return = "sheet not found.";
				break;
			case -30060:
				s_return = "Cannot get all location right now.";
				break;	
			case -30061:
				s_return = "No uid.";
				break;					
			default:
				s_return = "Unknown Error.";
				break;
		}
		return s_return;
	}
}
