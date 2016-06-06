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
           " (PRODUCT_NO,CATEGORY_ID,VENDOR_ID,BRAND_ID,ATL_NO,DESCRIPTION,COST_PRICE,MARKUP,RETAIL_PRICE,QUANTITY,MIN_PRODUCT,RETURN_RULE,IMAGE,CREATED_DATE)" +
           " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public String addCustomerQuery =
            "INSERT INTO CUSTOMER " +
            "(FIRST_NAME,LAST_NAME,PHONE_NO,EMAIL,DATEOFBIRTH,GENDER,APT_NO,STREET,CITY,STATE,COUNTRY,ZIPCODE,FAX,CUSTOMER_CREATE_DATE)" +
            " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public String addVendorQuery =
            "INSERT INTO VENDOR " +
            "(VENDOR_NAME,COMMISION,PHONENO,COMPANY_NAME,ADDRESS) " +
            "VALUES(?,?,?,?,?)";

    public String addCategoryQuery =
            "INSERT INTO CATEGORY " +
                    "(CATEGORY_NAME,DESCRIPTION) " +
                    "VALUES(?,?)";
    public String addBrandQuery =
            "INSERT INTO BRAND " +
            "(BRAND_NAME,DESCRIPTION)" +
            "VALUES(?,?)";

    //ADD TRANSACTION INTO DATABASE

    public String addTransaction =
            "INSERT INTO TRANSACTION " +
                    "(TRANSACTION_DATE,TOTAL_AMOUNT,TAX_AMOUNT,DISCOUNT_AMOUNT,CUSTOMER_ID,USER_ID,PAYMENT_ID,STATUS) " +
                     "VALUES (?,?,?,?,?,?,?,?)";

    public String addTransactionLineItem =
            "INSERT INTO TRANSACTION_LINE_ITEM " +
            "(TRANSACTION_ID,PRODUCT_ID,QUANTITY,RETAIL,COST,DISCOUNT,TAX,)" +
            " VALUES (?,?,?,?,?,?,?)";

 public String addUserQuery
         = "INSERT INTO USER" +
         " (USERNAME,PASSWORD,USER_ROLE,USER_CREATED_DATE) " +
         "VALUES (?,?,?,?)";

    //ADD CLOSING DETAILS INTO DATABASE

    public String addClosingDetails = "INSERT INTO CLOSING_DETAILS " +
            "(USER_ID,OPEN_DATE,OPEN_AMOUNT,REPORT_CASH,REPORT_CREDIT,REPORT_TOTAL_AMOUNT,CLOSE_CASH," +
            "CLOSE_CREDIT," + "CLOSE_DATE,CLOSE_TOTAL_AMOUNT,CASH_DIFFERENCE,CREDIT_DIFFERENCE,TOTAL_DIFFERENCE,TOTAL_BUSINESS_AMOUNT) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


 public String addTransactionPaymentDetail = "INSERT INTO TRANSACTION_PAYMENT " +
         "(TRANSACTION_ID,TRANSACTION_DATE,PAYMENT_ID,PAYMENT_AMOUNT)" +
         "VALUES (?,?,?,?)";


    //SQL QUERY FOR EDIT  INTO DATABASE

    public String editProductQuery = "UPDATE PRODUCT SET PRODUCT_NO = ?, CATEGORY_ID = ?, VENDOR_ID = ?, BRAND_ID = ?, " +
            "ATL_NO = ?, DESCRIPTION = ?, COST_PRICE = ?,  MARKUP = ?, RETAIL_PRICE = ?, QUANTITY = ?, MIN_PRODUCT = ?, RETURN_RULE = ?, " +
            "IMAGE = ? WHERE PRODUCT_ID = ?";

    public String editCustomerQuery = "UPDATE CUSTOMER SET FIRST_NAME = ?,  LAST_NAME = ?, PHONE_NO = ?, EMAIL = ?, DATEOFBIRTH = ?, GENDER = ?, APT_NO = ?," +
            " STREET = ?, CITY = ?, STATE = ?, COUNTRY = ?, ZIPCODE = ?, FAX = ? WHERE CUSTOMER_ID = ?";

    public String editVendorQuery = "UPDATE VENDOR SET VENDOR_NAME = ?, COMMISION = ?, PHONENO = ?, COMPANY_NAME = ?, ADDRESS = ? WHERE VENDOR_ID = ?";

    public String editCategoryQuery = "UPDATE CATEGORY SET CATEGORY_NAME = ?, DESCRIPTION = ? WHERE CATEGORY_ID = ?";

    public String editBrandQuery = "UPDATE BRAND SET BRAND_NAME = ?, DESCRIPTION = ? WHERE BRAND_ID = ?";

    public String editUserQuery = "UPDATE USER SET PASSWORD = ?, USER_ROLE = ? WHERE USER_ID = ? AND USERNAME = ?";


    //SQL QUERY TO GET  DETAILS FROM DATABASE

    public String getProductDetails = "SELECT * FROM PRODUCT";

    public String getCustomerDetails = "SELECT * FROM CUSTOMER";

    public String getVendorDetails = "SELECT * FROM VENDOR";

    public String getCategoryDetails = "SELECT * FROM CATEGORY";

    public String getBrandDetails = "SELECT * FROM BRAND";

    public String getUserDetails = "SELECT * FROM USER";

    public String getTransactionDetails = "SELECT * FROM TRANSACTION";

    public String getTransactionLineItemDetails = "SELECT * FROM TRANSACTION_LINE_ITEM";

    public String getTransactionPaymentDetails = "SELECT * FROM TRANSACTION_PAYMENT";

    //SQL QUERY TO DELETE FROM DATABASE

    public String deleteProduct = "DELETE FROM PRODUCT WHERE ";

    public String deleteCustomer = "DELETE FROM CUSTOMER WHERE CUSTOMER_ID = ?";

    public String deleteVendor = "DELETE FROM VENDOR WHERE VENDOR_ID = ?";

    public String deleteCategory = "DELETE FROM CATEGORY WHERE CATEGORY_ID = ?";

    public String deleteBrand = "DELETE FROM BRAND WHERE BRAND_ID = ?";


}
