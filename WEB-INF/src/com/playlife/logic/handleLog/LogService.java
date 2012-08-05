package com.playlife.logic.handleLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.playlife.persistence.DAO.LogDAO;
import com.playlife.persistence.DAO.UserLogDAO;
import com.playlife.persistence.domainObject.Log;
import com.playlife.persistence.domainObject.UserLog;
import com.playlife.persistence.domainObject.UserLog_Type;
import com.playlife.utility.exceptions.LogicException;

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
	public UserLog addUserLog(String ip, UserLog_Type type, String status, String message, String email, String password) throws LogicException {
		try {
			UserLog userLog = new UserLog(type, ip, status , message, email, password);
			userLogDAO.save(userLog);
			return userLog;
		} catch (Exception ex){
			throw new LogicException(-9999);
		}
	}

	@Override
	public Log addLog(String ip, String persistenceException, String logicException, String presentationException) throws LogicException {
		try {
			Log log = new Log(ip, persistenceException, logicException, presentationException);
			logDAO.save(log);
			return log;
		} catch (Exception ex){
			throw new LogicException(-9999);
		}
 	}
}
