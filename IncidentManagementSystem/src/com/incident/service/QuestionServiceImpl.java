package com.incident.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.incident.dao.QuestionDao;
import com.incident.entity.Question;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDao dao;

	public boolean save(Question question) {
		try {
			dao.save(question);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Question> findAll() {
		return dao.findAll();
	}

	public boolean update(Question question) {
		try {
			dao.update(question);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Question> findByIncidentType(int typeId) {
		try {
			return dao.findByIncidentType(typeId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Question> findActiveQuestionsByIncidentType(int typeId) {
		try {
			return dao.findActiveQuestionsByIncidentType(typeId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
