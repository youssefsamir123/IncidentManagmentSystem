package com.incident.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.incident.configuration.AppConfig;
import com.incident.entity.User;
import com.incident.service.IncidentService;
import com.incident.service.UserService;
import com.incident.util.Util;

@ManagedBean
@SessionScoped
public class Login implements Serializable {
	private String userMail;
	private String password;
	private String userType;
	private UserService userService;
	private boolean loggedIn;
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession currentSession = (HttpSession) fc.getExternalContext()
				.getSession(true);
		currentSession.setAttribute("currentPage","home.xhtml");
	}

	public String login() {
		String navigation = "";
		User user = null;
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession currentSession = (HttpSession) fc.getExternalContext()
					.getSession(true);
			ApplicationContext applicationContext = Util
					.getApplicationContext();
			userService = (UserService) applicationContext
					.getBean(UserService.class);
			if (userMail.contains("@")
					&& userMail.split("@")[1].toLowerCase().equals("mum.edu")) {
				user = userService.getUserByUserMail(userMail);
				userService = null;
				if (user != null) {
					currentSession.setAttribute("userType", user.getType());
				} else {
					currentSession.setAttribute("userType", "reporter");
				}
				loggedIn = true;
				navigation = "home.xhtml";
			} else {
				String strMsg = "Please be Sure That You Enter Correct User Name and Password.";
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, strMsg,
								" "));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return navigation;
	}

	public String logOut() {
		return "login.xhtml";
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
}
