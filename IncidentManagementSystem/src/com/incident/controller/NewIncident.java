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
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import com.incident.configuration.AppConfig;
import com.incident.entity.Incident;
import com.incident.entity.IncidentType;
import com.incident.entity.Question;
import com.incident.service.IncidentService;
import com.incident.service.IncidentTypeService;
import com.incident.service.QuestionService;
import com.incident.util.Util;

@ManagedBean
@ViewScoped
public class NewIncident implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<IncidentType> incidentTypes;
	private List<Question> questions;
	private Date incidentDate;
	private IncidentTypeService incidentTypeService;
	private QuestionService questionService;
	private int selectedTypeId;
	private String incidentLocation;
	private String itemValue;
	private String remarks;
	@ManagedProperty(value = "#{login}")
	private Login login;
	private IncidentService incidentService;

	@PostConstruct
	public void init() {
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession currentSession = (HttpSession) fc.getExternalContext()
					.getSession(true);
			currentSession.setAttribute("currentPage", "newIncident.xhtml");
			ApplicationContext applicationContext = Util
					.getApplicationContext();
			incidentTypeService = (IncidentTypeService) applicationContext
					.getBean(IncidentTypeService.class);
			questionService = (QuestionService) applicationContext
					.getBean(QuestionService.class);
			incidentTypes = incidentTypeService.findAllActiveTypes();
			IncidentType incidentType = incidentTypes.get(0);
			questions = questionService
					.findActiveQuestionsByIncidentType(incidentType.getTypeId());
			questionService = null;
			incidentTypeService = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changeQuestions() {
		try {
			ApplicationContext applicationContext = Util
					.getApplicationContext();
			questionService = (QuestionService) applicationContext
					.getBean(QuestionService.class);
			questions = questionService
					.findActiveQuestionsByIncidentType(selectedTypeId);
			questionService = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addNewIncident() {
		try {
			ApplicationContext applicationContext = Util
					.getApplicationContext();
			incidentService = (IncidentService) applicationContext
					.getBean(IncidentService.class);

			Incident incident = new Incident();
			incident.setIncidentDate(incidentDate);
			incident.setItemValue(itemValue);
			incident.setLocation(incidentLocation);
			incident.setRemarks(remarks);
			incident.setReportedUser(login.getUserMail());
			incident.setStatus("Initiated");
			for (IncidentType type : incidentTypes) {
				if (selectedTypeId == type.getTypeId()) {
					incident.setIncidentType(type);
					break;
				}
			}

			boolean result = incidentService.saveIncidentAndQuestions(incident,
					questions);

			if (result) {
				clearFields();
				String strMsg = "The Incident Added successfuly.";
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, strMsg,
								" "));
			} else {
				String strMsg = "Please Try Again Later To Add The Incident There is an Error.";
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, strMsg,
								" "));
			}

			incidentService = null;

		} catch (Exception e) {
			String strMsg = "Please Try Again Later To Add The Incident There is an Error.";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, strMsg, " "));
			e.printStackTrace();
		}

	}

	public void clearFields() {
		itemValue = "";
		incidentLocation = "";
		remarks = "";
		questions = null;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public List<IncidentType> getIncidentTypes() {
		return incidentTypes;
	}

	public void setIncidentTypes(List<IncidentType> incidentTypes) {
		this.incidentTypes = incidentTypes;
	}

	public int getSelectedTypeId() {
		return selectedTypeId;
	}

	public void setSelectedTypeId(int selectedTypeId) {
		this.selectedTypeId = selectedTypeId;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Date getIncidentDate() {
		return incidentDate;
	}

	public void setIncidentDate(Date incidentDate) {
		this.incidentDate = incidentDate;
	}

	public String getIncidentLocation() {
		return incidentLocation;
	}

	public void setIncidentLocation(String incidentLocation) {
		this.incidentLocation = incidentLocation;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
