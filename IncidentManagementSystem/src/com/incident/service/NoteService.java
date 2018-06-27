package com.incident.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.incident.entity.Note;

@Service
@Transactional
public interface NoteService extends GenericService<Note>{

	public List<Note> findNotesByIncidentId(int id);
}
