package command;

import com.voronovich.entity.RoleEntity;
import com.voronovich.entity.UserEntity;
import com.voronovich.serviceImpl.RoleServiceImpl;
import com.voronovich.serviceImpl.UserServiceImpl;
import controller.Action;
import controller.ActionCommand;
import controller.FormHelper;
import hash.RandomSalt;
import hash.Sha256;
import org.apache.commons.lang3.StringUtils;
import patterns.ValidationPatterns;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;

public class CommandReg implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = Action.REG.inPage;
        int DEFAULT_USER_ID = 0;
        int DEFAULT_ROLE_ID = 1;
        int LENGTH_RANDOM_NUMBER = 20;
        UserServiceImpl service = UserServiceImpl.getInstance();
        RoleServiceImpl service1 = RoleServiceImpl.getInstance();
        try {
            if (FormHelper.isPost(request)) {
                String name = request.getParameter("Name");
                String surname = request.getParameter("Surname");
                String email = request.getParameter("Email");
                String login = request.getParameter("Login");
                String password = request.getParameter("Password");
                String passwordRepeat = request.getParameter("PasswordRepeat");
                String blockValue = "false";
                if (StringUtils.isEmpty(name) || StringUtils.isEmpty(surname)
                        || StringUtils.isEmpty(email) || StringUtils.isEmpty(login)
                        || StringUtils.isEmpty(password) || StringUtils.isEmpty(passwordRepeat)) {
                    request.setAttribute(Action.msgMessage,
                            "Для регистрации вашего аккаунта заполните все данные.");
                    return page;
                } else if (service.getByLogin(login) != null) {
                    request.setAttribute(Action.msgMessage,
                            "Пользователь не создан. Данный логин занят.");
                    page = Action.REG.inPage;
                } else if (service.getByEmail(email) != null) {
                    request.setAttribute(Action.msgMessage,
                            "Пользователь не создан. Данный Email занят.");
                    page = Action.REG.inPage;
                } else if (!StringUtils.equals(password, passwordRepeat)) {
                    request.setAttribute(Action.msgMessage,
                            "Подтвердите пароль корректно.");
                    page = Action.REG.inPage;
                } else if (FormHelper.getString(request, "Name", ValidationPatterns.NAME)
                        && FormHelper.getString(request, "Surname", ValidationPatterns.SURNAME)
                        && FormHelper.getString(request, "Email", ValidationPatterns.EMAIL)
                        && FormHelper.getString(request, "Login", ValidationPatterns.LOGIN)
                        && FormHelper.getString(request, "Password", ValidationPatterns.PASSWORD)) {
                    String salt = RandomSalt.csRandomAlphaNumericString(LENGTH_RANDOM_NUMBER);
                    String staticSalt = ResourceBundle.getBundle("staticValue")
                            .getString("staticValue");
                    String password1 = Sha256.sha256(password + salt + staticSalt);
                    RoleEntity roleEntity = service1.get(DEFAULT_ROLE_ID);
                    UserEntity userEntity = new UserEntity();
                    userEntity.setIdUser(DEFAULT_USER_ID);
                    userEntity.setRoleEntity(roleEntity);
                    userEntity.setName(name);
                    userEntity.setSurname(surname);
                    userEntity.setEmail(email);
                    userEntity.setLogin(login);
                    userEntity.setPassword(password1);
                    userEntity.setSalt(salt);
                    userEntity.setBlackList(blockValue);
                    userEntity.setRegistrationDate(new Date());
                    service.saveOrUpdate(userEntity);
                    if (service.getByLogin(login) != null) {
                        request.setAttribute(Action.msgMessage,
                                "Пользователь создан. Введите данные для авторизации.");
                        page = Action.LOGIN.inPage;
                    } else {
                        request.setAttribute(Action.msgMessage,
                                "Пользователь НЕ создан. Введите данные заново.");
                        page = Action.REG.inPage;
                    }
                }
            }
        } catch (ParseException e) {
            request.setAttribute(Action.msgMessage,
                    "Пользователь НЕ создан. Проверьте формат данных.");
        }
        return page;
    }
}