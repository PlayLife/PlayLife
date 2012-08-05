package com.playlife.presentation.converters;

import net.sf.json.JSONObject;

import com.playlife.persistence.domainObject.PlayLifeUser;
import com.playlife.utility.exceptions.PresentationException;

public interface IUserConverter {
	public JSONObject constructUser(PlayLifeUser user) throws PresentationException;
}