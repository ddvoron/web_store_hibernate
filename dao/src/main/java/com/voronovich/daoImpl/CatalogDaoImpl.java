package com.voronovich.daoImpl;

import com.voronovich.dao.CatalogDao;
import com.voronovich.entity.CatalogEntity;
import com.voronovich.exceptions.DaoException;
import com.voronovich.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CatalogDaoImpl extends DaoImpl<CatalogEntity> implements CatalogDao{

    private static Logger log = Logger.getLogger(DaoImpl.class);
    private HibernateUtil util = HibernateUtil.getHibernateUtil();
    private static CatalogDaoImpl dao;

    public static CatalogDaoImpl getInstance() {
        if(dao == null) {
            dao = new CatalogDaoImpl();
        }
        return dao;
    }

    @Override
    public List<CatalogEntity> getAllDepartments() throws DaoException {
        List<CatalogEntity> catalogEntities;
        try {
            Session session = util.getSession();
            Query query = session.getNamedQuery("getAllDepartments");
            catalogEntities =  query.list();
            log.info("Got all departments:" + catalogEntities);
            return catalogEntities;
        } catch (HibernateException e) {
            log.error("Error getting all departments" + e);
            throw new DaoException(e);
        }
    }
}
