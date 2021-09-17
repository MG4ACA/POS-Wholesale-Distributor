import java.util.ArrayList;

public class InParkingArray {
    private static InParkingArray arrays;
    private ArrayList<InParking> arrayInParking;

    private InParkingArray() {

        arrayInParking=new ArrayList<>();

    }

    public static InParkingArray getInstance() {
        if (arrays==null){
            arrays=new InParkingArray();
        }
        return arrays;
    }


    public  ArrayList<InParking> getArrayInParking() {
        return arrayInParking;
    }

    public boolean setArrayInParking(InParking inParking) {
        arrayInParking.add(inParking);
        return true;

    }

}
