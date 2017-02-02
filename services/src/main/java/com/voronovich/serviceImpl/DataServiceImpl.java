package com.voronovich.serviceImpl;

import com.voronovich.daoImpl.DataDaoImpl;
import com.voronovich.entity.CatalogEntity;
import com.voronovich.entity.DataEntity;
import com.voronovich.exceptions.DaoException;
import com.voronovich.service.DataService;
import com.voronovich.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class DataServiceImpl implements DataService {

    private static Logger log = Logger.getLogger(RoleServiceImpl.class);
    private DataDaoImpl dao = DataDaoImpl.getInstance();
    private HibernateUtil util = HibernateUtil.getHibernateUtil();
    private Transaction tx = null;
    private static DataServiceImpl service;

    public static DataServiceImpl getInstance() {
        if (service == null) {
            service = new DataServiceImpl();
        }
        return service;
    }

    @Override
    public void saveOrUpdate(DataEntity dataEntity) {
        try {
            tx = util.getSession().beginTransaction();
            dao.saveOrUpdate(dataEntity);
            tx.commit();
        } catch (DaoException e) {
            log.error("Error data save/update in DataServiceImpl: " + e);
            tx.rollback();
        }
    }

    @Override
    public void delete(DataEntity dataEntity) {
        try {
            tx = util.getSession().beginTransaction();
            dao.delete(dataEntity);
            tx.commit();
        } catch (DaoException e) {
            log.error("Error data delete in DataServiceImpl: " + e);
            tx.rollback();
        }
    }

    @Override
    public List<DataEntity> getAllData() {
        List<DataEntity> dataEntities = null;
        try {
            tx = util.getSession().beginTransaction();
            dataEntities = dao.getAllData();
            tx.commit();
            return dataEntities;
        } catch (DaoException e) {
            log.error("Error getting data in DataServiceImpl: " + e);
            tx.rollback();
        }
        return dataEntities;
    }

    @Override
    public DataEntity get(Serializable id) {
        DataEntity dataEntity = null;
        try {
            tx = util.getSession().beginTransaction();
            dataEntity = dao.get(id);
            tx.commit();
            return dataEntity;
        } catch (DaoException e) {
            log.error("Error getting data by id in DataServiceImpl: " + e);
            tx.rollback();
        }
        return dataEntity;
    }

    @Override
    public List<DataEntity> getAllDataPerPage(int page, int recordPerPage) {
        List<DataEntity> dataEntities = null;
        try {
            tx = util.getSession().beginTransaction();
            dataEntities = dao.getAllDataPerPage(page, recordPerPage);
            tx.commit();
            return dataEntities;
        } catch (DaoException e) {
            log.error("Error getting all data" + e);
            tx.rollback();
        }
        return dataEntities;
    }

    @Override
    public List<DataEntity> getAllDataPerPage(int page, int recordPerPage,
                                              CatalogEntity catalogEntity) {
        List<DataEntity> dataEntities = null;
        try {
            tx = util.getSession().beginTransaction();
            dataEntities = dao.getAllDataPerPage(page, recordPerPage, catalogEntity);
            tx.commit();
            return dataEntities;
        } catch (DaoException e) {
            log.error("Error getting all data" + e);
            tx.rollback();
        }
        return dataEntities;
    }

    @Override
    public List<DataEntity> getAllDataPerPageAndCost(int page, int recordPerPage,
                                                     double minCost, double maxCost,
                                                     CatalogEntity catalogEntity) {
        List<DataEntity> dataEntities = null;
        try {
            tx = util.getSession().beginTransaction();
            dataEntities = dao.getAllDataPerPageAndCost(page, recordPerPage, minCost,
                    maxCost, catalogEntity);
            tx.commit();
            return dataEntities;
        } catch (DaoException e) {
            log.error("Error getting all data" + e);
            tx.rollback();
        }
        return dataEntities;
    }
}