package com.playlife.settings;

import java.io.File;
import java.text.SimpleDateFormat;

public class ErrorSetting {
	public static final String ERROR_LOG_PATH=File.separator + "log" + File.separator;
	public static final String ERROR_LOG_EXTENSION=".log";
	public static final SimpleDateFormat ERROR_LOG_TIME_FORMATTER = new SimpleDateFormat("yyyyMMddHHmmssSSS");
}
