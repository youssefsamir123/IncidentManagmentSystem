package com.incident.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.incident.entity.Question;

@Service
@Transactional
public interface QuestionService extends GenericService<Question> {

	public List<Question> findByIncidentType(int typeId);

	public List<Question> findActiveQuestionsByIncidentType(int typeId);
}
