package web.command;

import db.UserDao;
import db.entity.User;
import org.apache.log4j.Logger;
import web.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

/**
 * Update settings items.
 *
 * @author K.Zakharova
 */
public class UpdateSettingsCommand extends Command {

    private static final long serialVersionUID = 7732286214029478505L;

    private static final Logger log = Logger.getLogger(UpdateSettingsCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {

        log.debug("Command starts");

        User user = (User) request.getSession().getAttribute("user");
        boolean updateUser = false;

        String name = request.getParameter("name");
        if (name != null && !name.isEmpty()) {
            user.setName(name);
            updateUser = true;
        }

        String localeToSet = request.getParameter("localeToSet");
        if (localeToSet != null && !localeToSet.isEmpty()) {
            HttpSession session = request.getSession();
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", localeToSet);
            session.setAttribute("defaultLocale", localeToSet);
            user.setLogin(localeToSet);
            updateUser = true;
        }

        if (updateUser) {
            new UserDao().updateUser(user);
        }

        log.debug("Command finished");
        return Path.PAGE__SETTINGS;
    }
}