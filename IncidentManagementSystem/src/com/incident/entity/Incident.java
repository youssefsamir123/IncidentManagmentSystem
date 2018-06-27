package com.incident.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

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
@Table(name = "incident")
public class Incident implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "incidentId")
	private Integer incidentId;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "ITEMVALUE")
	private String itemValue;
	public Integer getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(Integer incidentId) {
		this.incidentId = incidentId;
	}

	@Column(name = "incidentDate")
	private Date incidentDate;
	@Column(name = "REMARKS")
	private String remarks;
	@Column(name = "LOCATION")
	private String location;

	@ManyToOne
	@JoinColumn(name = "TYPEID", referencedColumnName = "TYPEID")
	private IncidentType incidentType;
	@Column(name = "REPORTEDUSER")
	private String reportedUser;
	@Transient
	private String formatedDate;
	@Column(name = "RESULT")
	private String result;
	@ManyToOne
	@JoinColumn(name = "SECURITYUSER", referencedColumnName = "USERID")
	private User securityUser;

	public Incident() {
	}

	public User getSecurityUser() {
		return securityUser;
	}

	public void setSecurityUser(User securityUser) {
		this.securityUser = securityUser;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getFormatedDate() {
		String pattern = "MM-dd-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		formatedDate = simpleDateFormat.format(incidentDate);
		return formatedDate;
	}

	public void setFormatedDate(String formatedDate) {
		this.formatedDate = formatedDate;
	}

	public Incident(Integer incidentId) {
		this.incidentId = incidentId;
	}

	public Integer getincidentId() {
		return incidentId;
	}

	public void setincidentId(Integer incidentId) {
		this.incidentId = incidentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public Date getIncidentDate() {
		return incidentDate;
	}

	public void setIncidentDate(Date incidentDate) {
		this.incidentDate = incidentDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public IncidentType getIncidentType() {
		return incidentType;
	}

	public void setIncidentType(IncidentType incidentType) {
		this.incidentType = incidentType;
	}

	public String getReportedUser() {
		return reportedUser;
	}

	public void setReportedUser(String reporteduser) {
		this.reportedUser = reporteduser;
	}

	@Override
	public String toString() {
		return "Incident [incidentId=" + incidentId + ", status=" + status
				+ ", itemValue=" + itemValue + ", incidentDate=" + incidentDate
				+ ", remarks=" + remarks + ", location=" + location
				+ ", incidentType=" + incidentType + ", reportedUser="
				+ reportedUser + "]";
	}

}
