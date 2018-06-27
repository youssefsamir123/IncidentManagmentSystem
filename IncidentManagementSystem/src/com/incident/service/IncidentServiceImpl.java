package com.incident.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.incident.dao.IncidentDao;
import com.incident.dao.QuestionAnswerDao;
import com.incident.entity.Incident;
import com.incident.entity.Question;
import com.incident.entity.QuestionAnswer;

@Service
@Transactional
public class IncidentServiceImpl implements IncidentService {

	@Autowired
	private IncidentDao dao;
	@Autowired
	private QuestionAnswerDao questionAnswerDao;

	@Override
	public boolean save(Incident incident) {
		try {
			dao.save(incident);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Incident> findAll() {
		return dao.findAll();
	}

	@Override
	public boolean update(Incident incident) {
		try {
			dao.update(incident);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean saveIncidentAndQuestions(Incident incident,
			List<Question> questions) {
		try {

			save(incident);

			for (Question question : questions) {
				QuestionAnswer questionAnswer = new QuestionAnswer();
				questionAnswer.setQuestion(question.getQuestion());
				questionAnswer.setAnswer(question.getAnswer());
				questionAnswer.setIncident(incident);
				questionAnswerDao.save(questionAnswer);
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Incident> findByUserMail(String userMail) {
		return dao.findByUserMail(userMail);
	}

	@Override
	public Incident findByIncidentId(int incindetId) {
		return dao.findByIncidentId(incindetId);
	}

	@Override
	public List<Incident> findIncdentsByIncidentType(int typeId) {
		return dao.findIncdentsByIncidentType(typeId);
	}

}
