package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.ProductDto;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asp5045 on 5/24/16.
 */
@Component
public class ProductManager
{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQuery;

    public void addProductToDB(ProductDto productDto) {
        try
        {
            jdbcTemplate.update(sqlQuery.addProductQuery,
                    productDto.getProductNo(),
                    productDto.getCategoryId(),
                    productDto.getVendorId(),
                    productDto.getBrandId(),
                    productDto.getAltNo(),
                    productDto.getDescription(),
                    productDto.getCostPrice(),
                    productDto.getMarkup(),
                    productDto.getRetailPrice(),
                    productDto.getQuantity(),
                    productDto.getMinProductQuantity(),
                    productDto.getReturnRule(),
                    productDto.getImage(),
                    productDto.getCreatedDate());
            System.out.println("Product Added Successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void editProductToDB(ProductDto productDto) {

        try
        {
            jdbcTemplate.update(sqlQuery.editProductQuery,
                    productDto.getProductNo(),
                    productDto.getCategoryId(),
                    productDto.getVendorId(),
                    productDto.getBrandId(),
                    productDto.getAltNo(),
                    productDto.getDescription(),
                    productDto.getCostPrice(),
                    productDto.getMarkup(),
                    productDto.getRetailPrice(),
                    productDto.getQuantity(),
                    productDto.getMinProductQuantity(),
                    productDto.getReturnRule(),
                    productDto.getImage(),
                    productDto.getProductId());

            System.out.println("Product Edited Successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
        public List<ProductDto> getProductDetails() {

            List<ProductDto> productList = new ArrayList<>();

            try
            {
                productList = jdbcTemplate.query(sqlQuery.getProductDetails,new ProductMapper());
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            return productList;
        }



    private static final class ProductMapper implements RowMapper<ProductDto>
        {

            @Override
            public ProductDto mapRow(ResultSet rs, int rowNum) throws SQLException {

                ProductDto product = new ProductDto();

                product.setProductId(rs.getInt("PRODUCT_ID"));
                product.setProductNo(rs.getString("PRODUCT_NO"));
                product.setCategoryId(rs.getInt("CATEGORY_ID"));
                product.setVendorId(rs.getInt("VENDOR_ID"));
                product.setAltNo(rs.getString("ATL_NO"));
                product.setDescription(rs.getString("DESCRIPTION"));
                product.setCostPrice(rs.getString("COST_PRICE"));
                product.setMarkup(rs.getString("MARKUP"));
                product.setRetailPrice(rs.getString("RETAIL_PRICE"));
                product.setQuantity(rs.getString("QUANTITY"));
                product.setMinProductQuantity(rs.getString("MIN_PRODUCT"));
                product.setReturnRule(rs.getString("RETURN_RULE"));
                product.setImage(rs.getString("IMAGE"));
                product.setCreatedDate(rs.getString("CREATED_DATE"));
                product.setBrandId(rs.getInt("BRAND_ID"));

                return product;
            }
        }

    public void deleteProductToDB(String productNo) {

    }


    }

