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
                    "(TRANSACTION_COMP_ID,TRANSACTION_DATE,TOTAL_AMOUNT,TAX_AMOUNT,DISCOUNT_AMOUNT,SUBTOTAL,TOTALQUANTITY,CUSTOMER_PHONENO,USER_ID,PAYMENT_ID_CASH,STATUS,PAID_AMOUNT_CASH,CHANGE_AMOUNT,PAYMENT_ID_CREDIT,TOTAL_AMOUNT_CREDIT) " +
                     "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public String addTransactionLineItem =
            "INSERT INTO TRANSACTION_LINE_ITEM " +
            "(TRANSACTION_COMP_ID,DATE,PRODUCT_ID,QUANTITY,RETAIL,COST,DISCOUNT,RETAILWITHDISCOUNT,TOTALPRODUCTPRICE)" +
            " VALUES (?,?,?,?,?,?,?,?,?)";

 public String addUserQuery
         = "INSERT INTO USER" +
         " (USERNAME,PASSWORD,USER_ROLE,USER_CREATED_DATE) " +
         "VALUES (?,?,?,?)";

    //ADD CLOSING DETAILS INTO DATABASE

    public String addClosingDetails = "INSERT INTO CLOSING_DETAILS " +
            "(USER_ID,OPEN_DATE,OPEN_AMOUNT,REPORT_CASH,REPORT_CREDIT,REPORT_TOTAL_AMOUNT,CLOSE_CASH," +
            "CLOSE_CREDIT," + "CLOSE_DATE,CLOSE_TOTAL_AMOUNT,CASH_DIFFERENCE,CREDIT_DIFFERENCE,TOTAL_DIFFERENCE,TOTAL_BUSINESS_AMOUNT,TOTAL_TAX,TOTAL_DISCOUNT,TOTAL_PROFIT,TOTAL_MARKUP) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


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
            /*"" +
            "" +
            "SELECT t.TRANSACTION_COMP_ID,t.TRANSACTION_DATE,t.TOTAL_AMOUNT,t.TAX_AMOUNT,t.DISCOUNT_AMOUNT," +
            "t.CUSTOMER_PHONENO,t.USER_ID, u.USERNAME,t.PAYMENT_ID_CASH,t.STATUS,t.PAID_AMOUNT_CASH,t.CHANGE_AMOUNT,t.PAYMENT_ID_CREDIT," +
            "t.TOTAL_AMOUNT_CREDIT FROM TRANSACTION t, USER u WHERE t.USER_ID = u.USER_ID AND t.USER_ID = ? AND TRANSACTION_DATE BETWEEN " +
            "? AND ?";*/

    public String getTransactionLineItemDetails = "SELECT * FROM TRANSACTION_LINE_ITEM WHERE TRANSACTION_COMP_ID = ?";

    public String getTransactionPaymentDetails = "SELECT * FROM TRANSACTION_PAYMENT";

    //SQL QUERY TO DELETE FROM DATABASE

    public String deleteProduct = "DELETE FROM PRODUCT WHERE ";

    public String deleteCustomer = "DELETE FROM CUSTOMER WHERE CUSTOMER_ID = ?";

    public String deleteVendor = "DELETE FROM VENDOR WHERE VENDOR_ID = ?";

    public String deleteCategory = "DELETE FROM CATEGORY WHERE CATEGORY_ID = ?";

    public String deleteBrand = "DELETE FROM BRAND WHERE BRAND_ID = ?";


    public String getNoOfProductsForBrand = "SELECT COUNT(*) from PRODUCT where BRAND_ID = ?";
    public String getNoOfProductsForCategory = "SELECT COUNT(*) from PRODUCT where CATEGORY_ID = ?";
    public String getNoOfProductsForVendor = "SELECT COUNT(*) from PRODUCT where VENDOR_ID = ?";
    public String getCategoryName =  "SELECT CATEGORY_NAME FROM CATEGORY WHERE CATEGORY_ID = ? ";
    public String getVendorName = "SELECT VENDOR_NAME FROM VENDOR WHERE VENDOR_ID = ?";
    public String getBrandName = "SELECT BRAND_NAME FROM BRAND WHERE BRAND_ID = ?";
    public String getVendorFromProductTable = "SELECT COUNT(VENDOR_ID) FROM PRODUCT WHERE VENDOR_ID = ?";
    public String getBrandFromProductTable = "SELECT COUNT(BRAND_ID) FROM PRODUCT WHERE BRAND_ID = ?";
    public String getCategoryFromProductTable = "SELECT COUNT(CATEGORY_ID) FROM PRODUCT WHERE CATEGORY_ID = ?";
    public String getUsernameFromUser = "SELECT USERNAME FROM USER WHERE USER_ID = ?";

    public String getTransactionDetailsForReceipt = "SELECT * FROM TRANSACTION WHERE TRANSACTION_COMP_ID = ?";

            /*"SELECT t.TRANSACTION_COMP_ID,t.TRANSACTION_DATE,t.TOTAL_AMOUNT," +
            "t.TAX_AMOUNT,t.DISCOUNT_AMOUNT,t.CUSTOMER_PHONENO,t.USER_ID,l.PRODUCT_ID,l.QUANTITY,l.COST,l.DISCOUNT,l.RETAIL" +
            ",t.PAYMENT_ID_CASH,t.STATUS,t.PAID_AMOUNT_CASH,t.CHANGE_AMOUNT,t.PAYMENT_ID_CREDIT,t.TOTAL_AMOUNT_CREDIT " +
            "FROM TRANSACTION t, TRANSACTION_LINE_ITEM l WHERE t.TRANSACTION_COMP_ID = l.TRANSACTION_COMP_ID AND" +
            " t.TRANSACTION_COMP_ID = 94";
            //"INNER JOIN TRANSACTION_LINE_ITEM ON TRANSACTION.TRANSACTION_COMP_ID = ?";
   public String productDescriptionFromProduct = "SELECT DESCRIPTION FROM PRODUCT WHERE PRODUCT_NO = ?";*/


    public static String getProductHistory = "SELECT t.DATE,p.PRODUCT_ID,p.PRODUCT_NO,p.DESCRIPTION,t.QUANTITY,t.RETAIL," +
            "t.COST,t.DISCOUNT FROM TRANSACTION_LINE_ITEM t, PRODUCT p WHERE t.PRODUCT_ID=p.PRODUCT_ID AND t.PRODUCT_ID = ?";

    public static String getProductHistoryCount = "SELECT count(PRODUCT_ID) FROM TRANSACTION_LINE_ITEM where PRODUCT_ID = ?";
   public String getProductDescription = "SELECT DESCRIPTION FROM PRODUCT WHERE PRODUCT_ID = ?";
}
