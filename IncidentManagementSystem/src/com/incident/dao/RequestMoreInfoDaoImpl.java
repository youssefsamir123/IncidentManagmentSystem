package com.incident.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.incident.entity.IncidentType;
import com.incident.entity.RequestMoreInfo;

@Repository
public class RequestMoreInfoDaoImpl extends AbstractDao implements RequestMoreInfoDao{

	@Override
	public void save(RequestMoreInfo requestMoreInfo) {
		persist(requestMoreInfo);
	}

	@SuppressWarnings("unchecked")
	public List<RequestMoreInfo> findAll() {
		Criteria criteria = getSession().createCriteria(IncidentType.class);
		return (List<RequestMoreInfo>) criteria.list();
	}
	
	@Override
	public void update(RequestMoreInfo requestMoreInfo){
		getSession().update(requestMoreInfo);
	}

	@Override
	public List<RequestMoreInfo> findByIncidentId(int incidentId){
		Criteria crit = getSession().createCriteria(RequestMoreInfo.class);
		crit.add(Restrictions.eq("incident.incidentId",incidentId));
		List<RequestMoreInfo> infoList;
		infoList = crit.list();
		return infoList;
	}
	
}
