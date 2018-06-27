package com.incident.service;

import java.util.List;


public interface GenericService<T> {

	boolean save(T t);

	List<T> findAll();

	boolean update(T t);
}
