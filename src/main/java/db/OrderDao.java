package db;

import db.bean.UserOrderBean;
import db.entity.Order;
import db.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDao {

    private static final String SQL__GET_USER_ORDER_BEANS =
            "SELECT o.id, u.name" +
                    "	FROM authorized_user u, order o" +
                    "	WHERE o.authorized_user_id_authorized_user=u.id";

    private static final String SQL__FIND_ALL_ORDERS =
            "SELECT * FROM order";

    private static final String SQL__FIND_ORDERS_BY_PAYMENT_AND_USER =
            "SELECT * FROM order WHERE authorized_user_id_authorized_user=? AND payment=?";

    private static final String SQL__FIND_ORDERS_BY_PAYMENT =
            "SELECT * FROM order WHERE payment=?";

    private static final String SQL_INSERT_ORDER =
            "INSERT INTO `delivery`.`order` (departure_date, type, weight, volume, city) VALUES (?, ?, ?, ?, ?)";


    /**
     * Returns all categories.
     *
     * @return List of category entities.
     */
    public List<UserOrderBean> getUserOrderBeans() {
        List<UserOrderBean> orderUserBeanList = new ArrayList<>();

        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;

        try {
            connection = DBManager.getInstance().getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL__GET_USER_ORDER_BEANS);

            UserOrderBeanMapper mapper = new UserOrderBeanMapper();

            while (resultSet.next()) {
                orderUserBeanList.add(mapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);

            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return orderUserBeanList;
    }

    /**
     * Returns all orders.
     *
     * @return List of order entities.
     */
    public List<Order> findOrders() {
        List<Order> ordersList = new ArrayList<>();

        Statement statement = null;
        ResultSet resultSets = null;
        Connection connection = null;

        try {
            connection = DBManager.getInstance().getConnection();
            OrderMapper mapper = new OrderMapper();

            statement = connection.createStatement();
            resultSets = statement.executeQuery(SQL__FIND_ALL_ORDERS);

            while (resultSets.next()) {
                ordersList.add(mapper.mapRow(resultSets));
            }
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);

            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return ordersList;
    }

    /**
     * Returns orders with the given status of payment.
     *
     * @param payment Payment identifier.
     * @return List of order entities.
     */
    public List<Order> findOrders(boolean payment) {
        List<Order> ordersList = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;

        try {
            connection = DBManager.getInstance().getConnection();
            OrderMapper mapper = new OrderMapper();

            preparedStatement = connection.prepareStatement(SQL__FIND_ORDERS_BY_PAYMENT);

            preparedStatement.setBoolean(1, payment);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ordersList.add(mapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);

            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return ordersList;
    }

    /**
     * Returns orders with given identifiers.
     *
     * @param ids Orders identifiers.
     * @return List of order entities.
     */
    public List<Order> findOrders(String[] ids) {
        List<Order> ordersList = new ArrayList<>();

        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;

        try {
            connection = DBManager.getInstance().getConnection();
            OrderMapper mapper = new OrderMapper();

            StringBuilder query = new StringBuilder(
                    "SELECT * FROM orders WHERE id IN (");

            for (String idAsString : ids)
                query.append(idAsString).append(',');

            query.deleteCharAt(query.length() - 1);
            query.append(')');

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query.toString());

            while (resultSet.next()) {
                ordersList.add(mapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);

            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return ordersList;
    }

    /**
     * Returns orders of the given user and payment
     *
     * @param user    User entity.
     * @param payment Payment identifier.
     * @return List of order entities.
     */
    public List<Order> findOrders(User user, boolean payment) {
        List<Order> ordersList = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;

        try {
            connection = DBManager.getInstance().getConnection();
            OrderMapper mapper = new OrderMapper();

            preparedStatement = connection.prepareStatement(SQL__FIND_ORDERS_BY_PAYMENT_AND_USER);

            preparedStatement.setLong(1, user.getId());
            preparedStatement.setBoolean(2, payment);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ordersList.add(mapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);

            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return ordersList;
    }

    public void insertOrder(Date departureDate, String type, int weight, int volume, String city) throws SQLException {
        Connection connection = DBManager.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_ORDER);

        preparedStatement.setDate(1, departureDate);
        preparedStatement.setString(2, type);
        preparedStatement.setInt(3, weight);
        preparedStatement.setInt(4, volume);
        preparedStatement.setString(5, city);

        preparedStatement.execute();

        connection.close();
    }

    /**
     * Extracts a user order bean from the result set row.
     */
    private static class UserOrderBeanMapper implements EntityMapper<UserOrderBean> {

        @Override
        public UserOrderBean mapRow(ResultSet resultSet) {
            try {
                UserOrderBean bean = new UserOrderBean();

                bean.setId(resultSet.getLong(Fields.AUTHORIZED_USER_ORDER_BEAN__ORDER_ID));
                bean.setOrderType(resultSet.getString(Fields.AUTHORIZED_USER_ORDER_BEAN__ORDER_TYPE));
                bean.setOrderWeight(resultSet.getInt(Fields.AUTHORIZED_USER_ORDER_BEAN__ORDER_WEIGHT));
                bean.setOrderVolume(resultSet.getInt(Fields.AUTHORIZED_USER_ORDER_BEAN__ORDER_VOLUME));
                bean.setOrderCity(resultSet.getString(Fields.AUTHORIZED_USER_ORDER_BEAN__ORDER_CITY));
                bean.setOrderPrice(resultSet.getInt(Fields.AUTHORIZED_USER_ORDER_BEAN__ORDER_PRICE));
                bean.setOrderPayment(resultSet.getBoolean(Fields.AUTHORIZED_USER_ORDER_BEAN__ORDER_PAYMENT));
                bean.setUserName(resultSet.getString(Fields.AUTHORIZED_USER_ORDER_BEAN__AUTHORIZED_USER_NAME));
                bean.setRateId(resultSet.getInt(Fields.AUTHORIZED_USER_ORDER_BEAN__RATE_ID));
                bean.setDriverId(resultSet.getInt(Fields.AUTHORIZED_USER_ORDER_BEAN__DRIVER_ID));

                return bean;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    /**
     * Extracts an order from the result set row.
     */
    private static class OrderMapper implements EntityMapper<Order> {

        @Override
        public Order mapRow(ResultSet rs) {
            try {
                Order order = new Order();
                order.setId(rs.getLong(Fields.ENTITY__ID));
                order.setDepartureDate(rs.getString(Fields.ORDER__DEPARTURE_DATE));
                order.setType(rs.getString(Fields.ORDER__TYPE));
                order.setWeight(rs.getInt(Fields.ORDER__WEIGHT));
                order.setVolume(rs.getInt(Fields.ORDER__VOLUME));
                order.setCity(rs.getString(Fields.ORDER__CITY));
                order.setPrice(rs.getInt(Fields.ORDER__PRICE));
                order.setPayment(rs.getBoolean(Fields.ORDER__PAYMENT));

                return order;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
