package command;

import controller.Action;
import controller.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static command.CommonDescriptionMethods.methodBodyDescription;

/**
 * Class implements the content and functionality of the page fridge description
 *
 * @author Dmitry V
 * @version 1.0
 */
public class CommandDescriptionFridge implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = Action.DESCRIPTIONFRIDGE.inPage;

        methodBodyDescription(request);

        return page;
    }
}
