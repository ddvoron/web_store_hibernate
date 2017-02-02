package com.voronovich.daoImpl;

import com.voronovich.entity.DataEntity;
import com.voronovich.entity.DescriptionEntity;
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
public class DescriptionDaoImplTest {

     private ResourceBundle myResources = ResourceBundle.getBundle("dataTest");
    private int idData = Integer.parseInt(myResources.getString("idDataBasket"));
    private int idDescription= Integer.parseInt(myResources.getString("idDescription"));
    private String title= myResources.getString("title");
    private String value= myResources.getString("value");

    private DescriptionDaoImpl daoDescription = DescriptionDaoImpl.getInstance();
    private DataDaoImpl daoData = DataDaoImpl.getInstance();

    private Transaction tx = null;
    private Session session = HibernateUtil.getHibernateUtil().getSession();

    @Test
    public void aSaveCatalog() throws DaoException {
        try {
            tx = session.beginTransaction();
            DataEntity dataEntity = daoData.get(idData);
            DescriptionEntity descriptionEntity = new DescriptionEntity(idDescription,title,value,dataEntity);
            List<DescriptionEntity> list_before = daoDescription.getAllDescription();
            int size_before = list_before.size();
            daoDescription.saveOrUpdate(descriptionEntity);
            List<DescriptionEntity> list_after = daoDescription.getAllDescription();
            int size_after = list_after.size();
            tx.commit();
            Assert.assertEquals("Not created", size_before + 1, size_after);
        } catch (DaoException ex) {
            ex.printStackTrace();
            tx.rollback();
        }
    }

    @Test
    public void bGetByData() throws DaoException{
        try {
            tx = session.beginTransaction();
            DataEntity dataEntity = daoData.get(idData);
            List<DescriptionEntity> descriptionEntities = daoDescription.getAllDescriptionByData(dataEntity);
            tx.commit();
            Assert.assertNotNull(descriptionEntities);
        } catch (DaoException ex) {
            ex.printStackTrace();
            tx.rollback();
        }
    }

    @Test
    public void cDeleteCatalog() throws DaoException {
        try {
            tx = session.beginTransaction();
            List<DescriptionEntity> list_before = daoDescription.getAllDescription();
            int size_before = list_before.size();
            DescriptionEntity descriptionEntity = daoDescription.get(idDescription);
            daoDescription.delete(descriptionEntity);
            List<DescriptionEntity> list_after = daoDescription.getAllDescription();
            tx.commit();
            int size_after = list_after.size();
            Assert.assertEquals("Not deleted", size_before - 1, size_after);
        } catch (DaoException ex){
            ex.printStackTrace();
            tx.rollback();
        }
    }
}
