package com.abm.pos.com.abm.pos.util;

import org.springframework.stereotype.Component;

/**
 * Created by asp5045 on 5/9/16.
 */
@Component
public class SQLQueries {



    //SQL QUERY FOR ADD INTO DATABASE

   public String addProductQuery =

           "INSERT INTO product" +
           " (" +
                   "PRODUCT_NO," +
                   "CATEGORY_ID," +
                   "VENDOR_ID," +
                   "BRAND_ID," +
                   "ATL_NO," +
                   "DESCRIPTION," +
                   "COST_PRICE," +
                   "MARKUP," +
                   "RETAIL_PRICE," +
                   "QUANTITY," +
                   "MIN_PRODUCT," +
                   "RETURN_RULE," +
                   "IMAGE," +
                   "CREATED_DATE," +
                   //"IMEI_NUMBER," +
                   "TAX, " +
                   "IS_RELATED_PRODUCT)" +
           " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public String addProductQueryForPhone = "INSERT INTO product" +
            " (" +
            "PRODUCT_NO," +
            "CATEGORY_ID," +
            "VENDOR_ID," +
            "BRAND_ID," +
            "ATL_NO," +
            "DESCRIPTION," +
            "TAX, " +
            "QUANTITY," +
            "IS_RELATED_PRODUCT)" +
            " VALUES(?,?,?,?,?,?,?,?,?)";

    public String addRelatedProduct = "INSERT INTO RELATED_PRODUCTS (PRODUCT_NO, RELATED_PRODUCT_NO) VALUES (?,?)";


    public String addCustomerQuery =
            "INSERT INTO customer " +
            "(" +
                    "FIRST_NAME," +
                    "LAST_NAME," +
                    "PHONE_NO," +
                    "EMAIL," +
                    "TAX_ID," +
                    "DATEOFBIRTH," +
                    "CUSTOMER_TYPE," +
                    "GENDER," +
                    "STREET," +
                    "CITY," +
                    "STATE," +
                    "COUNTRY," +
                    "ZIPCODE," +
                    "FAX," +
                    "CUSTOMER_CREATE_DATE," +
                    "COMPANY_NAME)" +
            " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public String addMultyCustomerQuery =
            "INSERT INTO customer " +
                    "(" +
                    "FIRST_NAME," +
                    "LAST_NAME," +
                    "PHONE_NO," +
                    "EMAIL," +
                    "TAX_ID,COMPANY_NAME)" +
                    " VALUES(?,?,?,?,?,?)";

    public String addVendorQuery =
            "INSERT INTO vendor " +
            "(" +
                    "VENDOR_NAME," +
                    "COMMISION,PHONENO," +
                    "COMPANY_NAME,ADDRESS) " +
            "VALUES(?,?,?,?,?)";

    public String addCategoryQuery =
            "INSERT INTO category " +
                    "(CATEGORY_NAME," +
                    "DESCRIPTION) " +
                    "VALUES(?,?)";
    public String addBrandQuery =
            "INSERT INTO brand " +
            "(" +
            "BRAND_NAME," +
            "DESCRIPTION)" +
            "VALUES(?,?)";

    public String addModelQuery =
              "INSERT INTO PRODUCT_MODEL " +
                      "(" +
                      "NAME," +
                      "DESCRIPTION)" +
                      "VALUES(?,?)";

    //ADD TRANSACTION INTO DATABASE

    public String addTransaction =
            "INSERT INTO transaction(TRANSACTION_COMP_ID," +
                    "TRANSACTION_DATE," +
                    "TOTAL_AMOUNT," +
                    "TAX_AMOUNT," +
                    "DISCOUNT_AMOUNT," +
                    "SUBTOTAL," +
                    "TOTALQUANTITY," +
                    "CUSTOMER_PHONENO," +
                    "USER_ID," +
                    "STATUS," +
                    "PAID_AMOUNT_CASH," +
                    "CHANGE_AMOUNT," +
                    "TOTAL_AMOUNT_CREDIT," +
                    "TOTAL_AMOUNT_CHECK," +
                    "TRANS_CREDIT_ID," +
                    "LAST_4_DIGITS, " +
                    "PREVIOUS_BALANCE, " +
                    "BALANCE, " +
                    "PAID_AMOUNT_DEBIT," +
                    "RECEIPT_NOTE," +
                    "TRANSACTION_NOTE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public String addTransactionLineItem =
            "INSERT INTO transaction_line_item " +
            "(TRANSACTION_COMP_ID," +
                    "DATE," +
                    "TRANSACTION_STATUS," +
                    "PRODUCT_NO," +
                    "QUANTITY," +
                    "RETAIL," +
                    "COST," +
                    "DISCOUNT," +
                    "DISCOUNT_PERCENTAGE," +
                    "RETAILWITHDISCOUNT," +
                    "TOTALPRODUCTPRICE," +
                    "TOTAL_PRODUCT_PRICE_WITH_TAX, " +
                    "IMEI_NO)" +
            " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

 public String addUserQuery =
          "INSERT INTO user" +
         " (" +
                  "USERNAME," +
                  "PASSWORD," +
                  "USER_ROLE," +
                  "USER_CREATED_DATE," +
                  "HORLYRATE," +
                  "USER_COMMISSION_PERCENTAGE) " +
         "VALUES (?,?,?,?,?,?)";

    //ADD CLOSING DETAILS INTO DATABASE

    public String addOpeningDetails =
            "INSERT INTO CASH_REGISTER " +
            "(" +
                    "USER_ID_OPEN," +
                    "OPEN_AMOUNT," +
                    "OPEN_DATE VALUES (?,?,?)";

    public String addClosingDetails =
            "INSERT INTO CASH_REGISTER " +
            "(" +
                    "USER_ID_CLOSE, " +
                    "REPORT_CASH, " +
                    "REPORT_CREDIT ," +
                    "REPORT_CHECK," +
                    "REPORT_DEBIT, " +
                    "REPORT_TOTAL_AMOUNT, " +
                    "CLOSE_CASH, " +
                    "CLOSE_CREDIT," +
                    "CLOSE_CHECK," +
                    "CLOSE_DEBIT, " +
                    "CLOSE_DATE, " +
                    "CLOSE_TOTAL_AMOUNT, " +
                    "CREDIT_DIFFERENCE, " +
                    "CASH_DIFFERENCE," +
                    "CHECK_DIFFERENCE," +
                    "DEBIT_DIFFERENCE," +
                    "TOTAL_DIFFERENCE, " +
                    "TOTAL_BUSINESS_AMOUNT, " +
                    "TOTAL_TAX, " +
                    "TOTAL_DISCOUNT, " +
                    "TOTAL_PROFIT, " +
                    "TOTAL_MARKUP," +
                    "BANKDEPOSIT, " +
                    "COMISSION," +
                    "CUSTOMER_BALANCE,CASH_IN_HAND,BUSINESS_TYPE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


 public String addTransactionPaymentDetail =
         "INSERT INTO TRANSACTION_PAYMENT " +
         "" +
                     "(TRANSACTION_ID," +
                     "TRANSACTION_DATE," +
                    "PAYMENT_ID," +
                    "PAYMENT_AMOUNT)" +
                    "VALUES (?,?,?,?)";


    public String addUserClockIn =
            "INSERT INTO USER_CLOCK_IN " +
                    "(USERNAME," +
                    "CLOCK_IN," +
                    "CLOCK_DATE, USER_ID ) VALUES (?,?,?,?)";



