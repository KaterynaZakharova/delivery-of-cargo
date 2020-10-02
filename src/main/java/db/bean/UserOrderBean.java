package db.bean;

import db.entity.Entity;

/**
 * Provide records for virtual table:
 * <pre>
 * |order.id|order.type|order.weight|order.volume|order.city|order.price|order.payment|user.name|rate.id|driver.id|
 * </pre>
 *
 * @author K.Zakharova
 */
public class UserOrderBean extends Entity {

    private static final long serialVersionUID = -5654982557199337483L;

    private long orderId;

    private String orderType;

    private int orderWeight;

    private int orderVolume;

    private String orderCity;

    private int orderPrice;

    private boolean orderPayment;

    private String userName;

    private int rateId;

    private int driverId;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public int getOrderWeight() {
        return orderWeight;
    }

    public void setOrderWeight(int orderWeight) {
        this.orderWeight = orderWeight;
    }

    public int getOrderVolume() {
        return orderVolume;
    }

    public void setOrderVolume(int orderVolume) {
        this.orderVolume = orderVolume;
    }

    public String getOrderCity() {
        return orderCity;
    }

    public void setOrderCity(String orderCity) {
        this.orderCity = orderCity;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public boolean getOrderPayment() {
        return orderPayment;
    }

    public void setOrderPayment(boolean orderPayment) {
        this.orderPayment = orderPayment;
    }

    public int getRateId() {
        return rateId;
    }

    public void setRateId(int rateId) {
        this.rateId = rateId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    @Override
    public String toString() {
        return "OrderUserBean [orderId=" + orderId + ", orderType="
                + orderType + ", orderWeight=" + orderWeight
                + ", orderVolume=" + orderVolume + ", orderCity=" + orderCity
                + ", orderPrice=" + orderPrice + ", orderPayment=" + orderPayment
                + ", userName=" + userName + ", rateId=" + rateId
                + ", driverId=" + driverId + "]";
    }
}
