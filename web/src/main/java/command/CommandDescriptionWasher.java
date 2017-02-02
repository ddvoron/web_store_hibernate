package command;

import controller.Action;
import controller.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static command.CommonDescriptionMethods.methodBodyDescription;


/**
 * Class implements the content and functionality of the page washer description
 *
 * @author Dmitry V
 * @version 1.0
 */
public class CommandDescriptionWasher implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = Action.DESCRIPTIONWASHER.inPage;

        methodBodyDescription(request);

        return page;
    }
}
