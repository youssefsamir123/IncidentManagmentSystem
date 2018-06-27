package com.incident.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import com.incident.configuration.AppConfig;
import com.incident.entity.Incident;
import com.incident.entity.IncidentType;
import com.incident.entity.Question;
import com.incident.entity.User;
import com.incident.service.IncidentService;
import com.incident.service.IncidentTypeService;
import com.incident.service.QuestionService;
import com.incident.service.UserService;
import com.incident.util.Util;

@ManagedBean
@ViewScoped
public class MyIncident implements Serializable {
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{login}")
	private Login login;
	private IncidentService incidentService;
	private List<Incident> incidents;
	private List<Incident> filteredIncidents;
	private int selectedIncidentId;
	private UserService userService;

	@PostConstruct
	public void init() {
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession currentSession = (HttpSession) fc.getExternalContext()
					.getSession(true);
			currentSession.setAttribute("currentPage", "myIncident.xhtml");
			ApplicationContext applicationContext = Util
					.getApplicationContext();
			incidentService = (IncidentService) applicationContext
					.getBean(IncidentService.class);
			userService = (UserService) applicationContext
					.getBean(UserService.class);
			User user = userService.getUserByUserMail(login.getUserMail());
			if (user != null)
				incidents = incidentService.findAll();
			else
				incidents = incidentService.findByUserMail(login.getUserMail());

			incidentService = null;
			userService = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String openIncidentPage() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession currentSession = (HttpSession) fc.getExternalContext()
				.getSession(true);
		currentSession.setAttribute("selectedIncidentId", selectedIncidentId);
		ApplicationContext applicationContext = Util.getApplicationContext();
		userService = (UserService) applicationContext
				.getBean(UserService.class);
		User user = userService.getUserByUserMail(login.getUserMail());
		userService = null;
		if (user != null) {
			incidentService = (IncidentService) applicationContext
					.getBean(IncidentService.class);
			Incident incident = incidentService
					.findByIncidentId(selectedIncidentId);
			if (incident.getStatus().equals("Initiated") && user.getType().equals("security")) {
				incident.setStatus("Opened");
				incidentService.update(incident);				
			}
			incidentService = null;
			return "incidentInvestegation.xhtml";
		} else
			return "incidentDetails.xhtml";

	}

	public void selectIncidentId(ActionEvent event) {
		selectedIncidentId = (Integer) event.getComponent().getAttributes()
				.get("incidentId");
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public List<Incident> getIncidents() {
		return incidents;
	}

	public void setIncidents(List<Incident> incidents) {
		this.incidents = incidents;
	}

	public List<Incident> getFilteredIncidents() {
		return filteredIncidents;
	}

	public void setFilteredIncidents(List<Incident> filteredIncidents) {
		this.filteredIncidents = filteredIncidents;
	}

}
