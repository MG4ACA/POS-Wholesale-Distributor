package lk.ijse.pos.dto;

public class CustomerDTO {
    private String customer_id;
    private String customer_name;
    private String address;
    private String city;
    private String province;
    private int contact;

    public CustomerDTO() {
    }

    public CustomerDTO(String customer_id, String customer_name, String address, String city, String province, int contact) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.address = address;
        this.city = city;
        this.province = province;
        this.contact = contact;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customer_id='" + customer_id + '\'' +
                ", customer_name='" + customer_name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", contact=" + contact +
                '}';
    }
}
