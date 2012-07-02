package com.playlife.persistence.domainObjects;

import com.playlife.utility.settings.UserSetting;

public enum Type_UserRole {
	UNKNOWN(-1), USER(0), ADMIN(1);
	
	Type_UserRole(int value){
	}
	
	public static Type_UserRole fromInt(int value) {    
         switch(value) {
             case 0: return USER;
             case 1: return ADMIN;
             default:
                     return UNKNOWN;
         }
    }
	
	public String toString() {
		switch(this) {
			case USER:
				return UserSetting.USERROLE_USER;
			case ADMIN:
				return UserSetting.USERROLE_ADMIN;
		}
		return UserSetting.USERROLE_UNKNOWN;
	}
}