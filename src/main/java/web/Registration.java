package web;

import db.UserDao;
import db.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;


/**
 * Servlet implementation class Registration
 */
public class Registration extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        RequestDispatcher requestDispatcher;

        if (name.isEmpty() || login.isEmpty() || password.isEmpty()) {
            requestDispatcher = request.getRequestDispatcher(Path.PAGE__REGISTRATION);
            requestDispatcher.include(request, response);
        } else {
            User currentUser = new UserDao().findUserByLogin(login);

            if (currentUser == null) {
                try {
                    new UserDao().insertUser(name, login, password);
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
                }

                currentUser = new UserDao().findUserByLogin(login);

                String userName = currentUser.getName();
                request.setAttribute("name", userName);

                requestDispatcher = request.getRequestDispatcher(Path.PAGE__CLIENT_CABINET);
            } else {
                requestDispatcher = request.getRequestDispatcher(Path.PAGE__LOGIN);
            }
            requestDispatcher.forward(request, response);
        }
    }
}