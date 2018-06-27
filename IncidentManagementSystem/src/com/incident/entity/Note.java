/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.incident.entity;

import java.io.Serializable;
import javax.persistence.Basic;
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
@Table(name = "note")
public class Note implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOTEID")
    private Integer noteid;
    @Column(name = "NOTEBODY")
    private String noteBody;
    @ManyToOne
 	@JoinColumn(name="INCIDENTID",referencedColumnName="INCIDENTID")
    private Incident incident;
	@ManyToOne
	@JoinColumn(name = "USER", referencedColumnName = "USERID")
	private User user;
	

   

	public Note() {
    }

    public Note(Integer noteid) {
        this.noteid = noteid;
    }

    public Integer getNoteid() {
        return noteid;
    }

    public void setNoteid(Integer noteid) {
        this.noteid = noteid;
    }

    public String getNoteBody() {
        return noteBody;
    }

    public void setNoteBody(String notebody) {
        this.noteBody = notebody;
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
		return "Note [noteid=" + noteid + ", notebody=" + noteBody
				+ ", incident=" + incident.toString() + "]";
	}
    
}
