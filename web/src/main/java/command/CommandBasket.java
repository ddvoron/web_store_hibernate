package command;

import com.voronovich.entity.BasketEntity;
import com.voronovich.entity.DataEntity;
import com.voronovich.entity.UserEntity;
import com.voronovich.serviceImpl.BasketServiceImpl;
import com.voronovich.serviceImpl.DataServiceImpl;
import com.voronovich.util.HibernateUtil;
import controller.Action;
import controller.ActionCommand;
import controller.FormHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Class implements the content and functionality of the page orders
 *
 * @author Dmitry V
 * @version 1.0
 */
public class CommandBasket implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = Action.BASKET.inPage;

        BasketServiceImpl service = BasketServiceImpl.getInstance();
        DataServiceImpl serviceData = DataServiceImpl.getInstance();

        if ((request.getSession(true).getAttribute("user")) == null) {
            request.setAttribute(Action.msgMessage,
                    "Для работы с корзиной требуется авторизация");
        } else {
            UserEntity userEntity = (UserEntity) request.getSession(true)
                    .getAttribute("user");
            List<BasketEntity> list1 = service.getByUser(userEntity);
            if (list1.size() == 0) {
                request.setAttribute(Action.msgMessage,
                        "Ваша корзина пуста");
            } else {
                List<DataEntity> list = new ArrayList<>();
                for (BasketEntity basketEntity : list1) {
                    DataEntity dataEntity = serviceData
                            .get(basketEntity.getDataEntity().getIdData());
                    list.add(dataEntity);
                }
                request.setAttribute("list", list);
            }
        }
        if (FormHelper.isPost(request)) {
            int id = Integer.parseInt(request.getParameter("neID"));
            DataEntity dataEntity = serviceData.get(id);
            BasketEntity basketEntity = service.getByData(dataEntity);
            service.delete(basketEntity);
            HibernateUtil.getHibernateUtil().getSession().clear();
            if (service.getByData(dataEntity) == null) {
                request.setAttribute(Action.msgMessage,
                        "Товар удален из корзины");
            } else {
                request.setAttribute(Action.msgMessage,
                        "Товар не удален из корзины");
            }
        }
        return page;
    }
}