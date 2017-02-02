package command;

import com.voronovich.entity.UserEntity;
import com.voronovich.serviceImpl.UserServiceImpl;
import com.voronovich.util.HibernateUtil;
import controller.Action;
import controller.ActionCommand;
import controller.FormHelper;
import hash.RandomSalt;
import hash.Sha256;
import org.apache.commons.lang3.StringUtils;
import patterns.ValidationPatterns;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ResourceBundle;

public class CommandModifyProfile implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = Action.MODIFYPROFILE.inPage;
        UserServiceImpl service = UserServiceImpl.getInstance();

        if ((request.getSession(true).getAttribute("user")) == null) {
            request.setAttribute(Action.msgMessage,
                    "Для редактирования профиля требуется авторизация");
        } else {
            UserEntity userEntity = (UserEntity) request.getSession(true).getAttribute("user");
            int id_default = userEntity.getIdUser();
            String login_before = userEntity.getLogin();
            String email_before = userEntity.getEmail();
            try {
                if (FormHelper.isPost(request)) {
                    String name = request.getParameter("name");
                    String surname = request.getParameter("surname");
                    String email = request.getParameter("email");
                    String login = request.getParameter("login");
                    String password = request.getParameter("password");
                    if (StringUtils.isEmpty(name) || StringUtils.isEmpty(surname)
                            || StringUtils.isEmpty(email) || StringUtils.isEmpty(login)
                            || StringUtils.isEmpty(password)) {
                        request.setAttribute(Action.msgMessage,
                                "Для изменения вашего аккаунта заполните все данные.");
                        return page;
                    } else if (service.getByLogin(login) != null &&
                            !(service.getByLogin(login).getLogin()).equals(login_before)) {
                        request.setAttribute(Action.msgMessage,
                                "Пользователь не изменен. Данный логин занят.");
                        page = Action.MODIFYPROFILE.inPage;
                    } else if (service.getByEmail(email) != null &&
                            !(service.getByEmail(email).getEmail()).equals(email_before)) {
                        request.setAttribute(Action.msgMessage,
                                "Пользователь не изменен. Данный Email занят.");
                        page = Action.MODIFYPROFILE.inPage;
                    } else if (FormHelper.getString(request, "name", ValidationPatterns.NAME)
                            && FormHelper.getString(request, "surname", ValidationPatterns.SURNAME)
                            && FormHelper.getString(request, "email", ValidationPatterns.EMAIL)
                            && FormHelper.getString(request, "login", ValidationPatterns.LOGIN)
                            && FormHelper.getString(request, "password", ValidationPatterns.PASSWORD)) {
                        String salt = RandomSalt.csRandomAlphaNumericString(20);
                        String staticSalt = ResourceBundle.getBundle("staticValue").getString("staticValue");
                        String password2 = Sha256.sha256(password + salt + staticSalt);
                        userEntity = service.get(id_default);
                        userEntity.setName(name);
                        userEntity.setSurname(surname);
                        userEntity.setEmail(email);
                        userEntity.setLogin(login);
                        userEntity.setPassword(password2);
                        userEntity.setSalt(salt);
                        service.saveOrUpdate(userEntity);
                        HibernateUtil.getHibernateUtil().getSession().clear();
                        if (userEntity != service.get(id_default)) {
                            request.getSession().invalidate();
                            request.setAttribute(
                                    Action.msgMessage,
                                    "Пользователь изменен. Введите данные для авторизации.");
                            page = Action.LOGIN.inPage;
                        } else {
                            request.setAttribute(
                                    Action.msgMessage,
                                    "Пользователь не изменен. Введите данные заново.");
                            page = Action.MODIFYPROFILE.inPage;
                        }
                    }
                }
            } catch (ParseException e) {
                request.setAttribute(Action.msgMessage,
                        "Пользователь НЕ изменен. Проверьте формат данных.");
            }
        }
        return page;
    }
}
