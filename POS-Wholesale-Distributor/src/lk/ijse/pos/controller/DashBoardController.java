//package lk.ijse.pos.controller;
//
//import lk.ijse.pos.db.DBConnection;
//import lk.ijse.pos.dto.OrderDetailsDTO;
//import lk.ijse.pos.dto.OrdersDTO;
//import lk.ijse.pos.dao.CrudUtils;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class DashBoardController {
//    Connection connection=null;
//    public boolean placeOrder(OrdersDTO ordersDTO) throws SQLException {
//        try {
//            connection= DBConnection.getInstance().getConnection();
//            connection.setAutoCommit(false);
//            PreparedStatement stm1 = connection.prepareStatement("INSERT INTO orders VALUES (?,?,?,?,?)");
//            stm1.setObject(1, ordersDTO.getOrder_id());
//            stm1.setObject(2, ordersDTO.getOrder_date());
//            stm1.setObject(3, ordersDTO.getTotal_cost());
//            stm1.setObject(4, ordersDTO.getCustomer_id());
//            stm1.setObject(5, ordersDTO.getUser_id());
//
//            Boolean isOrderSaved= stm1.executeUpdate()>0;
//                    if (isOrderSaved){
//                        boolean isODetailsSaved = saveOrderDetails(ordersDTO);
//                        if (isODetailsSaved){
//                            connection.commit();
//                            return true;
//                        }else {
//                            connection.rollback();
//                            return false;
//                        }
//                    }else {
//                        connection.rollback();
//                        return false;
//                    }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }finally {
//            connection.setAutoCommit(true);
//        }
//        return false;
//    }
//
//    public boolean saveOrderDetails(OrdersDTO ods){
//        for (OrderDetailsDTO details : ods.getOrderDetails()) {
//            try {
//                PreparedStatement stm2 = connection.prepareStatement("INSERT INTO orderdetail VALUES (?,?,?,?)");
//                stm2.setObject(1, details.getQty() );
//                stm2.setObject(2, details.getUnit_price());
//                stm2.setObject(3,ods.getOrder_id());
//                stm2.setObject(4, details.getProperty_id());
//                boolean b = stm2.executeUpdate() > 0;
//                if (b){
//                    if (updateBachTable(details.getProperty_id(), details.getQty())) {
//
//                    }else {
//                        return false;
//                    }
//                }else {
//                    return false;
//                }
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        return true;
//    }
//
//    private boolean     updateBachTable(String property_id, int qty) throws SQLException, ClassNotFoundException {
//        System.out.println(qty);
//        PreparedStatement stm3 = connection.prepareStatement("UPDATE batch SET quantity=quantity-? WHERE property_id=?");
//        stm3.setObject(1,qty);
//        stm3.setObject(2,property_id);
//
//        return stm3.executeUpdate()>0;
//    }
//
//    public ArrayList<OrdersDTO> loadCustomerOrders(String id) throws SQLException, ClassNotFoundException {
//        ArrayList<OrdersDTO> list= new ArrayList<>();
//        ResultSet rst=CrudUtils.execute("SELECT * FROM orders WHERE customer_id=?", id);
//        while (rst.next()){
//            list.add(new OrdersDTO(rst.getString(1),rst.getDate(2),rst.getBigDecimal(3),rst.getString(4),rst.getString(5)));
//        }
//        return list;
//    }
//}
