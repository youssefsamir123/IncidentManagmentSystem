package com.incident.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.incident.entity.IncidentType;

@Service
@Transactional
public interface IncidentTypeService extends GenericService<IncidentType> {

	public IncidentType getIncidentTypeById(int typeId);
	public List<IncidentType> findAllActiveTypes();
}
