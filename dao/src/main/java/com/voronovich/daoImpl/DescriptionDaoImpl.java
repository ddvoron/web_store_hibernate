package com.voronovich.daoImpl;

import com.voronovich.dao.DescriptionDao;
import com.voronovich.entity.DataEntity;
import com.voronovich.entity.DescriptionEntity;
import com.voronovich.exceptions.DaoException;
import com.voronovich.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class DescriptionDaoImpl extends DaoImpl<DescriptionEntity> implements DescriptionDao {

    private static Logger log = Logger.getLogger(DaoImpl.class);
    private HibernateUtil util = HibernateUtil.getHibernateUtil();
    private static DescriptionDaoImpl dao;

    public static DescriptionDaoImpl getInstance() {
        if (dao == null) {
            dao = new DescriptionDaoImpl();
        }
        return dao;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DescriptionEntity> getAllDescription() throws DaoException {
        List<DescriptionEntity> descriptionEntities;
        try {
            Session session =  util.getSession();
            Query query = session.getNamedQuery("getAllDescriptions");
            descriptionEntities = query.list();
            log.info("Got all descriptions:" + descriptionEntities);
            return descriptionEntities;
        } catch (HibernateException e) {
            log.error("Error getting all descriptions" + e);
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DescriptionEntity> getAllDescriptionByData(DataEntity dataEntity) throws DaoException {
        List<DescriptionEntity> descriptionEntities;
        try {
            Session session = util.getSession();
            Query query = session.getNamedQuery("getAllDescriptionsByData")
                    .setParameter("dataEntity", dataEntity);
            descriptionEntities = query.list();
            log.info("Got all descriptions by data:" + descriptionEntities);
            return descriptionEntities;
        } catch (HibernateException e) {
            log.error("Error getting all descriptions by data" + e);
            throw new DaoException(e);
        }
    }
}
