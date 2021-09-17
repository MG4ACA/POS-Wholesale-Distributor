public class ParkingSlot {
    private String slot;
    private String vehicleType;
    public ParkingSlot() {
    }

    public ParkingSlot(String slot, String vehicleType) {
        this.slot = slot;
        this.vehicleType = vehicleType;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "slot='" + slot + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                '}';
    }
}
