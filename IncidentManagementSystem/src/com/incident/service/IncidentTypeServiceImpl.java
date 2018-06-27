package com.incident.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.incident.dao.IncidentTypeDao;
import com.incident.entity.IncidentType;

@Service
@Transactional
public class IncidentTypeServiceImpl implements IncidentTypeService {

	@Autowired
	private IncidentTypeDao dao;

	@Override
	public boolean save(IncidentType incidentType) {
		try {
			dao.save(incidentType);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<IncidentType> findAll() {
		return dao.findAll();
	}

	@Override
	public boolean update(IncidentType incidentType) {
		try {
			dao.update(incidentType);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public IncidentType getIncidentTypeById(int typeId) {
		try {
			return dao.getIncidentTypeById(typeId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<IncidentType> findAllActiveTypes() {
		try {
			return dao.findAllActiveTypes();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
