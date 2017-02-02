package com.voronovich.serviceImpl;

import com.voronovich.entity.BasketEntity;
import com.voronovich.entity.DataEntity;
import com.voronovich.entity.UserEntity;
import com.voronovich.exceptions.DaoException;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.ResourceBundle;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BasketServiceImplTest {

    private ResourceBundle myResources = ResourceBundle.getBundle("dataTest");

    private final int idUserBasket = Integer.parseInt(myResources.getString("idUserBasket"));
    private final int idDataBasket = Integer.parseInt(myResources.getString("idDataBasket"));
    private final int idBasket = Integer.parseInt(myResources.getString("idBasket"));

    private UserServiceImpl serviceUser = UserServiceImpl.getInstance();
    private DataServiceImpl serviceData = DataServiceImpl.getInstance();
    private BasketServiceImpl serviceBasket = BasketServiceImpl.getInstance();

    @Test
    public void aSaveBasket() throws DaoException {
            UserEntity userEntity = serviceUser.get(idUserBasket);
            DataEntity dataEntity = serviceData.get(idDataBasket);
            BasketEntity basketEntity = new BasketEntity(idBasket, userEntity, dataEntity);
            List<BasketEntity> list_before = serviceBasket.getByUser(userEntity);
            int size_before = list_before.size();
        serviceBasket.saveOrUpdate(basketEntity);
            List<BasketEntity> list_after = serviceBasket.getByUser(userEntity);
            int size_after = list_after.size();
            Assert.assertEquals("Not created", size_before + 1, size_after);
    }

    @Test
    public void bGetByData() {
            DataEntity dataEntity = serviceData.get(idDataBasket);
            BasketEntity basketEntity = serviceBasket.getByData(dataEntity);
            Assert.assertNotNull(basketEntity);
    }

    @Test
    public void cGetAll() {
            List<BasketEntity> basketEntity = serviceBasket.getAllBasket();
            Assert.assertNotNull(basketEntity);
    }

    @Test
    public void dGetBasketById() {
            UserEntity userEntity = serviceUser.get(idUserBasket);
            BasketEntity basketEntity = serviceBasket.get(idBasket);
            Assert.assertEquals("Not got", userEntity.getLogin(),
                    basketEntity.getUserEntity().getLogin());
    }

    @Test
    public void eDeleteBasket() {
            UserEntity userEntity = serviceUser.get(idUserBasket);
            List<BasketEntity> list_before = serviceBasket.getByUser(userEntity);
            int size_before = list_before.size();
            BasketEntity basketEntity = serviceBasket.get(idBasket);
        serviceBasket.delete(basketEntity);
            List<BasketEntity> list_after = serviceBasket.getByUser(userEntity);
            int size_after = list_after.size();
            Assert.assertEquals("Not deleted", size_before - 1, size_after);
    }
}