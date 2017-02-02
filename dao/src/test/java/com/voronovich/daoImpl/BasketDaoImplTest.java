package com.voronovich.daoImpl;

import com.voronovich.entity.BasketEntity;
import com.voronovich.entity.DataEntity;
import com.voronovich.entity.UserEntity;
import com.voronovich.exceptions.DaoException;
import com.voronovich.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.ResourceBundle;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BasketDaoImplTest {

    private ResourceBundle myResources = ResourceBundle.getBundle("dataTest");

    private final int idUserBasket = Integer.parseInt(myResources.getString("idUserBasket"));
    private final int idDataBasket = Integer.parseInt(myResources.getString("idDataBasket"));
    private final int idBasket = Integer.parseInt(myResources.getString("idBasket"));

    private UserDaoImpl daoUser = UserDaoImpl.getInstance();
    private DataDaoImpl daoData = DataDaoImpl.getInstance();
    private BasketDaoImpl daoBasket = BasketDaoImpl.getInstance();

    private Transaction tx = null;
    private Session session = HibernateUtil.getHibernateUtil().getSession();

    @Test
    public void aSaveBasket() throws DaoException {
        try {
            tx = session.beginTransaction();
            UserEntity userEntity = daoUser.get(idUserBasket);
            DataEntity dataEntity = daoData.get(idDataBasket);
            BasketEntity basketEntity = new BasketEntity(idBasket, userEntity, dataEntity);
            List<BasketEntity> list_before = daoBasket.getByUser(userEntity);
            int size_before = list_before.size();
            daoBasket.saveOrUpdate(basketEntity);
            List<BasketEntity> list_after = daoBasket.getByUser(userEntity);
            int size_after = list_after.size();
            tx.commit();
            Assert.assertEquals("Not created", size_before + 1, size_after);
        } catch (DaoException ex) {
            ex.printStackTrace();
            tx.rollback();
        }
    }

    @Test
    public void bGetByData() throws DaoException {
        try {
            tx = session.beginTransaction();
            DataEntity dataEntity = daoData.get(idDataBasket);
            BasketEntity basketEntity = daoBasket.getByData(dataEntity);
            tx.commit();
            Assert.assertNotNull(basketEntity);
        } catch (DaoException ex) {
            ex.printStackTrace();
            tx.rollback();
        }
    }

    @Test
    public void cGetAll() throws DaoException {
        try {
            tx = session.beginTransaction();
            List<BasketEntity> basketEntity = daoBasket.getAllBasket();
            tx.commit();
            Assert.assertNotNull(basketEntity);
        } catch (DaoException ex) {
            ex.printStackTrace();
            tx.rollback();
        }
    }
    @Test
    public void dGetBasketById() throws DaoException {
        try {
            tx = session.beginTransaction();
            UserEntity userEntity = daoUser.get(idUserBasket);
            BasketEntity basketEntity = daoBasket.get(idBasket);
            tx.commit();
            Assert.assertEquals("Not got", userEntity.getLogin(),
                    basketEntity.getUserEntity().getLogin());
        } catch (DaoException ex) {
            ex.printStackTrace();
            tx.rollback();
        }
    }

    @Test
    public void eDeleteBasket() throws DaoException {
        try {
            tx = session.beginTransaction();
            UserEntity userEntity = daoUser.get(idUserBasket);
            List<BasketEntity> list_before = daoBasket.getByUser(userEntity);
            int size_before = list_before.size();
            BasketEntity basketEntity = daoBasket.get(idBasket);
            daoBasket.delete(basketEntity);
            List<BasketEntity> list_after = daoBasket.getByUser(userEntity);
            tx.commit();
            int size_after = list_after.size();
            Assert.assertEquals("Not deleted", size_before - 1, size_after);
        } catch (DaoException ex) {
            ex.printStackTrace();
            tx.rollback();
        }
    }
}
