package web.command;

import org.apache.log4j.Logger;
import web.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * No command.
 *
 * @author K.Zakharova
 */
public class NoCommand extends Command {

    private static final long serialVersionUID = -2785976616686657267L;

    private static final Logger log = Logger.getLogger(NoCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {

        log.debug("Command starts");

        String errorMessage = "No such command";
        request.setAttribute("errorMessage", errorMessage);
        log.error("Set the request attribute: errorMessage --> " + errorMessage);

        log.debug("Command finished");

        return Path.PAGE__ERROR_PAGE;
    }

}