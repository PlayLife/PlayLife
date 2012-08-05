package com.playlife.presentation.converters;

import java.util.Set;

import com.playlife.persistence.domainObject.Address;
import com.playlife.utility.exceptions.PresentationException;

public interface IPlaceConverter {
	public Object constructAddress(Address address) throws PresentationException;
	public Object constructAddresses(Set<Address> addresses) throws PresentationException;
}
