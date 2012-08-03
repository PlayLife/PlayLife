package com.playlife.presentationLayer.converters;

import net.sf.json.JSONObject;

public interface IErrorConverter {
	public JSONObject constructError(Exception exception) throws Exception;
}
