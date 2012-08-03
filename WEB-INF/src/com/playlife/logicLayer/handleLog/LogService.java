package com.playlife.logicLayer.handleLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.playlife.persistenceLayer.DAO.LogDAO;
import com.playlife.persistenceLayer.DAO.UserLogDAO;
import com.playlife.persistenceLayer.domainObject.Log;
import com.playlife.persistenceLayer.domainObject.UserLog;
import com.playlife.persistenceLayer.domainObject.UserLog_Type;
import com.playlife.utility.exceptions.LogicLayerException;

@Component
@Qualifier("logService")
public class LogService implements ILogService {
	@Autowired
	@Qualifier("logDAO")
	private LogDAO logDAO;
	@Autowired
	@Qualifier("userLogDAO")
	private UserLogDAO userLogDAO;
	
	@Override
	public UserLog addUserLog(String ip, UserLog_Type type, String status, String message, String email, String password) throws LogicLayerException {
		try {
			UserLog userLog = new UserLog(type, ip, status , message, email, password);
			userLogDAO.save(userLog);
			return userLog;
		} catch (Exception ex){
			throw new LogicLayerException(-9999);
		}
	}

	@Override
	public Log addLog(String ip, String persistenceLayerException, String logicLayerException, String presentationLayerException) throws LogicLayerException {
		try {
			Log log = new Log(ip, persistenceLayerException, logicLayerException, presentationLayerException);
			logDAO.save(log);
			return log;
		} catch (Exception ex){
			throw new LogicLayerException(-9999);
		}
 	}
}
