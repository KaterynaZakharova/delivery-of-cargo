package db.entity;

/**
 * Driver entity.
 *
 * @author K.Zakharova
 */
public class Driver extends Entity {

    private static final long serialVersionUID = 4716395168539434663L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Driver [name=" + name + ", getId()=" + getId() + "]";
    }
}
