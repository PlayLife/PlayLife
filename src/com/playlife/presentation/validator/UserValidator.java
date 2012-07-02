package com.playlife.presentation.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.playlife.logic.UserService;
import com.playlife.persistence.daos.IUserDAO;
import com.playlife.persistence.domainObjects.User;
import com.playlife.utility.ValidationException;

@Component
public class UserValidator {
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private UserService userService;
	
	public void validate(User user) {
		if (user.getUsername().trim().isEmpty())
			throw new ValidationException(-50000);
		if (user.getUsername().length() < 3 || user.getUsername().length() > 20)
			throw new ValidationException(-50001);
		
		if (user.getPassword().trim().isEmpty())
			throw new ValidationException(-50002);
		
		if (user.getEmail().trim().isEmpty())
			throw new ValidationException(-50003);
		if (!Pattern.compile(".+@.+\\.[a-z]+").matcher(user.getEmail()).matches())
			throw new ValidationException(-50004);
		if (userService.checkEmailExists(user.getEmail()))
			throw new ValidationException(-50005);
	}
	
	public User checkLogin(User loginUser){
		User user = userDAO.find_ByEmailAndPassword(loginUser.getEmail(), loginUser.getPassword());
		if (user == null)
			throw new ValidationException(-50006);
		return user;
	}
}