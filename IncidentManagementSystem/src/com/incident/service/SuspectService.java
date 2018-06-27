package com.incident.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.incident.entity.Suspect;

@Service
@Transactional
public interface SuspectService extends GenericService<Suspect>{

	public List<Suspect> findSuspectsByIncidentId(int incidentId);
}
