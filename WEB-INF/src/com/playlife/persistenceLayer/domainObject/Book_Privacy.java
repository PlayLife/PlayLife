package com.playlife.persistenceLayer.domainObject;

import com.playlife.settings.BookSetting;

public enum Book_Privacy {
	Unknown(-1), Public(0), Protected(1), Private(2);

	private int value = 0;
	Book_Privacy(int value){
		this.setValue(value);
	}

	public static Book_Privacy fromInt(int value) {    
		switch(value) {
			case 0: return Public;
        	case 1: return Protected;
        	case 2: return Private;
        	default:
        		return Unknown;
		}
	}
 
	public String toString() {
		switch(this) {
			case Public:
				return BookSetting.privacy_public;
			case Protected:
				return BookSetting.privacy_protected;
			case Private:
				return BookSetting.privacy_private;
		}
		return BookSetting.privacy_unknown;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
