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
		if (user.getUsername().trim().isEmpty())
			throw new ValidationException(-50000);
		if (user.getUsername().length() < 3 || user.getUsername().length() > 20)
			throw new ValidationException(-50001);
		
		if (user.getPassword().trim().isEmpty())
			throw new ValidationException(-50002);
		if (user.getPassword().length() != 32)
			throw new ValidationException(-99999);
		
		if (user.getEmail().trim().isEmpty())
			throw new ValidationException(-50003);
		if (!Pattern.compile(".+@.+\\.[a-z]+").matcher(user.getEmail()).matches())
			throw new ValidationException(-50004);
		if (playLifeUserService.checkEmailExists(user.getEmail()))
			throw new ValidationException(-50005);
	}
}
