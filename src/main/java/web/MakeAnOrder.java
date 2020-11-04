package web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MakeAnOrder extends HttpServlet {

    private static final long serialVersionUID = 3L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String departureDate = request.getParameter("departureDate");
        String type = request.getParameter("type");
        String password = request.getParameter("password");

        RequestDispatcher requestDispatcher;
        requestDispatcher = request.getRequestDispatcher(Path.PAGE__CLIENT_CABINET);
        requestDispatcher.forward(request, response);

    }
}