    public String addPaidOutDetails =
            "INSERT INTO PAIDOUT " +
                    "(PAIDOUT1," +
                    "PAIDOUT2," +
                    "PAIDOUT3," +
                    "REASON1," +
                    "REASON2," +
                    "REASON3," +
                    "DATE) VALUES (?,?,?,?,?,?,?)";

    public String addPhoneDetailsAsProduct =
            "INSERT INTO phone " +
                    "(PRODUCT_NO,IMEI_NO,COST,RETAIL,MARKUP,LAST_UPDATED_TIME) " +
                    "VALUES (?,?,?,?,?,?)";


    //SQL QUERY FOR EDIT  INTO DATABASE

    public String editProductQuery =
            "UPDATE product SET " +
                    "PRODUCT_NO = ?, " +
                    "CATEGORY_ID = ?, " +
                    "VENDOR_ID = ?, " +
                    "BRAND_ID = ?, " +
                    "ATL_NO = ?, " +
                    "DESCRIPTION = ?, " +
                    "COST_PRICE = ?,  " +
                    "MARKUP = ?, " +
                    "RETAIL_PRICE = ?, " +
                    "QUANTITY = ?, " +
                    "MIN_PRODUCT = ?, " +
                    "RETURN_RULE = ?, " +
                    "IMAGE = ?, " +
                    //"IMEI_NUMBER = ?, " +
                    "TAX = ?, " +
                    "IS_RELATED_PRODUCT = ? " +
                    "WHERE PRODUCT_ID = ? AND PRODUCT_NO = ?";

    public String editCustomerQuery = "UPDATE customer" +
            " SET FIRST_NAME = ?,  " +
            "LAST_NAME = ?, " +
            "PHONE_NO = ?, " +
            "EMAIL = ?, " +
            "TAX_ID = ?,  " +
            "DATEOFBIRTH = ?," +
            "CUSTOMER_TYPE = ?, " +
            "GENDER = ?, " +
            " STREET = ?," +
            " CITY = ?, " +
            "STATE = ?, " +
            "COUNTRY = ?, " +
            "ZIPCODE = ?, " +
            "FAX = ?, " +
            "COMPANY_NAME = ? " +
            "WHERE CUSTOMER_ID = ? AND PHONE_NO = ?";

    public String editVendorQuery =
            "UPDATE VENDOR SET " +
                    "VENDOR_NAME = ?, " +
                    "COMMISION = ?, " +
                    "PHONENO = ?, " +
                    "COMPANY_NAME = ?, " +
                    "ADDRESS = ? " +
                    "WHERE VENDOR_ID = ?";

    public String editCategoryQuery =
            "UPDATE CATEGORY SET " +
                    "CATEGORY_NAME = ?," +
                    " DESCRIPTION = ? " +
                    "WHERE CATEGORY_ID = ?";

    public String editBrandQuery =
            "UPDATE BRAND SET " +
                    "BRAND_NAME = ?, " +
                    "DESCRIPTION = ? " +
                    "WHERE BRAND_ID = ?";

    public String editModelQuery =
            "UPDATE PRODUCT_MODEL SET " +
                    "NAME = ?, " +
                    "DESCRIPTION = ? " +
                    "WHERE ID = ? ";


    public String editUserWithPasswordQuery =
            "UPDATE USER SET " +
                    "PASSWORD = ?, " +
                    "USER_ROLE = ?, " +
                    "HORLYRATE = ?, " +
                    "USER_COMMISSION_PERCENTAGE = ? " +
                    "WHERE USERNAME = ?";

    public String editPageSetUpDetails =
            "UPDATE GET_PAGE_SETUP_DETAILS SET " +
                    "TAX = ?, " +
                    "STORE_ADDRESS = ?, " +
                    "STORE_LOGO = ?, " +
                    "FOOTER_RECEIPT = ?, " +
                    "STORE_EMAIL = ?, RECEIPT_TYPE = ? " +
                    "WHERE GET_PAGE_SETUP_DETAILS_ID = ?";

    public String editPaidOutDetails =
            "UPDATE PAIDOUT SET " +
                    "PAIDOUT1 = ?, " +
                    "PAIDOUT2 = ? , " +
                    "PAIDOUT3 = ?, " +
                    "REASON1 = ?, " +
                    "REASON2 = ?, " +
                    "REASON3 = ?, " +
                    "DATE = ? " +
                    "WHERE PAIDOUT_ID = ? ";

    //Can not update customer's phone no thats why i have removed edit customers phone number
    public String editTransactionStatus = "UPDATE TRANSACTION" +
            "SET TRANSACTION_DATE = ?," +
            "SET TOTAL_AMOUNT = ? ," +
            "SET TAX_AMOUNT = ?," +
            "SET DISCOUNT_AMOUNT = ?," +
            "SET SUBTOTAL = ?," +
            "SET TOTALQUANTITY = ?," +
            "SET USER_ID = ?," +
            "SET PAYMENT_ID_CASH = ?," +
            "SET STATUS = ?," +
            "SET PAID_AMOUNT_CASH = ?," +
            "SET CHANGE_AMOUNT = ?," +
            "SET PAYMENT_ID_CREDIT = ?," +
            "SET TOTAL_AMOUNT_CREDIT = ?," +
            "SET TOTAL_AMOUNT_CHECK = ?," +
            "SET TRANS_CREDIT_ID = ?," +
            "SET LAST_4_DIGITS = ?" +
            "WHERE TRANSACTION_COMP_ID = ?";

    public String updateUserClockInDetails =
            "UPDATE USER_CLOCK_IN SET " +
                    "CLOCK_OUT = ?, " +
                    "NOOFHOURS = ? " +
                    "WHERE USER_CLOCK_IN_ID = ?";

    public String updateLineItemDetailsStatus =
            "UPDATE TRANSACTION_LINE_ITEM SET " +
                    "TRANSACTION_STATUS = 'r' " +
                    "WHERE TRANSACTION_LINE_ITEM_ID = ?";

    public String updateProductQuantity =
            "UPDATE PRODUCT SET " +
                    "QUANTITY = ? " +
                    "WHERE PRODUCT_ID = ?";

    public String editTransactionLineItem =
            "UPDATE TRANSACTION_LINE_ITEM SET " +
                    "DATE = ?, " +
                    "PRODUCT_NO = ?, " +
                    "QUANTITY = ?, " +
                    "RETAIL = ?, " +
                    "COST = ?, " +
                    "DISCOUNT = ?, " +
                    "RETAILWITHDISCOUNT = ?, " +
                    "TOTALPRODUCTPRICE = ? " +
                    "WHERE TRANSACTION_COMP_ID = ?";

    public String editClosingDetails =
            "UPDATE CASH_REGISTER SET " +
                    "USER_ID_CLOSE = ? ," +
                    " REPORT_CASH = ? , " +
                    "REPORT_CREDIT = ? ," +
                    " REPORT_CHECK = ?," +
                    "REPORT_DEBIT = ?," +
                    "REPORT_TOTAL_AMOUNT = ?, " +
                    "CLOSE_CASH = ? , " +
                    "CLOSE_CREDIT = ?," +
                    "CLOSE_CHECK = ?," +
                    "CLOSE_DEBIT = ?, " +
                    "CLOSE_DATE = ?, " +
                    "CLOSE_TOTAL_AMOUNT = ?, " +
                    "CREDIT_DIFFERENCE = ?," +
                    "CASH_DIFFERENCE = ?, " +
                    "CHECK_DIFFERENCE = ?, " +
                    "DEBIT_DIFFERENCE = ?, " +
                    "TOTAL_DIFFERENCE = ? , " +
                    "TOTAL_BUSINESS_AMOUNT = ?, " +
                    "TOTAL_TAX = ?, " +
                    "TOTAL_DISCOUNT = ?, " +
                    "TOTAL_PROFIT = ?, " +
                    "TOTAL_MARKUP = ?, " +
                    "BANKDEPOSIT = ?, " +
                    "COMISSION = ?," +
                    "CUSTOMER_BALANCE = ?, CASH_IN_HAND = ? " +
                    "WHERE REGISTER_ID = ? ";


