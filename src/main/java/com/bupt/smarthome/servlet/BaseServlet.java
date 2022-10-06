package com.bupt.smarthome.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) {
		String uri = req.getRequestURI();



		try { // this refers to class invoked by AJAX; for /servlet/user/login, this refers to UserServlet
			Method method = this.getClass().getMethod(uri.substring(uri.lastIndexOf('/') + 1), HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, req, res);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
