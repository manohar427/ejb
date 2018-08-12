package com.cf.bean;

import java.util.Date;

import javax.ejb.Stateless;

@Stateless(mappedName="ejb/myCfBean")
public class MyCfBean implements MyCfBeanRemote {
	public String getFullName(String name) {
		System.out.println(new Date()+":Received Name is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:"+name);
		return "Mr." + name;
	}
}