package com.incident.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import com.incident.entity.emergency;

@Repository
public class EmergencyDaoImpl extends AbstractDao implements EmergencyDao{

	@Override
	public void save(emergency emergency) {
		persist(emergency);
	}

	@SuppressWarnings("unchecked")
	public List<emergency> findAll() {
		Criteria criteria = getSession().createCriteria(emergency.class);
		return (List<emergency>) criteria.list();
	}
	
	@Override
	public void update(emergency emergency){
		getSession().update(emergency);
	}

}
