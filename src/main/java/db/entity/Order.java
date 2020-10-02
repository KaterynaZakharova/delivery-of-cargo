package db.entity;

/**
 * Order entity.
 *
 * @author K.Zakharova
 */
public class Order extends Entity {

    private static final long serialVersionUID = 4732718395046889396L;

    private String departureDate;

    private String type;

    private int weight;

    private int volume;

    private String city;

    private int price;

    private boolean payment;

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean getPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Order [departureDate=" + departureDate + ", type=" + type + ", weight=" + weight +
                ", volume=" + volume + ", city=" + city + ", price=" + price + ", payment=" + payment +
                ", getId()=" + getId() + "]";
    }
}