    //SQL QUERY TO GET  DETAILS FROM DATABASE

    public String getProductDetails = "SELECT * FROM product ";

    public String getModelDetails = "SELECT * FROM PRODUCT_MODEL ";

   // ORDER BY DESCRIPTION

    public String getCustomerDetails = "SELECT * FROM customer ORDER BY FIRST_NAME";

    public String getVendorDetails = "SELECT * FROM vendor ORDER BY VENDOR_NAME";

    public String getCategoryDetails = "SELECT * FROM category ORDER BY CATEGORY_NAME";

    public String getBrandDetails = "SELECT * FROM brand ORDER BY BRAND_NAME";

    public String getUserDetails = "SELECT * FROM user ORDER BY USERNAME";

    public String getTransactionDetails =
            "SELECT * FROM transaction " +
                    "WHERE TRANSACTION_DATE " +
                    "BETWEEN ? ANd ? " +
                    "order by TRANSACTION_DATE";


//This query to get discount from lineitem table and then i am gonna apand it with main transaction discount.
    public String getDiscountFromLineItem =
                "SELECT SUM(DISCOUNT)" +
                " FROM transaction_line_item " +
                "WHERE TRANSACTION_COMP_ID = ? ";

//Need to find the way when status in not equal to 'w'
    public String getDiscountFromLineItemwithDate =
            "  SELECT SUM(DISCOUNT) " +
                    "            FROM transaction_line_item " +
                    "            WHERE DATE " +
                    "            BETWEEN ? AND ? AND TRANSACTION_STATUS <> 'w' ";
//Need to change it according to wholesale
    public String getDiscountFromLineItemwithDateForWholesale =
            "  SELECT SUM(DISCOUNT) " +
                    "            FROM transaction_line_item " +
                    "            WHERE DATE " +
                    "            BETWEEN ? AND ? AND TRANSACTION_STATUS = 'w' ";

    public String getDiscountFromLineItemwithDateForWholeSale =
            "  SELECT SUM(DISCOUNT) " +
                    "            FROM TRANSACTION_LINE_ITEM " +
                    "            WHERE DATE " +
                    "            BETWEEN ? AND ? AND TRANSACTION_STATUS = 'w' ";

    public String getTransactionLineItemDetails = "SELECT * FROM transaction_line_item WHERE TRANSACTION_COMP_ID = ?";


    //SQL QUERY TO DELETE FROM DATABASE

    public String deleteProduct = "DELETE FROM product WHERE PRODUCT_ID = ?";

    public String deleteCustomer = "DELETE FROM customer WHERE CUSTOMER_ID = ?";

    public String deleteVendor = "DELETE FROM vendor WHERE VENDOR_ID = ?";

    public String deleteCategory = "DELETE FROM category WHERE CATEGORY_ID = ?";

    public String deleteBrand = "DELETE FROM brand WHERE BRAND_ID = ?";

    public String deleteModel = "DELETE FROM PRODUCT_MODEL WHERE ID = ?";



    public String getNoOfProductsForBrand = "SELECT COUNT(*) from product where BRAND_ID = ?";


    public String getNoOfProductsForCategory = "SELECT COUNT(*) from product where CATEGORY_ID = ?";


    public String getNoOfProductsForVendor = "SELECT COUNT(*) from product where VENDOR_ID = ?";

    public String getNoOfProductsForModel = "SELECT COUNT(*) from product where MODEL_ID = ?";


    public String getCategoryName =  "SELECT CATEGORY_NAME FROM category WHERE CATEGORY_ID = ? ";


    public String getVendorName = "SELECT VENDOR_NAME FROM vendor WHERE VENDOR_ID = ?";


    public String getBrandName = "SELECT BRAND_NAME FROM brand WHERE BRAND_ID = ?";


    public String getUsernameFromUser = "SELECT USERNAME FROM user WHERE USER_ID = ?";


    public static String getProductHistory = "SELECT t.DATE, p.PRODUCT_NO, p.DESCRIPTION, t.QUANTITY,t.IMEI_NO, \n" +
            "(select sum(QUANTITY) from TRANSACTION_LINE_ITEM where DATE BETWEEN ? AND  ? AND PRODUCT_NO = ?) as TOTALQUANTITY,\n" +
            "t.RETAIL, t.COST, t.DISCOUNT,p.CATEGORY_ID\n" +
            "FROM transaction_line_item t , product p \n" +
            "WHERE t.PRODUCT_NO = p.PRODUCT_NO \n" +
            "AND t.PRODUCT_NO = ?" +
            "AND t.DATE BETWEEN ? AND ? ";

    public String getProductDescription = "SELECT DESCRIPTION FROM product WHERE PRODUCT_NO = ?";

    public String getClosingDetailsFromSystem = "SELECT * FROM CASH_REGISTER WHERE CLOSE_DATE BETWEEN ? AND ? AND BUSINESS_TYPE = 1 ";

    //Need to change this query for wholesale--> i think BUSINESS_TYPE flag = 0 condition will work
    public String getClosingDetailsFromSystemForWholesale = "SELECT * FROM CASH_REGISTER WHERE CLOSE_DATE BETWEEN ? AND ? AND BUSINESS_TYPE = 0";



    //Done
    public String getDailyTransaction = "SELECT count(TRANSACTION_COMP_ID) NOOFTRANS ,\n" +
            "AVG(TOTAL_AMOUNT) AVGTOTAL, \n" +
            "sum(TOTAL_AMOUNT) TOTAL, \n" +
            "SUM(BALANCE) BALANCE, \n" +
            "sum(PAID_AMOUNT_CASH) CASH ,\n" +
            "sum(TOTAL_AMOUNT_CREDIT) CREDIT ,\n" +
            "sum(TOTAL_AMOUNT_CHECK) SUMCHECK,\n" +
            "sum(PAID_AMOUNT_DEBIT) DEBIT,\n" +
            "sum(TAX_AMOUNT) TAX, \n" +
            "sum(DISCOUNT_AMOUNT) DISCOUNT \n" +
            "FROM transaction \n" +
            "WHERE TRANSACTION_DATE  BETWEEN ? AND ? AND status <> 'w' ";


    public String getPaidOutDetails = "SELECT * FROM PAIDOUT WHERE DATE BETWEEN ? AND ?";

