package web.command;

import db.OrderDao;
import db.bean.UserOrderBean;
import org.apache.log4j.Logger;
import web.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Lists orders.
 *
 * @author K.Zakharova
 */
public class ListOrdersCommand extends Command {

    private static final long serialVersionUID = 1863978254689586513L;

    private static final Logger log = Logger.getLogger(ListOrdersCommand.class);

    /**
     * Serializable comparator used with TreeMap container. When the servlet
     * container tries to serialize the session it may fail because the session
     * can contain TreeMap object with not serializable comparator.
     *
     * @author K.Zakharova
     */
    private static class CompareById implements Comparator<UserOrderBean>, Serializable {
        private static final long serialVersionUID = -1573481565177573283L;

        public int compare(UserOrderBean bean1, UserOrderBean bean2) {
            if (bean1.getId() > bean2.getId()) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    private static Comparator<UserOrderBean> compareById = new CompareById();

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
        log.debug("Commands starts");

        List<UserOrderBean> userOrderBeanList = new OrderDao().getUserOrderBeans();
        log.trace("Found in DB: userOrderBeanList --> " + userOrderBeanList);

        Collections.sort(userOrderBeanList, compareById);

        request.setAttribute("userOrderBeanList", userOrderBeanList);
        log.trace("Set the request attribute: userOrderBeanList --> " + userOrderBeanList);

        log.debug("Commands finished");
        return Path.PAGE__LIST_ORDERS;
    }
}
