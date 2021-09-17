import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class test {


    public static void main(String[] args) {
//        ArrayList<String> car=new ArrayList<>();
//        car.add("1");
//        car.add("2");
//        car.add("3");
//        car.add("4");
//        car.add("12");
//        car.add("13");
//        ArrayList<String> slot=new ArrayList<>();
//        ArrayList<String> pCar = new ArrayList<>();
//        pCar.add("4");
//        pCar.add("13");
//        pCar.add("1");
//        pCar.add("3");
//        ArrayList<InParking> arrayInParking = InParkingArray.getInstance().getArrayInParking();
//
//        for (InParking inParking : arrayInParking) {
//            if (inParking.getVehicleType()=="Van"){
//                pCar.add(inParking.getParkingSlot());
//
//            }
//        }
//        for ( String s :pCar) {
//            car.removeIf(s::equals);
//
//        }

//        String temp;
//        int size = car.size();
//        for (int i = 0; i < size-1; i++)
//        {
//            for (int j = i + 1; j < size; j++) {
//                for (String s: car
//                ) {
//                    System.out.print("  "+s);
//                }
//                System.out.println("===============");
//                if (Integer.parseInt(car.get(i)) > Integer.parseInt(car.get(j)))
//                {
//                    temp = car.get(i);
//                    car.add(i,car.get(j));
//                    car.add(j, temp);
//                    System.out.println("hutto");
//                }
//                System.out.println("===========");
//            }
//        }

        ArrayList<Integer> car=new ArrayList();
        car.add(1);
        car.add(2);
        car.add(3);
        car.add(4);
        car.add(12);
        car.add(13);


        ArrayList<Integer> pCar = new ArrayList<>();
        pCar.add(4);
        pCar.add(13);
        pCar.add(1);
        pCar.add(3);



        ArrayList<InParking> arrayInParking = InParkingArray.getInstance().getArrayInParking();
        for (InParking inParking : arrayInParking) {
            if (inParking.getVehicleType()=="Van"){
                pCar.add(Integer.valueOf(inParking.getParkingSlot()));

            }
        }


        for (int t: pCar) {
            int z=0;
            for (int i : car) {
                if (i==t){
                    car.remove(z);
                    break;
                }
                z++;
            }
        }




        Collections.sort(car);


    }
}
