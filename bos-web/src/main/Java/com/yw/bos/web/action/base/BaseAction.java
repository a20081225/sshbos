package com.yw.bos.web.action.base;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yw.bos.utils.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 通用实现
 * @param <T>
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
    //分页属性
    protected PageBean pageBean = new PageBean();
    DetachedCriteria detachedCriteria = null;

    public void setPage(int page) {
        pageBean.setCurrentPage(page);

    }
    public void setRows(int rows) {
        pageBean.setPageSize(rows);
    }

    protected static final String HOME = "home";
    protected static final String LIST = "list";

    protected T model;

    public T getModel() {
        return model;
    }
    //获取实体
    public BaseAction(){
        ParameterizedType ptClass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class<T> entityClass = (Class<T>) ptClass.getActualTypeArguments()[0];
        //分页
        detachedCriteria = detachedCriteria.forClass(entityClass);
        pageBean.setDetachedCriteria(detachedCriteria);
        try {
            model = entityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 转换成Json
     * @param object
     * @param exclude
     */
    public void makeJson(Object object,String[] exclude){
        JsonConfig jsonConfig = new JsonConfig();

        jsonConfig.setExcludes(exclude);
        String json = JSONObject.fromObject(object,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
           try {
               ServletActionContext.getResponse().getWriter().print(json);
           } catch (IOException e) {
               e.printStackTrace();
           }
    }

    public void makeJson(List list, String[] exclude){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(exclude);
        String json = JSONArray.fromObject(list,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
