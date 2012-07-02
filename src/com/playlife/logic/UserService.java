package com.playlife.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.playlife.persistence.daos.IUserDAO;
import com.playlife.persistence.domainObjects.Type_UserRole;
import com.playlife.persistence.domainObjects.User;
import com.playlife.utility.LogicException;

@Component
public class UserService {
	@Autowired
	IUserDAO userDAO;
	
	public User create(String username, String password, String email, Type_UserRole type){
		try {
			User user = new User(username, password, email, type);
			userDAO.save(user);
			
			return user;
		} catch (Exception ex){
			throw new LogicException(-9999, ex);
		}
	}
	
	public boolean checkEmailExists(String email){
		try {
			User user = userDAO.find_ByEmail(email);
			return user != null;
		} catch (Exception ex){
			throw new LogicException(-9999, ex);
		}
	}
	
	public boolean isInit(){
		try {
			return userDAO.count_ByUserRole(Type_UserRole.ADMIN) > 0;
		} catch (Exception ex){
			throw new LogicException(-9999, ex);
		}
	}
	
	public List<User> getAll(List<String> search, int start, int end, List<String> orderList, List<String> orderByList){
		try {		
			return userDAO.find_All(search, start, end, orderList, orderByList);
		} catch (Exception ex){
			throw new LogicException(-9999, ex);
		}
	}
}
