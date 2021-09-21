package lk.ijse.pos.controller;

import lk.ijse.pos.dto.BatchDTO;
import lk.ijse.pos.dao.CrudUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BatchController {
    public Boolean saveBatch(BatchDTO batchDTO) throws SQLException, ClassNotFoundException {
        return CrudUtils.execute("INSERT INTO batch VALUES (?,?,?,?,?,?,?,?,?)",
                batchDTO.getProperty_id(), batchDTO.getBatch(), batchDTO.getPrice(), batchDTO.getDiscount(), batchDTO.isDiscount_state(), batchDTO.isActive_state(),
                batchDTO.getQuantity(), batchDTO.getSystem_date(), batchDTO.getProduct_id());
    }

    public Boolean updateBatch(BatchDTO batchDTO)throws SQLException, ClassNotFoundException {
        return CrudUtils.execute( "UPDATE batch SET batch=?, price=?, discount=?, discount_state=?, active_state=?, quantity=?, system_date=?, product_id=? WHERE property_id=? ", batchDTO.getBatch(), batchDTO.getPrice(), batchDTO.getDiscount(), batchDTO.isDiscount_state(), batchDTO.isActive_state(),
                batchDTO.getQuantity(), batchDTO.getSystem_date(), batchDTO.getProduct_id(), batchDTO.getProperty_id());
    }

    public BatchDTO searchBatch(String id)throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtils.execute("SELECT * FROM batch WHERE property_id=?", id);
        while (rst.next()){
            return new BatchDTO(rst.getString(1), rst.getString(2), rst.getBigDecimal(3), rst.getBigDecimal(4), rst.getBoolean(5), rst.getBoolean(6), rst.getInt(7), rst.getString(8), rst.getString(9));
        }
        return null;
    }

    public Boolean deleteBatch(String id)throws SQLException, ClassNotFoundException {
        return CrudUtils.execute("DELETE FROM batch WHERE property_id=?", id);
    }

    public ArrayList<BatchDTO> getAllBatch( )throws SQLException, ClassNotFoundException {
        ArrayList<BatchDTO> batchDTOS = new ArrayList<>();
        ResultSet rst = CrudUtils.execute("SELECT * FROM batch");
        while (rst.next()){
            batchDTOS.add(new BatchDTO(rst.getString(1), rst.getString(2), rst.getBigDecimal(3), rst.getBigDecimal(4), rst.getBoolean(5), rst.getBoolean(6), rst.getInt(7), rst.getString(8), rst.getString(9)));
        }
        return batchDTOS;
    }

    public ArrayList<BatchDTO> getAllActiveBatch() throws SQLException, ClassNotFoundException {
        ArrayList<BatchDTO> batchDTOS = new ArrayList<>();
        ResultSet rst = CrudUtils.execute("SELECT * FROM batch WHERE active_state=1");
        while (rst.next()){
            batchDTOS.add(new BatchDTO(rst.getString(1), rst.getString(2), rst.getBigDecimal(3), rst.getBigDecimal(4), rst.getBoolean(5), rst.getBoolean(6), rst.getInt(7), rst.getString(8), rst.getString(9)));
        }
        return batchDTOS;
    }
}
