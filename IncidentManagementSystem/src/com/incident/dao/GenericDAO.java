package com.incident.dao;

import java.util.List;

public interface GenericDAO<T> {

	void save(T t);

	List<T> findAll();
	
	void update(T t);
	
}