    public String getMonthlyTransDetails = "      SELECT DATE(TRANSACTION_DATE) AS DATE,\n" +
            "            sum(TOTAL_AMOUNT_CREDIT) CREDIT, \n" +
            "            sum(PAID_AMOUNT_CASH) CASH, \n" +
            "            SUM(TOTAL_AMOUNT_CHECK) CHEC,  \n" +
            "            SUM(PAID_AMOUNT_DEBIT) DEBIT, \n" +
            "            SUM(TAX_AMOUNT) TAX , \n" +
            "            SUM(DISCOUNT_AMOUNT + DISCOUNT) DISCOUNT , \n" +
            "            SUM(TOTAL_AMOUNT_CREDIT + PAID_AMOUNT_CASH + TOTAL_AMOUNT_CHECK + PAID_AMOUNT_DEBIT) TOTAL,  \n" +
            "            SUM(BALANCE) BALANCE,  \n" +
            "            count(A.TRANSACTION_COMP_ID) NOOFTRANS, \n" +
            "            SUM(PROFIT) PROFIT \n" +
            "\t\t\tFROM TRANSACTION A, \n" +
            "\t\t\t\t(SELECT TRANSACTION_COMP_ID,  \n" +
            "\t\t\t\tSUM(CASE WHEN Y.CATEGORY_ID <> 1 AND Y.CATEGORY_ID <> 2 AND TRANSACTION_STATUS = 'c'\n" +
            "                THEN ((X.RETAIL-X.COST-X.DISCOUNT/X.QUANTITY)) * X.QUANTITY \n" +
            "\t\t\t\tWHEN Y.CATEGORY_ID <> 1 AND Y.CATEGORY_ID <> 2 AND (TRANSACTION_STATUS = 'r' or TRANSACTION_STATUS = 'p' )\n" +
            "                THEN ((X.RETAIL-X.COST-X.DISCOUNT/-X.QUANTITY)) * -X.QUANTITY\n" +
            "                ELSE 0.0 END) AS PROFIT, \n" +
            "\t\t\tSUM(DISCOUNT) AS DISCOUNT \n" +
            "\t\t\tFROM TRANSACTION_LINE_ITEM X, product Y \n" +
            "\t\t\tWHERE X.PRODUCT_NO = Y.PRODUCT_NO  \n" +
            "\t\t\tAND DATE BETWEEN ? AND ?\n" +
            "\t\t\tGroup by TRANSACTION_COMP_ID)B \n" +
            "            WHERE A.TRANSACTION_COMP_ID = B.TRANSACTION_COMP_ID \n" +
            "            AND TRANSACTION_DATE BETWEEN ? AND ? AND A.STATUS <> 'w' \n" + //here added condition <> to not to count for wholesale
            "            GROUP BY DATE";

    public String getWeeklyTransDetails = "";

    public String getProductQuantity = "SELECT QUANTITY FROM PRODUCT WHERE PRODUCT_NO = ?";

    public String getTop50Items = "\t\tSELECT p.DESCRIPTION as COMMON_NAME, \n" +
            "                        sum(t.TOTAL_PRODUCT_PRICE_WITH_TAX) SALESTOTAL,  \n" +
            "                        sum(t.QUANTITY) QUANTITY,  \n" +
            "                        sum(TOTAL_PRODUCT_PRICE_WITH_TAX - TOTALPRODUCTPRICE) TAX,  \n" +
            "                        sum(t.DISCOUNT) DISCOUNT,  \n" +
            "                        avg(t.TOTALPRODUCTPRICE) AVGTOTALPRODUCTPRICE,  \n" +
            "               sum(" +
            "               CASE WHEN t.TRANSACTION_STATUS= 'c' THEN (t.RETAIL-t.COST-t.DISCOUNT/t.QUANTITY) * t.QUANTITY\n" +
            "               ELSE (t.RETAIL-t.COST-t.DISCOUNT/-t.QUANTITY) * -t.QUANTITY END )PROFIT  \n" +
            "                        FROM TRANSACTION_LINE_ITEM t, PRODUCT p  \n" +
            "                        WHERE t.PRODUCT_NO = p.PRODUCT_NO   \n" +
            "                        AND t.DATE  \n" +
            "                        BETWEEN ? AND ? \n" +
            "                        group by p.DESCRIPTION  \n" +
            "                        order by SALESTOTAL desc limit 50";

    public String getYearlyTransaction = "SELECT monthname(TRANSACTION_DATE) AS NameOfMonth,\n" +
            " \n" +
            "\t\t\tsum(TOTAL_AMOUNT_CREDIT) CREDIT,  \n" +
            "            sum(PAID_AMOUNT_CASH) CASH,  \n" +
            "\t\t\tSUM(TOTAL_AMOUNT_CHECK) CHEC,  \n" +
            "\t\t\tSUM(PAID_AMOUNT_DEBIT) DEBIT, \n" +
            "\t\t\tSUM(TAX_AMOUNT) TAX ,  \n" +
            "\t\t\tSUM(DISCOUNT_AMOUNT + DISCOUNT) DISCOUNT , \n" +
            "\t\t\tSUM(TOTAL_AMOUNT) TOTAL,  \n" +
            "            SUM(BALANCE) BALANCE,  \n" +
            "\t\t\tcount(A.TRANSACTION_COMP_ID) NOOFTRANS, \n" +
            "\t\t\tSUM(PROFIT) PROFIT \n" +
            "\t\t\tFROM TRANSACTION A, \n" +
            "\t\t\t\t(SELECT TRANSACTION_COMP_ID,  \n" +
            "\t\t\t\tSUM(CASE WHEN Y.CATEGORY_ID <> 1 AND Y.CATEGORY_ID <> 2 AND TRANSACTION_STATUS = 'c'\n" +
            "                THEN ((X.RETAIL-X.COST-X.DISCOUNT/X.QUANTITY)) * X.QUANTITY \n" +
            "\t\t\t\tWHEN Y.CATEGORY_ID <> 1 AND Y.CATEGORY_ID <> 2 AND (TRANSACTION_STATUS = 'r' or TRANSACTION_STATUS = 'p' )\n" +
            "                THEN ((X.RETAIL-X.COST-X.DISCOUNT/-X.QUANTITY)) * -X.QUANTITY\n" +
            "                ELSE 0.0 END) AS PROFIT, \n" +
            "\t\t\tSUM(DISCOUNT) AS DISCOUNT \n" +
            "\t\t\tFROM TRANSACTION_LINE_ITEM X, product Y \n" +
            "\t\t\tWHERE X.PRODUCT_NO = Y.PRODUCT_NO  \n" +
            "\t\t\tAND DATE BETWEEN ? AND ?\n" +
            "\t\t\tGroup by TRANSACTION_COMP_ID)B \n" +
            "\t\t\tWHERE A.TRANSACTION_COMP_ID = B.TRANSACTION_COMP_ID  \n" +
            "\t\t\tAND TRANSACTION_DATE BETWEEN ? AND ? AND A.STATUS <> 'w' \n" +//Here added condition <> to not to count the whole sale transaction
            "\t\t\tGROUP BY NameOfMonth  \n" +
            "\t\t\tORDER BY field(NameOfMonth,'January','February','March','April','May','June','July','August','September','October','November','December') ;\n" +
            "            ";

//    public String getDashboardDetailsForMonths = "SELECT monthname(TRANSACTION_DATE) AS NameOfMonth,\n" +
//            "sum(TOTAL_AMOUNT_CREDIT) CREDIT,  \n" +
//            "sum(PAID_AMOUNT_CASH) CASH,  \n" +
//            "SUM(TOTAL_AMOUNT_CHECK) CHEC,  \n" +
//            "SUM(TAX_AMOUNT) TAX ,  \n" +
//            "SUM(DISCOUNT_AMOUNT + DISCOUNT) DISCOUNT , \n" +
//            "SUM(TOTAL_AMOUNT) TOTAL,  \n" +
//            "SUM(BALANCE) BALANCE,  \n" +
//            "count(A.TRANSACTION_COMP_ID) NOOFTRANS, \n" +
//            "SUM(PROFIT) PROFIT \n" +
//            "FROM TRAN
//
// SACTION A, \n" +
//            "(SELECT TRANSACTION_COMP_ID,  \n" +
//            "SUM(CASE WHEN Y.CATEGORY_ID <> 1 AND Y.CATEGORY_ID <> 2 THEN ((X.RETAIL-X.COST-X.DISCOUNT)/X.QUANTITY) * X.QUANTITY ELSE 0.0 END) AS PROFIT, \n" +
//            "SUM(DISCOUNT) AS DISCOUNT \n" +
//            "FROM TRANSACTION_LINE_ITEM X, product Y \n" +
//            "WHERE X.PRODUCT_NO = Y.PRODUCT_NO  \n" +
//            "AND DATE BETWEEN ? AND ?  \n" +
//            "AND TRANSACTION_STATUS = 'c' Group by TRANSACTION_COMP_ID)B \n" +
//            "WHERE A.TRANSACTION_COMP_ID = B.TRANSACTION_COMP_ID  \n" +
//            "AND TRANSACTION_DATE BETWEEN ? AND ?   \n" +
//            "AND STATUS = 'c'  \n" +
//            "GROUP BY NameOfMonth  \n" +
//            "ORDER BY field(NameOfMonth,'January','February','March','April','May','June','July','August','September','October','November','December') ";


