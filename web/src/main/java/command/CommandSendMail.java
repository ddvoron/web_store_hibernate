package command;

import com.voronovich.entity.UserEntity;
import com.voronovich.serviceImpl.UserServiceImpl;
import controller.Action;
import controller.ActionCommand;
import controller.FormHelper;
import hash.RandomSalt;
import hash.Sha256;
import mail.Sender;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public class CommandSendMail implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = Action.SENDMAIL.inPage;
        String ADMIN_MAIL = ResourceBundle.getBundle("data").getString("mail");
        String ADMIN_PASSWORD = ResourceBundle.getBundle("data").getString("password");
        String clientMail;
        Sender sslSender = new Sender(ADMIN_MAIL, ADMIN_PASSWORD);
        UserServiceImpl service = UserServiceImpl.getInstance();

        if (FormHelper.isPost(request)) {
            clientMail = request.getParameter("Email");
            UserEntity userEntity = service.getByEmail(clientMail);
            String login = userEntity.getLogin();
            String password = RandomSalt.csRandomAlphaNumericString(7);
            String salt = RandomSalt.csRandomAlphaNumericString(20);
            String staticSalt = ResourceBundle.getBundle("staticValue")
                    .getString("staticValue");
            String password1 = Sha256.sha256(password + salt + staticSalt);
            userEntity.setPassword(password1);
            userEntity.setSalt(salt);
            service.saveOrUpdate(userEntity);
            if (service.get(login, password1) != null) {
                String subject = "Восстановление пароля";
                String text = "Здравствуйте, " + userEntity.getName()
                        + "! Ваш Login: " + login + "; Password: "
                        + password + ".";
                sslSender.send(subject, text, clientMail);
                request.setAttribute(Action.msgMessage,
                        "Данные отправлены на Ваш электронный адрес");
                page = Action.SENDMAIL.okPage;
            }
        }
        return page;
    }
}
