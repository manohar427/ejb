package com.cf.jms.client;

import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.cf.jms.util.DsUtil;

public class MyDataSourceClient {

	public static void main(String[] args) throws NamingException, JMSException {
		DataSource ds = DsUtil.getDataSource(DsUtil.getInitialContext());
		
		System.out.println(ds);
	}

}
