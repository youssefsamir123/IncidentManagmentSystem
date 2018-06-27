package com.incident.dao;

import java.util.List;

import com.incident.entity.Incident;

public interface IncidentDao extends GenericDAO<Incident>{
	
	public List<Incident> findByUserMail(String userMail);
	public Incident findByIncidentId(int incindetId);
	public List<Incident> findIncdentsByIncidentType(int typeId);
	
}
