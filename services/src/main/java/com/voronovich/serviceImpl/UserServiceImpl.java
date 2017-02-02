package com.voronovich.serviceImpl;

import com.voronovich.daoImpl.UserDaoImpl;
import com.voronovich.entity.UserEntity;
import com.voronovich.exceptions.DaoException;
import com.voronovich.service.UserService;
import com.voronovich.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static Logger log = Logger.getLogger(RoleServiceImpl.class);
    private UserDaoImpl dao = UserDaoImpl.getInstance();
    private HibernateUtil util = HibernateUtil.getHibernateUtil();
    private Transaction tx = null;
    private static UserServiceImpl service;

    public static UserServiceImpl getInstance() {
        if (service == null) {
            service = new UserServiceImpl();
        }
        return service;
    }

    @Override
    public UserEntity get(String login, String password) {
        UserEntity userEntity = null;
        try {
            tx = util.getSession().beginTransaction();
            userEntity = dao.get(login, password);
            tx.commit();
            return userEntity;
        } catch (DaoException e) {
            log.error("Error getting user by login and password in UserServiceImpl: " + e);
            tx.rollback();
        }
        return userEntity;
    }

    @Override
    public UserEntity getByLogin(String login) {
        UserEntity userEntity = null;
        try {
            tx = util.getSession().beginTransaction();
            userEntity = dao.getByLogin(login);
            tx.commit();
            return userEntity;
        } catch (DaoException e) {
            log.error("Error getting user by login in UserServiceImpl: " + e);
            tx.rollback();
        }
        return userEntity;
    }

    @Override
    public UserEntity getByEmail(String email) {
        UserEntity userEntity = null;
        try {
            tx = util.getSession().beginTransaction();
            userEntity = dao.getByEmail(email);
            tx.commit();
            return userEntity;
        } catch (DaoException e) {
            log.error("Error getting user by email in UserServiceImpl: " + e);
            tx.rollback();
        }
        return userEntity;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        List<UserEntity> userEntities = null;
        try {
            tx = util.getSession().beginTransaction();
            userEntities = dao.getAllUsers();
            tx.commit();
            return userEntities;
        } catch (DaoException e) {
            log.error("Error getting users in UserServiceImpl: " + e);
            tx.rollback();
        }
        return userEntities;
    }

    @Override
    public void saveOrUpdate(UserEntity userEntity) {
        try {
            tx = util.getSession().beginTransaction();
            dao.saveOrUpdate(userEntity);
            tx.commit();
        } catch (DaoException e) {
            log.error("Error role save/update in UserServiceImpl: " + e);
            tx.rollback();
        }
    }

    @Override
    public void delete(UserEntity userEntity) {
        try {
            tx = util.getSession().beginTransaction();
            dao.delete(userEntity);
            tx.commit();
        } catch (DaoException e) {
            log.error("Error role delete in UserServiceImpl: " + e);
            tx.rollback();
        }
    }

    @Override
    public UserEntity get(Serializable id) {
        UserEntity userEntity = null;
        try {
            tx = util.getSession().beginTransaction();
            userEntity = dao.get(id);
            tx.commit();
            return userEntity;
        } catch (DaoException e) {
            log.error("Error getting user by id in UserServiceImpl: " + e);
            tx.rollback();
        }
        return userEntity;
    }
}
