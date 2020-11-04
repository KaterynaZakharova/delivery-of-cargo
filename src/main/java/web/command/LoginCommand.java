package web.command;

import db.Role;
import db.UserDao;
import db.entity.User;
import org.apache.log4j.Logger;
import web.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

/**
 * Login command.
 *
 * @author K.Zakharova
 */
public class LoginCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger log = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {

        log.debug("Command starts");

        HttpSession session = request.getSession();

        // obtain login and password from the request
        String login = request.getParameter("login");
        log.trace("Request parameter: login --> " + login);

        String password = request.getParameter("password");

        // error handler
        String errorMessage = null;
        String forward = Path.PAGE__ERROR_PAGE;

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            errorMessage = "Login/password cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        }

        User user = null;
        user = new UserDao().findUserByLogin(login);
        log.trace("Found in DB: user --> " + user);

        if (user == null || !password.equals(user.getPassword())) {
            errorMessage = "Cannot find user with such login/password";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        } else {
            Role userRole = Role.getRole(user);
            log.trace("userRole --> " + userRole);

            if (userRole == Role.ADMIN) {
                forward = Path.COMMAND__LIST_REPORTS;
            }

            if (userRole == Role.CLIENT) {
                forward = Path.COMMAND__LIST_ORDERS;
            }

            session.setAttribute("user", user);
            log.trace("Set the session attribute: user --> " + user);

            session.setAttribute("userRole", userRole);
            log.trace("Set the session attribute: userRole --> " + userRole);

            log.info("User " + user + " logged as " + userRole.toString().toLowerCase());

            // work with i18n
            String userLogin = user.getLogin();
            log.trace("userLocalName --> " + userLogin);

            if (userLogin != null && !userLogin.isEmpty()) {
                Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", userLogin);

                session.setAttribute("defaultLocale", userLogin);
                log.trace("Set the session attribute: defaultLocaleName --> " + userLogin);

                log.info("Locale for user: defaultLocale --> " + userLogin);
            }
        }

        log.debug("Command finished");
        return forward;
    }
}