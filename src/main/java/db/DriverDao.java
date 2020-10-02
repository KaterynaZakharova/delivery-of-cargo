package db;

import db.bean.DriverCityDateBean;
import db.entity.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverDao {

    private static final String SQL__FIND_ALL_DRIVERS =
            "SELECT * FROM driver";

    private static final String SQL__FIND_DRIVERS_BY_CITY =
            "SELECT * FROM driver WHERE city=?";

    private static final String SQL__FIND_DRIVERS_BY_DAY =
            "SELECT * FROM driver WHERE day=?";

    /**
     * Returns all drivers.
     *
     * @return List of driver entities.
     */
    public List<Driver> findReports() {
        List<Driver> driversList = new ArrayList<>();

        Statement statement = null;
        ResultSet resultSets = null;
        Connection connection = null;

        try {
            connection = DBManager.getInstance().getConnection();
            DriverDao.DriverMapper mapper = new DriverDao.DriverMapper();

            statement = connection.createStatement();
            resultSets = statement.executeQuery(SQL__FIND_ALL_DRIVERS);

            while (resultSets.next()) {
                driversList.add(mapper.mapRow(resultSets));
            }
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);

            Logger.getLogger(DriverDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return driversList;
    }

    /**
     * Returns drivers with the given city.
     *
     * @param city City identifier.
     * @return List of drivers entities.
     */
    public List<Driver> findDrivers(String city) {
        List<Driver> ratesList = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;

        try {
            connection = DBManager.getInstance().getConnection();
            DriverDao.DriverMapper mapper = new DriverDao.DriverMapper();

            preparedStatement = connection.prepareStatement(SQL__FIND_DRIVERS_BY_CITY);

            preparedStatement.setString(1, city);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ratesList.add(mapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);

            Logger.getLogger(DriverDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return ratesList;
    }

    /**
     * Returns drivers with the given city.
     *
     * @param day Day identifier.
     * @return List of drivers entities.
     */
    public List<DriverCityDateBean> findDrivers(Date day) {
        List<DriverCityDateBean> ratesList = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;

        try {
            connection = DBManager.getInstance().getConnection();
            DriverDao.DriverCityDateBeanMapper mapper = new DriverDao.DriverCityDateBeanMapper();

            preparedStatement = connection.prepareStatement(SQL__FIND_DRIVERS_BY_DAY);

            preparedStatement.setDate(1, day);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ratesList.add(mapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);

            Logger.getLogger(DriverDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return ratesList;
    }

    /**
     * Extracts a driver city date bean from the result set row.
     */
    private static class DriverCityDateBeanMapper implements EntityMapper<DriverCityDateBean> {

        @Override
        public DriverCityDateBean mapRow(ResultSet resultSet) {
            try {
                DriverCityDateBean bean = new DriverCityDateBean();

                bean.setId(resultSet.getLong(Fields.DRIVER_CITY_DATE__DRIVER_ID));
                bean.setdriverName(resultSet.getString(Fields.DRIVER_CITY_DATE__BEAN__DRIVER_NAME));
                bean.setCityDateCity(resultSet.getString(Fields.DRIVER_CITY_DATE__CITY));
                bean.setCityDateDay(resultSet.getDate(Fields.DRIVER_CITY_DATE__DAY));

                return bean;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    /**
     * Extracts a driver from the result set row.
     */
    private static class DriverMapper implements EntityMapper<Driver> {

        @Override
        public Driver mapRow(ResultSet rs) {
            try {
                Driver driver = new Driver();
                driver.setId(rs.getLong(Fields.ENTITY__ID));
                driver.setName(rs.getString(Fields.DRIVER__NAME));

                return driver;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
