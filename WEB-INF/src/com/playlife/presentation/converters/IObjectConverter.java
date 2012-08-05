package com.playlife.presentation.converters;

import net.sf.json.JSON;

import com.playlife.utility.exceptions.PresentationException;

public interface IObjectConverter {
	public JSON constructObject(Object object) throws PresentationException;
}