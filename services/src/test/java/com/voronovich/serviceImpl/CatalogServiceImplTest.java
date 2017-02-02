package com.voronovich.serviceImpl;

import com.voronovich.entity.CatalogEntity;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.ResourceBundle;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CatalogServiceImplTest {

    private ResourceBundle myResources = ResourceBundle.getBundle("dataTest");

    private final int idCatalog = Integer.parseInt(myResources.getString("idCatalog"));
    private final String department = myResources.getString("department");

    private CatalogServiceImpl dao = CatalogServiceImpl.getInstance();

    @Test
    public void aSaveCatalog() {
        CatalogEntity roleEntity = new CatalogEntity(idCatalog,department);
            List<CatalogEntity> list_before = dao.getAllDepartments();
            int size_before = list_before.size();
            dao.saveOrUpdate(roleEntity);
            List<CatalogEntity> list_after = dao.getAllDepartments();
            int size_after = list_after.size();
            Assert.assertEquals("Not created", size_before + 1, size_after);
    }

    @Test
    public void bGetCatalogById() {
            CatalogEntity catalogEntity = dao.get(idCatalog);
            Assert.assertEquals("Not got", department, catalogEntity.getDepartment());
    }

    @Test
    public void cDeleteCatalog() {
            List<CatalogEntity> list_before = dao.getAllDepartments();
            int size_before = list_before.size();
            CatalogEntity catalogEntity = dao.get(idCatalog);
            dao.delete(catalogEntity);
            List<CatalogEntity> list_after = dao.getAllDepartments();
            int size_after = list_after.size();
            Assert.assertEquals("Not deleted", size_before - 1, size_after);
    }
}
