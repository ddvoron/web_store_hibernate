package controller;

import com.voronovich.entity.UserEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class function is Dispatcher Servlet
 *
 * @author Dmitry V
 * @version 1.0
 */
@WebServlet("/do2")
public class Controller extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {

        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);

        String viewPage = command.execute(request);

        response.setHeader("Cache-Control", "no-store");

        UserEntity userEntity = (UserEntity) request.getSession(true)
                .getAttribute("user");
        if (userEntity != null) {
            Cookie cookieLogin = new Cookie("Login",userEntity.getLogin());
            cookieLogin.setMaxAge(24 * 60 * 60);
            response.addCookie(cookieLogin);
        }
        if (viewPage != null) {
            ServletContext servletContext = getServletContext();
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher(viewPage);
            dispatcher.forward(request, response);
        } else {
            viewPage = Action.ERROR.inPage;
            response.sendRedirect(request.getContextPath() + viewPage);
        }
    }
}