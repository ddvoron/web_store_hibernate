package command;

import controller.Action;
import controller.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static command.CommonDescriptionMethods.methodBodyDescription;

/**
 * Class implements the content and functionality of the page tv description
 *
 * @author Dmitry V
 * @version 1.0
 */
public class CommandDescriptionTv implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = Action.DESCRIPTIONTV.inPage;

        methodBodyDescription(request);

        return page;
    }
}
