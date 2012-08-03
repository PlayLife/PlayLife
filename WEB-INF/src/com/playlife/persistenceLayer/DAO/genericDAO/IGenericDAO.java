package com.playlife.persistenceLayer.DAO.genericDAO;

import java.io.Serializable;

public interface IGenericDAO<T, PK extends Serializable> {
    PK save(T newInstance);
    T get(PK id);
    void update(T transientObject);
    void delete(T persistentObject);
    void persist(T transientObject);
    T merge(T transientObject);
	void flush();
	int count();
}

