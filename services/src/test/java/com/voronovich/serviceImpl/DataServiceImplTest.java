package com.voronovich.serviceImpl;

import com.voronovich.entity.CatalogEntity;
import com.voronovich.entity.DataEntity;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DataServiceImplTest {

    private ResourceBundle myResources = ResourceBundle.getBundle("dataTest");

    private final int idCatalog = Integer.parseInt(myResources.getString("idCatalogTest"));
    private final String nameCatalog = myResources.getString("nameCatalog");
    private final int idData = Integer.parseInt(myResources.getString("idData"));
    private final String brand = myResources.getString("brand");
    private final String model = myResources.getString("model");
    private final double price = Double.parseDouble(myResources.getString("price"));
    private final String release = myResources.getString("release");
    private final String picture = myResources.getString("picture");
    private final String creator = myResources.getString("creator");
    private final String updater = myResources.getString("updater");
    private final int pageNumber = Integer.parseInt(myResources.getString("pageNumber"));
    private final int pageAmount = Integer.parseInt(myResources.getString("pageAmount"));
    private final int priceMin = Integer.parseInt(myResources.getString("priceMin"));
    private final int priceMax = Integer.parseInt(myResources.getString("priceMax"));
    private final Date creationDate = new Date();
    private final Date updateDate = new Date();


    private DataServiceImpl dao = DataServiceImpl.getInstance();

    @Test
    public void aSaveData() {
        CatalogEntity catalogEntity = new CatalogEntity(idCatalog, nameCatalog);
            List<DataEntity> list_before = dao.getAllData();
            int size_before = list_before.size();
            DataEntity dataEntity = new DataEntity(idData, brand,
                    model, price, release, picture, creationDate,
                    creator, updateDate, updater, catalogEntity);
            dao.saveOrUpdate(dataEntity);
            List<DataEntity> list_after = dao.getAllData();
            int size_after = list_after.size();
            Assert.assertEquals("Not created", size_before + 1, size_after);
    }

    @Test
    public void bGetByPagination() {
            List<DataEntity> dataEntity = dao.getAllDataPerPage(pageNumber, pageAmount);
            int size = dataEntity.size();
            Assert.assertEquals("Not got", pageAmount, size);
    }

    @Test
    public void cGetByPagination() {
        CatalogEntity catalogEntity = new CatalogEntity(idCatalog, nameCatalog);
            List<DataEntity> dataEntity = dao.getAllDataPerPage(pageNumber,
                    pageAmount, catalogEntity);
            int size = dataEntity.size();
            Assert.assertEquals("Not got", pageAmount, size);
    }

    @Test
    public void dGetByPagination() {
        CatalogEntity catalogEntity = new CatalogEntity(idCatalog, nameCatalog);
            List<DataEntity> dataEntity = dao.getAllDataPerPageAndCost(pageNumber,
                    pageAmount, priceMin, priceMax, catalogEntity);
            int size = dataEntity.size();
            Assert.assertEquals("Not got", 3, size);
    }

    @Test
    public void eGetDataById() {
            DataEntity dataEntity = dao.get(idData);
            Assert.assertEquals("Not got", brand, dataEntity.getBrand());
    }

    @Test
    public void fDeleteData() {
            List<DataEntity> list_before = dao.getAllData();
            int size_before = list_before.size();
            DataEntity dataEntity = dao.get(idData);
            dao.delete(dataEntity);
            List<DataEntity> list_after = dao.getAllData();
            int size_after = list_after.size();
            Assert.assertEquals("Not deleted", size_before - 1, size_after);
    }
}