package controller;

import command.CommandError;

import javax.servlet.http.HttpServletRequest;

/**
 * Class designed for command definition
 *
 * @author Dmitry V
 * @version 1.0
 */
public class ActionFactory {

    /**
     * Method implements mechanism of command definition
     *
     * @param request HttpServletRequest
     * @return command
     */
    ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new CommandError();
        String action = request.getParameter("command");
        if (action != null && !action.isEmpty()) {
            try {
                Action currentEnum = Action.valueOf(action.toUpperCase());
                current = currentEnum.getCurrentCommand();
            } catch (IllegalArgumentException e) {
                request.setAttribute(Action.msgError,
                        "<b>Неизвестная команда: " + action + "</b>");
                request.setAttribute(Action.msgMessage,
                        "<b>Неизвестная команда: " + action + "</b>");
            }
        }
        return current;
    }
}