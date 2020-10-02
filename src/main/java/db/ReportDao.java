package db;

import db.entity.Report;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportDao {

    private static final String SQL__FIND_ALL_REPORTS =
            "SELECT * FROM report";

    private static final String SQL__FIND_REPORTS_BY_ORDER =
            "SELECT * FROM report WHERE order_id_order=?";

    /**
     * Returns all reports.
     *
     * @return List of report entities.
     */
    public List<Report> findReports() {
        List<Report> reportsList = new ArrayList<>();

        Statement statement = null;
        ResultSet resultSets = null;
        Connection connection = null;

        try {
            connection = DBManager.getInstance().getConnection();
            ReportDao.ReportMapper mapper = new ReportDao.ReportMapper();

            statement = connection.createStatement();
            resultSets = statement.executeQuery(SQL__FIND_ALL_REPORTS);

            while (resultSets.next()) {
                reportsList.add(mapper.mapRow(resultSets));
            }
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);

            Logger.getLogger(ReportDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return reportsList;
    }

    /**
     * Returns reports with the given orderID.
     *
     * @param orderId Order identifier.
     * @return List of report entities.
     */
    public List<Report> findReports(int orderId) {
        List<Report> reportsList = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;

        try {
            connection = DBManager.getInstance().getConnection();
            ReportDao.ReportMapper mapper = new ReportDao.ReportMapper();

            preparedStatement = connection.prepareStatement(SQL__FIND_REPORTS_BY_ORDER);

            preparedStatement.setInt(1, orderId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                reportsList.add(mapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);

            Logger.getLogger(ReportDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return reportsList;
    }

    /**
     * Extracts a report from the result set row.
     */
    private static class ReportMapper implements EntityMapper<Report> {

        @Override
        public Report mapRow(ResultSet rs) {
            try {
                Report report = new Report();
                report.setId(rs.getLong(Fields.ENTITY__ID));
                report.setDeliveryDate(rs.getString(Fields.REPORT__DELIVERY_DATE));

                return report;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
