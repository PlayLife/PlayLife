package com.playlife.settings;

public class UISetting {
	public static final String STATUS = "status";
	public static final String STATUS_OK = "ok";
	public static final String STATUS_ERROR = "error";
	public static final String ERROR = "error";
	
	/* Register Controller */
	public static final String REGISTER_PATH_MAIN = "/register/";
	public static final String REGISTER_PATH_INTRO = "/register/intro";
	
	public static final String ACTION = "action";
	/* User Controller - action */
	public static final String ACTION_USER_LOGIN = "login";
	public static final String ACTION_USER_REGISTER = "register";
	public static final String ACTION_USER_GET = "get";
	public static final String ACTION_USER_CHECK = "check";
	
	/* Book Controller - action */
	public static final String BOOK_ACTION_CREATE = "create";
	public static final String BOOK_ACTION_MODIFY = "modify";
	public static final String BOOK_ACTION_DELETE = "delete";
	public static final String BOOK_ACTION_GET = "get";
	public static final String BOOK_MODIFY_ISUPDATEADDRESS = "isUpdateAddress";
	public static final String BOOK_MODIFY_ISUPDATETAG = "isUpdateTag";
	
	/* Sheet Controller - action */
	public static final String  ACTION_SHEET_CREATE = "create";
	public static final String  ACTION_SHEET_MODIFY = "modify";
	public static final String  ACTION_SHEET_DELETE = "delete";
	public static final String  ACTION_SHEET_GET = "get";
	public static final String  ACTION_SHEET_COMMENT = "comment";
	public static final String  ACTION_SHEET_RATE = "rate";
	
	/* Text Controller - action */
	public static final String  ACTION_TEXT_CREATE = "create";
	public static final String  ACTION_TEXT_MODIFY = "modify";
	public static final String  ACTION_TEXT_DELETE = "delete";
	
	/* Photo Controller - action */
	public static final String  ACTION_PHOTO_CREATE = "create";
	public static final String  ACTION_PHOTO_MODIFY = "modify";
	public static final String  ACTION_PHOTO_DELETE = "delete";
		
	/* Category Controller - action */
	public static final String ACTION_CATEGORY_GETALL = "getAll";
	public static final String CATEGORY_OUTPUT_ALLCATEGORY = "allCategory";
	
	/* Location Controller - action */
	public static final String ACTION_LOCATION_GETALL = "getAll";
	public static final String LOCATION_OUTPUT_ALLLOCATION = "allLocation";

	/* User Controller - input */
	public static final String USER_INPUT_UID = "uid";
	public static final String USER_INPUT_PASSWORD = "password";
	public static final String USER_INPUT_EMAIL = "email";
	public static final String USER_INPUT_USERNAME = "username";
	public static final String USER_INPUT_ACCESSTOKEN = "accessToken";
	
	/* Book Controller - input */
	public static final String BOOK_INPUT_BOOKNAME = "bookname";
	public static final String BOOK_INPUT_BID = "bid";
	public static final String BOOK_INPUT_BUDGET = "budget";
	public static final String BOOK_INPUT_STARTDATE = "startDate";
	public static final String BOOK_INPUT_ENDDATE = "endDate";
	
	/* Sheet Controller - input */
	public static final String SHEET_INPUT_TITLE = "title";
	public static final String SHEET_INPUT_BID = "bid";
	public static final String SHEET_INPUT_SID = "sid";
	public static final String SHEET_INPUT_LOCATION = "location";
	public static final String SHEET_INPUT_CATEGORY = "category";
	public static final String SHEET_INPUT_PLACE = "place";
	public static final String SHEET_INPUT_COMMENT = "comment";
	public static final String SHEET_INPUT_RATE = "rate";
	
	/* Text Controller - input */
	public static final String TEXT_INPUT_CONTENT = "content";
	public static final String TEXT_INPUT_SID = "sid";
	public static final String TEXT_INPUT_TCID = "tcid";
	
	/* Photo Controller - input */
	public static final String PHOTO_INPUT_LINK = "link";
	public static final String PHOTO_INPUT_TITLE = "title";
	public static final String PHOTO_INPUT_SID = "sid";
	public static final String PHOTO_INPUT_PCID = "pcid";
	
	/* Captcha Controller - action */
	public static final String ACTION_CAPTCHA_1 = "/captcha/1.jpg";
	public static final String ACTION_CAPTCHA_2 = "/captcha/2.jpg";
	public static final String ACTION_CAPTCHA_3 = "/captcha/3.jpg";
	public static final String ACTION_CAPTCHA_4 = "/captcha/4.jpg";
	public static final String ACTION_CAPTCHA_5 = "/captcha/5.jpg";
	public static final String ACTION_CAPTCHA_6 = "/captcha/6.jpg";
	public static final String ACTION_CAPTCHA_7 = "/captcha/7.jpg";
	public static final String ACTION_CAPTCHA_8 = "/captcha/8.jpg";
	public static final String ACTION_CAPTCHA_REFRESH = "/captcha/refresh";
	public static final String ACTION_CAPTCHA_VERIFY = "/captcha/verify";
	
	public static final String CAPTCHA_INPUT_REORDER = "reorder";
	
	/* For User JSON Object */
	public static final String JSON_USER = "user";
	public static final String JSON_USER_UID = "uid";
	public static final String JSON_USER_EMAIL = "email";
	public static final String JSON_USER_USERNAME = "username";
	
	/* For Book Set JSON Object */
	public static final String JSON_BOOKSet = "bookSet";
	
	/* For Book JSON Object */
	public static final String JSON_BOOK = "book";
	public static final String JSON_BOOK_BID = "bid";
	public static final String JSON_BOOK_BUDGET = "budget";
	public static final String JSON_BOOK_STARTDATE = "startDate";
	public static final String JSON_BOOK_ENDDATE = "endDate";
	
	/* For Sheet JSON Object */
	public static final String JSON_SHEET = "sheet";
	public static final String JSON_SHEET_SID = "sid";
	public static final String JSON_SHEET_TITLE= "title";
	
	/* For Text Content JSON Object */
	public static final String JSON_TEXTCONTENT = "textContent";
	public static final String JSON_TEXTCONTENT_TCID = "tcid";
	public static final String JSON_TEXTCONTENT_CONTENT = "content";
	public static final String JSON_TEXTCONTENT_CSS = "css";
	
	/* For Photo Content JSON Object */
	public static final String JSON_PHOTOCONTENT = "photoContent";
	public static final String JSON_PHOTOCONTENT_PCID = "pcid";
	public static final String JSON_PHOTOCONTENT_TITLE = "title";
	public static final String JSON_PHOTOCONTENT_LINK = "link";
	public static final String JSON_PHOTOCONTENT_CSS = "css";
	
	/* For Category JSON Object */
	public static final String JSON_CATEGORY = "category";
	public static final String JSON_CATEGORY_CID = "cid";
	public static final String JSON_CATEGORY_NAME = "name";
	
	/* For Location JSON Object */
	public static final String JSON_LOCATION = "location";
	public static final String JSON_LOCATION_LID = "lid";
	public static final String JSON_LOCATION_NAME = "name";
}
