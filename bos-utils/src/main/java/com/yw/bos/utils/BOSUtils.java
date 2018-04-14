package com.yw.bos.utils;

import com.yw.bos.domain.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

public class BOSUtils {
    //获取session对象
    public static HttpSession getSession(){
        return ServletActionContext.getRequest().getSession();
    }

    public static User getLoginUser(){
        return (User) getSession().getAttribute("loginUser");
    }
}
