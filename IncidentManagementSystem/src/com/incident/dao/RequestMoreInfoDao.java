package com.incident.dao;


import java.util.List;

import com.incident.entity.RequestMoreInfo;

public interface RequestMoreInfoDao extends GenericDAO<RequestMoreInfo>{
	
	public List<RequestMoreInfo> findByIncidentId(int incidentId);
}
