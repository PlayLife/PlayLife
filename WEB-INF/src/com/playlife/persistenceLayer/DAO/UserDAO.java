package com.playlife.persistenceLayer.DAO;

import java.util.List;

import com.playlife.persistenceLayer.DAO.genericDAO.IGenericDAO;
import com.playlife.persistenceLayer.DAO.genericDAO.Param;
import com.playlife.persistenceLayer.domainObject.User;

public interface UserDAO extends IGenericDAO<User, Long>{
    List<User> hql_find_ByEmail(@Param(name="email") String email);
    List<User> hql_find_ByEmailAndPassword(@Param(name="email") String email, @Param(name="password") String password);	
}
