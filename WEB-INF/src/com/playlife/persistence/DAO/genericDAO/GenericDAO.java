package com.playlife.persistence.DAO.genericDAO;


import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.type.Type;

import com.playlife.utility.exceptions.PersistenceException;

@SuppressWarnings("rawtypes")
public class GenericDAO<T, PK extends Serializable> implements IGenericDAO<T, PK>, FinderExecutor {
	private FinderNamingStrategy namingStrategy = new SimpleFinderNamingStrategy();
    private FinderArgumentTypeFactory argumentTypeFactory = new SimpleFinderArgumentTypeFactory();
    private Class<T> type;
    
    public SessionFactory sessionFactory;

    public GenericDAO(Class<T> type) {
        this.type = type;
    }

    @SuppressWarnings("unchecked")
	public PK save(T o){
    	try {
    		return (PK) getSession().save(o);
    	} catch (Exception ex){
    		throw new PersistenceException(-9999, ex);
    	}
    }

    @SuppressWarnings("unchecked")
	public T get(PK id){
    	try {
    		return (T) getSession().get(type, id);
    	} catch (Exception ex){
    		throw new PersistenceException(-9999, ex);
    	}
    }

    public void update(T o){
    	try {
    		getSession().update(o);
    	} catch (Exception ex){
    		throw new PersistenceException(-9999, ex);
    	}
    }

    public void delete(T o){
    	try {
    		getSession().delete(o);
    	} catch (Exception ex){
    		throw new PersistenceException(-9999, ex);
    	}
    }

	public void persist(T transientObject) {
		try {
			getSession().persist(transientObject);
		} catch (Exception ex){
			throw new PersistenceException(-9999, ex);
		}
	}
	
	@SuppressWarnings("unchecked")
	public T merge(T transientObject) {
		try {
			return (T) getSession().merge(transientObject);
		} catch (Exception ex){
			throw new PersistenceException(-9999, ex);
		}
	}
	
	public void flush(){
		try {
			getSession().flush();
    	} catch (Exception ex){
    		throw new PersistenceException(-9999, ex);
    	}
    }
	
	public void evict(Object obj){
		try {
			getSession().evict(obj);
    	} catch (Exception ex){
    		throw new PersistenceException(-9999, ex);
    	}
	}
	
