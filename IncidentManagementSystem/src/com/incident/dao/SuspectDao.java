package com.incident.dao;


import java.util.List;

import com.incident.entity.Question;
import com.incident.entity.Suspect;

public interface SuspectDao extends GenericDAO<Suspect>{
	
	public List<Suspect> findSuspectsByIncidentId(int incidentId);
}
