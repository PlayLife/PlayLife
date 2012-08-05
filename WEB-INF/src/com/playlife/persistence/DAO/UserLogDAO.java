package com.playlife.persistence.DAO;

import java.util.Date;
import java.util.List;

import com.playlife.persistence.DAO.genericDAO.IGenericDAO;
import com.playlife.persistence.DAO.genericDAO.Param;
import com.playlife.persistence.domainObject.UserLog;
import com.playlife.persistence.domainObject.UserLog_Type;

public interface UserLogDAO extends IGenericDAO<UserLog, Long>{
	List<UserLog> hql_find_ByIpAndTypeAndDate(@Param(name="ip") String ip, @Param(name="type") UserLog_Type type, 
			@Param(name="createdDate") Date createdDate, @Param(name="interval")int interval);
}