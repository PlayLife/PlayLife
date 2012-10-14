package com.playlife.persistence.DAO;

import java.util.List;

import com.playlife.persistence.DAO.genericDAO.IGenericDAO;
import com.playlife.persistence.DAO.genericDAO.Param;
import com.playlife.persistence.domainObject.Book;

public interface BookDAO extends IGenericDAO<Book, Long>{
	List<Book> hql_find_ByBookIdAndUserId(@Param(name="bookId") Long bookId, @Param(name="userId") Long userId);
}