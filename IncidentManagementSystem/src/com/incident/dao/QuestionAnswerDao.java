package com.incident.dao;


import java.util.List;
import com.incident.entity.QuestionAnswer;

public interface QuestionAnswerDao extends GenericDAO<QuestionAnswer>{
	
	public List<QuestionAnswer> findByIncidentId(int incidentId);
}
