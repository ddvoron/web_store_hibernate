package com.voronovich.daoImpl;

import com.voronovich.entity.CatalogEntity;
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

public class CatalogDaoImplTest {

    private ResourceBundle myResources = ResourceBundle.getBundle("dataTest");

    private final int idCatalog = Integer.parseInt(myResources.getString("idCatalog"));
    private final String department = myResources.getString("department");

    private CatalogDaoImpl dao = CatalogDaoImpl.getInstance();

    private Transaction tx = null;
    private Session session = HibernateUtil.getHibernateUtil().getSession();

    @Test
    public void aSaveCatalog() throws DaoException {
        CatalogEntity roleEntity = new CatalogEntity(idCatalog,department);
        try {
            tx = session.beginTransaction();
            List<CatalogEntity> list_before = dao.getAllDepartments();
            int size_before = list_before.size();
            dao.saveOrUpdate(roleEntity);
            List<CatalogEntity> list_after = dao.getAllDepartments();
            int size_after = list_after.size();
            tx.commit();
            Assert.assertEquals("Not created", size_before + 1, size_after);
        } catch (DaoException ex) {
            ex.printStackTrace();
            tx.rollback();
        }
    }

    @Test
    public void bGetCatalogById() throws DaoException {
        try {
            tx = session.beginTransaction();
            CatalogEntity catalogEntity = dao.get(idCatalog);
            tx.commit();
            Assert.assertEquals("Not got", department, catalogEntity.getDepartment());
        } catch (DaoException ex) {
            ex.printStackTrace();
            tx.rollback();
        }
    }

    @Test
    public void cDeleteCatalog() throws DaoException {
        try {
            tx = session.beginTransaction();
            List<CatalogEntity> list_before = dao.getAllDepartments();
            int size_before = list_before.size();
            CatalogEntity catalogEntity = dao.get(idCatalog);
            dao.delete(catalogEntity);
            List<CatalogEntity> list_after = dao.getAllDepartments();
            tx.commit();
            int size_after = list_after.size();
            Assert.assertEquals("Not deleted", size_before - 1, size_after);
        } catch (DaoException ex){
            ex.printStackTrace();
            tx.rollback();
        }
    }
}
