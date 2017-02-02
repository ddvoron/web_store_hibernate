package command;

import com.voronovich.entity.*;
import com.voronovich.serviceImpl.*;
import com.voronovich.util.HibernateUtil;
import controller.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Class implements the content and functionality of the page modify goods
 *
 * @author Dmitry V
 * @version 1.0
 */
public class CommandModifyGoods implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = Action.MODIFYGOODS.inPage;
        int RECORD_PER_PAGE = 10;
        DataServiceImpl serviceData = DataServiceImpl.getInstance();
        CatalogServiceImpl serviceCatalog = CatalogServiceImpl.getInstance();
        UserEntity userEntity = (UserEntity) request.getSession(true)
                .getAttribute("user");
        if (FormHelper.isPost(request)) {
            int id = Integer.parseInt(request.getParameter("ID"));
            if (id > 0) {
                DataEntity dataEntityUpdate = serviceData.get(id);
                String brand = request.getParameter("Brand");
                String model = request.getParameter("Model");
                Double price = Double.parseDouble(request.getParameter("Price"));
                String release = request.getParameter("ReleaseDate");
                String picture = request.getParameter("Picture");
                dataEntityUpdate.setBrand(brand);
                dataEntityUpdate.setModel(model);
                dataEntityUpdate.setPrice(price);
                dataEntityUpdate.setReleaseDate(release);
                dataEntityUpdate.setPicture(picture);
                dataEntityUpdate.setUpdateDate(new Date());
                dataEntityUpdate.setUpdater(userEntity.getName() + " "
                        + userEntity.getSurname());
                serviceData.saveOrUpdate(dataEntityUpdate);
                HibernateUtil.getHibernateUtil().getSession().clear();
                if (dataEntityUpdate != serviceData.get(id)) {
                    request.setAttribute(Action.msgMessage,
                            "Товар успешно обновлен");
                    page = Action.MODIFYGOODS.okPage;
                } else {
                    request.setAttribute(Action.msgMessage,
                            "Товар не обновлен");
                    page = Action.MODIFYGOODS.inPage;
                }
            }
            if (id == 0) {
                DataEntity dataEntity = new DataEntity();
                String brand = request.getParameter("Brand");
                String model = request.getParameter("Model");
                Double price = Double.parseDouble(request.getParameter("Price"));
                String release = request.getParameter("ReleaseDate");
                String picture = request.getParameter("Picture");
                int fk = Integer.parseInt(request.getParameter("Department"));
                CatalogEntity catalogEntity = serviceCatalog.get(fk);
                List<DataEntity> list_b = serviceData.getAllData();
                dataEntity.setIdData(id);
                dataEntity.setBrand(brand);
                dataEntity.setModel(model);
                dataEntity.setPrice(price);
                dataEntity.setReleaseDate(release);
                dataEntity.setPicture(picture);
                dataEntity.setCreator(userEntity.getName() + " "
                        + userEntity.getSurname());
                dataEntity.setUpdater(userEntity.getName() + " "
                        + userEntity.getSurname());
                dataEntity.setCatalogEntity(catalogEntity);
                serviceData.saveOrUpdate(dataEntity);
                HibernateUtil.getHibernateUtil().getSession().clear();
                List<DataEntity> list_a = serviceData.getAllData();
                if (list_a.size() != list_b.size()) {
                    request.setAttribute(Action.msgMessage,
                            "Товар успешно добавлен");
                    page = Action.MODIFYGOODS.okPage;
                } else {
                    request.setAttribute(Action.msgMessage,
                            "Товар не добавлен");
                    page = Action.MODIFYGOODS.inPage;
                }
            }
            if (id < 0) {
                id = (-1) * id;
                DataEntity dataEntityDelete = serviceData.get(id);
                serviceData.delete(dataEntityDelete);
                HibernateUtil.getHibernateUtil().getSession().clear();
                if (serviceData.get(id) == null) {
                    request.setAttribute(Action.msgMessage,
                            "Товар успешно удален");
                    page = Action.MODIFYGOODS.okPage;
                } else {
                    request.setAttribute(Action.msgMessage,
                            "Товар не удален");
                    page = Action.MODIFYGOODS.inPage;
                }
            }
        }
        if (userEntity != null && userEntity.getRoleEntity().getIdRole() == 2) {
            String number = request.getParameter("page");
            int pageNumber;
            if (number == null) {
                pageNumber = 1;
            } else {
                pageNumber = Integer.parseInt(number);
            }
            List<DataEntity> list = serviceData.getAllDataPerPage(pageNumber, RECORD_PER_PAGE);
            request.setAttribute("list", list);
            request.setAttribute("page1", pageNumber);
        } else {
            request.setAttribute(Action.msgMessage,
                    "Вы не авторизированы либо не обладаете правами администратора");
        }
        return page;
    }
}