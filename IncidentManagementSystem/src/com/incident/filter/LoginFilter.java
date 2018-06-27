package com.incident.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.incident.controller.Login;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//		String requestURL = httpServletRequest.getRequestURI();
		Login login = (Login) httpServletRequest.getSession(true).getAttribute(
				"login");
		String contextPath = httpServletRequest.getContextPath();
		// String pageName = (String) httpServletRequest.getSession()
		// .getAttribute("currentPage");

		if (login == null || !login.isLoggedIn()) {
			System.out.println("User Not Logged In");
			httpServletResponse.sendRedirect(contextPath + "/login.xhtml");
		} else {
			chain.doFilter(httpServletRequest, httpServletResponse);
		}

		// else if (!pageName.equals("") && pageName != null) {
		// System.out.println("User Looooooooogged In");
		// httpServletResponse.sendRedirect(contextPath + "/" + pageName);
		// }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
