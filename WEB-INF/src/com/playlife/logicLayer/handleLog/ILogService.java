package com.playlife.logicLayer.handleLog;

import com.playlife.persistenceLayer.domainObject.Log;
import com.playlife.persistenceLayer.domainObject.UserLog;
import com.playlife.persistenceLayer.domainObject.UserLog_Type;
import com.playlife.utility.exceptions.LogicLayerException;

public interface ILogService {
	public UserLog addUserLog(String ip, UserLog_Type type, String status, String message, String email, String password) throws LogicLayerException;
	public Log addLog(String ip, String persistenceLayerException, String logicLayerException, String presentationLayerException) throws LogicLayerException;
}