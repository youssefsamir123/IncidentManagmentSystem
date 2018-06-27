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
@Table(name = "requestmoreinfo")
public class RequestMoreInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RMINFOID")
	private Integer rmInfoId;
	@Column(name = "REQUESTBODY")
	private String requestBody;
	@Column(name = "REQUESTANSWER")
	private String requestAnswer;
	@Column(name="ASNWEREDORNOT")
	private boolean answeredOrNot;
	@ManyToOne
	@JoinColumn(name = "INCIDENTID", referencedColumnName = "INCIDENTID")
	private Incident incident;
	@ManyToOne
	@JoinColumn(name = "USER", referencedColumnName = "USERID")
	private User user;

	public RequestMoreInfo() {
	}

	
	public boolean isAnsweredOrNot() {
		return answeredOrNot;
	}
	
	public void setAnsweredOrNot(boolean answeredOrNot) {
		this.answeredOrNot = answeredOrNot;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getRmInfoId() {
		return rmInfoId;
	}

	public void setRmInfoId(Integer rmInfoId) {
		this.rmInfoId = rmInfoId;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public String getRequestAnswer() {
		return requestAnswer;
	}

	public void setRequestAnswer(String requestAnswer) {
		this.requestAnswer = requestAnswer;
	}

	public Incident getIncident() {
		return incident;
	}

	public void setIncident(Incident incident) {
		this.incident = incident;
	}

	@Override
	public String toString() {
		return "RequestMoreInfo [rmInfoId=" + rmInfoId + ", requestBody="
				+ requestBody + ", requestAnswer=" + requestAnswer
				+ ", incident=" + incident + ", user=" + user + "]";
	}

}
