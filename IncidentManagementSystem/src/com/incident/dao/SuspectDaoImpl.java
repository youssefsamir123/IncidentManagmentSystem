package com.incident.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.incident.entity.IncidentType;
import com.incident.entity.Suspect;

@Repository
public class SuspectDaoImpl extends AbstractDao implements SuspectDao {

	@Override
	public void save(Suspect suspect) {
		persist(suspect);
	}

	@SuppressWarnings("unchecked")
	public List<Suspect> findAll() {
		Criteria criteria = getSession().createCriteria(IncidentType.class);
		return (List<Suspect>) criteria.list();
	}

	@Override
	public void update(Suspect suspect) {
		getSession().update(suspect);
	}

	@Override
	public List<Suspect> findSuspectsByIncidentId(int incidentId) {
		Criteria crit = getSession().createCriteria(Suspect.class);
		crit.add(Restrictions.eq("incident.incidentId", incidentId));
		List<Suspect> suspects;
		suspects = crit.list();
		return suspects;
	}

}