    public String getHourlyTransactions = "      SELECT Hour(TRANSACTION_DATE) AS HOUR,\n" +
            "            sum(TOTAL_AMOUNT_CREDIT) CREDIT, \n" +
            "            sum(PAID_AMOUNT_CASH) CASH, \n" +
            "            SUM(TOTAL_AMOUNT_CHECK) CHEC,  \n" +
            "            SUM(PAID_AMOUNT_DEBIT) DEBIT, \n" +
            "            SUM(TAX_AMOUNT) TAX , \n" +
            "            SUM(DISCOUNT_AMOUNT + DISCOUNT) DISCOUNT , \n" +
            "            SUM(TOTAL_AMOUNT_CREDIT + PAID_AMOUNT_CASH + TOTAL_AMOUNT_CHECK + PAID_AMOUNT_DEBIT) TOTAL,  \n" +
            "            SUM(BALANCE) BALANCE,  \n" +
            "            count(A.TRANSACTION_COMP_ID) NOOFTRANS, \n" +
            "            SUM(PROFIT) PROFIT \n" +
            "            FROM TRANSACTION A, \n" +
            "            (SELECT TRANSACTION_COMP_ID,  \n" +
            "             SUM(CASE WHEN Y.CATEGORY_ID <> 1 AND Y.CATEGORY_ID <> 2 AND TRANSACTION_STATUS = 'c'\n" +
            "                THEN ((X.RETAIL-X.COST-X.DISCOUNT/X.QUANTITY)) * X.QUANTITY \n" +
            "             WHEN Y.CATEGORY_ID <> 1 AND Y.CATEGORY_ID <> 2 AND (TRANSACTION_STATUS = 'r' or TRANSACTION_STATUS = 'p' )\n" +
            "                THEN ((X.RETAIL-X.COST-X.DISCOUNT/-X.QUANTITY)) * -X.QUANTITY\n" +
            "                ELSE 0.0 END) AS PROFIT, \n" +
            "\t\t\tSUM(DISCOUNT) AS DISCOUNT \n" +
            "\t\t\tFROM TRANSACTION_LINE_ITEM X, product Y \n" +
            "\t\t\tWHERE X.PRODUCT_NO = Y.PRODUCT_NO  \n" +
            "\t\t\tAND DATE BETWEEN ? AND ?\n" +
            "\t\t\tGroup by TRANSACTION_COMP_ID)B \n" +
            "            WHERE A.TRANSACTION_COMP_ID = B.TRANSACTION_COMP_ID \n" +
            "            AND TRANSACTION_DATE BETWEEN ? AND ?\n" +
            "            GROUP BY hour ";

    //done
    public String getSalesCategoryDetails = " SELECT  c.CATEGORY_NAME as COMMON_NAME ,  \n" +
            " \n" +
            "                        sum(t.TOTAL_PRODUCT_PRICE_WITH_TAX) SALESTOTAL,   \n" +
            "                        sum(t.QUANTITY) QUANTITY,   \n" +
            "                        sum(TOTAL_PRODUCT_PRICE_WITH_TAX - TOTALPRODUCTPRICE) TAX,  \n" +
            "                        sum( \n" +
            "                        CASE \n" +
            "                        WHEN t.TRANSACTION_STATUS= 'c' THEN (t.RETAIL-t.COST-t.DISCOUNT/t.QUANTITY) * t.QUANTITY\n" +
            "                        WHEN t.TRANSACTION_STATUS= 'r' THEN (t.RETAIL-t.COST-t.DISCOUNT/-t.QUANTITY) * -t.QUANTITY\n" +
            "                        WHEN t.TRANSACTION_STATUS= 'p' THEN (t.RETAIL-t.COST-t.DISCOUNT/-t.QUANTITY) * -t.QUANTITY\n" +
            "                        ELSE 0.0\n" +
            "                        END )PROFIT,   \n" +
            "                        sum(t.DISCOUNT) DISCOUNT,   \n" +
            "                        avg(t.TOTALPRODUCTPRICE) AVGTOTALPRODUCTPRICE   \n" +
            "                        FROM TRANSACTION_LINE_ITEM t, CATEGORY c, PRODUCT p   \n" +
            "                        WHERE t.PRODUCT_NO = p.PRODUCT_NO  AND c.CATEGORY_ID = p.CATEGORY_ID AND t.DATE   \n" +
            "                        BETWEEN ? AND ? AND t.TRANSACTION_STATUS <> 'w' group by c.CATEGORY_NAME";

    public String getSalesVendorDetails = " SELECT v.VENDOR_NAME as COMMON_NAME,  \n" +
            "            sum(t.TOTAL_PRODUCT_PRICE_WITH_TAX) SALESTOTAL,  \n" +
            "            sum(t.QUANTITY) QUANTITY,  \n" +
            "            sum(TOTAL_PRODUCT_PRICE_WITH_TAX - TOTALPRODUCTPRICE) TAX, \n" +
            "            sum(t.DISCOUNT) DISCOUNT,  \n" +
            "            avg(t.TOTALPRODUCTPRICE) AVGTOTALPRODUCTPRICE,  \n" +
            "            sum(\n" +
            "            CASE WHEN t.TRANSACTION_STATUS= 'c' THEN (t.RETAIL-t.COST-t.DISCOUNT/t.QUANTITY) * t.QUANTITY\n" +
            "            ELSE (t.RETAIL-t.COST-t.DISCOUNT/-t.QUANTITY) * -t.QUANTITY END )PROFIT   \n" +
            "            FROM TRANSACTION_LINE_ITEM t, VENDOR v, PRODUCT p  \n" +
            "            WHERE t.PRODUCT_NO = p.PRODUCT_NO   \n" +
            "            AND v.VENDOR_ID = p.VENDOR_ID  \n" +
            "            AND t.DATE   \n" +
            "            BETWEEN ? AND ?  AND t.TRANSACTION_STATUS <> 'w' " +
            "            group by v.VENDOR_NAME ";

