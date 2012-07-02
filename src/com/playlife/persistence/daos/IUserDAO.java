package com.playlife.persistence.daos;

import java.util.List;

import com.playlife.persistence.domainObjects.Type_UserRole;
import com.playlife.persistence.domainObjects.User;

public interface IUserDAO extends IGenericDAO<User, Long>{
	public User find_ByUsername(String username);
	public User find_ByEmailAndPassword(String username, String password);
	public User find_ByEmail(String email);
	public int count_ByUserRole(Type_UserRole userRole);
	public List<User> find_All(List<String> search, int start, int end, List<String> order, List<String> orderBy);
}