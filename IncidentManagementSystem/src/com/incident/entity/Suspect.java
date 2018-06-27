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
@Table(name = "suspect")
public class Suspect implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SUSPECTID")
	private Integer suspectId;
	@Column(name = "SUSPECTNAME")
	private String suspectName;
	@ManyToOne
	@JoinColumn(name = "INCIDENTID", referencedColumnName = "INCIDENTID")
	private Incident incident;
	@ManyToOne
	@JoinColumn(name = "USER", referencedColumnName = "USERID")
	private User user;

	public Suspect() {
	}

	public Integer getSuspectId() {
		return suspectId;
	}

	public void setSuspectId(Integer suspectId) {
		this.suspectId = suspectId;
	}

	public String getSuspectName() {
		return suspectName;
	}

	public void setSuspectName(String suspectName) {
		this.suspectName = suspectName;
	}

	public Incident getIncident() {
		return incident;
	}

	public void setIncident(Incident incident) {
		this.incident = incident;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Suspect [suspectid=" + suspectId + ", suspectname="
				+ suspectName + ", incident=" + incident.toString() + "]";
	}

}
