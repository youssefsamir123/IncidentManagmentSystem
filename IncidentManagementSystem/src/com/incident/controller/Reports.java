package com.incident.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.springframework.context.ApplicationContext;
import com.incident.entity.Incident;
import com.incident.entity.IncidentType;
import com.incident.service.IncidentService;
import com.incident.service.IncidentTypeService;
import com.incident.util.Util;

@ManagedBean
@ViewScoped
public class Reports implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<IncidentType> incidentTypes;
	private IncidentTypeService incidentTypeService;
	private IncidentService incidentService;
	private BarChartModel barModel;

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
			incidentTypes = incidentTypeService.findAll();
			incidentTypeService = null;
			createBarModel();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	private void createBarModel() {
		int maxNumber = 0;
		try {
			barModel = new BarChartModel();
			barModel.setTitle("Number of Incidents Per Incident Type");
			barModel.setLegendPosition("ne");
			ChartSeries typesSeries = new ChartSeries();
			typesSeries.setLabel("Incident Types");
			for (IncidentType type : incidentTypes) {
				int incidentNumber = getNumberOfIncidentsPerType(type.getTypeId());
				maxNumber += incidentNumber; //count
				typesSeries.set(type.getTypeName(),incidentNumber);
			}
			barModel.addSeries(typesSeries);
			Axis xAxis = barModel.getAxis(AxisType.X);
			xAxis.setLabel("Incident Types");

			Axis yAxis = barModel.getAxis(AxisType.Y);
			yAxis.setLabel("Number of Incidnet");
			yAxis.setMin(0);
			yAxis.setMax(maxNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getNumberOfIncidentsPerType(int typeId) {
		try {
			ApplicationContext applicationContext = Util
					.getApplicationContext();
			incidentService = (IncidentService) applicationContext
					.getBean(IncidentService.class);
			List<Incident> list = incidentService
					.findIncdentsByIncidentType(typeId);
			incidentService = null;
			return list.size();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}
}
