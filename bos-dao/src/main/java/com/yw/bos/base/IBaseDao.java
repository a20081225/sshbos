package com.yw.bos.base;

import com.yw.bos.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

/**
 * 通用接口
 * @param <T>
 */
public interface IBaseDao<T> {

    public void save(T entity);

    public void update(T entity);

    public void delete(T entity);

    public T findById(Serializable id);

    public List<T> findAll();

    public List<T> findByCriteria(DetachedCriteria detachedCriteria);

    public void excuteUpdate(String queryName,Object...objects);

    public void pageQuery(PageBean pageBean);

    public void saveOrUpdate(T entity);

}
