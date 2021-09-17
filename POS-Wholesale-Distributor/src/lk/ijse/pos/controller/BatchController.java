package lk.ijse.pos.controller;

import lk.ijse.pos.model.Batch;
import lk.ijse.pos.model.Customer;
import lk.ijse.pos.utils.CrudUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BatchController {
    public Boolean saveBatch(Batch batch) throws SQLException, ClassNotFoundException {
        return CrudUtils.execute("INSERT INTO batch VALUES (?,?,?,?,?,?,?,?,?)",
                batch.getProperty_id(), batch.getBatch(), batch.getPrice(), batch.getDiscount(), batch.isDiscount_state(), batch.isActive_state(),
                batch.getQuantity(), batch.getSystem_date(), batch.getProduct_id());
    }

    public Boolean updateBatch(Batch batch)throws SQLException, ClassNotFoundException {
        return CrudUtils.execute( "UPDATE batch SET batch=?, price=?, discount=?, discount_state=?, active_state=?, quantity=?, system_date=?, product_id=? WHERE property_id=? ", batch.getBatch(), batch.getPrice(), batch.getDiscount(), batch.isDiscount_state(), batch.isActive_state(),
                batch.getQuantity(), batch.getSystem_date(), batch.getProduct_id(), batch.getProperty_id());
    }

    public Batch searchBatch(String id)throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtils.execute("SELECT * FROM batch WHERE property_id=?", id);
        while (rst.next()){
            return new Batch(rst.getString(1), rst.getString(2), rst.getBigDecimal(3), rst.getBigDecimal(4), rst.getBoolean(5), rst.getBoolean(6), rst.getInt(7), rst.getString(8), rst.getString(9));
        }
        return null;
    }

    public Boolean deleteBatch(String id)throws SQLException, ClassNotFoundException {
        return CrudUtils.execute("DELETE FROM batch WHERE property_id=?", id);
    }

    public ArrayList<Batch> getAllBatch( )throws SQLException, ClassNotFoundException {
        ArrayList<Batch> batches = new ArrayList<>();
        ResultSet rst = CrudUtils.execute("SELECT * FROM batch");
        while (rst.next()){
            batches.add(new Batch(rst.getString(1), rst.getString(2), rst.getBigDecimal(3), rst.getBigDecimal(4), rst.getBoolean(5), rst.getBoolean(6), rst.getInt(7), rst.getString(8), rst.getString(9)));
        }
        return batches;
    }

    public ArrayList<Batch> getAllActiveBatch() throws SQLException, ClassNotFoundException {
        ArrayList<Batch> batches = new ArrayList<>();
        ResultSet rst = CrudUtils.execute("SELECT * FROM batch WHERE active_state=1");
        while (rst.next()){
            batches.add(new Batch(rst.getString(1), rst.getString(2), rst.getBigDecimal(3), rst.getBigDecimal(4), rst.getBoolean(5), rst.getBoolean(6), rst.getInt(7), rst.getString(8), rst.getString(9)));
        }
        return batches;
    }
}
