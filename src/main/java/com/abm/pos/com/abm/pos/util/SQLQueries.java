package com.abm.pos.com.abm.pos.util;

/**
 * Created by asp5045 on 5/9/16.
 */
public class SQLQueries {

   public String addProductQuery =

           "INSERT INTO PRODUCT" +
           " (PRODUCT_NO,VENDOR_ID,DESCRIPTION,CATEGORY_ID,UPC_CODE,ATL_NO,COST_PRICE,RETAIL_PRICE,QUANTITY,MIN_PRODUCT_QUANTITY,RETURN_RULE,IMAGE)" +
           " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

    public String addCustomerQuery =
            "INSERT INTO CUSTOMER " +
            "(FIRST_NAME,LAST_NAME,PHONE_NO,EMAIL,DATEOFBIRTH,GENDER,APT_NO,STREET,CITY,STATE,COUNTRY,ZIPCODE,FAX,IMAGE)" +
            " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


}
