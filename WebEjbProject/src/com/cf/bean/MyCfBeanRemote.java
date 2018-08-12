package com.cf.bean;

import javax.ejb.Remote;

@Remote
public interface MyCfBeanRemote {
    String getFullName(String name);
}