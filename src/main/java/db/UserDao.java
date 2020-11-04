package db;

import db.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * Data access object for User entity.
 */
public class UserDao {

    private static final String SQL__FIND_USER_BY_LOGIN =
            "SELECT * FROM authorized_user WHERE login=?";

    private static final String SQL__FIND_USER_BY_ID =
            "SELECT * FROM authorized_user WHERE id_authorized_user=?";

    private static final String SQL_UPDATE_USER =
            "UPDATE authorized_user SET password=?, name=? WHERE id_authorized_user=?";

    private static final String SQL_INSERT_USER =
            "INSERT INTO `delivery`.`authorized_user` (id_authorized_user, name, login, password, role_id_role) VALUES (DEFAULT, ?, ?, ?, 1)";

    /**
     * Returns a user with the given identifier.
     *
     * @param id User identifier.
     * @return User entity.
     */
    public User findUser(Long id) {
        User user = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;

        try {
            connection = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();

            preparedStatement = connection.prepareStatement(SQL__FIND_USER_BY_ID);

            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = mapper.mapRow(resultSet);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);

            java.util.logging.Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return user;
    }

    /**
     * Returns a user with the given login.
     *
     * @param login User login.
     * @return User entity.
     */
    public User findUserByLogin(String login) {
        User user = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;

        try {
            connection = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();

            preparedStatement = connection.prepareStatement(SQL__FIND_USER_BY_LOGIN);

            preparedStatement.setString(1, login);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = mapper.mapRow(resultSet);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            //DBManager.getInstance().rollbackAndClose(connection);

            java.util.logging.Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //DBManager.getInstance().commitAndClose(connection);
        }

        return user;
    }

    /**
     * Update user.
     *
     * @param user user to update.
     */
    public void updateUser(User user) {
        Connection connection = null;

        try {
            connection = DBManager.getInstance().getConnection();

            updateUser(connection, user);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);

            java.util.logging.Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }

    // //////////////////////////////////////////////////////////
    // Entity access methods (for transactions)
    // //////////////////////////////////////////////////////////

    /**
     * Update user.
     *
     * @param user user to update.
     * @throws SQLException
     */
    public void updateUser(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER);

        int k = 1;

        preparedStatement.setString(k++, user.getPassword());
        preparedStatement.setString(k++, user.getName());
        preparedStatement.setInt(k++, user.getRoleId());
        preparedStatement.setLong(k, user.getId());

        preparedStatement.executeUpdate();

        preparedStatement.close();
    }

    public void insertUser(String name, String login, String password) throws SQLException {
        Connection connection = DBManager.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER);

        preparedStatement.setString(1, name);
        preparedStatement.setString(2, login);
        preparedStatement.setString(3, password);

        preparedStatement.execute();

        connection.close();
    }

    /**
     * Extracts a user from the result set row.
     */
    private static class UserMapper implements EntityMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet) {
            try {
                User user = new User();

                user.setId(resultSet.getLong(Fields.ENTITY__ID));
                user.setLogin(resultSet.getString(Fields.AUTHORIZED_USER__LOGIN));
                user.setPassword(resultSet.getString(Fields.AUTHORIZED_USER__PASSWORD));
                user.setName(resultSet.getString(Fields.AUTHORIZED_USER__NAME));
                user.setRoleId(resultSet.getInt(Fields.AUTHORIZED_USER__ROLE_ID));

                return user;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
