package db;

/**
 * Holder for fields names of DB tables and beans.
 *
 * @author K.Zakharova
 */
public final class Fields {

    // entities
    public static final String ENTITY__ID = "id";

    public static final String AUTHORIZED_USER__LOGIN = "login";
    public static final String AUTHORIZED_USER__PASSWORD = "password";
    public static final String AUTHORIZED_USER__NAME = "name";
    public static final String AUTHORIZED_USER__ROLE_ID = "role_id";

    public static final String RATE__TYPE = "type";
    public static final String RATE__COEFFICIENT = "coefficient";

    public static final String ORDER__USER_LOGIN = "login";
    public static final String ORDER__NAME = "name";
    public static final String ORDER__DEPARTURE_DATE = "departure_date";
    public static final String ORDER__TYPE = "type";
    public static final String ORDER__WEIGHT = "weight";
    public static final String ORDER__VOLUME = "volume";
    public static final String ORDER__CITY = "city";
    public static final String ORDER__PRICE = "price";
    public static final String ORDER__PAYMENT = "payment";
    public static final String ORDER__AUTHORIZED_USER_ID = "authorized_user_id";
    public static final String ORDER__RATE_ID = "rate_id";

    public static final String REPORT__DELIVERY_DATE = "delivery_date";
    public static final String REPORT__CITY = "city";
    public static final String REPORT__ORDER_ID = "order_id";

    public static final String DRIVER__NAME = "name";

    public static final String DRIVER_CITY_DATE__CITY = "city";
    public static final String DRIVER_CITY_DATE__DAY = "day";
    public static final String DRIVER_CITY_DATE__DRIVER_ID = "driver_id";
    public static final String DRIVER_CITY_DATE__REPORT_ID = "report_id";
}