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
import com.playlife.settings.UserSetting;
import com.playlife.utility.exceptions.LogicException;
import com.playlife.utility.validators.StringValidator;

@Component
@Qualifier("userService")
public class PlayLifeUserService implements IPlayLifeUserService {
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
	@Override
	public PlayLifeUser register(PlayLifeUser new_user) throws LogicException{
		try {
			/* Step 1 : Validation */
			StringValidator.validateEmpty(new_user.getEmail(), "email");
			StringValidator.validateRegx_email(new_user.getEmail());
			StringValidator.validateEmpty(new_user.getPassword(), "password");
			StringValidator.validateLength_min(new_user.getPassword(), UserSetting.USER_PASSWORD_MIN, "password");
			StringValidator.validateLength_max(new_user.getPassword(), UserSetting.USER_PASSWORD_MAX, "password");
			if (new_user.getUsername() != null && !new_user.getUsername().trim().isEmpty())
				StringValidator.validateLength_max(new_user.getUsername(), UserSetting.USER_USERNAME_MAX, "username");
			
			/* Step 2 : Check DB username */
			List<PlayLifeUser> userList = playLifeUserDAO.hql_find_ByEmail(new_user.getEmail());
			if (!(userList == null || userList.size() <= 0))
				throw new LogicException(-30005);
	
			/* Step 3 : submit to DB */
			PlayLifeUser user = new PlayLifeUser(new_user.getEmail(), new_user.getPassword(), new_user.getUsername());
			playLifeUserDAO.save(user);
			
			return user;
		} catch (Exception ex){
			throw new LogicException(-30007, ex);
		}
	}
	
	@Override
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
	
	@Override
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

	@Override
	public PlayLifeUser getUserByUserId(PlayLifeUser user) throws LogicException {
		try {
			return accessService.checkUser(user.getUserId());
		} catch (Exception ex){
			throw new LogicException(-9999, ex);
		}
	}

	@Override
	public PlayLifeUser getUserByEmailAllowNull(PlayLifeUser user) throws LogicException {
		try {
			List<PlayLifeUser> userList = playLifeUserDAO.hql_find_ByEmail(user.getEmail());
			if (userList.size() > 1)
				throw new LogicException(-9999);
			
			if (userList.size() == 0)
				return null;
			
			return userList.get(0);
		} catch (Exception ex){
			throw new LogicException(-9999, ex);
		}
	}
}
