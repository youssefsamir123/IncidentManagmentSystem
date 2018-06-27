package com.incident.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.incident.entity.QuestionAnswer;

@Service
@Transactional
public interface QuestionAnswerService extends GenericService<QuestionAnswer>{

	public List<QuestionAnswer> findByIncidentId(int incidentId);
}
