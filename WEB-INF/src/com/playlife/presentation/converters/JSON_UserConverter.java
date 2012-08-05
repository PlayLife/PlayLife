package com.playlife.presentation.converters;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.playlife.persistence.domainObject.PlayLifeUser;
import com.playlife.utility.exceptions.PresentationException;

@Component
@Qualifier("userConverter")
public class JSON_UserConverter implements IUserConverter{
	public JSONObject constructUser(PlayLifeUser user) throws PresentationException{
		try {
			JSONObject obj_return = new JSONObject();
			obj_return.put("userId", user.getUserId());
			obj_return.put("email", user.getEmail());
			obj_return.put("username", user.getUsername());
			
			return obj_return;
		} catch (Exception ex){
			throw new PresentationException(-9999, ex);
		}
	}
}
