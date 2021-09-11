package lk.ijse.pos.model;

import java.math.BigDecimal;

public class OrderDetails {
    private int qty;
    private BigDecimal unit_price;
    private String order_id;
    private String product_id;

    public OrderDetails() {
    }

    public OrderDetails(int qty, BigDecimal unit_price, String order_id, String product_id) {
        this.qty = qty;
        this.unit_price = unit_price;
        this.order_id = order_id;
        this.product_id = product_id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "qty=" + qty +
                ", unit_price=" + unit_price +
                ", order_id='" + order_id + '\'' +
                ", product_id='" + product_id + '\'' +
                '}';
    }
}
