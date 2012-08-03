package com.playlife.presentationLayer.converters;

import java.util.Set;

import com.playlife.persistenceLayer.domainObject.Address;
import com.playlife.utility.exceptions.PresentationLayerException;

public class JSON_PlaceConverter implements IPlaceConverter{

	@Override
	public Object constructAddress(Address address) throws PresentationLayerException {
		return null;
	}

	@Override
	public Object constructAddresses(Set<Address> addresses) throws PresentationLayerException {
		return null;
	}

}
