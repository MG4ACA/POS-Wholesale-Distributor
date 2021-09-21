package lk.ijse.pos.controller;

import lk.ijse.pos.dto.ProductDTO;
import lk.ijse.pos.dao.CrudUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductController {
    public Boolean saveProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        return CrudUtils.execute("INSERT INTO product VALUES (?,?,?,?,?,?,?,?)", productDTO.getProductId(), productDTO.getProductName(), productDTO.getDescription(), productDTO.getSpecification(), productDTO.getDisplayName(), productDTO.isAvailability(), productDTO.isActiveState(), productDTO.getAvailableBrands() );
    }

    public Boolean updateProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        return CrudUtils.execute("UPDATE product SET product_name=?, description=?, specification=?, display_name=?, availability=?, active_state=?, available_brands=? WHERE  product_id=?",  productDTO.getProductName(), productDTO.getDescription(), productDTO.getSpecification(), productDTO.getDisplayName(), productDTO.isAvailability(), productDTO.isActiveState(), productDTO.getAvailableBrands(), productDTO.getProductId());
    }

    public ProductDTO searchProduct(String id)throws SQLException, ClassNotFoundException{
        ResultSet rst = CrudUtils.execute("SELECT * FROM product WHERE product_id=?", id);
        if (rst.next()) {
            return new ProductDTO(rst.getString(1), rst.getString(2),rst.getString(3), rst.getString(4), rst.getString(5), (Integer.parseInt(rst.getString(6)))>0, (Integer.parseInt(rst.getString(7)))>0,rst.getString(8));
        }
        return null;
    }

    public Boolean deleteProduct(String id)throws SQLException, ClassNotFoundException{
        return CrudUtils.execute("DELETE FROM product WHERE product_id=?", id);
    }

    public ArrayList<ProductDTO> getAllProduct()throws SQLException, ClassNotFoundException{
        ArrayList<ProductDTO> productDTOS = new ArrayList<>();
        ResultSet rst = CrudUtils.execute("SELECT * FROM product");
        while (rst.next()){
            productDTOS.add(new ProductDTO(rst.getString(1), rst.getString(2),rst.getString(3), rst.getString(4), rst.getString(5), (Integer.parseInt(rst.getString(6)))>0, (Integer.parseInt(rst.getString(7)))>0,rst.getString(8)));
        }
        return productDTOS;
    }
}