	public int count(){
		try {
			return ((Integer)getSession().createCriteria(type).setProjection(Projections.rowCount()).uniqueResult());
		} catch (Exception ex){
			throw new PersistenceException(-9999, ex);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> executeSQLFind(Method method, String[] nameArgs, Object[] queryArgs) {
        try {
	    	final Query namedQuery = prepareHQLQuery(method, nameArgs, queryArgs, true);
	        return (List<T>) namedQuery.list();
    	} catch (Exception ex){
    		throw new PersistenceException(-9999, ex);
    	}
	}

	@Override
	public void executeSQLUpdate(Method method, String[] nameArgs, Object[] queryArgs) {
        try {
        	final Query namedQuery = prepareHQLQuery(method, nameArgs, queryArgs, true);
        
        	namedQuery.executeUpdate();
    	} catch (Exception ex){
    		throw new PersistenceException(-9999, ex);
    	}
	}

	@Override
	public Number executeSQLCount(Method method, String[] nameArgs, Object[] queryArgs) {
        try {
	    	final Query namedQuery = prepareHQLQuery(method, nameArgs, queryArgs, true);
	    	
	        return ((Number)namedQuery.list().get(0));
    	} catch (Exception ex){
    		throw new PersistenceException(-9999, ex);
    	}
	}
	
	public Number executeHQLCount(Method method, final String[] nameArgs, final Object[] queryArgs){
        try {
	    	final Query namedQuery = prepareHQLQuery(method, nameArgs, queryArgs, false);
	    	
	        return ((Number)namedQuery.list().get(0));
    	} catch (Exception ex){
    		throw new PersistenceException(-9999, ex);
    	}
    }
	
    @SuppressWarnings("unchecked")
	public List<T> executeHQLFind(Method method, final String[] nameArgs, final Object[] queryArgs){
        try {
	    	final Query namedQuery = prepareHQLQuery(method, nameArgs, queryArgs, false);
	        return (List<T>) namedQuery.list();
    	} catch (Exception ex){
    		throw new PersistenceException(-9999, ex);
    	}
    }

	public void executeHQLUpdate(Method method, final String[] nameArgs, final Object[] queryArgs){
        try {
        	final Query namedQuery = prepareHQLQuery(method, nameArgs, queryArgs, false);
        
        	namedQuery.executeUpdate();
    	} catch (Exception ex){
    		throw new PersistenceException(-9999, ex);
    	}
    }
   
    private Query prepareHQLQuery(Method method, String[] nameArgs, Object[] queryArgs, boolean isSQL){
    	try {
	        final String queryName = getNamingStrategy().queryNameFromMethod(type, method);
	        
	        if(queryArgs!=null && nameArgs != null){
	        	/* Step 1 : Set up order parameter */
	        	String orderField = "";
	        	String orderType = "";
	        	boolean isOrder = false;
	        	for (int i = 0; i < nameArgs.length; i++){
	        		String name = nameArgs[i];
	        		Object arg = queryArgs[i];
	        		
	        		if (name.equalsIgnoreCase("_orderByField") && arg != null && arg instanceof String){
	        			orderField = (String)arg;
	        			isOrder = true;
	    			} else if (name.equalsIgnoreCase("_orderType") && arg != null && arg instanceof String){
	    				orderType = (String)arg;
	    				isOrder = true;
	    			}
	        	}
	        	
	        	final Query namedQuery;
	        	if (isOrder){
	        		if (isSQL)
	        			namedQuery = getSession().createSQLQuery(getSession().getNamedQuery(queryName).getQueryString() + " ORDER BY " + orderField + " " + orderType);
	        		else 
	        			namedQuery = getSession().createQuery(getSession().getNamedQuery(queryName).getQueryString() + " ORDER BY " + orderField + " " + orderType);
	        	} else 
	        		namedQuery = getSession().getNamedQuery(queryName);
	        	
	        	/* Step 2 : Set up other parameter */
	        	HashMap<String, Object> paraMap = new HashMap<String, Object>();
	        	for (int i = 0; i < nameArgs.length; i++){ 
	        		String name = nameArgs[i];
	        		Object arg = queryArgs[i];
	        		
	        		/* Step up paging and ordering parameter */
	    			if (name.equalsIgnoreCase("_startEntry") && arg instanceof Integer && (Integer.parseInt(arg.toString()) != -1)){
	    				namedQuery.setFirstResult((Integer)arg);
	    			} else if (name.equalsIgnoreCase("_numOfEntry") && arg instanceof Integer && (Integer)arg != -1){
	    				namedQuery.setMaxResults((Integer)arg);
	    			}
	    	        
	        		paraMap.put(name, arg);
	        	}
	        	
		        setParams(namedQuery, paraMap);
		        
		        return namedQuery;
	        } else 
	        	return getSession().getNamedQuery(queryName);
    	} catch (Exception ex){
    		throw new PersistenceException(-9999, ex);
    	}
    }
    
    private void setParams(Query namedQuery, HashMap<String, Object> paraMap){
    	try {
	        String[] namedParameters = namedQuery.getNamedParameters();
	        if(namedParameters.length!=0 && paraMap != null && paraMap.size() != 0){
	            setNamedParams(namedParameters, paraMap, namedQuery);
	        }
    	} catch (Exception ex){
    		throw new PersistenceException(-9999, ex);
    	}
    }

    private Query setNamedParams(String[] namedParameters, HashMap<String, Object> paraMap, Query namedQuery){
    	try {
            for(int i = 0; i < namedParameters.length; i++){
                Object arg = paraMap.get(namedParameters[i]);
                Type argType = getArgumentTypeFactory().getArgumentType(arg);
                if(argType != null){
                    namedQuery.setParameter(namedParameters[i], arg, argType);
                } else{
                    if(arg instanceof Collection) {
                        namedQuery.setParameterList(namedParameters[i], (Collection) arg);
                    }
                    else{
                        namedQuery.setParameter(namedParameters[i], arg);
                    }
                }
            }
            
	        return namedQuery;
    	} catch (Exception ex){
    		throw new PersistenceException(-9999, ex);
    	}
    }

    public Session getSession() {
    	return sessionFactory.getCurrentSession();
    }

    public FinderNamingStrategy getNamingStrategy(){
        return namingStrategy;
    }

    public void setNamingStrategy(FinderNamingStrategy namingStrategy){
        this.namingStrategy = namingStrategy;
    }

    public FinderArgumentTypeFactory getArgumentTypeFactory(){
        return argumentTypeFactory;
    }

    public void setArgumentTypeFactory(FinderArgumentTypeFactory argumentTypeFactory){
        this.argumentTypeFactory = argumentTypeFactory;
    }

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}