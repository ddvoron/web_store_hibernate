package command;

import controller.Action;
import controller.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static command.CommonDescriptionMethods.methodBodyDescription;

/**
 * Class implements the content and functionality of the page mobile description
 *
 * @author Dmitry V
 * @version 1.0
 */
public class CommandDescriptionMobile implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = Action.DESCRIPTIONMOBILE.inPage;

        methodBodyDescription(request);

        return page;
    }

}
