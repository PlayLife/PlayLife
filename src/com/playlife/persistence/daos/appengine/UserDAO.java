package com.playlife.persistence.daos.appengine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jdo.Query;

import com.playlife.persistence.daos.IUserDAO;
import com.playlife.persistence.domainObjects.Type_UserRole;
import com.playlife.persistence.domainObjects.User;
import com.playlife.utility.PersistenceException;

public class UserDAO extends GenericDAO<User, Long> implements IUserDAO {
	public UserDAO() {
		super(User.class);
	}

	@Override
	public User find_ByUsername(String username) {
		try {
			Query query = pm().newQuery(User.class);
			query.setFilter("username == usernameParam");
			query.declareParameters("String usernameParam");
			query.setUnique(true);
			
			return (User) query.execute(username);
		} catch (Exception ex){
			throw new PersistenceException(-99999, ex);
		}
	}
	
	@Override
	public User find_ByEmailAndPassword(String email, String password) {
		try {
			Query query = pm().newQuery(User.class);
			query.setFilter("email == emailParam && password == passwordParam");
			query.declareParameters("String emailParam, String passwordParam");
			query.setUnique(true);
			
			return (User) query.execute(email, password);
		} catch (Exception ex){
			throw new PersistenceException(-99999, ex);
		}
	}
	
	@Override
	public User find_ByEmail(String email) {
		try {
			Query query = pm().newQuery(User.class);
			query.setFilter("email == p_email");
			query.declareParameters("String p_email");
			query.setUnique(true);
			
			return (User) query.execute(email);
		} catch (Exception ex){
			throw new PersistenceException(-99999, ex);
		}
	}

	@Override
	public int count_ByUserRole(Type_UserRole userRole) {
		try {
			Query query = pm().newQuery(User.class);
			query.setFilter("userRole == p_userRole");
			query.declareParameters("Type_UserRole p_userRole");
			query.setResult("count()");
			return (Integer)(query.execute(userRole));
		} catch (Exception ex){
			throw new PersistenceException(-99999, ex);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> find_All(List<String> searchList, int start, int end, List<String> orderByList, List<String> orderList) {
		try {
			Query query = pm().newQuery(User.class);
			query.setFilter("p_id.contains(userId)");
			
			/* Step 1 : Set up subqueries */
			Map<Query, String> subQueryList = new HashMap<Query, String>();
			for (String search : searchList){
				Query subQuery = pm().newQuery(User.class);
				subQuery.setFilter("email.indexOf(:p) >= 0");
				subQuery.setResult("userId");
				
//				query.addSubquery(subQuery, "", arg2)
				subQueryList.put(query, search);
			}
			

			Iterator iterator = subQueryList.keySet().iterator();
			while (iterator.hasNext()){
				Query subQuery = (Query)iterator.next();
//				Search				
			}
			
			if (orderByList.size() != orderList.size())
				throw new PersistenceException(-99999);
			
			String s_order = "";
			for (int i = 0; i < orderByList.size(); i++){
				String orderBy = orderByList.get(i);
				String order = orderList.get(i);
				
				s_order += orderBy + " " + order;
				if (i != orderByList.size()-1)
					s_order += ",";
			}
			query.setOrdering(s_order);
			query.setRange(start, end);
			
			return (List<User>) query.execute(searchList);
		} catch (Exception ex){
			throw new PersistenceException(-99999, ex);
		}
	}
}
