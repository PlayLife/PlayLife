package com.playlife.presentationLayer.converters;

import net.sf.json.JSON;

import com.playlife.utility.exceptions.PresentationLayerException;

public interface IObjectConverter {
	public JSON constructObject(Object object) throws PresentationLayerException;
}