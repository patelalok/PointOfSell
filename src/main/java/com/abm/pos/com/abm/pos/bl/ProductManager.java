package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.*;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import com.itextpdf.text.pdf.PRAcroForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

            if(productDto.getCategoryId() == 10)
            {
                System.out.println("This product in phone category");

                jdbcTemplate.update(sqlQuery.addProductQueryForPhone,
                        productDto.getProductNo(),
                        productDto.getCategoryId(),
                        productDto.getVendorId(),
                        productDto.getBrandId(),
                        productDto.getAltNo(),
                        productDto.getDescription(),
                        productDto.isAddTax(),
                        //Sending quantity 0 to fix issue with add line item where its throwing null pointer
                        0,
                        productDto.isRelatedProduct());

                System.out.println("Products basic information added successfully for phone category");

              /*  jdbcTemplate.update(sqlQuery.addPhoneDetailsAsProduct,
                        productDto.getProductNo(),
                        productDto.getImeiNo(),
                        productDto.getCostPrice(),
                        productDto.getRetailPrice(),
                        productDto.getMarkup(),
                        productDto.getCreatedDate());

                System.out.println("Phone's IMEI information added successfully");*/

            }
            else {
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
                        // productDto.getImeiNo(),
                        productDto.isAddTax(),
                        productDto.isRelatedProduct());

                System.out.println(productDto.isAddTax());
                System.out.println("Product Added Successfully");
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


    public void editProductToDB(ProductDto productDto) {

        try {

            if (productDto.getCategoryId() == 10) {

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
                        0,
                        productDto.getMinProductQuantity(),
                        productDto.getReturnRule(),
                        productDto.getImage(),
                        productDto.isAddTax(),
                        productDto.isRelatedProduct(),
                        productDto.getProductId(),
                        productDto.getOldProductNo());

                System.out.println("Products basic information Edited successfully for phone category");

              /*  jdbcTemplate.update(sqlQuery.editPhoneDetailsAsProduct,
                        productDto.getProductNo(),
                        productDto.getImeiNo(),
                        productDto.getCostPrice(),
                        productDto.getRetailPrice(),
                        productDto.getMarkup(),
                        productDto.getCreatedDate(),
                        productDto.getPhoneId());*/
            }
            else {
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
                        //productDto.getImeiNo(),
                        productDto.isAddTax(),
                        productDto.isRelatedProduct(),
                        productDto.getProductId(),
                        productDto.getOldProductNo());

                System.out.println("Product Edited Successfully");
                System.out.println(productDto.isAddTax());
            }
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

        productDtoList = jdbcTemplate.query(sqlQuery.getRelatedProducts, new ProductMapper (), productNo);

        return productDtoList;

       // List<RelatedProductDto> relatedProductDtosList = new ArrayList<>();

        //ProductMapper mapper = new ProductMapper();


        //Here getting the product no to get the related product no to particular product from Related Product table
        //relatedProductDtosList = jdbcTemplate.query(sqlQuery.getRelatedProducts, new RelatedProductMapper (), productNo);

