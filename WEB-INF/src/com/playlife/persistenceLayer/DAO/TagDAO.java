package com.playlife.persistenceLayer.DAO;

import java.util.List;

import com.playlife.persistenceLayer.DAO.genericDAO.IGenericDAO;
import com.playlife.persistenceLayer.DAO.genericDAO.Param;
import com.playlife.persistenceLayer.domainObject.Tag;

public interface TagDAO extends IGenericDAO<Tag, Long>{
	public List<Tag> hql_find_ByName(@Param(name = "name") String name);
}
