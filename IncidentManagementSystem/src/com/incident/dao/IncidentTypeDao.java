package com.incident.dao;

import java.util.List;

import com.incident.entity.IncidentType;

public interface IncidentTypeDao extends GenericDAO<IncidentType> {
	public IncidentType getIncidentTypeById(int typeId);
	public List<IncidentType> findAllActiveTypes();
} 
