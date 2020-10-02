package db.entity;

/**
 * Report entity.
 *
 * @author K.Zakharova
 */
public class Report extends Entity {

    private static final long serialVersionUID = 4732708766041889396L;

    private String deliveryDate;

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return "Report [deliveryDate=" + deliveryDate + ", getId()=" + getId() + "]";
    }
}
