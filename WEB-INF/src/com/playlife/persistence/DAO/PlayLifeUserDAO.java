package com.playlife.persistence.DAO;

import java.util.List;

import com.playlife.persistence.DAO.genericDAO.IGenericDAO;
import com.playlife.persistence.DAO.genericDAO.Param;
import com.playlife.persistence.domainObject.PlayLifeUser;

public interface PlayLifeUserDAO extends IGenericDAO<PlayLifeUser, Long>{
    List<PlayLifeUser> hql_find_ByEmail(@Param(name="email") String email);
    List<PlayLifeUser> hql_find_ByEmailAndPassword(@Param(name="email") String email, @Param(name="password") String password);
	List<PlayLifeUser> hql_find_All(@Param(name="_startEntry")int _startEntry, @Param(name="_numOfEntry")int _numOfEntry, @Param(name="_orderByField") String _orderByField,
									@Param(name="_orderByType") String _orderByType, @Param(name="search_text") String search_text);
	Long hql_count_All(@Param(name="search_text") String search_text);	
}
