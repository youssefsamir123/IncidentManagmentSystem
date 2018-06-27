package com.test.main;

import java.util.Date;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import com.incident.configuration.AppConfig;
import com.incident.entity.Incident;
import com.incident.entity.IncidentType;
import com.incident.service.IncidentService;
import com.incident.service.IncidentTypeService;

public class IncidentMain {

	public static void main(String args[]) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(
				AppConfig.class);

		IncidentService incidentService = (IncidentService) context
				.getBean(IncidentService.class);

		IncidentTypeService incidentTypeService = (IncidentTypeService) context
				.getBean(IncidentTypeService.class);
		/* Create incident type */
		IncidentType incidentType =new IncidentType();
		incidentType.setTypeName("Complain");
		
		incidentTypeService.save(incidentType);
		
		/* create Incident */
		Incident incident = new Incident();
		incident.setItemValue("1000500");
		incident.setLocation("MUM");
		incident.setIncidentDate(new Date());
		incident.setRemarks("my mobile was stolen from me ");
		incident.setReportedUser("aabdelmohsen@mum");
		incident.setStatus("OPENED");
		incident.setIncidentType(incidentType);
		incidentService.save(incident);

		context.close();
	}
}
