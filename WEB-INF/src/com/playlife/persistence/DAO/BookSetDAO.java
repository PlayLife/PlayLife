package com.playlife.persistence.DAO;

import com.playlife.persistence.DAO.genericDAO.IGenericDAO;
import com.playlife.persistence.DAO.genericDAO.Param;
import com.playlife.persistence.domainObject.BookSet;

public interface BookSetDAO extends IGenericDAO<BookSet, Long> {
	void sql_update_DeletedByBookSetId(@Param(name="bookSetId") Long bookSetId);
}
