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

/**
 * 
 * @author youssef
 */
@Entity
@Table(name = "questionanswer")
public class QuestionAnswer implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "QAID")
	private Integer qaId;
	@Column(name = "QUESTIONBODY")
	private String question;
	@Column(name = "ANSWERBODY")
	private String answer;
	@ManyToOne
	@JoinColumn(name = "INCIDENTID", referencedColumnName = "INCIDENTID")
	private Incident incident;

	public QuestionAnswer() {
	}

	public Integer getQaId() {
		return qaId;
	}

	public void setQaId(Integer qaId) {
		this.qaId = qaId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Incident getIncident() {
		return incident;
	}

	public void setIncident(Incident incident) {
		this.incident = incident;
	}

	@Override
	public String toString() {
		return "Questionanswer [qaid=" + qaId + ", question=" + question
				+ ", answer=" + answer + ", incidentid=" + incident.toString()
				+ "]";
	}

}
