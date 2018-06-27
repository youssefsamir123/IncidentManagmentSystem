package com.incident.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Navigation {

	@PostConstruct
	public void init() {
	}

	public String newIncident() {
		return "newIncident.xhtml";
	}

	public String allIncidents() {
		return "myIncident.xhtml";
	}

	public String reportPage() {
		return "report.xhtml";
	}

	public String emergencyPage() {
		return "emergency.xhtml";
	}

	public String incidentTypes() {
		return "incidentTypes.xhtml";
	}
}