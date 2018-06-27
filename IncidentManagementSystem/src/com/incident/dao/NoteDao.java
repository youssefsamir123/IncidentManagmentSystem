package com.incident.dao;

import java.util.List;

import com.incident.entity.Note;

public interface NoteDao extends GenericDAO<Note> {

	public List<Note> findNotesByIncidentId(int id);
}
