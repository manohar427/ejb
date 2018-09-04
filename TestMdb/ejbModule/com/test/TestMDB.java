package com.test;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

@MessageDriven(name = "TestMDB", mappedName = "jms/WLReceiverQueue", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class TestMDB implements MessageListener {

	public void onMessage(Message msg) {
		System.out.println("\n=========================================");
		System.out.println("=> Message received!");

		if (msg instanceof TextMessage) {

			try {
				System.out.println("=> Message: " + ((TextMessage) msg).getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}

		putMessage(msg);
		System.out.println("=========================================");
	}

	@Resource(name = "WLReplyQueue", mappedName = "jms/WLReplyQueue")
	private javax.jms.Queue queue;
	@Resource(name = "WLReplyQCF", mappedName = "jms/WLReplyQCF")
	private javax.jms.QueueConnectionFactory tcf;

	public void putMessage(javax.jms.Message msg) {
		System.out.println("=> Placing message on WLReplyQueue...");
		QueueConnection queueConnection = null;
		QueueSession queueSession = null;
		QueueSender queueSender = null;

		try {
			queueConnection = tcf.createQueueConnection();
			queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			queueSender = queueSession.createSender(queue);
			queueSender.send(msg);
			System.out.println("=> Message sent!");

		} catch (Exception e) {
			e.printStackTrace();

			if (e instanceof JMSException) {
				((JMSException) e).getLinkedException().printStackTrace();
			}

		} finally {

			try {
				queueSession.close();
				queueConnection.close();
			} catch (Exception e) {
				e.printStackTrace();

				if (e instanceof JMSException) {
					((JMSException) e).getLinkedException().printStackTrace();
				}
			}
		}
	}

}