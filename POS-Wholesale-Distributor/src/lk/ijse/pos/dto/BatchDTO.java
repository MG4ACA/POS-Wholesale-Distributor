package lk.ijse.pos.dto;

import java.math.BigDecimal;

public class BatchDTO {
    private String property_id;
    private String batch;
    private BigDecimal price;
    private BigDecimal discount;
    private boolean discount_state;
    private boolean active_state;
    private int quantity ;
    private String system_date ;
    private String product_id;

    public BatchDTO() {
    }

    public BatchDTO(String property_id, String batch, BigDecimal price, BigDecimal discount, boolean discount_state, boolean active_state, int quantity, String system_date, String product_id) {
        this.property_id = property_id;
        this.batch = batch;
        this.price = price;
        this.discount = discount;
        this.discount_state = discount_state;
        this.active_state = active_state;
        this.quantity = quantity;
        this.system_date = system_date;
        this.product_id = product_id;
    }

    public String getProperty_id() {
        return property_id;
    }

    public void setProperty_id(String property_id) {
        this.property_id = property_id;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public boolean isDiscount_state() {
        return discount_state;
    }

    public void setDiscount_state(boolean discount_state) {
        this.discount_state = discount_state;
    }

    public boolean isActive_state() {
        return active_state;
    }

    public void setActive_state(boolean active_state) {
        this.active_state = active_state;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSystem_date() {
        return system_date;
    }

    public void setSystem_date(String system_date) {
        this.system_date = system_date;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "BatchDTO{" +
                "property_id='" + property_id + '\'' +
                ", batch='" + batch + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", discount_state=" + discount_state +
                ", active_state=" + active_state +
                ", quantity=" + quantity +
                ", system_date='" + system_date + '\'' +
                ", product_id='" + product_id + '\'' +
                '}';
    }
}
