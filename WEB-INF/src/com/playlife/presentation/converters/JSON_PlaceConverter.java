package com.playlife.presentation.converters;

import java.util.Set;

import com.playlife.persistence.domainObject.Address;
import com.playlife.utility.exceptions.PresentationException;

public class JSON_PlaceConverter implements IPlaceConverter{

	@Override
	public Object constructAddress(Address address) throws PresentationException {
		return null;
	}

	@Override
	public Object constructAddresses(Set<Address> addresses) throws PresentationException {
		return null;
	}

}
