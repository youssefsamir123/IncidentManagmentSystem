package com.incident.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.incident.dao.EmergencyDao;
import com.incident.entity.emergency;

@Service
@Transactional
public class EmergencyServiceImpl implements EmergencyService {

	@Autowired
	private EmergencyDao dao;

	public boolean save(emergency emergency) {
		try {
			dao.save(emergency);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<emergency> findAll() {
		return dao.findAll();
	}

	public boolean update(emergency emergency) {

		try {
			dao.update(emergency);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
