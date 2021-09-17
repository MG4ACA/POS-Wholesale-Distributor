package lk.ijse.POS.view.tm;

public class CustomerTM {
    private String id;
    private String name;
    private String address;
    private Double salary;

    @Override
    public String toString() {
        return "CustomerTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public CustomerTM() {
    }

    public CustomerTM(String id, String name, String address, Double salary) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }
}
