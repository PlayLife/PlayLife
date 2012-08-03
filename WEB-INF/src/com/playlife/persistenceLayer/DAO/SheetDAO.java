package com.playlife.persistenceLayer.DAO;

import com.playlife.persistenceLayer.DAO.genericDAO.IGenericDAO;
import com.playlife.persistenceLayer.DAO.genericDAO.Param;
import com.playlife.persistenceLayer.domainObject.Sheet;

public interface SheetDAO extends IGenericDAO<Sheet, Long>{
	void sql_update_DeletedByBookId(@Param(name="bookId") Long bookId);
}