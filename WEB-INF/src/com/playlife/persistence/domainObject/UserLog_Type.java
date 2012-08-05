package com.playlife.persistence.domainObject;

import com.playlife.settings.LogSetting;

public enum UserLog_Type {
	UNKNOWN(-1), REGISTER(0), LOGIN(1), ERROR(2);

	private int value = 0;
	UserLog_Type(int value){
		this.setValue(value);
	}

	public static UserLog_Type fromInt(int value) {    
		switch(value) {
			case 0: return REGISTER;
        	case 1: return LOGIN;
        	case 2: return ERROR;
        	default:
        		return UNKNOWN;
		}
	}
 
	public String toString() {
		switch(this) {
			case REGISTER:
				return LogSetting.TYPE_REGISTER;
			case LOGIN:
				return LogSetting.TYPE_LOGIN;
			case ERROR:
				return LogSetting.TYPE_ERROR;
		}
		return LogSetting.TYPE_UNKNOWN;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}