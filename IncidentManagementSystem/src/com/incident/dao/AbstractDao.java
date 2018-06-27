package com.incident.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public <T> void persist(T t) {
		getSession().persist(t);
	}

	public <T> void delete(T t) {
		getSession().delete(t);
	}
}
