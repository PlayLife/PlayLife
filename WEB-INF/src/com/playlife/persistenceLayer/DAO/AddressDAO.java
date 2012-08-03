package com.playlife.persistenceLayer.DAO;

import java.util.List;

import com.playlife.persistenceLayer.DAO.genericDAO.IGenericDAO;
import com.playlife.persistenceLayer.DAO.genericDAO.Param;
import com.playlife.persistenceLayer.domainObject.Address;

public interface AddressDAO extends IGenericDAO<Address, Long>{
	public List<Address> hql_find_ByName(@Param(name = "name") String name);
}
