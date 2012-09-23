package com.playlife.utility.validators;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.playlife.logic.user.PlayLifeUserService;
import com.playlife.persistence.domainObject.PlayLifeUser;
import com.playlife.utility.exceptions.ValidationException;

@Component
public class UserValidator {
	@Autowired
	private PlayLifeUserService playLifeUserService;
	
	public void validate(PlayLifeUser user) {
		if (user.getUsername() != null && user.getUsername().trim().isEmpty())
			throw new ValidationException(-50000);
		if (user.getUsername() != null && user.getUsername().length() < 3 || user.getUsername().length() > 20)
			throw new ValidationException(-50001);
		
		if (user.getPassword() != null && user.getPassword().trim().isEmpty())
			throw new ValidationException(-50002);
		if (user.getPassword() != null && user.getPassword().length() != 32)
			throw new ValidationException(-99999);
		
		if (user.getEmail() != null && user.getEmail().trim().isEmpty())
			throw new ValidationException(-50003);
		if (user.getEmail() != null && !Pattern.compile(".+@.+\\.[a-z]+").matcher(user.getEmail()).matches())
			throw new ValidationException(-50004);
		if (user.getEmail() != null && playLifeUserService.checkEmailExists(user.getEmail()))
			throw new ValidationException(-50005);
	}
}
