package com.cf.jms.util;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DsUtil {
	public final static String DS_JNDI_NAME = "cf/myds";
	private static final String WEBLOGIC_JMS_URL = "t3://localhost:7001";
	private final static String WEBLOGIC_JNDI_FACTORY_NAME = "weblogic.jndi.WLInitialContextFactory";

	public static InitialContext getInitialContext() throws NamingException {
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, WEBLOGIC_JNDI_FACTORY_NAME);
		env.put(Context.PROVIDER_URL, WEBLOGIC_JMS_URL);
		env.put(Context.SECURITY_PRINCIPAL, "weblogic");
		env.put(Context.SECURITY_CREDENTIALS, "weblogic12");
		InitialContext initialContext = new InitialContext(env);
		return initialContext;
	}

	public static DataSource getDataSource(Context ctx) throws NamingException, JMSException {
		return (DataSource) ctx.lookup(DS_JNDI_NAME);
	}

}
