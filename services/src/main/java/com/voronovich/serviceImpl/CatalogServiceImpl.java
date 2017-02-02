package com.voronovich.serviceImpl;

import com.voronovich.daoImpl.CatalogDaoImpl;
import com.voronovich.entity.CatalogEntity;
import com.voronovich.exceptions.DaoException;
import com.voronovich.service.CatalogService;
import com.voronovich.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class CatalogServiceImpl implements CatalogService{

    private static Logger log = Logger.getLogger(RoleServiceImpl.class);
    private CatalogDaoImpl dao = CatalogDaoImpl.getInstance();
    private HibernateUtil util = HibernateUtil.getHibernateUtil();
    private Transaction tx = null;
    private static CatalogServiceImpl service;

    public static CatalogServiceImpl getInstance() {
        if (service == null) {
            service = new CatalogServiceImpl();
        }
        return service;
    }

    @Override
    public void saveOrUpdate(CatalogEntity catalogEntity) {
        try {
            tx = util.getSession().beginTransaction();
            dao.saveOrUpdate(catalogEntity);
            tx.commit();
        } catch (DaoException e) {
            log.error("Error department save/update in CatalogServiceImpl: " + e);
            tx.rollback();
        }
    }

    @Override
    public void delete(CatalogEntity catalogEntity) {
        try {
            tx = util.getSession().beginTransaction();
            dao.delete(catalogEntity);
            tx.commit();
        } catch (DaoException e) {
            log.error("Error department delete in CatalogServiceImpl: " + e);
            tx.rollback();
        }
    }

    @Override
    public List<CatalogEntity> getAllDepartments() {
        List<CatalogEntity> catalogEntities = null;
        try {
            tx = util.getSession().beginTransaction();
            catalogEntities = dao.getAllDepartments();
            tx.commit();
            return catalogEntities;
        } catch (DaoException e) {
            log.error("Error getting departments in CatalogServiceImpl: " + e);
            tx.rollback();
        }
        return catalogEntities;
    }

    @Override
    public CatalogEntity get(Serializable id) {
        CatalogEntity catalogEntity = null;
        try {
            tx = util.getSession().beginTransaction();
            catalogEntity = dao.get(id);
            tx.commit();
            return catalogEntity;
        } catch (DaoException e) {
            log.error("Error getting department by id in CatalogServiceImpl: " + e);
            tx.rollback();
        }
        return catalogEntity;
    }
}
