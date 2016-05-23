package com.abm.pos.com.abm.pos.util;

import org.springframework.stereotype.Component;

/**
 * Created by asp5045 on 5/9/16.
 */
@Component
public class SQLQueries {

    //SQL QUERY FOR ADD INTO DATABASE

   public String addProductQuery =

           "INSERT INTO PRODUCT" +
           " (PRODUCT_NO,CATEGORY_ID,VENDOR_ID,BRAND_ID,ATL_NO,DESCRIPTION,COST_PRICE,RETAIL_PRICE,QUANTITY,MIN_PRODUCT,RETURN_RULE,IMAGE,CREATED_DATE)" +
           " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public String addCustomerQuery =
            "INSERT INTO CUSTOMER " +
            "(FIRST_NAME,LAST_NAME,PHONE_NO,EMAIL,DATEOFBIRTH,GENDER,APT_NO,STREET,CITY,STATE,COUNTRY,ZIPCODE,FAX,CUSTOMER_CREATE_DATE)" +
            " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public String addVendorQuery =
            "INSERT INTO VENDOR " +
            "(VENDOR_NAME,DESCRIPTION) " +
            "VALUES(?,?)";

    public String addCategoryQuery =
            "INSERT INTO CATEGORY " +
                    "(CATEGORY_NAME,DESCRIPTION) " +
                    "VALUES(?,?)";
    public String addBrandQuery =
            "INSERT INTO CATEGORY " +
            "(BRAND_NAME,BRAND_DESCRIPTION)" +
            "VALUES(?,?)";

    //ADD TRANSACTION INTO DATABASE

    public String addTransaction =
            "INSERT INTO TRANSACTION " +
                    "(TRANSACTION_DATE,TOTAL_AMOUNT,TAX_AMOUNT,DISCOUNT_AMOUNT,CUSTOMER_ID,USER_ID,PAYMENT_ID) " +
                     "VALUES (?,?,?,?,?,?,?)";

    public String addTransactionLineItem = "INSERT INTO TRANSACTION_LINE_ITEM " +
            "(TRANSACTION_ID,PRODUCT_ID,QUANTITY,RETAIL,COST,DISCOUNT,TAX,)" +
            " VALUES (?,?,?,?,?,?,?)";

    //ADD CLOSING DETAILS INTO DATABASE

    public String addClosingDetails = "INSERT INTO CLOSING_DETAILS (USER_ID,OPEN_DATE,INITIAL_AMOUNT,REPORT_CASH,REPORT_CREDIT,CLOSE_CASH," +
            "CLOSE_CREDIT," + "CLOSE_DATE,DIFFERENCE_CASH,DIFFERENCE_CREDIT,TOTAL_DIFFERENCE,TOTAL_AMOUNT) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";



    //SQL QUERY FOR EDIT  INTO DATABASE

    public String editProductQuery = "UPDATE PRODUCT SET PRODUCT_NO = ?, CATEGORY_ID = ?, VENDOR_ID = ?, BRAND_ID = ?, ATL_NO = ?, DESCRIPTION = ?, " +
            "COST_PRICE = ?, RETAIL_PRICE = ?, QUANTITY = ?, MIN_PRODUCT = ?, RETURN_RULE = ?, IMAGE = ?, CREATED_DATE = ?";

    public String editCustomerQuery = "UPDATE CUSTOMER SET FIRST_NAME = ?,  LAST_NAME = ?, PHONE_NO = ?, EMAIL = ?, DATEOFBIRTH = ?, GENDER = ?, APT_NO = ?," +
            " STREET = ?, CITY = ?, STATE = ?, COUNTRY = ?, ZIPCODE = ?, FAX = ?, CUSTOMER_CREATE_DATE = ? WHERE ";

    public String editVendorQuery = "";

    public String editCategoryQueryy = "";

    public String editBrandQuery = "";


    //SQL QUERY TO GET  DETAILS FROM DATABASE

    public String getProductDetails = "SELECT * FROM PRODUCT WHERE ";

    public String getCustomerDetails = "SELECT * FROM CUSTOMER WHERE ";

    public String getVendorDetails = "SELECT * FROM VENDOR WHERE ";

    public String getCategoryDetails = "SELECT * FROM CATEGORY WHERE";

    public String getBrandDetails = "SELECT * FROM CATEGORY WHERE";

    //SQL QUERY TO DELETE FROM DATABASE

    public String deleteProduct = "DELETE FROM PRODUCT WHERE ";

    public String deleteCustomer = "DELETE FROM PRODUCT WHERE ";

    public String deleteVendor = "DELETE FROM PRODUCT WHERE ";

    public String deleteCategory = "DELETE FROM PRODUCT WHERE ";

    public String deleteBrand = "DELETE FROM PRODUCT WHERE ";


}
