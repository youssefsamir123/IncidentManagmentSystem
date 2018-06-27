package com.incident.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import com.incident.entity.Incident;
import com.incident.entity.Note;
import com.incident.entity.QuestionAnswer;
import com.incident.entity.RequestMoreInfo;
import com.incident.entity.Suspect;
import com.incident.entity.User;
import com.incident.service.IncidentService;
import com.incident.service.NoteService;
import com.incident.service.QuestionAnswerService;
import com.incident.service.RequestMoreInfoService;
import com.incident.service.SuspectService;
import com.incident.service.UserService;
import com.incident.util.Util;

@ManagedBean
@ViewScoped
public class Investigation implements Serializable {
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{login}")
	private Login login;
	private IncidentService incidentService;
	private QuestionAnswerService questionAnswerService;
	private Incident incident;
	private List<QuestionAnswer> questionAnswers;
	private List<RequestMoreInfo> requestMoreInfos;
	private RequestMoreInfoService requestMoreInfoService;
	private String requestBody;
	private UserService userService;
	private String noteBody;
	private NoteService noteService;
	private List<Note> notes;
	private SuspectService suspectService;
	private String suspectName;
	private List<Suspect> suspects;
	private User currentUser;
	private RequestMoreInfo selectedInfo;
	private boolean updateDataFlag = false;
	private String investigationResult;
	private String userType;

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
			noteService = (NoteService) applicationContext
					.getBean(NoteService.class);
			suspectService = (SuspectService) applicationContext
					.getBean(SuspectService.class);
			userService = (UserService) applicationContext
					.getBean(UserService.class);
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession currentSession = (HttpSession) fc.getExternalContext()
					.getSession(true);
			currentSession.setAttribute("currentPage", "myIncident.xhtml");
			userType = (String) currentSession.getAttribute("userType");
			int incidentId = (Integer) currentSession
					.getAttribute("selectedIncidentId");
			currentUser = userService.getUserByUserMail(login.getUserMail());
			incident = incidentService.findByIncidentId(incidentId);
			questionAnswers = questionAnswerService
					.findByIncidentId(incidentId);
			requestMoreInfos = requestMoreInfoService
					.findByIncidentId(incidentId);
			notes = noteService.findNotesByIncidentId(incidentId);
			suspects = suspectService.findSuspectsByIncidentId(incidentId);
			userService = null;
			suspectService = null;
			noteService = null;
			requestMoreInfoService = null;
			questionAnswerService = null;
			incidentService = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void requestMoreInfo() {
		try {
			ApplicationContext applicationContext = Util
					.getApplicationContext();
			requestMoreInfoService = (RequestMoreInfoService) applicationContext
					.getBean(RequestMoreInfoService.class);
			RequestMoreInfo requestMoreInfo = new RequestMoreInfo();
			requestMoreInfo.setRequestBody(requestBody);
			requestMoreInfo.setIncident(incident);
			requestMoreInfo.setAnsweredOrNot(false);
			requestMoreInfo.setUser(currentUser);
			requestMoreInfoService.save(requestMoreInfo);
			requestMoreInfos = requestMoreInfoService.findByIncidentId(incident
					.getincidentId());
			requestBody = "";
			requestMoreInfoService = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void resetRequest() {
		requestBody = "";
		updateDataFlag = false;
	}

	public void selectRequest(AjaxBehaviorEvent event) {
		selectedInfo = (RequestMoreInfo) event.getComponent().getAttributes()
				.get("moreInfo");
		requestBody = selectedInfo.getRequestBody();
		updateDataFlag = true;
	}

	public void updateRequest() {
		try {
			ApplicationContext applicationContext = Util
					.getApplicationContext();
			requestMoreInfoService = (RequestMoreInfoService) applicationContext
					.getBean(RequestMoreInfoService.class);
			selectedInfo.setRequestBody(requestBody);
			requestMoreInfoService.update(selectedInfo);
			requestMoreInfos = requestMoreInfoService.findByIncidentId(incident
					.getincidentId());
			selectedInfo = null;
			updateDataFlag = false;
			requestBody = "";
			requestMoreInfoService = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addInvestigationNote() {
		try {
			ApplicationContext applicationContext = Util
					.getApplicationContext();
			noteService = (NoteService) applicationContext
					.getBean(NoteService.class);
			Note note = new Note();
			note.setNoteBody(noteBody);
			note.setIncident(incident);
			note.setUser(currentUser);
			noteService.save(note);
			notes = noteService.findNotesByIncidentId(incident.getincidentId());
			noteBody = "";
			noteService = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addSuspect() {
		try {
			ApplicationContext applicationContext = Util
					.getApplicationContext();
			suspectService = (SuspectService) applicationContext
					.getBean(SuspectService.class);
			Suspect suspect = new Suspect();
			suspect.setSuspectName(suspectName);
			suspect.setIncident(incident);
			suspect.setUser(currentUser);
			suspectService.save(suspect);
			suspects = suspectService.findSuspectsByIncidentId(incident
					.getincidentId());
			suspectName = "";
			suspectService = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeTheIncident() {
		try {
			ApplicationContext applicationContext = Util
					.getApplicationContext();
			incidentService = (IncidentService) applicationContext
					.getBean(IncidentService.class);

			incident.setResult(investigationResult);
			incident.setSecurityUser(currentUser);
			incident.setStatus("Closed");
			incidentService.update(incident);
			investigationResult = "";
			incidentService = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getInvestigationResult() {
		return investigationResult;
	}

	public void setInvestigationResult(String investigationResult) {
		this.investigationResult = investigationResult;
	}

	public boolean isUpdateDataFlag() {
		return updateDataFlag;
	}

	public void setUpdateDataFlag(boolean updateDataFlag) {
		this.updateDataFlag = updateDataFlag;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
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

	public String getNoteBody() {
		return noteBody;
	}

	public void setNoteBody(String noteBody) {
		this.noteBody = noteBody;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public String getSuspectName() {
		return suspectName;
	}

	public void setSuspectName(String suspectName) {
		this.suspectName = suspectName;
	}

	public List<Suspect> getSuspects() {
		return suspects;
	}

	public void setSuspects(List<Suspect> suspects) {
		this.suspects = suspects;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
