package controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface includes method for performing all commands
 *
 * @author Dmitry V
 * @version 1.0
 */
public interface ActionCommand {

    /**
     * Method performs commands
     *
     * @param request HttpServletRequest
     * @return jsp page/html
     */
    String execute(HttpServletRequest request);
}