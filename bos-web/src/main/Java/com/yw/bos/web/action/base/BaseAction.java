package com.yw.bos.web.action.base;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.lang.reflect.ParameterizedType;

/**
 * 通用实现
 * @param <T>
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
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
        try {
            model = entityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
