package com.incident.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class Home {

	@PostConstruct
	public void init() {
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession currentSession = (HttpSession) fc.getExternalContext()
					.getSession(true);
			currentSession.setAttribute("currentPage", "home.xhtml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String newIncident() {
		return "newIncident.xhtml";
	}
}
