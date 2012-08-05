package com.playlife.persistence.DAO;

import java.util.List;

import com.playlife.persistence.DAO.genericDAO.IGenericDAO;
import com.playlife.persistence.DAO.genericDAO.Param;
import com.playlife.persistence.domainObject.PlayLifeUser;

public interface PlayLifeUserDAO extends IGenericDAO<PlayLifeUser, Long>{
    List<PlayLifeUser> hql_find_ByEmail(@Param(name="email") String email);
    List<PlayLifeUser> hql_find_ByEmailAndPassword(@Param(name="email") String email, @Param(name="password") String password);	
}
