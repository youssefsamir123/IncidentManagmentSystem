package com.incident.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.incident.entity.emergency;

@Service
@Transactional
public interface EmergencyService extends GenericService<emergency>{

}
