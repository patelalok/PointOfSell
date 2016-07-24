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
           " (PRODUCT_NO,CATEGORY_ID,VENDOR_ID,BRAND_ID,ATL_NO,DESCRIPTION,COST_PRICE,MARKUP,RETAIL_PRICE,QUANTITY,MIN_PRODUCT,RETURN_RULE,IMAGE,CREATED_DATE,IMEI_NUMBER,TAX)" +
           " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public String addCustomerQuery =
            "INSERT INTO CUSTOMER " +
            "(FIRST_NAME,LAST_NAME,PHONE_NO,EMAIL,DATEOFBIRTH,CUSTOMER_TYPE,GENDER,STREET,CITY,STATE,COUNTRY,ZIPCODE,FAX,CUSTOMER_CREATE_DATE)" +
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
            "INSERT INTO TRANSACTION(TRANSACTION_COMP_ID,TRANSACTION_DATE,TOTAL_AMOUNT,TAX_AMOUNT,DISCOUNT_AMOUNT,SUBTOTAL,TOTALQUANTITY,CUSTOMER_PHONENO,USER_ID,PAYMENT_ID_CASH,STATUS,PAID_AMOUNT_CASH,CHANGE_AMOUNT,PAYMENT_ID_CREDIT,TOTAL_AMOUNT_CREDIT,TOTAL_AMOUNT_CHECK,TRANS_CREDIT_ID,LAST_4_DIGITS) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public String addTransactionLineItem =
            "INSERT INTO TRANSACTION_LINE_ITEM " +
            "(TRANSACTION_COMP_ID," +
                    "DATE," +
                    "PRODUCT_ID," +
                    "QUANTITY," +
                    "RETAIL," +
                    "COST," +
                    "DISCOUNT," +
                    "RETAILWITHDISCOUNT," +
                    "TOTALPRODUCTPRICE)" +
            " VALUES (?,?,?,?,?,?,?,?,?)";

 public String addUserQuery
         = "INSERT INTO USER" +
         " (USERNAME,PASSWORD,USER_ROLE,USER_CREATED_DATE) " +
         "VALUES (?,?,?,?)";

    //ADD CLOSING DETAILS INTO DATABASE

    public String addOpeningDetails = "INSERT INTO CASH_REGISTER " +
            "(USER_ID_OPEN,OPEN_AMOUNT,OPEN_DATE VALUES (?,?,?)";

    public String addClosingDetails = "INSERT INTO CASH_REGISTER (USER_ID_CLOSE , REPORT_CASH , REPORT_CREDIT , REPORT_TOTAL_AMOUNT , " +
            "CLOSE_CASH , CLOSE_CREDIT , CLOSE_DATE , CLOSE_TOTAL_AMOUNT , CASH_DIFFERENCE , CREDIT_DIFFERENCE ," +
            "TOTAL_DIFFERENCE , TOTAL_BUSINESS_AMOUNT , TOTAL_TAX , TOTAL_DISCOUNT , TOTAL_PROFIT , TOTAL_MARKUP) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


 public String addTransactionPaymentDetail = "INSERT INTO TRANSACTION_PAYMENT " +
         "(TRANSACTION_ID,TRANSACTION_DATE,PAYMENT_ID,PAYMENT_AMOUNT)" +
         "VALUES (?,?,?,?)";


    //SQL QUERY FOR EDIT  INTO DATABASE

    public String editProductQuery = "UPDATE PRODUCT SET PRODUCT_NO = ?, CATEGORY_ID = ?, VENDOR_ID = ?, BRAND_ID = ?, " +
            "ATL_NO = ?, DESCRIPTION = ?, COST_PRICE = ?,  MARKUP = ?, RETAIL_PRICE = ?, QUANTITY = ?, MIN_PRODUCT = ?, RETURN_RULE = ?, " +
            "IMAGE = ?, IMEI_NUMBER = ?, TAX = ? WHERE PRODUCT_ID = ?";

    public String editCustomerQuery = "UPDATE CUSTOMER SET FIRST_NAME = ?,  LAST_NAME = ?, EMAIL = ?, DATEOFBIRTH = ?,CUSTOMER_TYPE = ?, GENDER = ?, " +
            " STREET = ?, CITY = ?, STATE = ?, COUNTRY = ?, ZIPCODE = ?, FAX = ? WHERE PHONE_NO = ?";

    public String editVendorQuery = "UPDATE VENDOR SET VENDOR_NAME = ?, COMMISION = ?, PHONENO = ?, COMPANY_NAME = ?, ADDRESS = ? WHERE VENDOR_ID = ?";

    public String editCategoryQuery = "UPDATE CATEGORY SET CATEGORY_NAME = ?, DESCRIPTION = ? WHERE CATEGORY_ID = ?";

    public String editBrandQuery = "UPDATE BRAND SET BRAND_NAME = ?, DESCRIPTION = ? WHERE BRAND_ID = ?";

    public String editUserQuery = "UPDATE USER SET PASSWORD = ?, USER_ROLE = ? WHERE USERNAME = ?";


    //SQL QUERY TO GET  DETAILS FROM DATABASE

    public String getProductDetails = "SELECT * FROM PRODUCT";

    public String getCustomerDetails = "SELECT * FROM CUSTOMER";

    public String getVendorDetails = "SELECT * FROM VENDOR";

    public String getCategoryDetails = "SELECT * FROM CATEGORY";

    public String getBrandDetails = "SELECT * FROM BRAND";

    public String getUserDetails = "SELECT * FROM USER";

    public String getTransactionDetails = "SELECT * FROM TRANSACTION WHERE TRANSACTION_DATE between ? ANd ? ";
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

    public static String getProductHistoryCount = "SELECT SUM(QUANTITY) FROM TRANSACTION_LINE_ITEM where PRODUCT_ID = ?";
   public String getProductDescription = "SELECT DESCRIPTION FROM PRODUCT WHERE PRODUCT_ID = ?";

    public String getClosingDetailsFromSystem = "SELECT sum(TOTAL_AMOUNT_CREDIT) CREDIT, sum(PAID_AMOUNT_CASH) CASH, sum(TOTAL_AMOUNT_CHECK) CHECKAMOUNT, sum(TOTAL_AMOUNT) TOTAL FROM TRANSACTION where TRANSACTION_DATE BETWEEN ? AND ? ";


    public String getDailyTransaction = "SELECT count(t.TRANSACTION_COMP_ID) NOOFTRANS ,AVG(t.TOTAL_AMOUNT) AVGTOTAL, sum(t.TOTAL_AMOUNT) TOTAL ,sum(t.PAID_AMOUNT_CASH) CASH ,sum(t.TOTAL_AMOUNT_CREDIT) CREDIT ,sum(t.TOTAL_AMOUNT_CHECK) SUMCHECK, sum(t.TAX_AMOUNT) TAX, sum(t.DISCOUNT_AMOUNT) DISCOUNT, sum((l.RETAIL - l.COST) * l.QUANTITY) PROFIT FROM TRANSACTION t, TRANSACTION_LINE_ITEM l  WHERE t.TRANSACTION_COMP_ID = l.TRANSACTION_COMP_ID AND t.TRANSACTION_DATE  BETWEEN ? AND ? ";

    public String getDailyProfit = "SELECT sum(RETAIL)-sum(COST)-SUM(DISCOUNT) FROM TRANSACTION_LINE_ITEM where DATE BETWEEN ? AND ?";


    public String addPaidOutDetails = "INSERT INTO PAIDOUT (PAIDOUT,REASON,DATE) VALUES (?,?,?)";


    public String getPaidOutDetails = "SELECT * FROM PAIDOUT WHERE DATE BETWEEN ? AND ?";


    public String getMonthlyTransDetails = "SELECT date(t.TRANSACTION_DATE) as DATE, SUM(t.PAID_AMOUNT_CASH) SUM_CASH,sum(t.TOTAL_AMOUNT_CREDIT) SUM_CREDIT,sum(t.TOTAL_AMOUNT) TOTAL,sum(t.TAX_AMOUNT)  SUM_TAX, sum(t.DISCOUNT_AMOUNT) DISCOUNT, SUM(l.COST) COST, SUM(l.RETAIL) RETAIL, SUM((l.RETAIL - l.COST) * l.QUANTITY) PROFIT,count(t.TRANSACTION_COMP_ID) NOOFTRANS FROM TRANSACTION t, TRANSACTION_LINE_ITEM l  WHERE t.TRANSACTION_COMP_ID = l.TRANSACTION_COMP_ID AND t.TRANSACTION_DATE BETWEEN ? AND ? GROUP BY date(t.TRANSACTION_DATE)";

    public String getWeeklyTransDetails = "";
    public String getProductQuantity = "SELECT QUANTITY FROM PRODUCT WHERE PRODUCT_ID = ?";
    public String updateProductQuantity = "UPDATE PRODUCT SET QUANTITY = ? WHERE PRODUCT_ID = ?";
    public String getTop50Items = "SELECT PRODUCT.PRODUCT_NO,PRODUCT.DESCRIPTION,sum(TRANSACTION_LINE_ITEM.QUANTITY) QUANTITY," +
                                  "sum(TRANSACTION_LINE_ITEM.RETAIL) RETAIL, sum(TRANSACTION_LINE_ITEM.COST) COST FROM PRODUCT JOIN " +
                                  "TRANSACTION_LINE_ITEM on TRANSACTION_LINE_ITEM.PRODUCT_ID=PRODUCT.PRODUCT_ID WHERE DATE BETWEEN " +
                                  "? AND ? GROUP BY PRODUCT.PRODUCT_NO";

    public String getYearlyTransaction = "SELECT monthname(t.TRANSACTION_DATE) AS NameOfMonth, sum(t.TOTAL_AMOUNT_CREDIT) CREDIT,sum(t.PAID_AMOUNT_CASH) CASH, SUM(t.TOTAL_AMOUNT_CHECK) CHEC, SUM(t.TAX_AMOUNT) TAX ,SUM(t.DISCOUNT_AMOUNT) DISCOUNT , SUM(t.TOTAL_AMOUNT) TOTAL,  sum(l.COST) COST, sum(l.RETAIL) RETAIL, sum((l.RETAIL - l.COST) * l.QUANTITY) PROFIT, count(t.TRANSACTION_COMP_ID) NOOFTRANS FROM TRANSACTION t, TRANSACTION_LINE_ITEM l   WHERE t.TRANSACTION_COMP_ID = l.TRANSACTION_COMP_ID AND TRANSACTION_DATE BETWEEN ? AND ? GROUP BY NameOfMonth ORDER BY field(NameOfMonth,'January','February','March','April','May','June','July','August','September','October','November','December')";

    public String getDashboardDetailsForMonths = "SELECT monthname(t.TRANSACTION_DATE) AS NameOfMonth,sum(t.TOTAL_AMOUNT_CREDIT) CREDIT,sum(t.PAID_AMOUNT_CASH) CASH,SUM(t.TOTAL_AMOUNT_CHECK) CHEC,SUM(t.TAX_AMOUNT) TAX ,SUM(t.DISCOUNT_AMOUNT) DISCOUNT , SUM(t.TOTAL_AMOUNT) TOTAL, sum(l.RETAIL - l.COST) PROFIT,count(t.TRANSACTION_COMP_ID) NOOFTRANS FROM TRANSACTION t, TRANSACTION_LINE_ITEM l  WHERE t.TRANSACTION_COMP_ID = l.TRANSACTION_COMP_ID AND TRANSACTION_DATE BETWEEN ? AND ? GROUP BY NameOfMonth ORDER BY field(NameOfMonth,'January','February','March','April','May','June','July','August','September','October','November','December')";
    public String getSalesCategoryDetails = "SELECT  c.CATEGORY_NAME as COMMON_NAME ,sum(t.COST) COST,sum(t.RETAIL) RETAIL ,sum(t.QUANTITY) QUANTITY, sum((t.RETAIL - t.COST)* t.QUANTITY) PROFIT FROM TRANSACTION_LINE_ITEM t, CATEGORY c, PRODUCT p WHERE t.PRODUCT_ID = p.PRODUCT_ID  AND c.CATEGORY_ID = p.CATEGORY_ID AND t.DATE  BETWEEN ?  AND ? group by c.CATEGORY_NAME";
    public String getHourlyTransactions = "SELECT Hour(DATE) AS HOUR,sum(t.TOTAL_AMOUNT_CREDIT) CREDIT,sum(t.PAID_AMOUNT_CASH) CASH, SUM(t.TOTAL_AMOUNT_CHECK) CHEC, SUM(t.TAX_AMOUNT) TAX ,SUM(t.DISCOUNT_AMOUNT) DISCOUNT , SUM(t.TOTAL_AMOUNT) TOTAL,   sum(l.COST) COST, sum(l.RETAIL) RETAIL, sum((l.RETAIL - l.COST) * l.QUANTITY) PROFIT, count(t.TRANSACTION_COMP_ID) NOOFTRANS FROM TRANSACTION t, TRANSACTION_LINE_ITEM l WHERE t.TRANSACTION_COMP_ID = l.TRANSACTION_COMP_ID AND TRANSACTION_DATE BETWEEN ? AND ? GROUP BY hour";
    public String getSalesVendorDetails = "SELECT  v.VENDOR_NAME as COMMON_NAME,sum(t.COST) COST,sum(t.RETAIL) RETAIL ,sum(t.QUANTITY) QUANTITY, sum((t.RETAIL - t.COST)* t.QUANTITY) PROFIT FROM TRANSACTION_LINE_ITEM t, VENDOR v, PRODUCT p WHERE t.PRODUCT_ID = p.PRODUCT_ID  AND v.VENDOR_ID = p.VENDOR_ID AND t.DATE  BETWEEN ?  AND ? group by v.VENDOR_NAME";
    public String getSalesBrandDetails = "SELECT  b.BRAND_NAME as COMMON_NAME,sum(t.COST) COST,sum(t.RETAIL) RETAIL ,sum(t.QUANTITY) QUANTITY, sum((t.RETAIL - t.COST)* t.QUANTITY) PROFIT FROM TRANSACTION_LINE_ITEM t, BRAND b, PRODUCT p WHERE t.PRODUCT_ID = p.PRODUCT_ID  AND b.BRAND_ID = p.BRAND_ID AND t.DATE  BETWEEN ?  AND ? group by b.BRAND_NAME";
    public String getSalesProductDetails = "SELECT p.DESCRIPTION as COMMON_NAME,sum(t.COST) COST,sum(t.RETAIL) RETAIL,sum(t.QUANTITY) QUANTITY, sum((t.RETAIL- t.COST) * t.QUANTITY) PROFIT  FROM TRANSACTION_LINE_ITEM t, PRODUCT p WHERE t.PRODUCT_ID = p.PRODUCT_ID AND t.DATE BETWEEN ? AND ? group by p.DESCRIPTION";


    public String editPageSetUpDetails = "UPDATE GET_PAGE_SETUP_DETAILS SET TAX = ?, STORE_ADDRESS = ?, STORE_LOGO = ? WHERE GET_PAGE_SETUP_DETAILS_ID = ?";
    public String getPageSetUpDetails = "SELECT * FROM GET_PAGE_SETUP_DETAILS";

    public String editPaidOutDetails = "UPDATE PAIDOUT SET PAIDOUT = ?, REASON = ? WHERE PAIDOUT_ID = ? ";
    public String getInventoryByCategory = "SELECT c.CATEGORY_NAME as COMMON_NAME, count(p.PRODUCT_ID) NOOFPRODUCTS, sum((p.COST_PRICE)* p.QUANTITY) COST, sum((p.RETAIL_PRICE)* p.QUANTITY) RETAIL, avg(p.MARKUP) MARGIN FROM PRODUCT p, CATEGORY c WHERE p.CATEGORY_ID = c.CATEGORY_ID GROUP BY c.CATEGORY_NAME";
    public String getInventoryByVendor = "SELECT v.VENDOR_NAME as COMMON_NAME, count(p.PRODUCT_ID) NOOFPRODUCTS, sum((p.COST_PRICE)* p.QUANTITY) COST, sum((p.RETAIL_PRICE)* p.QUANTITY) RETAIL, avg(p.MARKUP) MARGIN FROM PRODUCT p, VENDOR v WHERE p.VENDOR_ID = v.VENDOR_ID GROUP BY  v.VENDOR_NAME";
    public String getInventoryByBrand = "SELECT b.BRAND_NAME as COMMON_NAME, count(p.PRODUCT_ID) NOOFPRODUCTS, sum((p.COST_PRICE)* p.QUANTITY) COST, sum((p.RETAIL_PRICE)* p.QUANTITY) RETAIL, avg(p.MARKUP) MARGIN FROM PRODUCT p, BRAND b WHERE p.BRAND_ID = b.BRAND_ID GROUP BY b.BRAND_NAME";
    public String getSalesByUser = "SELECT  c.FIRST_NAME as COMMON_NAME ,sum(t.COST) COST,sum(t.RETAIL) RETAIL ,sum(t.QUANTITY) QUANTITY, sum((t.RETAIL - t.COST)* t.QUANTITY) PROFIT  FROM TRANSACTION_LINE_ITEM t, CUSTOMER c, PRODUCT p, TRANSACTION l WHERE t.PRODUCT_ID = p.PRODUCT_ID  AND c.PHONE_NO = l.CUSTOMER_PHONENO AND t.DATE BETWEEN ? AND ? group by c.FIRST_NAME";
    public String getSalesbyCustomer = "SELECT  u.USERNAME as COMMON_NAME ,sum(t.COST) COST,sum(t.RETAIL) RETAIL ,sum(t.QUANTITY) QUANTITY, sum((t.RETAIL - t.COST)* t.QUANTITY) PROFIT FROM TRANSACTION_LINE_ITEM t, USER u, PRODUCT p, TRANSACTION l WHERE t.PRODUCT_ID = p.PRODUCT_ID  AND u.USER_ID = l.USER_ID AND t.DATE BETWEEN ? AND ? group by u.USERNAME";
    public String getCustomerBalance = "SELECT BALANCE FROM CUSTOMER WHERE PHONE_NO = ?";
    public String addBlanceToCustomerProfile = "UPDATE CUSTOMER SET BALANCE = ? WHERE PHONE_NO = ? ";
}
