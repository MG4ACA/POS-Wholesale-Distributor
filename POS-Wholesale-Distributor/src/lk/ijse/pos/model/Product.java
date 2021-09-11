package lk.ijse.pos.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String productId;
    private String productName;
    private String description;
    private String specification;
    private String displayName;
    private boolean availability;
    private boolean activeState;
    private String availableBrands;

    public Product() {
    }

    public Product(String productId, String productName, String description, String specification, String displayName, boolean availability, boolean activeState, String availableBrands) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.specification = specification;
        this.displayName = displayName;
        this.availability = availability;
        this.activeState = activeState;
        this.availableBrands = availableBrands;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public boolean isActiveState() {
        return activeState;
    }

    public void setActiveState(boolean activeState) {
        this.activeState = activeState;
    }

    public String getAvailableBrands() {
        return availableBrands;
    }

    public void setAvailableBrands(String availableBrands) {
        this.availableBrands = availableBrands;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", specification='" + specification + '\'' +
                ", displayName='" + displayName + '\'' +
                ", availability=" + availability +
                ", activeState=" + activeState +
                ", availableBrands='" + availableBrands + '\'' +
                '}';
    }
}
