package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.ProductDto;
import com.abm.pos.com.abm.pos.dto.ProductNoAndAltNoDTO;
import com.abm.pos.com.abm.pos.dto.RelatedProductDto;
import com.abm.pos.com.abm.pos.dto.TransactionLineItemDto;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
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
                    productDto.getCreatedDate(),
                    productDto.getImeiNo(),
                    productDto.isAddTax(),
                    productDto.isRelatedProduct());

            System.out.println(productDto.isAddTax());
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
                    productDto.getImeiNo(),
                    productDto.isAddTax(),
                    productDto.isRelatedProduct(),
                    productDto.getProductId(),
                    productDto.getOldProductNo());

            System.out.println("Product Edited Successfully");
            System.out.println(productDto.isAddTax());
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

                System.out.println("Send Product Details Successfully");
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            return productList;
        }

    public List<ProductDto> getRelatedProduct(String productNo) {

        List<ProductDto> productDtoList = new ArrayList<>();

        List<RelatedProductDto> relatedProductDtosList = new ArrayList<>();


        //Here getting the product no to get the related product no to particular product from Related Product table
        relatedProductDtosList = jdbcTemplate.query(sqlQuery.getRelatedProducts, new RelatedProductMapper (), productNo);

        if(null != relatedProductDtosList) {

            //getting all related product no and then adding into list and sending to UI
            for (int i = 0; i < relatedProductDtosList.size(); i++) {

                productDtoList = jdbcTemplate.query(sqlQuery.getProductDetailsWithProductNo, new ProductMapper(), relatedProductDtosList.get(i).getRelatedProductNo());

                return productDtoList;

            }
        }
        else
        {
            System.out.println("relatedProductDtosList is Null thats why going in else!!!");
        }

        return productDtoList;

    }



    private final class RelatedProductMapper implements RowMapper<RelatedProductDto>
    {

        @Override
        public RelatedProductDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            RelatedProductDto relatedProductDto = new RelatedProductDto();


            relatedProductDto.setRelatedProductNo(rs.getString("RELATED_PRODUCT_NO"));
            //System.out.println(rs.getBoolean("TAX"));


            //product.setQuantityForSell(1);


            return relatedProductDto;
        }
    }



    private final class ProductMapper implements RowMapper<ProductDto>
        {

            @Override
            public ProductDto mapRow(ResultSet rs, int rowNum) throws SQLException {

                ProductDto product = new ProductDto();

                product.setProductId(rs.getInt("PRODUCT_ID"));
                product.setProductNo(rs.getString("PRODUCT_NO"));
                product.setCategoryId(rs.getInt("CATEGORY_ID"));
                product.setCategoryName(jdbcTemplate.queryForObject(sqlQuery.getCategoryName, new Object[] {product.getCategoryId()},String.class));
                product.setVendorId(rs.getInt("VENDOR_ID"));
                product.setVendorName(jdbcTemplate.queryForObject(sqlQuery.getVendorName, new Object[] {product.getVendorId()},String.class));
                product.setAltNo(rs.getString("ATL_NO"));
                product.setDescription(rs.getString("DESCRIPTION"));
                product.setCostPrice(rs.getDouble("COST_PRICE"));
                product.setMarkup(rs.getDouble("MARKUP"));
                product.setRetailPrice(rs.getDouble("RETAIL_PRICE"));
                product.setStock(rs.getInt("QUANTITY"));
                product.setQuantity(1);
                product.setMinProductQuantity(rs.getInt("MIN_PRODUCT"));
                product.setReturnRule(rs.getString("RETURN_RULE"));
                product.setImage(rs.getString("IMAGE"));
                product.setCreatedDate(rs.getString("CREATED_DATE"));
                product.setBrandId(rs.getInt("BRAND_ID"));
                product.setBrandName(jdbcTemplate.queryForObject(sqlQuery.getBrandName, new Object[] {product.getBrandId()},String.class));
                product.setImeiNo(rs.getString("IMEI_NUMBER"));
                product.setAddTax(rs.getBoolean("TAX"));
                product.setRelatedProduct(rs.getBoolean("IS_RELATED_PRODUCT"));
                //System.out.println(rs.getBoolean("TAX"));


                //product.setQuantityForSell(1);


                return product;
            }
        }

    public void addRelatedProduct(final List<RelatedProductDto> relatedProductDtoList) {

        try {

            jdbcTemplate.batchUpdate(sqlQuery.addRelatedProduct, new BatchPreparedStatementSetter() {

                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {

                    RelatedProductDto relatedProductDtoList1 = relatedProductDtoList.get(i);

                    ps.setString(1,relatedProductDtoList1.getProductNo());
                    ps.setString(2,relatedProductDtoList1.getRelatedProductNo());

                }

                @Override
                public int getBatchSize() {
                    return relatedProductDtoList.size();
                }

            });

        }
        catch (Exception e)

        {
            System.out.println(e);
        }
    }

    public List<TransactionLineItemDto> getProductHistoryFromDB(int productId) {

        List<TransactionLineItemDto> productHistory = new ArrayList<>();

        try
        {
            productHistory = jdbcTemplate.query(SQLQueries.getProductHistory,new ProductHistoryMapper(), productId);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return productHistory;

    }

    private final class ProductHistoryMapper implements RowMapper<TransactionLineItemDto>
    {

        @Override
        public TransactionLineItemDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            TransactionLineItemDto productHistory = new TransactionLineItemDto();

            productHistory.setTransactionDate(rs.getString("DATE"));
            productHistory.setProductNumber(rs.getString("PRODUCT_NO"));
            productHistory.setProductDescription(rs.getString("DESCRIPTION"));
            productHistory.setQuantity(rs.getInt("QUANTITY"));
            productHistory.setRetail(rs.getDouble("RETAIL"));
            productHistory.setCost(rs.getDouble("COST"));
            productHistory.setDiscount(rs.getDouble("DISCOUNT"));

            // NEED TO FIND THE LOGIC TO TAKE THIS DB CALL OUT FROM SUM OF QUANTITY
            productHistory.setProductCount(jdbcTemplate.queryForObject(SQLQueries.getProductHistoryCount,new Object[] {productHistory.getProductNumber()}, String.class));

            return productHistory;
        }
    }

    //This methode is for ui, to send the all product no and Atl No to check that they are unique by the time of add or edit product.

    public List<ProductNoAndAltNoDTO> getProductNoAndAltNo() {

        List<ProductNoAndAltNoDTO> productNoAndAltNoDTOs = new ArrayList<>();

        productNoAndAltNoDTOs = jdbcTemplate.query(sqlQuery.productNoAndAltNoDTOs, new ProductNoAltNoMapper());

        return productNoAndAltNoDTOs;


    }

    private final class ProductNoAltNoMapper implements RowMapper<ProductNoAndAltNoDTO>
    {

        @Override
        public ProductNoAndAltNoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

            ProductNoAndAltNoDTO productNoAndAltNoDTO = new ProductNoAndAltNoDTO();

            productNoAndAltNoDTO.setProductNo(rs.getString("PRODUCT_NO"));
            productNoAndAltNoDTO.setAtlNo(rs.getString("ATL_NO"));

            return productNoAndAltNoDTO;
        }
    }
    public void deleteProductToDB(String productId) {

        jdbcTemplate.update(sqlQuery.deleteProduct, productId);

    }

    public long getLastProductNo() {

        long lastProductNo = jdbcTemplate.queryForObject(sqlQuery.getLastProductNo, new Object[] {},Long.class);

        return lastProductNo;
    }


    }

