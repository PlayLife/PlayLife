package com.playlife.logic.user;

import javax.servlet.http.HttpServletRequest;

import com.playlife.persistence.domainObject.PlayLifeUser;
import com.playlife.utility.exceptions.LogicException;

public interface IPlayLifeUserService {
	public PlayLifeUser register(PlayLifeUser user) throws LogicException;
	public PlayLifeUser login(PlayLifeUser user) throws LogicException;
	public void logout(HttpServletRequest request) throws LogicException;
	public PlayLifeUser getUserByUserId(PlayLifeUser user) throws LogicException;
	public PlayLifeUser getUserByEmailAllowNull(PlayLifeUser user) throws LogicException;
}
