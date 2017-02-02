package com.voronovich.daoImpl;

import com.voronovich.entity.RoleEntity;
import com.voronovich.exceptions.DaoException;
import com.voronovich.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.ResourceBundle;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoleDaoImplTest {

    private ResourceBundle myResources = ResourceBundle.getBundle("dataTest");

    private final int idRole = Integer.parseInt(myResources.getString("idRoleRead"));
    private final String name = myResources.getString("roleNameTest");

    private RoleDaoImpl dao = RoleDaoImpl.getInstance();

    private Transaction tx = null;
    private Session session = HibernateUtil.getHibernateUtil().getSession();

    @Test
    public void aSaveRole() throws DaoException {
        RoleEntity roleEntity = new RoleEntity(idRole,name);
        try {
            tx = session.beginTransaction();
            List<RoleEntity> list_before = dao.getAllRoles();
            int size_before = list_before.size();
            dao.saveOrUpdate(roleEntity);
            List<RoleEntity> list_after = dao.getAllRoles();
            int size_after = list_after.size();
            tx.commit();
            Assert.assertEquals("Not created", size_before + 1, size_after);
        } catch (DaoException ex) {
            ex.printStackTrace();
            tx.rollback();
        }
    }

    @Test
    public void bGetRoleById() throws DaoException {
        try {
            tx = session.beginTransaction();
            RoleEntity roleEntity = dao.get(idRole);
            tx.commit();
            Assert.assertEquals("Not got", name, roleEntity.getName());
        } catch (DaoException ex) {
            ex.printStackTrace();
            tx.rollback();
        }
    }

    @Test
    public void cDeleteRole() throws DaoException {
        try {
            tx = session.beginTransaction();
            List<RoleEntity> list_before = dao.getAllRoles();
            int size_before = list_before.size();
            RoleEntity roleEntity = dao.get(idRole);
            dao.delete(roleEntity);
            List<RoleEntity> list_after = dao.getAllRoles();
            tx.commit();
            int size_after = list_after.size();
            Assert.assertEquals("Not deleted", size_before - 1, size_after);
        } catch (DaoException ex){
            ex.printStackTrace();
            tx.rollback();
        }
    }
}
