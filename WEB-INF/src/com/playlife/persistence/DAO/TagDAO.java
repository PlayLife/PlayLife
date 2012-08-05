package com.playlife.persistence.DAO;

import java.util.List;

import com.playlife.persistence.DAO.genericDAO.IGenericDAO;
import com.playlife.persistence.DAO.genericDAO.Param;
import com.playlife.persistence.domainObject.Tag;

public interface TagDAO extends IGenericDAO<Tag, Long>{
	public List<Tag> hql_find_ByName(@Param(name = "name") String name);
}
