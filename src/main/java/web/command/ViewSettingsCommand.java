package web.command;

import org.apache.log4j.Logger;
import web.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * View settings command.
 *
 * @author K.Zakharova
 */
public class ViewSettingsCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger log = Logger.getLogger(ViewSettingsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Command starts");

        log.debug("Command finished");
        return Path.PAGE__SETTINGS;
    }

}