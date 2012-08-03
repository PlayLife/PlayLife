package com.playlife.logicLayer.user;

import javax.servlet.http.HttpServletRequest;

import com.playlife.persistenceLayer.domainObject.User;
import com.playlife.utility.exceptions.LogicLayerException;

public interface IUserService {
	public User register(User user) throws LogicLayerException;
	public User login(User user) throws LogicLayerException;
	public void logout(HttpServletRequest request) throws LogicLayerException;
	public User getUserByUserId(User user) throws LogicLayerException;
	public User getUserByEmailAllowNull(User user) throws LogicLayerException;
}
