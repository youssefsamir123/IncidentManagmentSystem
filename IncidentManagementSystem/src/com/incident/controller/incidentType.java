package com.incident.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.event.RowEditEvent;
import org.springframework.context.ApplicationContext;
import com.incident.entity.IncidentType;
import com.incident.entity.Question;
import com.incident.service.IncidentTypeService;
import com.incident.service.QuestionService;
import com.incident.util.Util;

import java.util.*;

@ManagedBean
@ViewScoped
public class incidentType implements Serializable {
	private static final long serialVersionUID = 1L;
	String typeName, newTypeName, questionText;
	boolean typeActive, questActive, questMandatory;
	int typeId;
	private List<IncidentType> incidentTypes;
	private List<Question> questions;
	private IncidentTypeService incidentTypeService;
	private QuestionService questionService;
	private boolean disabled;
	
	@PostConstruct
	public void init() {
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession currentSession = (HttpSession) fc.getExternalContext()
					.getSession(true);
			currentSession.setAttribute("currentPage", "report.xhtml");
			ApplicationContext applicationContext = Util
					.getApplicationContext();
			incidentTypeService = (IncidentTypeService) applicationContext
					.getBean(IncidentTypeService.class);
			questionService = (QuestionService) applicationContext
					.getBean(QuestionService.class);
			incidentTypes = incidentTypeService.findAll();
			typeId = incidentTypes.get(0).getTypeId();
			questions = questionService.findByIncidentType(typeId);
			disabled = true;
			incidentTypeService = null;
			questionService = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getTypeQuestions(AjaxBehaviorEvent event) {
		ApplicationContext applicationContext = Util.getApplicationContext();
		questionService = (QuestionService) applicationContext
				.getBean(QuestionService.class);
		questions = questionService.findByIncidentType(typeId);
		questionService = null;
	}

	public void addnewType() {
		try {
			ApplicationContext applicationContext = Util
					.getApplicationContext();
			incidentTypeService = applicationContext
					.getBean(IncidentTypeService.class);

			System.out.println("incident Type ========== " + newTypeName);
			if (newTypeName == null || newTypeName.equals("")) {
				String strMsg = "Please Enter The New Type Name.";
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, strMsg,
								" "));
			} else {
				IncidentType type = new IncidentType();
				type.setActive(true);
				type.setTypeName(newTypeName);

				boolean success = incidentTypeService.save(type);
				if (success) {
					incidentTypes = incidentTypeService.findAll();
					newTypeName = "";
					String strMsg = "The " + newTypeName
							+ " Type Added Successfuly.";
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									strMsg, " "));
				} else {
					String strMsg = "Please Try Again Later To Add New Type There is an Error.";
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									strMsg, " "));
				}
			}

			incidentTypeService = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void editType() {
		ApplicationContext applicationContext = Util.getApplicationContext();
		incidentTypeService = (IncidentTypeService) applicationContext
				.getBean(IncidentTypeService.class);
		IncidentType editType = incidentTypeService.getIncidentTypeById(typeId);
		typeName = editType.getTypeName();
		typeActive = editType.isActive();
		disabled = false;
		incidentTypeService = null;
	}

	public void updateIncidentType() {
		try {
			ApplicationContext applicationContext = Util
					.getApplicationContext();
			incidentTypeService = (IncidentTypeService) applicationContext
					.getBean(IncidentTypeService.class);

			IncidentType editType = incidentTypeService
					.getIncidentTypeById(typeId);
			editType.setActive(typeActive);
			editType.setTypeName(typeName);
			if (typeName == null || typeName.equals("")) {
				String strMsg = "Please Enter The Type Name to Update.";
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, strMsg,
								" "));
			} else {
				boolean success = incidentTypeService.update(editType);
				if (success) {
					incidentTypes = incidentTypeService.findAll();
					typeName = "";
					typeActive = false;
					disabled = true;
					String strMsg = "The " + typeName
							+ " Type Updated Successfuly.";
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									strMsg, " "));
				} else {
					String strMsg = "Please Try Again Later To Update The Incident Type There is an Error.";
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									strMsg, " "));
				}
			}

			incidentTypeService = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addNewQuestion() {
		try {
			ApplicationContext applicationContext = Util
					.getApplicationContext();
			incidentTypeService = (IncidentTypeService) applicationContext
					.getBean(IncidentTypeService.class);
			questionService = (QuestionService) applicationContext
					.getBean(QuestionService.class);

			if (questionText == null || questionText.equals("")) {
				String strMsg = "Please Enter The Question Text to Add It.";
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, strMsg,
								" "));
			} else {
				IncidentType Type = incidentTypeService
						.getIncidentTypeById(typeId);
				Question newQuestion = new Question();
				newQuestion.setActive(true);
				newQuestion.setMandatory(true);
				newQuestion.setQuestion(questionText);
				newQuestion.setIncidentType(Type);

				boolean success = questionService.save(newQuestion);
				if (success) {
					questions = questionService.findByIncidentType(typeId);
					questionText = "";
					String strMsg = "The Question  Added Successfuly.";
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									strMsg, " "));
				} else {
					String strMsg = "Please Try Again Later To Add New Question There is an Error.";
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									strMsg, " "));
				}
			}

			incidentTypeService = null;
			questionService = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateQuestion(RowEditEvent event) {
		try {
			ApplicationContext applicationContext = Util
					.getApplicationContext();
			questionService = (QuestionService) applicationContext
					.getBean(QuestionService.class);

			Question question = (Question) event.getObject();
			boolean success = questionService.update(question);
			if (success) {
				questions = questionService.findByIncidentType(typeId);
				String strMsg = "The Question Updated Successfuly.";
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, strMsg,
								" "));
			} else {
				String strMsg = "Please Try Again Later To Update The Question There is an Error.";
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, strMsg,
								" "));
			}

			questionService = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateQuestionStatus(AjaxBehaviorEvent event) {
		try {
			ApplicationContext applicationContext = Util
					.getApplicationContext();
			questionService = (QuestionService) applicationContext
					.getBean(QuestionService.class);

			Question question = (Question) event.getComponent().getAttributes()
					.get("targetquestion");

			boolean success = questionService.update(question);
			if (success) {
				questions = questionService.findByIncidentType(typeId);
				String strMsg = "The Question Updated Successfuly.";
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, strMsg,
								" "));
			} else {
				String strMsg = "Please Try Again Later To Update The Question There is an Error.";
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, strMsg,
								" "));
			}

			questionService = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getNewTypeName() {
		return newTypeName;
	}

	public void setNewTypeName(String newTypeName) {
		this.newTypeName = newTypeName;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public boolean isTypeActive() {
		return typeActive;
	}

	public void setTypeActive(boolean typeActive) {
		this.typeActive = typeActive;
	}

	public boolean isQuestActive() {
		return questActive;
	}

	public void setQuestActive(boolean questActive) {
		this.questActive = questActive;
	}

	public boolean isQuestMandatory() {
		return questMandatory;
	}

	public void setQuestMandatory(boolean questMandatory) {
		this.questMandatory = questMandatory;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public List<IncidentType> getIncidentTypes() {
		return incidentTypes;
	}

	public void setIncidentTypes(List<IncidentType> incidentTypes) {
		this.incidentTypes = incidentTypes;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

}
