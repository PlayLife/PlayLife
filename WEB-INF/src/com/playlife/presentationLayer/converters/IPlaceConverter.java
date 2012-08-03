package com.playlife.presentationLayer.converters;

import java.util.Set;

import com.playlife.persistenceLayer.domainObject.Address;
import com.playlife.utility.exceptions.PresentationLayerException;

public interface IPlaceConverter {
	public Object constructAddress(Address address) throws PresentationLayerException;
	public Object constructAddresses(Set<Address> addresses) throws PresentationLayerException;
}
