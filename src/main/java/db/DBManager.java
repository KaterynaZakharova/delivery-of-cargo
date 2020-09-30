package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {

    private static final File FILE = new File("./");
    private static URL[] urls;

    static {
        try {
            urls = new URL[]{FILE.toURI().toURL()};
        } catch (MalformedURLException e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private static final ClassLoader LOADER = new URLClassLoader(urls);

    private static DBManager dbManager;

    private static final String CONNECTION_URL = ResourceBundle.getBundle("app", Locale.getDefault(), LOADER).getString("connection.url");

    private DBManager() {
        try (Connection connection = getConnection(CONNECTION_URL);
             Statement statement = connection.createStatement();
             InputStream inputStream = new FileInputStream("sql/dbcreate-mysql.sql")) {

            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("/\\*[\\s\\S]*?\\*/|--[^\\r\\n]*|;");

            while (scanner.hasNext()) {
                String command = scanner.next().trim();

                if (!command.isEmpty())
                    statement.execute(command);
            }
        } catch (SQLException | IOException e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static DBManager getInstance() {
        if (dbManager == null) {
            dbManager = new DBManager();
        }

        return dbManager;
    }

    public Connection getConnection(String connectionUrl) throws SQLException {
        return DriverManager.getConnection(connectionUrl);
    }

    private static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