//        if(null != relatedProductDtosList) {
//
//            for(int i = 0; i<relatedProductDtosList.size(); i++) {
//
//                System.out.println(relatedProductDtosList.size());
//
//                //getting all related product no and then adding into list and sending to UI
//                productDtoList = jdbcTemplate.query(sqlQuery.getProductDetailsWithProductNo, new ProductMapper(), relatedProductDtosList.get(i).getRelatedProductNo());
//            }
//
//        }
//        else
//        {
//            System.out.println("relatedProductDtosList is Null thats why going in else!!!");
//        }



    }

    public List<ProductDto> getRelatedProductForProductPage(String productNo) {

        List<RelatedProductDto> relatedProductDtosList = new ArrayList<>();

        List<ProductDto> productDtos = new ArrayList<>();

        productDtos = jdbcTemplate.query(sqlQuery.getRelatedProducts, new ProductMapperWithRelatedId (), productNo);

        return productDtos;

    }



    private final class RelatedProductMapper implements RowMapper<RelatedProductDto>
    {

        @Override
        public RelatedProductDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            RelatedProductDto relatedProductDto = new RelatedProductDto();

            relatedProductDto.setProductNo(rs.getString("PRODUCT_NO"));
            relatedProductDto.setRelatedProductId(rs.getInt("RELATED_PRODUCT_ID"));
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
               // product.setImeiNo(rs.getString("IMEI_NUMBER"));
                product.setAddTax(rs.getBoolean("TAX"));
                product.setRelatedProduct(rs.getBoolean("IS_RELATED_PRODUCT"));
                //System.out.println(rs.getBoolean("TAX"));


                //product.setQuantityForSell(1);


                return product;
            }
        }

    private final class ProductMapperWithRelatedId implements RowMapper<ProductDto>
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
            // product.setImeiNo(rs.getString("IMEI_NUMBER"));
            product.setAddTax(rs.getBoolean("TAX"));
            product.setRelatedProduct(rs.getBoolean("IS_RELATED_PRODUCT"));
            product.setRelatedProductId(rs.getInt("RELATED_PRODUCT_ID"));
            //System.out.println(rs.getBoolean("TAX"));


            //product.setQuantityForSell(1);


            return product;
        }
    }


    public void addRelatedProduct(final List<RelatedProductDto> relatedProductDtoList) {

        try {

            //Here First deleting all related products and the adding back again to resolve issue with adding product again and again.

            if(null != relatedProductDtoList) {

                for (int i = 0; i < relatedProductDtoList.size(); i++) {
                    jdbcTemplate.update(sqlQuery.deleteRelatedProduct, relatedProductDtoList.get(i).getRelatedProductId());
                }
            }

            jdbcTemplate.batchUpdate(sqlQuery.addRelatedProduct, new BatchPreparedStatementSetter() {

                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {

                    RelatedProductDto relatedProductDtoList1 = relatedProductDtoList.get(i);

                    ps.setString(1,relatedProductDtoList1.getProductNo());
                    ps.setString(2,relatedProductDtoList1.getRelatedProductNo());

                    System.out.println("Related products added successfully");

                    jdbcTemplate.update(sqlQuery.changeRelatedProdcutStatus, true, relatedProductDtoList1.getProductNo());

                    System.out.println("Related products Flag updates to true successfully");

                }

                @Override
                public int getBatchSize() {
                    return relatedProductDtoList.size();
                }

            });

            //When user is adding related product, here i am updting the product Related falg to true so ui can know that, it need to pull the other related products.


        }
        catch (Exception e)

        {
            System.out.println(e);
        }
    }

    public List<TransactionLineItemDto> getProductHistoryFromDB(String productNo, String startDate, String endDate) {

        List<TransactionLineItemDto> productHistory = new ArrayList<>();

        try
        {
            productHistory = jdbcTemplate.query(SQLQueries.getProductHistory,new ProductHistoryMapper(), startDate,endDate,productNo,productNo,startDate,endDate);
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
            productHistory.setImeiNo(rs.getString("IMEI_NO"));
            productHistory.setProductCount(rs.getString("TOTALQUANTITY"));

            // NEED TO FIND THE LOGIC TO TAKE THIS DB CALL OUT FROM SUM OF QUANTITY

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
    public int deleteProductToDB(String productId) {

        int result = 0;
        try {

             result = jdbcTemplate.update(sqlQuery.deleteProduct, productId);
        }

        catch (Exception e)
        {
            System.out.println(e);
        }

        return result;

    }

    public long getLastProductNo() {

        long lastProductNo = jdbcTemplate.queryForObject(sqlQuery.getLastProductNo, new Object[] {},Long.class);

        return lastProductNo;
    }


    public void deleteRelatedProduct(String relatedProductId) {

        try {

            jdbcTemplate.update(sqlQuery.deleteRelatedProduct, relatedProductId);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }


    public List<ProductDto> getLowStockProductDetails() {

        List<ProductDto> productList = new ArrayList<>();

        try
        {
            productList = jdbcTemplate.query(sqlQuery.getLowStockProductDetails,new ProductMapper());

            System.out.println("Send Low Stock Product Details Successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return productList;
    }

    public void addIMEINo(PhoneDto phoneDto) {

        try
        {
            jdbcTemplate.update(sqlQuery.addPhoneDetailsAsProduct,
                    phoneDto.getProductNo(),
                    phoneDto.getImeiNo(),
                    phoneDto.getCostPrice(),
                    phoneDto.getRetailPrice(),
                    phoneDto.getMarkup(),
                    phoneDto.getLastUpdatedTimeStamp());

            //Handling phone update here first geting the current quantity for that phone from product table
          String quantity = jdbcTemplate.queryForObject(sqlQuery.getPhoneStockFromProductTable, new Object[] {phoneDto.getProductNo()}, String.class);

            //Getting product id bacuse i a doing safe operation so i need product id that why getting product id
            int productId = jdbcTemplate.queryForObject(sqlQuery.getProductId, new Object[] { phoneDto.getProductNo()}, Integer.class);

            if(null != quantity)
            {
                System.out.println("This phone has Already some quantity");
                int phoneQuantity = Integer.parseInt(quantity);
                //Updating the stock of the phone in product table.
                jdbcTemplate.update(sqlQuery.addPhoneStockToProductTable, phoneQuantity + 1, productId);
            }
            else
            {
                System.out.println("Adding stock first time for this phone");
                jdbcTemplate.update(sqlQuery.addPhoneStockToProductTable, 1 , productId);
            }



        }
        catch (Exception e)
        {
            System.out.println(e);
        }


    }


        public List<ProductDto> getPhoneDetails(String productNo) {

            List<ProductDto> productDtoList = new ArrayList<>();

        try
        {

            productDtoList = jdbcTemplate.query(sqlQuery.getPhoneDetails, new ProductMapperForPhone(), productNo);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return productDtoList;
    }

    private final class ProductMapperForPhone implements RowMapper<ProductDto>
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
            product.setCostPrice(rs.getDouble("COST"));
            product.setMarkup(rs.getDouble("MARKUP"));
            product.setRetailPrice(rs.getDouble("RETAIL"));
            product.setStock(rs.getInt("QUANTITY"));
            product.setQuantity(1);
            product.setMinProductQuantity(rs.getInt("MIN_PRODUCT"));
            product.setReturnRule(rs.getString("RETURN_RULE"));
            product.setImage(rs.getString("IMAGE"));
            product.setCreatedDate(rs.getString("CREATED_DATE"));
            product.setBrandId(rs.getInt("BRAND_ID"));
            product.setBrandName(jdbcTemplate.queryForObject(sqlQuery.getBrandName, new Object[] {product.getBrandId()},String.class));
            // product.setImeiNo(rs.getString("IMEI_NUMBER"));
            product.setAddTax(rs.getBoolean("TAX"));
            product.setRelatedProduct(rs.getBoolean("IS_RELATED_PRODUCT"));
            product.setImeiNo(rs.getString("IMEI_NO"));
            product.setPhoneId(rs.getInt("ID"));
            product.setCreatedDate(rs.getString("LAST_UPDATED_TIME"));
            //System.out.println(rs.getBoolean("TAX"));


            //product.setQuantityForSell(1);


            return product;
        }
    }
    private final class PhoneMapper implements RowMapper<PhoneDto>
    {

        @Override
        public PhoneDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            PhoneDto phoneDto = new PhoneDto();

            phoneDto.setPhoneId(rs.getInt("ID"));
            phoneDto.setProductNo(rs.getString("PRODUCT_NO"));
            phoneDto.setImeiNo(rs.getString("IMEI_NO"));
            phoneDto.setCostPrice(rs.getDouble("COST"));
            phoneDto.setRetailPrice(rs.getDouble("RETAIL"));
            phoneDto.setMarkup(rs.getDouble("MARKUP"));
            phoneDto.setLastUpdatedTimeStamp(rs.getString("LAST_UPDATED_TIME"));

            return phoneDto;

        }

    }

    public void editIMEINo(PhoneDto phoneDto) {

        try
        {
            jdbcTemplate.update(sqlQuery.EditPhoneDetailsFromPhoneTable,
                    phoneDto.getProductNo(),
                    phoneDto.getImeiNo(),
                    phoneDto.getCostPrice(),
                    phoneDto.getRetailPrice(),
                    phoneDto.getMarkup(),
                    phoneDto.getLastUpdatedTimeStamp(),
                    phoneDto.getPhoneId());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }


    }


    public void deleteImei(String phoneId) {

    try
    {
        jdbcTemplate.update(sqlQuery.deleteImeiDetailsFromPhone, phoneId);

        System.out.println("Imei No delete successfully" + phoneId);
    }
    catch (Exception e)
    {
        System.out.println(e);
    }
    }
}

