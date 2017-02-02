package com.voronovich.serviceImpl;

import com.voronovich.daoImpl.DescriptionDaoImpl;
import com.voronovich.entity.DataEntity;
import com.voronovich.entity.DescriptionEntity;
import com.voronovich.exceptions.DaoException;
import com.voronovich.service.DescriptionService;
import com.voronovich.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class DescriptionServiceImpl implements DescriptionService{

    private static Logger log = Logger.getLogger(RoleServiceImpl.class);
    private DescriptionDaoImpl dao = DescriptionDaoImpl.getInstance();
    private HibernateUtil util = HibernateUtil.getHibernateUtil();
    private static Transaction tx = null;
    private static DescriptionServiceImpl service;

    public static DescriptionServiceImpl getInstance() {
        if (service == null) {
            service = new DescriptionServiceImpl();
        }
        return service;
    }

    @Override
    public void saveOrUpdate(DescriptionEntity descriptionEntity) {
        try {
            tx = util.getSession().beginTransaction();
            dao.saveOrUpdate(descriptionEntity);
            tx.commit();
        } catch (DaoException e) {
            log.error("Error description save/update in DescriptionServiceImpl: " + e);
            tx.rollback();
        }
    }

    @Override
    public void delete(DescriptionEntity descriptionEntity) {
        try {
            tx = util.getSession().beginTransaction();
            dao.delete(descriptionEntity);
            tx.commit();
        } catch (DaoException e) {
            log.error("Error description delete in : " + e);
            tx.rollback();
        }
    }

    @Override
    public DescriptionEntity get(Serializable id) {
        DescriptionEntity descriptionEntity = null;
        try {
            tx = util.getSession().beginTransaction();
            descriptionEntity = dao.get(id);
            tx.commit();
            return descriptionEntity;
        } catch (DaoException e) {
            log.error("Error getting description by id in DescriptionServiceImpl: " + e);
            tx.rollback();
        }
        return descriptionEntity;
    }

    @Override
    public List<DescriptionEntity> getAllDescriptions() {
        List<DescriptionEntity> descriptionEntities = null;
        try {
            tx = util.getSession().beginTransaction();
            descriptionEntities = dao.getAllDescription();
            tx.commit();
            return descriptionEntities;
        } catch (DaoException e) {
            log.error("Error getting descriptions in DescriptionServiceImpl: " + e);
            tx.rollback();
        }
        return descriptionEntities;
    }

    @Override
    public List<DescriptionEntity> getAllDescriptionsByData(DataEntity dataEntity) {
        List<DescriptionEntity> descriptionEntities = null;
        try {
            tx = util.getSession().beginTransaction();
            descriptionEntities = dao.getAllDescriptionByData(dataEntity);
            tx.commit();
            return descriptionEntities;
        } catch (DaoException e) {
            log.error("Error getting descriptions by data in DescriptionServiceImpl: " + e);
            tx.rollback();
        }
        return descriptionEntities;
    }
}
