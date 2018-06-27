package com.incident.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.incident.dao.NoteDao;
import com.incident.entity.Note;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteDao dao;

	public boolean save(Note note) {		
		try {
			dao.save(note);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Note> findAll() {
		return dao.findAll();
	}

	public boolean update(Note note) {
		try {
			dao.update(note);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public List<Note> findNotesByIncidentId(int id) {
		return dao.findNotesByIncidentId(id);
	}

}
