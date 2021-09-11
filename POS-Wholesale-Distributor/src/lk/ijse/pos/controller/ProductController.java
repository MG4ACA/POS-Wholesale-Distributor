package lk.ijse.pos.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lk.ijse.pos.model.Customer;
import lk.ijse.pos.model.Product;
import lk.ijse.pos.utils.CrudUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductController {
    public Boolean saveProduct(Product product) throws SQLException, ClassNotFoundException {
        return CrudUtils.execute("INSERT INTO product VALUES (?,?,?,?,?,?,?,?)",product.getProductId(), product.getProductName(), product.getDescription(), product.getSpecification(), product.getDisplayName(), product.isAvailability(), product.isActiveState(), product.getAvailableBrands() );
    }

    public Boolean updateProduct(Product product) throws SQLException, ClassNotFoundException {
        return CrudUtils.execute("UPDATE product SET product_name=?, description=?, specification=?, display_name=?, availability=?, active_state=?, available_brands=? WHERE  product_id=?",  product.getProductName(), product.getDescription(), product.getSpecification(), product.getDisplayName(), product.isAvailability(), product.isActiveState(), product.getAvailableBrands(), product.getProductId());
    }

    public Product searchProduct(String id)throws SQLException, ClassNotFoundException{
        ResultSet rst = CrudUtils.execute("SELECT * FROM product WHERE product_id=?", id);
        if (rst.next()) {
            return new Product(rst.getString(1), rst.getString(2),rst.getString(3), rst.getString(4), rst.getString(5), (Integer.parseInt(rst.getString(6)))>0, (Integer.parseInt(rst.getString(7)))>0,rst.getString(8));
        }
        return null;
    }

    public Boolean deleteProduct(String id)throws SQLException, ClassNotFoundException{
        return CrudUtils.execute("DELETE FROM product WHERE product_id=?", id);
    }

    public ArrayList<Product> getAllProduct()throws SQLException, ClassNotFoundException{
        return CrudUtils.execute("SELECT * FROM product");
    }
}
