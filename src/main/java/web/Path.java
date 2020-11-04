package web;

/**
 * Path holder (jsp pages, controller commands).
 *
 * @author K.Zakharova
 */
public final class Path {

    // pages
    public static final String PAGE__LOGIN = "/login.jsp";
    public static final String PAGE__REGISTRATION = "/registration.jsp";
    public static final String PAGE__ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
    public static final String PAGE__LIST_REPORTS = "/WEB-INF/jsp/admin/list_reports.jsp";
    public static final String PAGE__LIST_ORDERS = "/WEB-INF/jsp/client/list_orders.jsp";
    public static final String PAGE__SETTINGS = "/WEB-INF/jsp/settings.jsp";
    public static final String PAGE__ADMIN_CABINET = "/WEB-INF/jsp/admin/admin_cabinet.jsp";
    public static final String PAGE__CLIENT_CABINET = "/WEB-INF/jsp/client/client_cabinet.jsp";

    // commands
    public static final String COMMAND__LIST_ORDERS = "/controller?command=listOrders";
    public static final String COMMAND__LIST_REPORTS = "/controller?command=listReports";

}