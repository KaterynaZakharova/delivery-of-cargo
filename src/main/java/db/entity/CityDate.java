package db.entity;

import java.sql.Date;

/**
 * City and Date entity.
 *
 * @author K.Zakharova
 */
public class CityDate extends Entity {

    private static final long serialVersionUID = 4716395045539434663L;

    private String city;

    private Date day;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "CityDate [city=" + city + ", day=" + day + ", getId()=" + getId() + "]";
    }
}
