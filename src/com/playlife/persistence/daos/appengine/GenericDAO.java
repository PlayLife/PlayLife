package com.playlife.persistence.daos.appengine;

import java.io.Serializable;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.playlife.persistence.daos.IGenericDAO;
import com.playlife.utility.PersistenceException;
import com.playlife.utility.persistenceHelpers.PMF;

public class GenericDAO<T, PK extends Serializable> implements IGenericDAO<T, PK>{
	private Class<T> type;
	
    public GenericDAO(Class<T> type) {
        this.type = type;
    }
    
    public PersistenceManager pm(){
    	return PMF.pm.get();
    }
    
	@Override
	public void save(T newInstance) {
		try {
			Transaction tx = pm().currentTransaction();
			tx.begin();
			pm().makePersistent(newInstance);
			tx.commit();
		} catch (Exception ex){
			throw new PersistenceException(-9999, ex);
		} finally {
	    }
	}

	@Override
	public T get(PK id) {
		try {
			T obj_return = pm().getObjectById(type, id);
			return obj_return;
		} catch (Exception ex){
			throw new PersistenceException(-9999, ex);
		} finally {
	    }
	}
	
	@Override
	public void update(T transientObject) {
		
	}

	@Override
	public void delete(T persistentObject) {
		try {
			pm().deletePersistent(persistentObject);
		} catch (Exception ex){
			throw new PersistenceException(-9999, ex);
		} finally {
	    }
	}

	@Override
	public int count() {
		try {
			Query query = pm().newQuery(type);
			query.setResult("count()");
			return (Integer)(query.execute());
		} catch (Exception ex){
			throw new PersistenceException(-9999, ex);
		} finally {
	    }
	}

}
