package com.incident.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import com.incident.entity.emergency;
import com.incident.service.EmergencyService;
import com.incident.util.Util;

@ManagedBean
@ViewScoped
public class Emergency implements Serializable {

	private List<emergency> emergencies;
	private EmergencyService emergencyService;

	@PostConstruct
	public void init() {
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession currentSession = (HttpSession) fc.getExternalContext()
					.getSession(true);
			currentSession.setAttribute("currentPage", "emergency.xhtml");
			ApplicationContext applicationContext = Util
					.getApplicationContext();
			emergencyService = (EmergencyService) applicationContext
					.getBean(EmergencyService.class);
			emergencies = emergencyService.findAll();
			emergencyService = null;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<emergency> getEmergencies() {
		return emergencies;
	}

	public void setEmergencies(List<emergency> emergencies) {
		this.emergencies = emergencies;
	}

}
