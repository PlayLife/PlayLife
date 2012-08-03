package com.playlife.persistenceLayer.domainObject;

import com.playlife.settings.PlaceSetting;

public enum Place_Type {
	Unknown(-1), Hostel(0), Restaurant(1), Shop(2), Scenery(3);

	private int value = 0;
	Place_Type(int value){
		this.setValue(value);
	}

	public static Place_Type fromInt(int value) {    
		switch(value) {
			case 0: return Hostel;
        	case 1: return Restaurant;
        	case 2: return Shop;
        	case 3: return Scenery;
        	default:
        		return Unknown;
		}
	}
 
	public String toString() {
		switch(this) {
			case Hostel:
				return PlaceSetting.type_hostel;
			case Restaurant:
				return PlaceSetting.type_restaurant;
			case Shop:
				return PlaceSetting.type_shop;
			case Scenery:
				return PlaceSetting.type_scenery;
		}
		return PlaceSetting.type_unknown;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
