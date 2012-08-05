package com.playlife.persistence.DAO;

import java.util.List;

import com.playlife.persistence.DAO.genericDAO.IGenericDAO;
import com.playlife.persistence.DAO.genericDAO.Param;
import com.playlife.persistence.domainObject.Place;
import com.playlife.persistence.domainObject.Place_Type;

public interface PlaceDAO extends IGenericDAO<Place, Long>{
	List<Place> hql_find_ByNameAndAddressAndType(@Param(name="name") String name, @Param(name = "address") String address, @Param(name = "type") Place_Type type);
}