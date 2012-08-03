package com.playlife.presentationLayer.converters;

import net.sf.json.JSONObject;

import com.playlife.persistenceLayer.domainObject.User;
import com.playlife.utility.exceptions.PresentationLayerException;

public interface IUserConverter {
	public JSONObject constructUser(User user) throws PresentationLayerException;
}