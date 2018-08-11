package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class MyListoner implements ServletRequestListener {

	public void requestDestroyed(ServletRequestEvent arg0) {
	}

	public void requestInitialized(ServletRequestEvent arg0) {

		DataSource dataSource = null;
		String JNDI_LOOKUP_SERVICE = "cf/myds";
		try {
			Context context = new InitialContext();
			Object lookup = context.lookup(JNDI_LOOKUP_SERVICE);
			if (lookup != null) {
				dataSource = (DataSource) lookup;
			} else {
				new RuntimeException("DataSource not found!!!");
			}
			Connection con = dataSource.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from book_table");
			while (rs.next()) {
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
				System.out.println(rs.getString(4));
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
