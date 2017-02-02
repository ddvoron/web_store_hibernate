package com.voronovich.serviceImpl;

import com.voronovich.entity.RoleEntity;
import com.voronovich.entity.UserEntity;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceImplTest {

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

    private UserServiceImpl service = UserServiceImpl.getInstance();

    @Test
    public void aSaveUser() {
        RoleEntity roleEntity = new RoleEntity(idRole, roleName);
        List<UserEntity> list_before = service.getAllUsers();
        int size_before = list_before.size();
        Date date = new Date(System.currentTimeMillis());
        UserEntity userEntity = new UserEntity(idUser, name, surname, email,
                login, password, salt, blackList, roleEntity, date);
        service.saveOrUpdate(userEntity);
        List<UserEntity> list_after = service.getAllUsers();
        int size_after = list_after.size();
        Assert.assertEquals("Not created", size_before + 1, size_after);
    }

    @Test
    public void bGetUserById() {
        UserEntity userEntity = service.get(idUser);
        Assert.assertEquals("Not got", login, userEntity.getLogin());
    }

    @Test
    public void cGetUserByLogin() {
        UserEntity userEntity = service.getByLogin(login);
        Assert.assertEquals("Not got", login, userEntity.getLogin());
    }

    @Test
    public void dGetUserByEmail() {
        UserEntity userEntity = service.getByEmail(email);
        Assert.assertEquals("Not got", email, userEntity.getEmail());
    }

    @Test
    public void eGetUserByLoginAndPassword() {
        UserEntity userEntity = service.get(login, password);
        Assert.assertEquals("Not got", login, userEntity.getLogin());
        Assert.assertEquals("Not got", password, userEntity.getPassword());
    }

    @Test
    public void fDeleteUser() {
        List<UserEntity> list_before = service.getAllUsers();
        int size_before = list_before.size();
        UserEntity userEntity = service.get(idUser);
        service.delete(userEntity);
        List<UserEntity> list_after = service.getAllUsers();
        int size_after = list_after.size();
        Assert.assertEquals("Not deleted", size_before - 1, size_after);
    }
}
