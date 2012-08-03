package com.playlife.settings;

public class UserSetting {
	public static final int USER_PASSWORD_MIN = 5;
	public static final int USER_PASSWORD_MAX = 50;
	public static final int USER_USERNAME_MIN = 3;
	public static final int USER_USERNAME_MAX = 25;
	
	public static final String USER_SESSIONNAME_USER = "user";
	
	public static int REGISTER_TRAIL = 5;
	public static int REGISTER_INTERVAL = 1800; /* 30 minutes = 30 x 60 second */
	public static int LOGIN_TRAIL = 5;
	public static int LOGIN_INTERVAL = 1800; /* 30 minutes = 30 x 60 second */
}
