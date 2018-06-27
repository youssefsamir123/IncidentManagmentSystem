package com.incident.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.incident.entity.IncidentType;
import com.incident.entity.Question;

@Repository
public class QuestionDaoImpl extends AbstractDao implements QuestionDao {

	@Override
	public void save(Question question) {
		persist(question);
	}

	@SuppressWarnings("unchecked")
	public List<Question> findAll() {
		Criteria criteria = getSession().createCriteria(IncidentType.class);
		return (List<Question>) criteria.list();
	}

	@Override
	public void update(Question question) {
		getSession().update(question);
	}

	@Override
	public List<Question> findByIncidentType(int typeId) {
		Criteria crit = getSession().createCriteria(Question.class);
		crit.createAlias("incidentType", "IT");
		crit.add(Restrictions.eq("IT.typeId", typeId));
		List<Question> questionsList;
		questionsList = crit.list();
		return questionsList;
	}

	public List<Question> findActiveQuestionsByIncidentType(int typeId) {
		Criteria crit = getSession().createCriteria(Question.class);
		crit.createAlias("incidentType", "IT");
		crit.add(Restrictions.eq("IT.typeId", typeId));
		crit.add(Restrictions.eq("active", true));
		List<Question> questionsList;
		questionsList = crit.list();
		return questionsList;
	}
}
