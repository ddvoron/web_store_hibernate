package com.voronovich.daoImpl;

import com.voronovich.dao.RoleDao;
import com.voronovich.entity.RoleEntity;
import com.voronovich.exceptions.DaoException;
import com.voronovich.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class RoleDaoImpl extends DaoImpl<RoleEntity> implements RoleDao {

    private static Logger log = Logger.getLogger(DaoImpl.class);
    private HibernateUtil util = HibernateUtil.getHibernateUtil();
    private static RoleDaoImpl dao;

    public static RoleDaoImpl getInstance() {
        if(dao == null) {
            dao = new RoleDaoImpl();
        }
        return dao;
    }

    @Override
    public List<RoleEntity> getAllRoles() throws DaoException {
        List<RoleEntity> roleEntities;
        try {
            Session session = util.getSession();
            Query query = session.getNamedQuery("getAllRoles");
            roleEntities =  query.list();
            log.info("Got all roles:" + roleEntities);
            return roleEntities;
        } catch (HibernateException e) {
            log.error("Error getting all roles" + e);
            throw new DaoException(e);
        }
    }
}

