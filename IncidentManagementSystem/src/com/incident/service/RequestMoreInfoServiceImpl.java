package com.incident.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.incident.dao.RequestMoreInfoDao;
import com.incident.entity.RequestMoreInfo;

@Service
@Transactional
public class RequestMoreInfoServiceImpl implements RequestMoreInfoService {

	@Autowired
	private RequestMoreInfoDao dao;

	@Override
	public boolean save(RequestMoreInfo requestMoreInfo) {
		try {
			dao.save(requestMoreInfo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<RequestMoreInfo> findAll() {
		return dao.findAll();
	}

	@Override
	public boolean update(RequestMoreInfo requestMoreInfo) {
		try {
			dao.update(requestMoreInfo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<RequestMoreInfo> findByIncidentId(int incidentId) {
		return dao.findByIncidentId(incidentId);
	}
}