    public String getSalesBrandDetails = "   SELECT  b.BRAND_NAME as COMMON_NAME,  \n" +
            "            sum(t.TOTAL_PRODUCT_PRICE_WITH_TAX) SALESTOTAL,  \n" +
            "            sum(t.QUANTITY) QUANTITY,  \n" +
            "            sum(TOTAL_PRODUCT_PRICE_WITH_TAX - TOTALPRODUCTPRICE) TAX, \n" +
            "            sum(t.DISCOUNT) DISCOUNT,  \n" +
            "            avg(t.TOTALPRODUCTPRICE) AVGTOTALPRODUCTPRICE,  \n" +
            "            sum(\n" +
            "            CASE WHEN t.TRANSACTION_STATUS= 'c' THEN (t.RETAIL-t.COST-t.DISCOUNT/t.QUANTITY) * t.QUANTITY\n" +
            "            ELSE (t.RETAIL-t.COST-t.DISCOUNT/-t.QUANTITY) * -t.QUANTITY END )PROFIT  \n" +
            "            FROM TRANSACTION_LINE_ITEM t, BRAND b, PRODUCT p  \n" +
            "            WHERE t.PRODUCT_NO = p.PRODUCT_NO  \n" +
            "            AND b.BRAND_ID = p.BRAND_ID  \n" +
            "            AND t.DATE  \n" +
            "            BETWEEN ? AND ?  AND t.TRANSACTION_STATUS <> 'w' " +
            "            group by b.BRAND_NAME ";

    public String getSalesProductDetails = "       SELECT p.DESCRIPTION as COMMON_NAME,  \n" +
            "            sum(t.TOTAL_PRODUCT_PRICE_WITH_TAX) SALESTOTAL,  \n" +
            "            sum(t.QUANTITY) QUANTITY,  \n" +
            "            sum(TOTAL_PRODUCT_PRICE_WITH_TAX - TOTALPRODUCTPRICE) TAX, \n" +
            "            sum(t.DISCOUNT) DISCOUNT,  \n" +
            "            avg(t.TOTALPRODUCTPRICE) AVGTOTALPRODUCTPRICE,  \n" +
            "\t\t\tsum(\n" +
            "            CASE WHEN t.TRANSACTION_STATUS= 'c' THEN (t.RETAIL-t.COST-t.DISCOUNT/t.QUANTITY) * t.QUANTITY\n" +
            "            ELSE (t.RETAIL-t.COST-t.DISCOUNT/-t.QUANTITY) * -t.QUANTITY END )PROFIT \n" +
            "            FROM TRANSACTION_LINE_ITEM t, PRODUCT p  \n" +
            "            WHERE t.PRODUCT_NO = p.PRODUCT_NO  \n" +
            "            AND t.DATE  \n" +
            "            BETWEEN ? AND ? AND t.TRANSACTION_STATUS <> 'w' \n" +
            "            group by p.DESCRIPTION" ;

    //Need to fix THIS QUERY
    public String getSalesbyCustomer = "            SELECT  c.FIRST_NAME as COMMON_NAME , \n" +
            "\t\t\t\t\t\tsum(TOTAL_PRODUCT_PRICE_WITH_TAX) SALESTOTAL,  \n" +
            "                        sum(t.QUANTITY) QUANTITY,  \n" +
            "                        sum(TOTAL_PRODUCT_PRICE_WITH_TAX - TOTALPRODUCTPRICE) TAX,  \n" +
            "                        sum(t.DISCOUNT) DISCOUNT,  \n" +
            "                        avg(t.TOTALPRODUCTPRICE) AVGTOTALPRODUCTPRICE,  \n" +
            "                        sum(\n" +
            "            CASE WHEN t.TRANSACTION_STATUS= 'c' THEN (t.RETAIL-t.COST-t.DISCOUNT/t.QUANTITY) * t.QUANTITY\n" +
            "            ELSE (t.RETAIL-t.COST-t.DISCOUNT/-t.QUANTITY) * -t.QUANTITY END )PROFIT \n" +
            "                        FROM TRANSACTION_LINE_ITEM t, CUSTOMER c,  \n" +
            "                        TRANSACTION l WHERE t.TRANSACTION_COMP_ID = l.TRANSACTION_COMP_ID  \n" +
            "                        AND c.PHONE_NO = l.CUSTOMER_PHONENO  \n" +
            "                        AND t.DATE BETWEEN ? AND ? AND t.TRANSACTION_STATUS <> 'w' " +
            "                        group by c.FIRST_NAME";

    //NEED TI FIX THIS QUERY TOO..
    public String  getSalesByUser = "      SELECT  u.USERNAME as COMMON_NAME, \n" +
            "                        Sum(t.TOTAL_PRODUCT_PRICE_WITH_TAX) SALESTOTAL, \n" +
            "                        sum(t.QUANTITY) QUANTITY, \n" +
            "                        sum(t.TOTAL_PRODUCT_PRICE_WITH_TAX - t.TOTALPRODUCTPRICE) TAX, \n" +
            "                        sum(\n" +
            "                        CASE WHEN t.TRANSACTION_STATUS= 'c' THEN (t.RETAIL-t.COST-t.DISCOUNT/t.QUANTITY) * t.QUANTITY\n" +
            "                        ELSE (t.RETAIL-t.COST-t.DISCOUNT/-t.QUANTITY) * -t.QUANTITY END )PROFIT, \n" +
            "                        sum(t.DISCOUNT) DISCOUNT, \n" +
            "                        avg(t.TOTALPRODUCTPRICE) AVGTOTALPRODUCTPRICE \n" +
            "                        FROM TRANSACTION_LINE_ITEM t, USER u, TRANSACTION l  \n" +
            "                        WHERE u.USER_ID = l.USER_ID AND t.TRANSACTION_COMP_ID = l.TRANSACTION_COMP_ID  \n" +
            "                        AND t.DATE  \n" +
            "                        BETWEEN ? AND ? AND t.TRANSACTION_STATUS <> 'w' group by u.USERNAME";

    public String getPageSetUpDetails = "SELECT * FROM GET_PAGE_SETUP_DETAILS";

    public String getInventoryByCategory = "SELECT c.CATEGORY_NAME as COMMON_NAME, count(p.PRODUCT_NO) NOOFPRODUCTS, sum((p.COST_PRICE)* p.QUANTITY) COST, sum((p.RETAIL_PRICE)* p.QUANTITY) RETAIL, avg(p.MARKUP) MARGIN FROM PRODUCT p, CATEGORY c WHERE p.CATEGORY_ID = c.CATEGORY_ID GROUP BY c.CATEGORY_NAME";

    public String getInventoryByVendor = "SELECT v.VENDOR_NAME as COMMON_NAME, count(p.PRODUCT_NO) NOOFPRODUCTS, sum((p.COST_PRICE)* p.QUANTITY) COST, sum((p.RETAIL_PRICE)* p.QUANTITY) RETAIL, avg(p.MARKUP) MARGIN FROM PRODUCT p, VENDOR v WHERE p.VENDOR_ID = v.VENDOR_ID GROUP BY  v.VENDOR_NAME";

    public String getInventoryByBrand = "SELECT b.BRAND_NAME as COMMON_NAME, count(p.PRODUCT_NO) NOOFPRODUCTS, sum((p.COST_PRICE)* p.QUANTITY) COST, sum((p.RETAIL_PRICE)* p.QUANTITY) RETAIL, avg(p.MARKUP) MARGIN FROM PRODUCT p, BRAND b WHERE p.BRAND_ID = b.BRAND_ID GROUP BY b.BRAND_NAME";




    public String getCustomerBalance = "SELECT BALANCE FROM CUSTOMER WHERE PHONE_NO = ?";

    public String updateBalanceToCustomerProfile = "UPDATE CUSTOMER SET BALANCE = ?, BALANCE_LAST_UPDATE_DATE = ? WHERE PHONE_NO = ? ";

