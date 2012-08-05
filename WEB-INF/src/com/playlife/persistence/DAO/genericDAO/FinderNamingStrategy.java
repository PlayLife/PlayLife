package com.playlife.persistence.DAO.genericDAO;
import java.lang.reflect.Method;

public interface FinderNamingStrategy {
    @SuppressWarnings("rawtypes")
	public String queryNameFromMethod(Class findTargetType, Method finderMethod);
}

