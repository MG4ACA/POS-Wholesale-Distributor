public class InParking {
    private String vehicleNumber;
    private String vehicleType;
    private String parkingSlot;
    private String dateTime;

    public InParking() {
    }

    public InParking(String vehicleNumber, String vehicleType, String parkingSlot, String dateTime) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.parkingSlot = parkingSlot;
        this.dateTime = dateTime;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getParkingSlot() {
        return parkingSlot;
    }

    public void setParkingSlot(String parkingSlot) {
        this.parkingSlot = parkingSlot;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "InParking{" +
                "vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", parkingSlot='" + parkingSlot + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
