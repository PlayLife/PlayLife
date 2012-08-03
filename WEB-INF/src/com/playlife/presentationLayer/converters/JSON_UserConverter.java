package com.playlife.presentationLayer.converters;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.playlife.persistenceLayer.domainObject.User;
import com.playlife.utility.exceptions.PresentationLayerException;

@Component
@Qualifier("userConverter")
public class JSON_UserConverter implements IUserConverter{
	public JSONObject constructUser(User user) throws PresentationLayerException{
		try {
			JSONObject obj_return = new JSONObject();
			obj_return.put("userId", user.getUserId());
			obj_return.put("email", user.getEmail());
			obj_return.put("username", user.getUsername());
			
			return obj_return;
		} catch (Exception ex){
			throw new PresentationLayerException(-9999, ex);
		}
	}
}
