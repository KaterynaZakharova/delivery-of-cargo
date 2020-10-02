package db.bean;

import db.entity.Entity;

import java.sql.Date;

/**
 * Provide records for virtual table:
 * <pre>
 * |driver.id|driver.name|driver.day|driver.city|
 * </pre>
 *
 * @author K.Zakharova
 */
public class DriverCityDateBean extends Entity {

    private static final long serialVersionUID = -5654982557199332343L;

    private long driverId;

    private String driverName;

    private String cityDateCity;

    private Date cityDateDay;

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getCityDateCity() {
        return cityDateCity;
    }

    public void setCityDateCity(String cityDateCity) {
        this.cityDateCity = cityDateCity;
    }

    public Date getCityDateDay() {
        return cityDateDay;
    }

    public void setCityDateDay(Date CityDateday) {
        this.cityDateDay = cityDateDay;
    }

    @Override
    public String toString() {
        return "DriverCityDateBean [driverId=" + driverId + ", driverName="
                + driverName + ", cityDateCity=" + cityDateCity
                + ", cityDateDay=" + cityDateDay + "]";
    }
}