    public String getTransactionDetailsForReceiptWithoutCustomer = "SELECT * FROM TRANSACTION WHERE TRANSACTION_COMP_ID = ?";

    public String getCustomerPhone = "SELECT CUSTOMER_PHONENO FROM TRANSACTION WHERE TRANSACTION_COMP_ID = ?";

    public String getCustomerDetailsForReceipt = "SELECT * FROM CUSTOMER WHERE PHONE_NO = ?";


//Done
    public String getClosingDetailsFromSystemFromTransaction =
            "SELECT SUM(PAID_AMOUNT_CASH) CASH, \n" +
                    "            SUM(TOTAL_AMOUNT_CREDIT) CREDIT, \n" +
                    "            SUM(TOTAL_AMOUNT_CHECK) CHECKAMOUNT, \n" +
                    "            SUM(PAID_AMOUNT_DEBIT) DEBIT, \n" +
                    "            SUM(PAID_AMOUNT_CASH + TOTAL_AMOUNT_CREDIT + TOTAL_AMOUNT_CHECK + PAID_AMOUNT_DEBIT) TOTAL, \n" +
                    "            SUM(BALANCE - PREVIOUS_BALANCE) BALANCE , \n" +
                    "            SUM(TAX_AMOUNT) TAX, \n" +
                    "            SUM(DISCOUNT_AMOUNT) DISCOUNT  \n" +
                    "            FROM TRANSACTION  \n" +
                    "            WHERE TRANSACTION_DATE BETWEEN ? AND ? AND STATUS <> 'w' ";


    public String getClosingDetailsFromSystemFromTransactionForWholeSale =
            "SELECT SUM(PAID_AMOUNT_CASH) CASH, \n" +
                    "            SUM(TOTAL_AMOUNT_CREDIT) CREDIT, \n" +
                    "            SUM(TOTAL_AMOUNT_CHECK) CHECKAMOUNT, \n" +
                    "            SUM(PAID_AMOUNT_DEBIT) DEBIT, \n" +
                    "            SUM(PAID_AMOUNT_CASH + TOTAL_AMOUNT_CREDIT + TOTAL_AMOUNT_CHECK + PAID_AMOUNT_DEBIT) TOTAL, \n" +
                    "            SUM(BALANCE - PREVIOUS_BALANCE) BALANCE , \n" +
                    "            SUM(TAX_AMOUNT) TAX, \n" +
                    "            SUM(DISCOUNT_AMOUNT) DISCOUNT  \n" +
                    "            FROM TRANSACTION  \n" +
                    "            WHERE TRANSACTION_DATE BETWEEN ? AND ? AND STATUS = 'w' ";


    //I need to for last 12 months but here i am not putting between date condition i need to fox it.
    public String getCustomersLast12MonthSpend = "SELECT sum(TOTAL_AMOUNT) TOTAL FROM transaction where CUSTOMER_PHONENO = ?";

    //Done
    public String getPrpfitForCloseRegister = " \tSELECT\n" +
            "            SUM(PROFIT) PROFIT \n" +
            "\t\t\tFROM TRANSACTION A, \n" +
            "\t\t\t(SELECT TRANSACTION_COMP_ID,  \n" +
            "\t\t\tSUM(CASE WHEN Y.CATEGORY_ID <> 1 AND Y.CATEGORY_ID <> 2 AND TRANSACTION_STATUS = 'c'\n" +
            "\t\t\tTHEN ((X.RETAIL-X.COST-X.DISCOUNT/X.QUANTITY)) * X.QUANTITY \n" +
            "\t\t\tWHEN Y.CATEGORY_ID <> 1 AND Y.CATEGORY_ID <> 2 AND (TRANSACTION_STATUS = 'r' or TRANSACTION_STATUS = 'p' )\n" +
            "\t\t\tTHEN ((X.RETAIL-X.COST-X.DISCOUNT/-X.QUANTITY)) * -X.QUANTITY\n" +
            "\t\t\tELSE 0.0 END) AS PROFIT, \n" +
            "\t\t\tSUM(DISCOUNT) AS DISCOUNT \n" +
            "\t\t\tFROM transaction_line_item X, product Y \n" +
            "\t\t\tWHERE X.PRODUCT_NO = Y.PRODUCT_NO  \n" +
            "\t\t\tAND DATE BETWEEN ? AND ? \n" +
            "\t\t\tGroup by TRANSACTION_COMP_ID)B \n" +
            "\t\t\tWHERE A.TRANSACTION_COMP_ID = B.TRANSACTION_COMP_ID  \n" +
            "\t\t\tAND TRANSACTION_DATE BETWEEN ? AND ? AND A.STATUS <> 'w' ";

//Need to change it for wholesale
    public String getPrpfitForCloseRegisterForWholesale = " \tSELECT\n" +
            "            SUM(PROFIT) PROFIT \n" +
            "\t\t\tFROM TRANSACTION A, \n" +
            "\t\t\t(SELECT TRANSACTION_COMP_ID,  \n" +
            "\t\t\tSUM(CASE WHEN Y.CATEGORY_ID <> 1 AND Y.CATEGORY_ID <> 2 AND TRANSACTION_STATUS = 'w' " +
            "\t\t\tTHEN ((X.RETAIL-X.COST-X.DISCOUNT/X.QUANTITY)) * X.QUANTITY \n" +
            "\t\t\tWHEN Y.CATEGORY_ID <> 1 AND Y.CATEGORY_ID <> 2 AND (TRANSACTION_STATUS = 'r' or TRANSACTION_STATUS = 'p' )\n" +
            "\t\t\tTHEN ((X.RETAIL-X.COST-X.DISCOUNT/-X.QUANTITY)) * -X.QUANTITY\n" +
            "\t\t\tELSE 0.0 END) AS PROFIT, \n" +
            "\t\t\tSUM(DISCOUNT) AS DISCOUNT \n" +
            "\t\t\tFROM transaction_line_item X, product Y \n" +
            "\t\t\tWHERE X.PRODUCT_NO = Y.PRODUCT_NO  \n" +
            "\t\t\tAND DATE BETWEEN ? AND ? \n" +
            "\t\t\tGroup by TRANSACTION_COMP_ID)B \n" +
            "\t\t\tWHERE A.TRANSACTION_COMP_ID = B.TRANSACTION_COMP_ID  \n" +
            "\t\t\tAND TRANSACTION_DATE BETWEEN ? AND ? AND A.STATUS = 'w' ";

    public String getUserClockInDetails = "SELECT * FROM USER_CLOCK_IN WHERE USERNAME = ? AND CLOCK_DATE = ?";


    public String productNoAndAltNoDTOs = "SELECT PRODUCT_NO,ATL_NO FROM product";


    public String getLastTransactionId = "SELECT max(CAST(TRANSACTION_COMP_ID AS SIGNED)) FROM transaction";


    public String getRelatedProducts = "SELECT  * from product a, RELATED_PRODUCTS b where a.PRODUCT_NO = b.RELATED_PRODUCT_NO and b.PRODUCT_NO = ? ";


    public String getLastProductNo = "SELECT max(CAST(PRODUCT_NO AS SIGNED)) FROM product";

    public String getFirstName = "SELECT FIRST_NAME FROM customer WHERE PHONE_NO = ?";

    public String getLastName = "SELECT LAST_NAME FROM customer WHERE PHONE_NO = ?";

    public String changeRelatedProdcutStatus = "UPDATE product SET IS_RELATED_PRODUCT = ? WHERE PRODUCT_NO = ?";

    public String deleteRelatedProduct = "DELETE FROM RELATED_PRODUCTS WHERE RELATED_PRODUCT_ID = ?";


