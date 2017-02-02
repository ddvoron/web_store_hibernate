package command;

import com.voronovich.entity.UserEntity;
import com.voronovich.serviceImpl.UserServiceImpl;
import com.voronovich.util.HibernateUtil;
import controller.Action;
import controller.ActionCommand;
import controller.FormHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandModifyUsers implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = Action.MODIFYUSERS.inPage;
        UserServiceImpl service = UserServiceImpl.getInstance();
        UserEntity userEntity = (UserEntity) request.getSession(true)
                .getAttribute("user");

        if (FormHelper.isPost(request)) {
            int id = Integer.parseInt(request.getParameter("ID"));
            UserEntity usersEntityUpdate = service.get(id);
            String name = request.getParameter("Name");
            String surname = request.getParameter("Surname");
            String email = request.getParameter("Email");
            String login = request.getParameter("Login");
            String blackList = request.getParameter("BlackList");
            usersEntityUpdate.setName(name);
            usersEntityUpdate.setSurname(surname);
            usersEntityUpdate.setEmail(email);
            usersEntityUpdate.setLogin(login);
            usersEntityUpdate.setBlackList(blackList);
            service.saveOrUpdate(usersEntityUpdate);
            HibernateUtil.getHibernateUtil().getSession().clear();
            if (usersEntityUpdate != service.get(id)) {
                request.setAttribute(Action.msgMessage,
                        "Пользователь успешно обновлен");
                page = Action.MODIFYUSERS.okPage;
            } else {
                request.setAttribute(Action.msgMessage,
                        "Пользователь не обновлен");
                page = Action.MODIFYUSERS.inPage;
            }
        }
        if (userEntity != null && userEntity.getRoleEntity().getIdRole() == 2) {
            List<UserEntity> list = service.getAllUsers();
            request.setAttribute("list", list);
        } else {
            request.setAttribute(Action.msgMessage,
                    "Вы не авторизированы либо не облядаете правами администратора");
        }
        return page;
    }
}
