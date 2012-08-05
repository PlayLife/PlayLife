package com.playlife.persistence.domainObject;

public enum User_Role {
	UNKNOWN(-1), USER(0), ADMIN(1);
	
	User_Role(int value){
	}
	
	public static User_Role fromInt(int value) {    
         switch(value) {
             case 0: return USER;
             case 1: return ADMIN;
             default:
                     return UNKNOWN;
         }
    }
	
	public static User_Role fromString(String s){
		if (s.equalsIgnoreCase("admin"))
			return ADMIN;
		else if (s.equalsIgnoreCase("user"))
			return USER;
		else
			return UNKNOWN;
	}
}
