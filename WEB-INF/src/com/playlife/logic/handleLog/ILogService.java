package com.playlife.logic.handleLog;

import com.playlife.persistence.domainObject.Log;
import com.playlife.persistence.domainObject.UserLog;
import com.playlife.persistence.domainObject.UserLog_Type;
import com.playlife.utility.exceptions.LogicException;

public interface ILogService {
	public UserLog addUserLog(String ip, UserLog_Type type, String status, String message, String email, String password) throws LogicException;
	public Log addLog(String ip, String persistenceException, String logicException, String presentationException) throws LogicException;
}