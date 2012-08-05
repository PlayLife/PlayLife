package com.playlife.persistence.DAO;

import com.playlife.persistence.DAO.genericDAO.IGenericDAO;
import com.playlife.persistence.DAO.genericDAO.Param;
import com.playlife.persistence.domainObject.Sheet;

public interface SheetDAO extends IGenericDAO<Sheet, Long>{
	void sql_update_DeletedByBookId(@Param(name="bookId") Long bookId);
}