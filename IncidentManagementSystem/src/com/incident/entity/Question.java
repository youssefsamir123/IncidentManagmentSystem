/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.incident.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * @author youssef
 */
@Entity
@Table(name = "question")
public class Question implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "QUESTIONID")
	private Integer questionId;
	@Column(name = "QUESTIONTEXT")
	private String question;
	@ManyToOne
	@JoinColumn(name = "TYPEID", referencedColumnName = "TYPEID")
	private IncidentType incidentType;
	@Column(name = "ACTIVE")
	private boolean active;
	@Column(name = "MANDATORY")
	private boolean mandatory;
	@Transient
	private String answer;

	public String getAnswer() {
		return answer;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Question() {
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public IncidentType getIncidentType() {
		return incidentType;
	}

	public void setIncidentType(IncidentType incidentType) {
		this.incidentType = incidentType;
	}

	@Override
	public String toString() {
		return "Question [questionid=" + questionId + ", question=" + question
				+ ", incidentType=" + incidentType.toString() + "]";
	}

}
