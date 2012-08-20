package com.playlife.persistence.domainObject;

public enum User_Type {
	UNKNOWN(-1), NORMAL(0), FACEBOOK(1);
	
	User_Type(int value){
	}
	
	public static User_Type fromInt(int value) {    
         switch(value) {
             case 0: return NORMAL;
             case 1: return FACEBOOK;
             default:
                     return UNKNOWN;
         }
    }
	
	public static User_Type fromString(String s){
		if (s.equalsIgnoreCase("normal"))
			return NORMAL;
		else if (s.equalsIgnoreCase("facebook"))
			return FACEBOOK;
		else
			return UNKNOWN;
	}
}
