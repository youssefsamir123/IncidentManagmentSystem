package com.incident.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.incident.dao.SuspectDao;
import com.incident.entity.Suspect;

@Service
@Transactional
public class SuspectServiceImpl implements SuspectService {

	@Autowired
	private SuspectDao dao;

	@Override
	public boolean save(Suspect suspect) {
		try {
			dao.save(suspect);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Suspect> findAll() {
		return dao.findAll();
	}

	@Override
	public boolean update(Suspect suspect) {
		try {
			dao.update(suspect);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Suspect> findSuspectsByIncidentId(int incidentId) {
		return dao.findSuspectsByIncidentId(incidentId);
	}
}
