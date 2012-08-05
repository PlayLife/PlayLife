package com.playlife.persistence.DAO.genericDAO;


import java.lang.reflect.Method;

public class ExtendedFinderNamingStrategy implements FinderNamingStrategy{
    @SuppressWarnings("rawtypes")
	public String queryNameFromMethod(Class findTargetType, Method finderMethod){
        return findTargetType.getSimpleName() + "." + finderMethod.getName();
    }
}
