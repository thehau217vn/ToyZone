package com.toyZone.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;

<<<<<<< HEAD
public interface AbstractRepo<ID extends Serializable,T > {
	List<T> findAll();
	T insert(T entity);
	T update(T entity);
	boolean delete(ID id);
	T findById(ID var1);
	T updateByProperty(SessionFactory sessionFactory, Map<String, Object> property,Map<String, Object> condition);
	Object[] findByProperty(Map<String, Object> property,String sortExpression, String sortDirection, Integer offset, Integer limit, String[] filter);
=======
public interface AbstractRepo<ID extends Serializable, T> {
    List<T> findAll();

    T insert(T entity);

    T update(T entity);

    boolean delete(ID id);

    T findById(ID var1);

    T updateByProperty(SessionFactory sessionFactory, Map<String, Object> property, Map<String, Object> condition);

    Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit, String[] filter);
>>>>>>> develop
}
