package com.incident.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.incident.dao.QuestionAnswerDao;
import com.incident.entity.QuestionAnswer;

@Service
@Transactional
public class QuestionAnswerServiceImpl implements QuestionAnswerService {

	@Autowired
	private QuestionAnswerDao dao;

	@Override
	public boolean save(QuestionAnswer questionAnswer) {
		try {
			dao.save(questionAnswer);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<QuestionAnswer> findAll() {
		return dao.findAll();
	}

	@Override
	public boolean update(QuestionAnswer questionAnswer) {
		try {
			dao.update(questionAnswer);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<QuestionAnswer> findByIncidentId(int incidentId) {
		return dao.findByIncidentId(incidentId);
	}
}
