package web.command;

import db.ReportDao;
import db.entity.Report;
import org.apache.log4j.Logger;
import web.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Lists reports.
 *
 * @author K.Zakharova
 */
public class ListReportsCommand extends Command {

    private static final long serialVersionUID = 1863978254689586513L;

    private static final Logger log = Logger.getLogger(ListReportsCommand.class);

    /**
     * Serializable comparator used with TreeMap container. When the servlet
     * container tries to serialize the session it may fail because the session
     * can contain TreeMap object with not serializable comparator.
     *
     * @author K.Zakharova
     */
    private static class CompareById implements Comparator<Report>, Serializable {
        private static final long serialVersionUID = -1573481565177573283L;

        public int compare(Report report1, Report report2) {
            if (report1.getId() > report2.getId()) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    private static Comparator<Report> compareById = new CompareById();

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
        log.debug("Commands starts");

        List<Report> reportList = new ReportDao().findReports();
        log.trace("Found in DB: reportList --> " + reportList);

        Collections.sort(reportList, compareById);

        request.setAttribute("reportList", reportList);
        log.trace("Set the request attribute: reportList --> " + reportList);

        log.debug("Commands finished");
        return Path.PAGE__LIST_REPORTS;
    }
}