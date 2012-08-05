package com.playlife.presentation.converters;

import net.sf.json.JSONObject;

public interface IErrorConverter {
	public JSONObject constructError(Exception exception) throws Exception;
}
