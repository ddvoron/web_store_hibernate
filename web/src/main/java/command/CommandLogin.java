package command;

import com.voronovich.entity.UserEntity;
import com.voronovich.serviceImpl.UserServiceImpl;
import controller.Action;
import controller.ActionCommand;
import controller.FormHelper;
import hash.Sha256;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

/**
 * Class implements the content and functionality of the page login
 *
 * @author Dmitry V
 * @version 1.0
 */
public class CommandLogin implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = Action.LOGIN.inPage;
        int TIME_SESSION = 900;
        UserEntity userEntity = null;
        UserServiceImpl service = UserServiceImpl.getInstance();

        if (FormHelper.isPost(request)) {
            String login = request.getParameter("Login");
            String password = request.getParameter("Password");
            if (!StringUtils.isEmpty(login) && !StringUtils.isEmpty(password)) {
                String salt = service.getByLogin(login).getSalt();
                String staticSalt = ResourceBundle.getBundle("staticValue")
                        .getString("staticValue");
                String password1 = Sha256.sha256(password + salt + staticSalt);
                userEntity = service.get(login, password1);
                if (userEntity==null) {
                    request.setAttribute(Action.msgMessage,
                            "Неверные данные, повторите ввод.");
                    page = Action.LOGIN.inPage;
                } else if (StringUtils.equals(userEntity.getBlackList(), "true")) {
                    request.setAttribute(Action.msgMessage,
                            "Ваш профиль заблокирован за несоблюдение правил сайта," +
                                    " за дополнительной информацией обратитесь к администратору.");
                    page = Action.LOGIN.inPage;
                } else {
                    HttpSession session = request.getSession(true);
                    session.setMaxInactiveInterval(TIME_SESSION);
                    session.setAttribute("user", userEntity);
                    request.setAttribute(Action.msgMessage,
                            "Добро пожаловать, " + userEntity.getName());
                    page = Action.LOGIN.okPage;
                }
            } else {
                request.setAttribute(Action.msgMessage,
                        "Для авторизации необходимы ввод логина и пароля.");
                page = Action.LOGIN.inPage;
            }
        } else {
            page = Action.LOGIN.inPage;
        }
        return page;
    }
}