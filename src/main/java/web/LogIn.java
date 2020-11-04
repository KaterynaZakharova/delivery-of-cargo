package web;

import db.UserDao;
import db.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class LogIn
 */
public class LogIn extends HttpServlet {

    private static final long serialVersionUID = 2L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        RequestDispatcher requestDispatcher;
        User currentUser;

        if (login.isEmpty() || password.isEmpty()) {
            requestDispatcher = request.getRequestDispatcher(Path.PAGE__LOGIN);

            requestDispatcher.include(request, response);
        } else {
            currentUser = new UserDao().findUserByLogin(login);

            if (null == currentUser) {
                requestDispatcher = request.getRequestDispatcher(Path.PAGE__REGISTRATION);
                requestDispatcher.forward(request, response);
            } else {
                if (login.equals("admin")) {
                    if (currentUser.getPassword().equals(password)) {
                        requestDispatcher = request.getRequestDispatcher(Path.PAGE__ADMIN_CABINET);
                        requestDispatcher.forward(request, response);
                    } else {
                        requestDispatcher = request.getRequestDispatcher(Path.PAGE__LOGIN);
                        requestDispatcher.include(request, response);
                    }
                } else if (currentUser.getPassword().equals(password)) {
                    String userName = currentUser.getName();
                    request.setAttribute("name", userName);

                    requestDispatcher = request.getRequestDispatcher(Path.PAGE__CLIENT_CABINET);
                    requestDispatcher.forward(request, response);
                } else {
                    requestDispatcher = request.getRequestDispatcher(Path.PAGE__LOGIN);
                    requestDispatcher.include(request, response);
                }
            }
        }
    }
}
