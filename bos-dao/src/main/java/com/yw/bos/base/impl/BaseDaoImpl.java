package com.yw.bos.base.impl;

import com.yw.bos.base.IBaseDao;
import com.yw.bos.utils.PageBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {
    //实体类型
    private Class<T> entityClass;

    @Resource
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
    //动态获取entityClass
    public BaseDaoImpl() {
        		//获得当前类型的带有泛型类型的父类
         		ParameterizedType ptClass = (ParameterizedType) this.getClass().getGenericSuperclass();
         		//获得运行期的泛型类型
                entityClass = (Class<T>) ptClass.getActualTypeArguments()[0];
    }

    public void save(T entity) {
        this.getHibernateTemplate().save(entity);
    }

    public void update(T entity) {
        this.getHibernateTemplate().update(entity);
    }

    public void delete(T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    public T findById(Serializable id) {
        return this.getHibernateTemplate().get(entityClass,id);
    }

    public List<T> findAll() {
        String hql = "FROM " + entityClass.getName();
        return (List<T>) this.getHibernateTemplate().find(hql);
    }

    public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
        return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }

    public void excuteUpdate(String queryName,Object...objects) {
        Session session = this.getSessionFactory().getCurrentSession();
        Query query = session.getNamedQuery(queryName);
        int i = 0;
        for (Object object : objects) {
           query.setParameter(i++,object);
        }
        query.executeUpdate();
    }

    //分页
    public void pageQuery(PageBean pageBean) {
        Integer currentPage = pageBean.getCurrentPage();
        Integer pageSize = pageBean.getPageSize();
        DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();

        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> countList = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        Long count = countList.get(0);
        pageBean.setTotal(count.intValue());

        detachedCriteria.setProjection(null);
        detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
        int firsResult = (currentPage - 1) * pageSize;
        int maxResult = pageSize;
        List rows = this.getHibernateTemplate().findByCriteria(detachedCriteria, firsResult, maxResult);
        pageBean.setRows(rows);
    }

    public void saveOrUpdate(T entity) {
        this.getHibernateTemplate().saveOrUpdate(entity);
    }
}
