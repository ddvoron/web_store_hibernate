package com.voronovich.daoImpl;

import com.voronovich.dao.Dao;
import com.voronovich.exceptions.DaoException;
import com.voronovich.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.lang.reflect.ParameterizedType;

import java.io.Serializable;

public class DaoImpl<T> implements Dao<T> {

    private static Logger log = Logger.getLogger(DaoImpl.class);
    private HibernateUtil util = HibernateUtil.getHibernateUtil();
    private final Class<T> persistentClass;

    @Override
    public void saveOrUpdate(T t) throws DaoException {
        try {
            Session session = util.getSession();
            session.saveOrUpdate(t);
            log.info("Save or update (commit):" + t);
        } catch (HibernateException e) {
            log.error("Error save or update Object in Dao" + e);
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(Serializable id) throws DaoException {
        T t = null;
        try {
            Session session = util.getSession();
            t = (T) session.get(persistentClass, id);
        } catch (HibernateException e) {
            log.error("Error get " + persistentClass + " in Dao" + e);
            throw new DaoException(e);
        }
        return t;
    }

    @Override
    public void delete(T t) throws DaoException {
        try {
            Session session = util.getSession();
            session.delete(t);
            log.info("Delete:" + t);
        } catch (HibernateException e) {
            log.error("Error save or update PERSON in Dao" + e);
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public DaoImpl() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
}