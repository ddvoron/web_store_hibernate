package com.voronovich.daoImpl;

import com.voronovich.dao.DataDao;
import com.voronovich.entity.CatalogEntity;
import com.voronovich.entity.DataEntity;
import com.voronovich.exceptions.DaoException;
import com.voronovich.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.List;

public class DataDaoImpl extends DaoImpl<DataEntity> implements DataDao {

    private static Logger log = Logger.getLogger(DaoImpl.class);
    private HibernateUtil util = HibernateUtil.getHibernateUtil();
    private static DataDaoImpl dao;

    public static DataDaoImpl getInstance() {
        if (dao == null) {
            dao = new DataDaoImpl();
        }
        return dao;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DataEntity> getAllData() throws DaoException {
        List<DataEntity> dataEntities;
        try {
            Session session = util.getSession();
            Query query = session.getNamedQuery("getAllData");
            dataEntities = query.list();
            log.info("Got all data:" + dataEntities);
            return dataEntities;
        } catch (HibernateException e) {
            log.error("Error getting all data" + e);
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DataEntity> getAllDataPerPage(int page,
                                              int recordPerPage) throws DaoException {
        List<DataEntity> dataEntities;
        try {
            Session session = util.getSession();
            Criteria cr = session.createCriteria(DataEntity.class);
            cr.setFirstResult((page - 1) * recordPerPage);
            cr.setMaxResults(recordPerPage);
            dataEntities = cr.list();
            return dataEntities;
        } catch (HibernateException e) {
            log.error("Error getting all data" + e);
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DataEntity> getAllDataPerPage(int page, int recordPerPage,
                                              CatalogEntity catalogEntity) throws DaoException {
        List<DataEntity> dataEntities;
        try {
            Session session = util.getSession();
            Criteria cr = session.createCriteria(DataEntity.class);
            cr.add(Restrictions.eq("catalogEntity", catalogEntity));
            cr.setFirstResult((page - 1) * recordPerPage);
            cr.setMaxResults(recordPerPage);
            dataEntities = cr.list();
            return dataEntities;
        } catch (HibernateException e) {
            log.error("Error getting all data" + e);
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DataEntity> getAllDataPerPageAndCost(int page, int recordPerPage,
                                                     double minCost, double maxCost,
                                                     CatalogEntity catalogEntity) throws DaoException {
        List<DataEntity> dataEntities;
        try {
            Session session = util.getSession();
            Criteria cr = session.createCriteria(DataEntity.class);
            cr.add(Restrictions.eq("catalogEntity", catalogEntity));
            cr.add(Restrictions.gt("price", minCost));
            cr.add(Restrictions.lt("price", maxCost));
            cr.setFirstResult((page - 1) * recordPerPage);
            cr.setMaxResults(recordPerPage);
            dataEntities = cr.list();
            return dataEntities;
        } catch (HibernateException e) {
            log.error("Error getting all data" + e);
            throw new DaoException(e);
        }
    }
}
