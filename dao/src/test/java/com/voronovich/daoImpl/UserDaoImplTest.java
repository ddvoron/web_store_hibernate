package com.voronovich.daoImpl;

import com.voronovich.entity.RoleEntity;
import com.voronovich.entity.UserEntity;
import com.voronovich.exceptions.DaoException;
import com.voronovich.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoImplTest {

    private ResourceBundle myResources = ResourceBundle.getBundle("dataTest");

    private final int idRole = Integer.parseInt(myResources.getString("idRole"));
    private final String roleName = myResources.getString("roleName");
    private final int idUser = Integer.parseInt(myResources.getString("idUser"));
    private final String name = myResources.getString("name");
    private final String surname = myResources.getString("surname");
    private final String email = myResources.getString("email");
    private final String login = myResources.getString("login");
    private final String password = myResources.getString("password");
    private final String salt = myResources.getString("salt");
    private final String blackList = myResources.getString("blackList");

    private UserDaoImpl daoUser = UserDaoImpl.getInstance();

    private Transaction tx = null;
    private Session session = HibernateUtil.getHibernateUtil().getSession();

    @Test
    public void aSaveUser() throws DaoException {
        RoleEntity roleEntity = new RoleEntity(idRole, roleName);
        try {
            tx = session.beginTransaction();
            List<UserEntity> list_before = daoUser.getAllUsers();
            int size_before = list_before.size();
            Date date = new Date(System.currentTimeMillis());
            UserEntity userEntity = new UserEntity(idUser, name, surname, email,
                    login, password, salt, blackList, roleEntity, date);
            daoUser.saveOrUpdate(userEntity);
            List<UserEntity> list_after = daoUser.getAllUsers();
            int size_after = list_after.size();
            tx.commit();
            Assert.assertEquals("Not created", size_before + 1, size_after);
        } catch (DaoException ex) {
            ex.printStackTrace();
            tx.rollback();
        }
    }

    @Test
    public void bGetUserById() throws DaoException {
        try {
            tx = session.beginTransaction();
            UserEntity userEntity = daoUser.get(idUser);
            tx.commit();
            Assert.assertEquals("Not got", login, userEntity.getLogin());
        } catch (DaoException ex) {
            ex.printStackTrace();
            tx.rollback();
        }
    }

    @Test
    public void cGetUserByLogin() throws DaoException {
        try {
            tx = session.beginTransaction();
            UserEntity userEntity = daoUser.getByLogin(login);
            tx.commit();
            Assert.assertEquals("Not got", login, userEntity.getLogin());
        } catch (DaoException ex) {
            ex.printStackTrace();
            tx.rollback();
        }
    }

    @Test
    public void dGetUserByEmail() throws DaoException {
        try {
            tx = session.beginTransaction();
            UserEntity userEntity = daoUser.getByEmail(email);
            tx.commit();
            Assert.assertEquals("Not got", email, userEntity.getEmail());
        } catch (DaoException ex) {
            ex.printStackTrace();
            tx.rollback();
        }
    }

    @Test
    public void eGetUserByLoginAndPassword() throws DaoException {
        try {
            tx = session.beginTransaction();
            UserEntity userEntity = daoUser.get(login, password);
            tx.commit();
            Assert.assertEquals("Not got", login, userEntity.getLogin());
            Assert.assertEquals("Not got", password, userEntity.getPassword());
        } catch (DaoException ex) {
            ex.printStackTrace();
            tx.rollback();
        }
    }

    @Test
    public void fDeleteUser() throws DaoException {
        try {
            tx = session.beginTransaction();
            List<UserEntity> list_before = daoUser.getAllUsers();
            int size_before = list_before.size();
            UserEntity userEntity = daoUser.get(idUser);
            daoUser.delete(userEntity);
            List<UserEntity> list_after = daoUser.getAllUsers();
            tx.commit();
            int size_after = list_after.size();
            Assert.assertEquals("Not deleted", size_before - 1, size_after);
        } catch (DaoException ex) {
            ex.printStackTrace();
            tx.rollback();
        }
    }
}
