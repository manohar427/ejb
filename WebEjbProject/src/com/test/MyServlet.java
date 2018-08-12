package com.test;

import java.io.IOException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cf.bean.MyCfBeanRemote;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		env.put(Context.PROVIDER_URL, "t3://localhost:7001");
		env.put(Context.SECURITY_PRINCIPAL, "weblogic");
		env.put(Context.SECURITY_CREDENTIALS, "weblogic12");
		InitialContext initialContext;

		try {
			initialContext = new InitialContext(env);
			MyCfBeanRemote mybean = (MyCfBeanRemote) initialContext.lookup("ejb/myCfBean#com.cf.bean.MyCfBeanRemote");
			String name = mybean.getFullName(request.getParameter("username"));

			response.getOutputStream().println("mmmmmmmmmmmmmmmmmmmmmmmmmmmm:" + name);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
