package com.playlife.persistenceLayer.DAO;

import java.util.List;

import com.playlife.persistenceLayer.DAO.genericDAO.IGenericDAO;
import com.playlife.persistenceLayer.DAO.genericDAO.Param;
import com.playlife.persistenceLayer.domainObject.Place;
import com.playlife.persistenceLayer.domainObject.Place_Type;

public interface PlaceDAO extends IGenericDAO<Place, Long>{
	List<Place> hql_find_ByNameAndAddressAndType(@Param(name="name") String name, @Param(name = "address") String address, @Param(name = "type") Place_Type type);
}