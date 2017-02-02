package command;

import com.voronovich.entity.BasketEntity;
import com.voronovich.entity.DataEntity;
import com.voronovich.entity.DescriptionEntity;
import com.voronovich.entity.UserEntity;
import com.voronovich.serviceImpl.BasketServiceImpl;
import com.voronovich.serviceImpl.DataServiceImpl;
import com.voronovich.serviceImpl.DescriptionServiceImpl;
import controller.Action;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static controller.FormHelper.isPost;

public class CommonDescriptionMethods {

    public static void methodBodyDescription(HttpServletRequest request) {
        DescriptionServiceImpl serviceDescription = DescriptionServiceImpl.getInstance();
        DataServiceImpl serviceData = DataServiceImpl.getInstance();
        if (isPost(request)) {
            methodBodyGood(request);
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            DataEntity dataEntity = serviceData.get(id);
            request.setAttribute("data", dataEntity);
            List<DescriptionEntity> descriptionEntity = serviceDescription
                    .getAllDescriptionsByData(dataEntity);
            request.setAttribute("list", descriptionEntity);
        }
    }

    public static void methodBodyGood(HttpServletRequest request) {
        DataServiceImpl dataService = DataServiceImpl.getInstance();
        BasketServiceImpl basketService = BasketServiceImpl.getInstance();
        UserEntity userEntity = (UserEntity) request.getSession(true)
                .getAttribute("user");
        if (userEntity == null) {
            request.setAttribute(Action.msgMessage,
                    "Для работы с корзиной требуется авторизация...");
        } else {
            int good = Integer.parseInt(request.getParameter("neID"));
            DataEntity dataEntity = dataService.get(good);
            BasketEntity basketEntity = new BasketEntity(0, userEntity, dataEntity);
            basketService.saveOrUpdate(basketEntity);
        }
    }
}
