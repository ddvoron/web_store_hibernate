package com.voronovich.serviceImpl;

import com.voronovich.entity.RoleEntity;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.ResourceBundle;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoleServiceImplTest {

    private ResourceBundle myResources = ResourceBundle.getBundle("dataTest");

    private final int idRole = Integer.parseInt(myResources.getString("idRoleRead"));
    private final String name = myResources.getString("roleNameTest");

    private RoleServiceImpl service = RoleServiceImpl.getInstance();

    @Test
    public void aSaveRole() {
        RoleEntity roleEntity = new RoleEntity(idRole, name);
            List<RoleEntity> list_before = service.getAllRoles();
            int size_before = list_before.size();
            service.saveOrUpdate(roleEntity);
            List<RoleEntity> list_after = service.getAllRoles();
            int size_after = list_after.size();
            Assert.assertEquals("Not created", size_before + 1, size_after);
    }

    @Test
    public void bGetRoleById() {
            RoleEntity roleEntity = service.get(idRole);
            Assert.assertEquals("Not got", name, roleEntity.getName());
    }

    @Test
    public void cGetRoles() {
            List<RoleEntity> roleEntity = service.getAllRoles();
            Assert.assertNotNull(roleEntity);
    }

    @Test
    public void dDelete() {
            List<RoleEntity> list_b = service.getAllRoles();
            int size_b = list_b.size();
            RoleEntity roleEntity = service.get(idRole);
            service.delete(roleEntity);
            List<RoleEntity> list_a = service.getAllRoles();
            int size_a = list_a.size();
            Assert.assertEquals("Not deleted", size_b - 1, size_a);
    }
}
