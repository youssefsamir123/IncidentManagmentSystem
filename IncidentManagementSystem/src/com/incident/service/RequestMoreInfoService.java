package com.incident.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.incident.entity.RequestMoreInfo;

@Service
@Transactional
public interface RequestMoreInfoService extends GenericService<RequestMoreInfo>{

	public List<RequestMoreInfo> findByIncidentId(int incidentId);
	
}
