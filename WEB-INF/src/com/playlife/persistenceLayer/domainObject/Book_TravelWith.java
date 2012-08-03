package com.playlife.persistenceLayer.domainObject;

import com.playlife.settings.BookSetting;

public enum Book_TravelWith {
	Unknown(-1), Single(0), Couple(1), Family(2), Friends(3);

	private int value = 0;
	Book_TravelWith(int value){
		this.setValue(value);
	}

	public static Book_TravelWith fromInt(int value) {    
		switch(value) {
			case 0: return Single;
        	case 1: return Couple;
        	case 2: return Family;
        	case 3: return Friends;
        	default:
        		return Unknown;
		}
	}
 
	public String toString() {
		switch(this) {
			case Single:
				return BookSetting.travelWith_single;
			case Couple:
				return BookSetting.travelWith_couple;
			case Family:
				return BookSetting.travelWith_family;
			case Friends:
				return BookSetting.travelWith_friends;
		}
		return BookSetting.travelWith_unknown;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
