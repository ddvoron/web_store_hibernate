package com.voronovich.daoImpl;

import com.voronovich.dao.UserDao;
import com.voronovich.entity.UserEntity;
import com.voronovich.exceptions.DaoException;
import com.voronovich.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoImpl extends DaoImpl<UserEntity> implements UserDao {

    private static Logger log = Logger.getLogger(DaoImpl.class);
    private HibernateUtil util = HibernateUtil.getHibernateUtil();
    private static UserDaoImpl dao;

    public static UserDaoImpl getInstance() {
        if (dao == null) {
            dao = new UserDaoImpl();
        }
        return dao;
    }

    @Override
    public UserEntity getByLogin(String login) throws DaoException {
        UserEntity userEntity;
        try {
            Session session = util.getSession();
            Query query = session.getNamedQuery("getUsersByLogin")
                    .setParameter("login", login);
            userEntity = (UserEntity) query.uniqueResult();
            log.info("Got by login:" + userEntity);
            return userEntity;
        } catch (HibernateException e) {
            log.error("Error getting user by login" + e);
            throw new DaoException(e);
        }
    }

    @Override
    public UserEntity getByEmail(String email) throws DaoException {
        UserEntity userEntity;
        try {
            Session session = util.getSession();
            Query query = session.getNamedQuery("getUsersByEmail")
                    .setParameter("email", email);
            userEntity = (UserEntity) query.uniqueResult();
            log.info("Got by email:" + userEntity);
            return userEntity;
        } catch (HibernateException e) {
            log.error("Error getting user by email" + e);
            throw new DaoException(e);
        }
    }

    @Override
    public UserEntity get(String login, String password) throws DaoException {
        UserEntity userEntity;
        try {
            Session session = util.getSession();
            Query query = session.getNamedQuery("getUsersByLoginAndPassword")
                    .setParameter("login", login).setParameter("password", password);
            userEntity = (UserEntity) query.uniqueResult();
            log.info("Got by login and password:" + userEntity);
            return userEntity;
        } catch (HibernateException e) {
            log.error("Error getting user by login and password" + e);
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserEntity> getAllUsers() throws DaoException {
        List<UserEntity> userEntities;
        try {
            Session session = util.getSession();
            Query query = session.getNamedQuery("getAllUsers");
            userEntities = query.list();
            log.info("Got all users:" + userEntities);
            return userEntities;
        } catch (HibernateException e) {
            log.error("Error getting all users" + e);
            throw new DaoException(e);
        }
    }
}
