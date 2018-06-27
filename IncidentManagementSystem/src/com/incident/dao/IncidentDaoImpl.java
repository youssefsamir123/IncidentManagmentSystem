package com.incident.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.incident.entity.Incident;

@Repository
public class IncidentDaoImpl extends AbstractDao implements IncidentDao{

	@Override
	public void save(Incident incident) {
		persist(incident);
	}

	@SuppressWarnings("unchecked")
	public List<Incident> findAll() {
		Criteria criteria = getSession().createCriteria(Incident.class);
		return (List<Incident>) criteria.list();
	}
	
	@Override
	public void update(Incident incident){
		getSession().update(incident);
	}

	@Override
	public List<Incident> findByUserMail(String userMail) {
		Criteria crit = getSession().createCriteria(Incident.class);
		crit.add(Restrictions.eq("reportedUser",userMail));
		List<Incident> incidentList;
		incidentList = crit.list();
		return incidentList;
	}
	
	@Override
	public Incident findByIncidentId(int incidentId){
		Criteria criteria = getSession().createCriteria(Incident.class);
		criteria.add(Restrictions.eq("incidentId",incidentId));
		return (Incident) criteria.uniqueResult();
	}
	
	@Override
	public List<Incident> findIncdentsByIncidentType(int typeId){
		Criteria crit = getSession().createCriteria(Incident.class);
		crit.add(Restrictions.eq("incidentType.typeId",typeId));
		List<Incident> incidentList;
		incidentList = crit.list();
		return incidentList;
	}

}
