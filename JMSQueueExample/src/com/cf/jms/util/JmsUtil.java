package com.cf.jms.util;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JmsUtil {
	public final static String QUEUE_JNDI_NAME = "com/info/InfoDistributedQueue";
	private static final String WEBLOGIC_JMS_URL = "t3://localhost:7001"; 
	private final static String WEBLOGIC_JNDI_FACTORY_NAME = "weblogic.jndi.WLInitialContextFactory";
	private final static String CONNECTION_FACTORY_JNDI_NAME = "com/info/InfoConnectionFactory";
																								
																								
	private static QueueConnection queueConnection = null;
	private static QueueSession queueSession = null;

	public static InitialContext getInitialContext() throws NamingException {
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, WEBLOGIC_JNDI_FACTORY_NAME);
		env.put(Context.PROVIDER_URL, WEBLOGIC_JMS_URL);
		env.put(Context.SECURITY_PRINCIPAL, "weblogic"); 
		env.put(Context.SECURITY_CREDENTIALS, "weblogic12"); 
		InitialContext initialContext = new InitialContext(env);
		return initialContext;
	}

	public static QueueSession getQueueSession(Context ctx) throws NamingException, JMSException {
		QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) ctx
				.lookup(CONNECTION_FACTORY_JNDI_NAME);
		queueConnection = queueConnectionFactory.createQueueConnection();
		queueConnection.start();
		queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		return queueSession;
	}

	public static void cleanUp() throws JMSException {
		if (queueSession != null)
			queueSession.close();
		if (queueConnection != null)
			queueConnection.close();
	}

}
