package db;

import db.entity.Rate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RateDao {

    private static final String SQL__FIND_ALL_RATES =
            "SELECT * FROM rate";

    private static final String SQL__FIND_RATES_BY_TYPE =
            "SELECT * FROM rate WHERE type=?";

    /**
     * Returns all rates.
     *
     * @return List of rate entities.
     */
    public List<Rate> findRates() {
        List<Rate> ratesList = new ArrayList<>();

        Statement statement = null;
        ResultSet resultSets = null;
        Connection connection = null;

        try {
            connection = DBManager.getInstance().getConnection();
            RateDao.RateMapper mapper = new RateDao.RateMapper();

            statement = connection.createStatement();
            resultSets = statement.executeQuery(SQL__FIND_ALL_RATES);

            while (resultSets.next()) {
                ratesList.add(mapper.mapRow(resultSets));
            }
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);

            Logger.getLogger(RateDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return ratesList;
    }

    /**
     * Returns rates with the given type.
     *
     * @param type Type identifier.
     * @return List of rates entities.
     */
    public List<Rate> findRates(String type) {
        List<Rate> ratesList = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;

        try {
            connection = DBManager.getInstance().getConnection();
            RateDao.RateMapper mapper = new RateDao.RateMapper();

            preparedStatement = connection.prepareStatement(SQL__FIND_RATES_BY_TYPE);

            preparedStatement.setString(1, type);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ratesList.add(mapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);

            Logger.getLogger(RateDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return ratesList;
    }

    /**
     * Extracts a rate from the result set row.
     */
    private static class RateMapper implements EntityMapper<Rate> {

        @Override
        public Rate mapRow(ResultSet rs) {
            try {
                Rate rate = new Rate();
                rate.setId(rs.getLong(Fields.ENTITY__ID));
                rate.setType(rs.getString(Fields.RATE__TYPE));
                rate.setCoefficient(rs.getFloat(Fields.RATE__COEFFICIENT));

                return rate;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
