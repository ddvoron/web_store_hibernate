package com.voronovich.serviceImpl;

import com.voronovich.entity.DataEntity;
import com.voronovich.entity.DescriptionEntity;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.ResourceBundle;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DescriptionServiceImplTest {

    private ResourceBundle myResources = ResourceBundle.getBundle("dataTest");
    private int idData = Integer.parseInt(myResources.getString("idDataBasket"));
    private int idDescription = Integer.parseInt(myResources.getString("idDescription"));
    private String title = myResources.getString("title");
    private String value = myResources.getString("value");

    private DescriptionServiceImpl serviceDescription = DescriptionServiceImpl.getInstance();
    private DataServiceImpl serviceData = DataServiceImpl.getInstance();

    @Test
    public void aSaveCatalog() {
        DataEntity dataEntity = serviceData.get(idData);
        DescriptionEntity descriptionEntity = new DescriptionEntity(idDescription,
                title, value, dataEntity);
        List<DescriptionEntity> list_before = serviceDescription.getAllDescriptions();
        int size_before = list_before.size();
        serviceDescription.saveOrUpdate(descriptionEntity);
        List<DescriptionEntity> list_after = serviceDescription.getAllDescriptions();
        int size_after = list_after.size();
        Assert.assertEquals("Not created", size_before + 1, size_after);
    }

    @Test
    public void bGetByData() {
        DataEntity dataEntity = serviceData.get(idData);
        List<DescriptionEntity> descriptionEntities = serviceDescription.getAllDescriptionsByData(dataEntity);
        Assert.assertNotNull(descriptionEntities);
    }

    @Test
    public void cDeleteCatalog() {
        List<DescriptionEntity> list_before = serviceDescription.getAllDescriptions();
        int size_before = list_before.size();
        DescriptionEntity descriptionEntity = serviceDescription.get(idDescription);
        serviceDescription.delete(descriptionEntity);
        List<DescriptionEntity> list_after = serviceDescription.getAllDescriptions();
        int size_after = list_after.size();
        Assert.assertEquals("Not deleted", size_before - 1, size_after);
    }
}
