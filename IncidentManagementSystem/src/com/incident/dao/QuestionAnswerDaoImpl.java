package com.incident.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.incident.entity.IncidentType;
import com.incident.entity.QuestionAnswer;

@Repository
public class QuestionAnswerDaoImpl extends AbstractDao implements
		QuestionAnswerDao {

	@Override
	public void save(QuestionAnswer questionAnswer) {
		persist(questionAnswer);
	}

	@SuppressWarnings("unchecked")
	public List<QuestionAnswer> findAll() {
		Criteria criteria = getSession().createCriteria(IncidentType.class);
		return (List<QuestionAnswer>) criteria.list();
	}

	@Override
	public void update(QuestionAnswer questionAnswer) {
		getSession().update(questionAnswer);
	}

	@Override
	public List<QuestionAnswer> findByIncidentId(int incidentId) {
		Criteria crit = getSession().createCriteria(QuestionAnswer.class);
		crit.add(Restrictions.eq("incident.incidentId", incidentId));
		List<QuestionAnswer> questionsAnswersList;
		questionsAnswersList = crit.list();
		return questionsAnswersList;
	}
}
