package com.yw.bos.web.realm;

import com.yw.bos.domain.Function;
import com.yw.bos.domain.User;
import com.yw.bos.service.IFunctionService;
import com.yw.bos.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

public class BOSRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    @Autowired
    private IFunctionService functionService;

    //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addStringPermission("staff-list");
        //获取当前用户
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        //User user2 = (User) principals.getPrimaryPrincipal();
        List<Function> list;
        if (user.getUsername().equals("admin")){
            list = functionService.findAll();
        }else {
            list = functionService.findFunctionByUserId(user.getId());
        }
        for (Function function : list) {
            info.addStringPermission(function.getCode());
        }
        return info;
    }

    //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //获得页面输入的用户名
        String username = token.getUsername();
        //根据用户名查询数据库中的用户
        User user = userService.findUserByUsername(username);
        if (user == null){
            //输入的用户名不存在
            return null;
        }
        //简单认证信息对象
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
        return info;
    }
}
