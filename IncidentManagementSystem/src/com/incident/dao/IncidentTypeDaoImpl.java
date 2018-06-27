package com.incident.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.incident.entity.Incident;
import com.incident.entity.IncidentType;

@Repository
public class IncidentTypeDaoImpl extends AbstractDao implements IncidentTypeDao {

	@Override
	public void save(IncidentType incidentType) {
		persist(incidentType);
	}

	@SuppressWarnings("unchecked")
	public List<IncidentType> findAll() {
		Criteria criteria = getSession().createCriteria(IncidentType.class);
		return (List<IncidentType>) criteria.list();
	}

	@Override
	public void update(IncidentType incidentType) {
		getSession().update(incidentType);
	}

	@Override
	public IncidentType getIncidentTypeById(int typeId) {
		Criteria criteria = getSession().createCriteria(IncidentType.class);
		criteria.add(Restrictions.eq("typeId", typeId));
		return (IncidentType) criteria.uniqueResult();
	}
	
	@Override
	public List<IncidentType> findAllActiveTypes(){
		Criteria criteria = getSession().createCriteria(IncidentType.class);
		criteria.add(Restrictions.eq("active", true));
		return (List<IncidentType>) criteria.list();
	}

}
