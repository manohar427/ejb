package com.cf.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.cf.bean.MyCfBeanRemote;


public class MyClient {

	public static void main(String[] args) throws NamingException {

		Properties  env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		env.put(Context.PROVIDER_URL, "t3://localhost:7001");
		env.put(Context.SECURITY_PRINCIPAL, "weblogic");
		env.put(Context.SECURITY_CREDENTIALS, "weblogic12");
		InitialContext initialContext = new InitialContext(env);
		
		MyCfBeanRemote mybean = (MyCfBeanRemote)initialContext.lookup("ejb/myCfBean#com.cf.bean.MyCfBeanRemote");
		System.out.println(mybean.getFullName("Manohar"));
		
	}
}
