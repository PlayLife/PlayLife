package com.playlife.logic.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.playlife.logic.access.IAccessService;
import com.playlife.persistence.DAO.PlayLifeUserDAO;
import com.playlife.persistence.domainObject.PlayLifeUser;
import com.playlife.persistence.domainObject.User_Type;
import com.playlife.settings.UserSetting;
import com.playlife.utility.exceptions.LogicException;

@Component
@Qualifier("userService")
public class PlayLifeUserService {
	/************************
	 * 						*
	 * 		Variable		* 
	 * 						*
	 ************************/
	@Autowired
	@Qualifier("userDAO")
	private PlayLifeUserDAO playLifeUserDAO;
	
	@Autowired
	@Qualifier("accessService")
	private IAccessService accessService;
	
	/************************
	 * 						*
	 * 	Implementation		* 
	 * 						*
	 ************************/
	public PlayLifeUser register(String email, String password, String username, User_Type type) throws LogicException {
		try {
			/* Step 3 : submit to DB */
			PlayLifeUser user = new PlayLifeUser(email, password, username, type);
			playLifeUserDAO.save(user);
			
			return user;
		} catch (Exception ex){
			throw new LogicException(-30007, ex);
		}
	}
	
	public PlayLifeUser login(PlayLifeUser old_user) throws LogicException {
		try {
			/* Step 1 : Check email and password */
			List<PlayLifeUser> userList = playLifeUserDAO.hql_find_ByEmailAndPassword(old_user.getEmail(), old_user.getPassword());
			if (userList == null || userList.size() <= 0)
				throw new LogicException(-30010);
			
			PlayLifeUser user = userList.get(0);
			
			return user;
		} catch (Exception ex){
			throw new LogicException(-30011, ex);
		}
	}
	
	public void logout(HttpServletRequest request) throws LogicException {
		try {
			HttpSession session = request.getSession(false);
			if(session!=null) {
				session.setAttribute(UserSetting.USER_SESSIONNAME_USER, null);
			}
		} catch (Exception ex){
			throw new LogicException(-30012, ex);			
		}
	}

	public PlayLifeUser getUserByUserId(PlayLifeUser user) throws LogicException {
		try {
			return accessService.checkUser(user.getUserId());
		} catch (Exception ex){
			throw new LogicException(-9999, ex);
		}
	}

	public PlayLifeUser getUserByEmailAllowNull(String email) throws LogicException {
		try {
			List<PlayLifeUser> userList = playLifeUserDAO.hql_find_ByEmail(email);
			if (userList.size() > 1)
				throw new LogicException(-9999);
			
			if (userList.size() == 0)
				return null;
			
			return userList.get(0);
		} catch (Exception ex){
			throw new LogicException(-9999, ex);
		}
	}
	
	public boolean checkEmailExists(String email) throws LogicException {
		try {
			List<PlayLifeUser> userList = playLifeUserDAO.hql_find_ByEmail(email);
			return (userList.size() >= 1);
		} catch (Exception ex){
			throw new LogicException(-9999, ex);
		}
	}
}
