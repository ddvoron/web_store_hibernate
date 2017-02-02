package command;

import controller.Action;
import controller.ActionCommand;
import controller.FormHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * Class implements the content and functionality of the page logout
 *
 * @author Dmitry V
 * @version 1.0
 */
public class CommandLogout implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = Action.LOGOUT.inPage;

        if(FormHelper.isPost(request)) {
            request.getSession().invalidate();
            request.setAttribute(Action.msgMessage, "Сеанс завершен");
            page = Action.LOGOUT.okPage;
        }
        return page;
    }
}