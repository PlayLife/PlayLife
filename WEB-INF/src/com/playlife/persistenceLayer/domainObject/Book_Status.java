package com.playlife.persistenceLayer.domainObject;

import com.playlife.settings.BookSetting;

public enum Book_Status {
	Unknown(-1), Normal(0), Reported(1), Deleted(2);

	private int value = 0;
	Book_Status(int value){
		this.setValue(value);
	}

	public static Book_Status fromInt(int value) {    
		switch(value) {
			case 0: return Normal;
        	case 1: return Reported;
        	case 2: return Deleted;
        	default:
        		return Unknown;
		}
	}
 
	public String toString() {
		switch(this) {
			case Normal:
				return BookSetting.status_normal;
			case Reported:
				return BookSetting.status_reported;
			case Deleted:
				return BookSetting.status_deleted;
		}
		return BookSetting.status_unknown;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
