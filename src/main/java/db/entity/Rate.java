package db.entity;

/**
 * Rate entity.
 *
 * @author K.Zakharova
 */
public class Rate extends Entity {

    private static final long serialVersionUID = 2386302708905518585L;

    private String type;

    private float coefficient;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public String toString() {
        return "Rate [type=" + type + ", coefficient=" + coefficient + ", getId()=" + getId() + "]";
    }

}