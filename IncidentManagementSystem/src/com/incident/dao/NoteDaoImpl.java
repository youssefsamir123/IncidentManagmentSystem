package com.incident.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.incident.entity.IncidentType;
import com.incident.entity.Note;
import com.incident.entity.RequestMoreInfo;

@Repository
public class NoteDaoImpl extends AbstractDao implements NoteDao {

	@Override
	public void save(Note note) {
		persist(note);
	}

	@SuppressWarnings("unchecked")
	public List<Note> findAll() {
		Criteria criteria = getSession().createCriteria(IncidentType.class);
		return (List<Note>) criteria.list();
	}

	@Override
	public void update(Note note) {
		getSession().update(note);
	}

	public List<Note> findNotesByIncidentId(int id) {
		Criteria crit = getSession().createCriteria(Note.class);
		crit.add(Restrictions.eq("incident.incidentId", id));
		List<Note> noteList;
		noteList = crit.list();
		return noteList;
	}

}
