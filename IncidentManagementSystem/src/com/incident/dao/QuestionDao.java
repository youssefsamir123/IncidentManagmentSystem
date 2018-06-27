package com.incident.dao;

import java.util.List;

import com.incident.entity.Question;

public interface QuestionDao extends GenericDAO<Question> {

	public List<Question> findByIncidentType(int typeId);

	public List<Question> findActiveQuestionsByIncidentType(int typeId);

}
