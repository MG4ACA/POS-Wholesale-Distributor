package lk.ijse.pos.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

public class OrdersDTO {
    private String order_id;
    private Date order_date;
    private BigDecimal total_cost;
    private String  customer_id;
    private String user_id;
    private ArrayList<OrderDetailsDTO> orderDetailDTOS;

    public OrdersDTO(String order_id, Date order_date, BigDecimal total_cost, String customer_id, String user_id) {
        this.order_id = order_id;
        this.order_date = order_date;
        this.total_cost = total_cost;
        this.customer_id = customer_id;
        this.user_id = user_id;
    }

    public OrdersDTO(String order_id, Date order_date, BigDecimal total_cost, String customer_id, String user_id, ArrayList<OrderDetailsDTO> orderDetailDTOS) {
        this.order_id = order_id;
        this.order_date = order_date;
        this.total_cost = total_cost;
        this.customer_id = customer_id;
        this.user_id = user_id;
        this.orderDetailDTOS = orderDetailDTOS;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public BigDecimal getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(BigDecimal total_cost) {
        this.total_cost = total_cost;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public ArrayList<OrderDetailsDTO> getOrderDetails() {
        return orderDetailDTOS;
    }

    public void setOrderDetails(ArrayList<OrderDetailsDTO> orderDetailDTOS) {
        this.orderDetailDTOS = orderDetailDTOS;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "order_id='" + order_id + '\'' +
                ", order_date=" + order_date +
                ", total_cost=" + total_cost +
                ", customer_id='" + customer_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", orderDetailDTOS=" + orderDetailDTOS +
                '}';
    }
}
