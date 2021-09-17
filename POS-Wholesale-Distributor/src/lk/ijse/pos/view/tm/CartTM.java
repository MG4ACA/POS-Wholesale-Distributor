package lk.ijse.pos.view.tm;

import javafx.scene.control.Button;

public class CartTM {
    private String code;
    private String description;
    private double unitPrice;
    private int quantity;
    private double total;
    private Button deleteButton;

    public CartTM(String code, String description, double unitPrice, int quantity, double total, Button deleteButton) {
        this.code = code;
        this.description = description;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.total = total;
        this.deleteButton = deleteButton;
    }

    public CartTM() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }
}
