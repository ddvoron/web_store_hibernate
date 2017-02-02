package com.voronovich.serviceImpl;

import com.voronovich.daoImpl.BasketDaoImpl;
import com.voronovich.entity.BasketEntity;
import com.voronovich.entity.DataEntity;
import com.voronovich.entity.UserEntity;
import com.voronovich.exceptions.DaoException;
import com.voronovich.service.BasketService;
import com.voronovich.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class BasketServiceImpl implements BasketService{

    private static Logger log = Logger.getLogger(RoleServiceImpl.class);
    private BasketDaoImpl dao = BasketDaoImpl.getInstance();
    private HibernateUtil util = HibernateUtil.getHibernateUtil();
    private Transaction tx = null;
    private static BasketServiceImpl service;

    public static BasketServiceImpl getInstance() {
        if (service == null) {
            service = new BasketServiceImpl();
        }
        return service;
    }

    @Override
    public List<BasketEntity> getByUser(UserEntity userEntity) {
        List<BasketEntity> basketEntities = null;
        try {
            tx = util.getSession().beginTransaction();
            basketEntities = dao.getByUser(userEntity);
            tx.commit();
            return basketEntities;
        } catch (DaoException e) {
            log.error("Error getting basket in BasketServiceImpl: " + e);
            tx.rollback();
        }
        return basketEntities;
    }

    @Override
    public BasketEntity getByData(DataEntity dataEntity) {
        BasketEntity basketEntity = null;
        try {
            tx = util.getSession().beginTransaction();
            basketEntity = dao.getByData(dataEntity);
            tx.commit();
            return basketEntity;
        } catch (DaoException e) {
            log.error("Error getting basket by email in BasketServiceImpl: " + e);
            tx.rollback();
        }
        return basketEntity;
    }

    @Override
    public void saveOrUpdate(BasketEntity basketEntity) {
        try {
            tx = util.getSession().beginTransaction();
            dao.saveOrUpdate(basketEntity);
            tx.commit();
        } catch (DaoException e) {
            log.error("Error basket save/update in BasketServiceImpl: " + e);
            tx.rollback();
        }
    }

    @Override
    public void delete(BasketEntity basketEntity) {
        try {
            tx = util.getSession().beginTransaction();
            dao.delete(basketEntity);
            tx.commit();
        } catch (DaoException e) {
            log.error("Error basket delete in BasketServiceImpl: " + e);
            tx.rollback();
        }
    }

    @Override
    public BasketEntity get(Serializable id) {
        BasketEntity basketEntity = null;
        try {
            tx = util.getSession().beginTransaction();
            basketEntity = dao.get(id);
            tx.commit();
            return basketEntity;
        } catch (DaoException e) {
            log.error("Error getting basket by id in BasketServiceImpl: " + e);
            tx.rollback();
        }
        return basketEntity;
    }

    @Override
    public List<BasketEntity> getAllBasket() {
        List<BasketEntity> basketEntities = null;
        try {
            tx = util.getSession().beginTransaction();
            basketEntities = dao.getAllBasket();
            tx.commit();
            return basketEntities;
        } catch (DaoException e) {
            log.error("Error getting basket in BasketServiceImpl: " + e);
            tx.rollback();
        }
        return basketEntities;
    }
}
