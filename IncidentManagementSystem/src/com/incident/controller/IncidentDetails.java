package com.incident.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import com.incident.entity.Incident;
import com.incident.entity.QuestionAnswer;
import com.incident.entity.RequestMoreInfo;
import com.incident.service.IncidentService;
import com.incident.service.QuestionAnswerService;
import com.incident.service.RequestMoreInfoService;
import com.incident.util.Util;

@ManagedBean
@ViewScoped
public class IncidentDetails implements Serializable {
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{login}")
	private Login login;
	private IncidentService incidentService;
	private QuestionAnswerService questionAnswerService;
	private Incident incident;
	private List<QuestionAnswer> questionAnswers;
	private List<RequestMoreInfo> requestMoreInfos;
	private RequestMoreInfoService requestMoreInfoService;
	private String replyText;
	private RequestMoreInfo selectedInfo;

	@PostConstruct
	public void init() {
		try {
			ApplicationContext applicationContext = Util
					.getApplicationContext();
			incidentService = (IncidentService) applicationContext
					.getBean(IncidentService.class);
			questionAnswerService = (QuestionAnswerService) applicationContext
					.getBean(QuestionAnswerService.class);
			requestMoreInfoService = (RequestMoreInfoService) applicationContext
					.getBean(RequestMoreInfoService.class);
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession currentSession = (HttpSession) fc.getExternalContext()
					.getSession(true);
			currentSession.setAttribute("currentPage", "myIncident.xhtml");
			int incidentId = (Integer) currentSession
					.getAttribute("selectedIncidentId");
			incident = incidentService.findByIncidentId(incidentId);
			questionAnswers = questionAnswerService
					.findByIncidentId(incidentId);
			requestMoreInfos = requestMoreInfoService
					.findByIncidentId(incidentId);
			requestMoreInfoService = null;
			questionAnswerService = null;
			incidentService = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void replyAction(ActionEvent event) {
		try {
			selectedInfo = (RequestMoreInfo) event.getComponent()
					.getAttributes().get("moreInfo");
			ApplicationContext applicationContext = Util
					.getApplicationContext();
			requestMoreInfoService = (RequestMoreInfoService) applicationContext
					.getBean(RequestMoreInfoService.class);
			selectedInfo.setRequestAnswer(replyText);
			selectedInfo.setAnsweredOrNot(true);
			requestMoreInfoService.update(selectedInfo);
			requestMoreInfos = requestMoreInfoService.findByIncidentId(incident
					.getincidentId());
			requestMoreInfoService = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getReplyText() {
		return replyText;
	}

	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public List<RequestMoreInfo> getRequestMoreInfos() {
		return requestMoreInfos;
	}

	public void setRequestMoreInfos(List<RequestMoreInfo> requestMoreInfos) {
		this.requestMoreInfos = requestMoreInfos;
	}

	public Incident getIncident() {
		return incident;
	}

	public void setIncident(Incident incident) {
		this.incident = incident;
	}

	public List<QuestionAnswer> getQuestionAnswers() {
		return questionAnswers;
	}

	public void setQuestionAnswers(List<QuestionAnswer> questionAnswers) {
		this.questionAnswers = questionAnswers;
	}

}
