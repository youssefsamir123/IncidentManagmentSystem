package com.incident.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.incident.entity.Incident;
import com.incident.entity.Question;

@Service
@Transactional
public interface IncidentService extends GenericService<Incident>{

	public boolean saveIncidentAndQuestions(Incident incident,List<Question> questions); 
	public List<Incident> findByUserMail(String userMail);
	public Incident findByIncidentId(int incindetId);
	public List<Incident> findIncdentsByIncidentType(int typeId);
}
