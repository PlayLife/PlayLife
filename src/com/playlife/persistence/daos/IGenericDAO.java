package com.playlife.persistence.daos;

import java.io.Serializable;

public interface IGenericDAO<T, PK extends Serializable>{
    public void save(T newInstance);
    public T get(PK id);
    public void update(T transientObject);
    public void delete(T persistentObject);
	public int count();
}
