package com.yw.bos.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.yw.bos.domain.User;
import com.yw.bos.utils.BOSUtils;
import org.apache.struts2.ServletActionContext;

/**
 * 用户未登录拦截
 */
public class BOSLoginIntercepter extends MethodFilterInterceptor {

    protected String doIntercept(ActionInvocation invocation) throws Exception {
        //获取action名
//        ActionProxy proxy = invocation.getProxy();
//        String url = proxy.getNamespace() + proxy.getActionName();
//        System.out.println(url);

        //从session获取用户信息
        User user = BOSUtils.getLoginUser();
        if (user == null){
            return "login";
        }
        //放行
        return invocation.invoke();
    }
}
