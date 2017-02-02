package com.voronovich.serviceImpl;

import com.voronovich.daoImpl.RoleDaoImpl;
import com.voronovich.entity.RoleEntity;
import com.voronovich.exceptions.DaoException;
import com.voronovich.service.RoleService;
import com.voronovich.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class RoleServiceImpl implements RoleService {

    private static Logger log = Logger.getLogger(RoleServiceImpl.class);
    private RoleDaoImpl dao = RoleDaoImpl.getInstance();
    private HibernateUtil util = HibernateUtil.getHibernateUtil();
    private static Transaction tx = null;
    private static RoleServiceImpl service;
    public static RoleServiceImpl getInstance() {
        if (service == null) {
            service = new RoleServiceImpl();
        }
        return service;
    }

    @Override
    public void saveOrUpdate(RoleEntity roleEntity) {
        try {
            tx = util.getSession().beginTransaction();
            dao.saveOrUpdate(roleEntity);
            tx.commit();
        } catch (DaoException e) {
            log.error("Error role save/update in RoleServiceImpl: " + e);
            tx.rollback();
        }
    }

    @Override
    public void delete(RoleEntity roleEntity) {
        try {
            tx = util.getSession().beginTransaction();
            dao.delete(roleEntity);
            tx.commit();
        } catch (DaoException e) {
            log.error("Error role delete in RoleServiceImpl: " + e);
            tx.rollback();
        }
    }

    @Override
    public List<RoleEntity> getAllRoles() {
        List<RoleEntity> roleEntities = null;
        try {
            tx = util.getSession().beginTransaction();
            roleEntities = dao.getAllRoles();
            tx.commit();
            return roleEntities;
        } catch (DaoException e) {
            log.error("Error getting roles in RoleServiceImpl: " + e);
            tx.rollback();
        }
        return roleEntities;
    }

    @Override
    public RoleEntity get(Serializable id) {
        RoleEntity roleEntity = null;
        try {
            tx = util.getSession().beginTransaction();
            roleEntity = dao.get(id);
            tx.commit();
            return roleEntity;
        } catch (DaoException e) {
            log.error("Error getting role by id in RoleServiceImpl: " + e);
            tx.rollback();
        }
        return roleEntity;
    }
}
