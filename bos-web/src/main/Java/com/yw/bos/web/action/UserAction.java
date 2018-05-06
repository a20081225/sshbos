package com.yw.bos.web.action;

import com.yw.bos.domain.User;
import com.yw.bos.service.IUserService;
import com.yw.bos.utils.BOSUtils;
import com.yw.bos.utils.MD5Utils;
import com.yw.bos.web.action.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.aop.UserAnnotationMethodInterceptor;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{

    private String checkcode;
    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    @Autowired
    private IUserService userService;

    //登录
    public String login(){
        //获取正确验证码
        String validatecode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        //校验是否正确
        if (StringUtils.isNotBlank(checkcode) && checkcode.equals(validatecode)){
            //shiro认证
            //获得当前用户对象,状态为“未认证”
            Subject subject = SecurityUtils.getSubject();
            //创建用户名密码令牌对象
            UsernamePasswordToken token = new UsernamePasswordToken(model.getUsername(), MD5Utils.md5(model.getPassword()));
            try {
                subject.login(token);
            }catch (UnknownAccountException e) {
                e.printStackTrace();
                this.addActionError("输入的用户名错误！");
                return LOGIN;
            } catch (IncorrectCredentialsException e) {
                e.printStackTrace();
                this.addActionError("输入的密码错误");
                return LOGIN;
            }
            User user = (User) subject.getPrincipal();
            ServletActionContext.getRequest().getSession().setAttribute("loginUser",user);
            return HOME;
        }else {
            this.addActionError("输入的验证码错误");
            return LOGIN;
        }
    }

    public String login_bak(){
        //获取正确验证码
        String validatecode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        //校验是否正确
        if (StringUtils.isNotBlank(checkcode) && checkcode.equals(validatecode)){
            User user = userService.login(model);
            if (user!=null){
                ServletActionContext.getRequest().getSession().setAttribute("loginUser",user);
                return HOME;
            }else {
                this.addActionError("用户名或者密码错误");
                return LOGIN;
            }
        }else {
            this.addActionError("输入的验证码错误");
            return LOGIN;
        }
    }

    //注销
    public String logout() {
        ServletActionContext.getRequest().getSession().invalidate();
        return LOGIN;
    }

    //修改密码

    public String editPassword() throws IOException {
        String flag = "1";
        User user = BOSUtils.getLoginUser();
        try {
            userService.editPassword(user.getId(),model.getPassword());
        }catch (Exception e){
            flag = "0";
            e.printStackTrace();
        }
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(flag);
        return NONE;
    }

    private String[] roleIds;

    public String[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
    }

    public String add(){
        userService.save(model,roleIds);
        return LIST;
    }

    public String pageQuery(){
        userService.pageQuery(pageBean);
        this.makeJson(pageBean,new String[]{"noticebills","roles","birthday"});
        return NONE;
    }
}
