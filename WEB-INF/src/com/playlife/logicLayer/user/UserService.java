package com.playlife.logicLayer.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.playlife.logicLayer.access.IAccessService;
import com.playlife.persistenceLayer.DAO.UserDAO;
import com.playlife.persistenceLayer.domainObject.User;
import com.playlife.settings.UserSetting;
import com.playlife.utility.exceptions.LogicLayerException;
import com.playlife.utility.validators.StringValidator;

@Component
@Qualifier("userService")
public class UserService implements IUserService {
	/************************
	 * 						*
	 * 		Variable		* 
	 * 						*
	 ************************/
	@Autowired
	@Qualifier("userDAO")
	private UserDAO userDAO;
	
	@Autowired
	@Qualifier("accessService")
	private IAccessService accessService;
	
	/************************
	 * 						*
	 * 	Implementation		* 
	 * 						*
	 ************************/
	@Override
	public User register(User new_user) throws LogicLayerException{
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
			List<User> userList = userDAO.hql_find_ByEmail(new_user.getEmail());
			if (!(userList == null || userList.size() <= 0))
				throw new LogicLayerException(-30005);
	
			/* Step 3 : submit to DB */
			User user = new User(new_user.getEmail(), new_user.getPassword(), new_user.getUsername());
			userDAO.save(user);
			
			return user;
		} catch (Exception ex){
			throw new LogicLayerException(-30007, ex);
		}
	}
	
	@Override
	public User login(User old_user) throws LogicLayerException {
		try {
			/* Step 1 : Check email and password */
			List<User> userList = userDAO.hql_find_ByEmailAndPassword(old_user.getEmail(), old_user.getPassword());
			if (userList == null || userList.size() <= 0)
				throw new LogicLayerException(-30010);
			
			User user = userList.get(0);
			
			return user;
		} catch (Exception ex){
			throw new LogicLayerException(-30011, ex);
		}
	}
	
	@Override
	public void logout(HttpServletRequest request) throws LogicLayerException {
		try {
			HttpSession session = request.getSession(false);
			if(session!=null) {
				session.setAttribute(UserSetting.USER_SESSIONNAME_USER, null);
			}
		} catch (Exception ex){
			throw new LogicLayerException(-30012, ex);			
		}
	}

	@Override
	public User getUserByUserId(User user) throws LogicLayerException {
		try {
			return accessService.checkUser(user.getUserId());
		} catch (Exception ex){
			throw new LogicLayerException(-9999, ex);
		}
	}

	@Override
	public User getUserByEmailAllowNull(User user) throws LogicLayerException {
		try {
			List<User> userList = userDAO.hql_find_ByEmail(user.getEmail());
			if (userList.size() > 1)
				throw new LogicLayerException(-9999);
			
			if (userList.size() == 0)
				return null;
			
			return userList.get(0);
		} catch (Exception ex){
			throw new LogicLayerException(-9999, ex);
		}
	}
}
