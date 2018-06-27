package com.incident.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.primefaces.push.annotation.Singleton;

/**
 * 
 * @author youssef
 */
@Entity
@Table(name = "emrgency")
public class emergency implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emergencyId")
	private Integer emergencyId;
	@Column(name = "emergencyName")
	private String emergencyName;
	@Column(name = "emergencyPhone")
	private String emergencyPhone;
	@Column(name = "emergencyLocation")
	private String emergencyLocation;

	public emergency() {
	}

	public Integer getEmergencyId() {
		return emergencyId;
	}

	public void setEmergencyId(Integer emergencyId) {
		this.emergencyId = emergencyId;
	}

	public String getEmergencyName() {
		return emergencyName;
	}

	public void setEmergencyName(String emergencyName) {
		this.emergencyName = emergencyName;
	}

	public String getEmergencyPhone() {
		return emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	public String getEmergencyLocation() {
		return emergencyLocation;
	}

	public void setEmergencyLocation(String emergencyLocation) {
		this.emergencyLocation = emergencyLocation;
	}

	@Override
	public String toString() {
		return "emergency [emergencyId=" + emergencyId + ", emergencyName="
				+ emergencyName + ", emergencyPhone=" + emergencyPhone
				+ ", emergencyLocation=" + emergencyLocation + "]";
	}

}
