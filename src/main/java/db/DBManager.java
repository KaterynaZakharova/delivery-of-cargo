package db;

//import org.apache.log4j.Logger;


import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * DB manager. Works with MySQL.
 * Only the required DAO methods are defined!
 *
 * @author K.Zakharova
 */
public class DBManager {

    //private static final Logger log = Logger.getLogger(DBManager.class);

    private static DBManager instance;

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }

        return instance;
    }

    /**
     * Returns a DB connection from the Pool Connections. Before using this
     * method you must configure the Date Source and the Connections Pool in your
     * WEB_APP_ROOT/META-INF/context.xml file.
     *
     * @return A DB connection.
     */
    public Connection getConnection() throws SQLException {
        Connection connection = null;

        try {
            String url = "jdbc:mysql://localhost/delivery?serverTimezone=Europe/Moscow&useSSL=false";

            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            connection = DriverManager.getConnection(url, "root", "root");
        } catch (InstantiationException e) {
            java.util.logging.Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
        } catch (InvocationTargetException e) {
            java.util.logging.Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
        } catch (NoSuchMethodException e) {
            java.util.logging.Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
        } catch (IllegalAccessException e) {
            java.util.logging.Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException e) {
            java.util.logging.Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return connection;
    }

    private DBManager() {
    }

    /**
     * Commits and close the given connection.
     *
     * @param connection Connection to be committed and closed.
     */
    public void commitAndClose(Connection connection) {
        try {
            connection.commit();

            connection.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Rollbacks and close the given connection.
     *
     * @param connection Connection to be rollbacked and closed.
     */
    public void rollbackAndClose(Connection connection) {
        try {
            connection.rollback();

            connection.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}