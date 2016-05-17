package com.abm.pos.com.abm.pos.util;

import org.springframework.stereotype.Component;

/**
 * Created by asp5045 on 5/9/16.
 */
@Component
public class SQLQueries {

    //SQL QUERY FOR ADD.

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
                    "(BRAND_NAME,BRAND_DESCRIPTION) " +
                    "VALUES(?,?)";

    //SQL QUERY FOR EDIT

    public String editProductQuery = "UPDATE PRODUCT SET PRODUCT_NO = ?, CATEGORY_ID = ?, VENDOR_ID = ?, BRAND_ID = ?, ATL_NO = ?, DESCRIPTION = ?, " +
            "COST_PRICE = ?, RETAIL_PRICE = ?, QUANTITY = ?, MIN_PRODUCT = ?, RETURN_RULE = ?, IMAGE = ?, CREATED_DATE = ?";

    public String editCustomerQuery = "UPDATE CUSTOMER SET FIRST_NAME = ?,  LAST_NAME = ?, PHONE_NO = ?, EMAIL = ?, DATEOFBIRTH = ?, GENDER = ?, APT_NO = ?," +
            " STREET = ?, CITY = ?, STATE = ?, COUNTRY = ?, ZIPCODE = ?, FAX = ?, CUSTOMER_CREATE_DATE = ?";

    public String editVendorQuery = "";

    public String editCategoryQueryy = "";

    public String editBrandQuery = "";

}
