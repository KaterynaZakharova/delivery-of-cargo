package db;

/**
 * Holder for fields names of DB tables and beans.
 *
 * @author K.Zakharova
 */
public final class Fields {

    public static final String ENTITY__ID = "id";

    public static final String AUTHORIZED_USER__LOGIN = "login";
    public static final String AUTHORIZED_USER__PASSWORD = "password";
    public static final String AUTHORIZED_USER__NAME = "name";
    public static final String AUTHORIZED_USER__ROLE_ID = "role_id_role";

    public static final String RATE__TYPE = "type";
    public static final String RATE__COEFFICIENT = "coefficient";

    public static final String ORDER__DEPARTURE_DATE = "departure_date";
    public static final String ORDER__TYPE = "type";
    public static final String ORDER__WEIGHT = "weight";
    public static final String ORDER__VOLUME = "volume";
    public static final String ORDER__CITY = "city";
    public static final String ORDER__PRICE = "price";
    public static final String ORDER__PAYMENT = "payment";
    public static final String ORDER__AUTHORIZED_USER_ID = "authorized_user_id_authorized_user";
    public static final String ORDER__RATE_ID = "rate_id_rate";
    public static final String ORDER__DRIVER_ID = "driver_id_driver";

    public static final String REPORT__DELIVERY_DATE = "delivery_date";
    public static final String REPORT__ORDER_ID = "order_id_order";

    public static final String DRIVER__NAME = "name";

    public static final String DRIVER_CITY_DATE__CITY = "city";
    public static final String DRIVER_CITY_DATE__DAY = "day";
    public static final String DRIVER_CITY_DATE__DRIVER_ID = "driver_id_driver";

    public static final String AUTHORIZED_USER_ORDER_BEAN__ORDER_ID = "id_order";
    public static final String AUTHORIZED_USER_ORDER_BEAN__ORDER_TYPE = "type";
    public static final String AUTHORIZED_USER_ORDER_BEAN__ORDER_WEIGHT = "weight";
    public static final String AUTHORIZED_USER_ORDER_BEAN__ORDER_VOLUME = "volume";
    public static final String AUTHORIZED_USER_ORDER_BEAN__ORDER_CITY = "city";
    public static final String AUTHORIZED_USER_ORDER_BEAN__ORDER_PRICE = "price";
    public static final String AUTHORIZED_USER_ORDER_BEAN__ORDER_PAYMENT = "payment";
    public static final String AUTHORIZED_USER_ORDER_BEAN__AUTHORIZED_USER_NAME = "name";
    public static final String AUTHORIZED_USER_ORDER_BEAN__RATE_ID = "rate_id_rate";
    public static final String AUTHORIZED_USER_ORDER_BEAN__DRIVER_ID = "driver_id_driver";

    public static final String DRIVER_CITY_DATE_BEAN__DRIVER_ID = "id_driver";
    public static final String DRIVER_CITY_DATE__BEAN__DRIVER_NAME = "name";
    public static final String DRIVER_CITY_DATE__ORDER_BEAN__CITY = "city";
    public static final String DRIVER_CITY_DATE__ORDER_BEAN__DAY = "day";
}