    public String getLowStockProductDetails = "SELECT * FROM product WHERE QUANTITY <= MIN_PRODUCT or QUANTITY<=0 ";


    public String getLicenceKey = "SELECT * FROM PRODUCT_LICENCE";

    public String deleteLicenseKey = "DELETE FROM PRODUCT_LICENCE WHERE LICENCE_ID = ?";


   // public String addImeiNo = "INSERT INTO PHONE (PRODUCT_NO,IMEI_NO,COST,RETAIL,MARKUP,LAST_UPDATED_TIME) VALUES (?,?,?,?,?,?)";


    public String editPhoneDetailsAsProduct = "UPDATE PHONE SET PRODUCT_NO = ?, IMEI_NO = ?, COST = ?, RETAIL = ?,MARKUP = ?, LAST_UPDATED_TIME = ? WHERE ID = ?";


    public String getPhoneDetails = "SELECT  a.PRODUCT_ID, a.PRODUCT_NO, a.CATEGORY_ID, a.VENDOR_ID, a.BRAND_ID, a.ATL_NO, a.DESCRIPTION,a.TAX,a.IS_RELATED_PRODUCT, a.QUANTITY, a.MIN_PRODUCT, a.RETURN_RULE, a.IMAGE, a.CREATED_DATE,  b.COST, b.RETAIL, b.MARKUP,b.ID, b.IMEI_NO, b.LAST_UPDATED_TIME from product a, PHONE b where a.PRODUCT_NO = b.PRODUCT_NO and a.PRODUCT_NO = ?";


    public String getSumOfPaidOut = "SELECT SUM(PAIDOUT1 + PAIDOUT2 + PAIDOUT3) PAIDOUT FROM PAIDOUT WHERE DATE BETWEEN ? AND ? ";


    public String getCustomerBalanceByDate = "SELECT sum(BALANCE) CUSTOMERBALANCE FROM customer WHERE BALANCE_LAST_UPDATE_DATE BETWEEN ? AND ? ";

    //Need to change for whole sale
    public String getCustomerBalanceByDateForWholesale = "SELECT sum(BALANCE) CUSTOMERBALANCE FROM customer WHERE BALANCE_LAST_UPDATE_DATE BETWEEN ? AND ? ";


    public String EditPhoneDetailsFromPhoneTable = "UPDATE PHONE SET PRODUCT_NO = ?, IMEI_NO = ?, COST = ?, RETAIL = ?, MARKUP = ?, LAST_UPDATED_TIME = ? WHERE ID = ?";


    public String getPhoneStockFromProductTable = "SELECT QUANTITY FROM product WHERE PRODUCT_NO = ?";


    public String getPhoneStockFromProductTableForDelete = "SELECT QUANTITY FROM product WHERE PRODUCT_ID = ?";


    public String addPhoneStockToProductTable = "UPDATE PRODUCT SET QUANTITY = ? WHERE PRODUCT_ID = ?";


    public String getProductId = "SELECT PRODUCT_ID FROM PRODUCT WHERE PRODUCT_NO = ?";


    public String deleteImeiDetailsFromPhone = "DELETE FROM PHONE WHERE ID = ? ";


    public String getPreviousBalance = "SELECT PREVIOUS_BALANCE FROM transaction WHERE TRANSACTION_COMP_ID = ?";


    public String getCustomerPhoneNo = "SELECT CUSTOMER_PHONENO FROM transaction WHERE TRANSACTION_COMP_ID = ?";


    public String updateBlanceToCustomerProfileWithoutDate = "UPDATE customer SET BALANCE = ? WHERE PHONE_NO = ?";


    public String getUserClockInForSetup ="SELECT * " +
            "FROM user_clock_in C  " +
            "WHERE c.CLOCK_DATE between ? AND ? \n" +
            "AND CAST(c.CLOCK_DATE AS DATE) \n" +
            "AND C.USER_ID = ?";

//This working query just need to code change and good to go.

//    SELECT c.CLOCK_DATE,c.USERNAME,c.CLOCK_IN,c.CLOCK_OUT, c.NOOFHOURS,
//    sum((t.RETAIL-t.COST-t.DISCOUNT/t.QUANTITY) * t.QUANTITY) USERPROFIT
//    FROM transaction_line_item t, transaction l , user_clock_in c
//    WHERE t.TRANSACTION_COMP_ID = l.TRANSACTION_COMP_ID
//    AND c.USER_ID = 2
//    AND c.USER_ID = l.USER_ID
//    AND l.TRANSACTION_DATE between '2016-01-01 00:00:00' AND '2016-12-31 23:59:59'
//    AND C.CLOCK_DATE = CAST(l.TRANSACTION_DATE AS DATE)
//    GROUP BY c.CLOCK_DATE,
//    c.USERNAME, c.CLOCK_IN, c.CLOCK_OUT, c.NOOFHOURS;

    //Commenting this query casue it not working to get commitssion.
//            "\tSELECT c.CLOCK_DATE,c.USERNAME,c.CLOCK_IN,c.CLOCK_OUT, c.NOOFHOURS, c.HORLYRATE,c.USER_COMMISSION, c.TOTAL, c.USER_CLOCK_IN_ID, \n" +
//            "\tsum((t.RETAIL-t.COST-t.DISCOUNT/t.QUANTITY) * t.QUANTITY) USERPROFIT \n" +
//            "\tFROM transaction_line_item t, transaction l , user_clock_in C \n" +
//            "\tWHERE t.TRANSACTION_COMP_ID = l.TRANSACTION_COMP_ID \n" +
//            "\tAND C.USER_ID = l.USER_ID \n" +
//            "\tAND l.TRANSACTION_DATE between ? AND ? \n" +
//            "\tAND C.CLOCK_DATE = CAST(l.TRANSACTION_DATE AS DATE) \n" +
//            "\tAND l.USER_ID = ? \n" +
//            "\tGROUP BY c.CLOCK_DATE, \n" +
//            "\tc.USERNAME, c.CLOCK_IN, c.CLOCK_OUT, c.NOOFHOURS, c.HORLYRATE, c.USER_COMMISSION, c.TOTAL, c.USER_CLOCK_IN_ID";

    public String editClockInDetails = "UPDATE USER_CLOCK_IN SET" +
            " CLOCK_IN = ?, " +
            " CLOCK_OUT = ?, " +
            " NOOFHOURS = ?, " +
            " HORLYRATE = ?, " +
            " USER_COMMISSION = ?, " +
            " TOTAL = ?" +
            " WHERE USER_CLOCK_IN_ID = ?";

    public String getUserCommissionPercentage = "SELECT USER_COMMISSION_PERCENTAGE FROM user WHERE USERNAME = ?";

    public String editTransactionNote = "UPDATE TRANSACTION SET RECEIPT_NOTE = ?, TRANSACTION_NOTE = ? WHERE TRANSACTION_COMP_ID = ?";

    public String getCommissionFromCashRegister = "SELECT COMISSION FROM cash_register WHERE CLOSE_DATE BETWEEN ? AND ? ";
    public String getTransactionIds = "SELECT TRANSACTION_COMP_ID FROM TRANSACTION WHERE TRANSACTION_DATE BETWEEN ? AND ? ";
    public String editUserWithOutPasswordQuery = "UPDATE USER SET " +
            "USER_ROLE = ?, " +
            "HORLYRATE = ?, " +
            "USER_COMMISSION_PERCENTAGE = ? " +
            "WHERE USERNAME = ? ";


}
