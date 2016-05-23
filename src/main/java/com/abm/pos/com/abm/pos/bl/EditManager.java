package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.*;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by asp5045 on 5/17/16.
 */
@Component
public class EditManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQuery;

    public  void editProductToDB(AddProductDto productDto) {

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
                    productDto.getRetailPrice(),
                    productDto.getQuantity(),
                    productDto.getMinProductQuantity(),
                    productDto.getReturnRule(),
                    productDto.getImage(),
                    productDto.getCreatedDate());
            System.out.println("Product Edited Successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

    public  void editCustomerToDB(AddCustomerDto customerDto) {

        try
        {
            jdbcTemplate.update(sqlQuery.editCustomerQuery,
                    customerDto.getFirstName(),
                    customerDto.getLastName(),
                    customerDto.getPhoneNo(),
                    customerDto.getEmail(),
                    customerDto.getDateOfBirth(),
                    customerDto.getGender(),
                    customerDto.getAptNo(),
                    customerDto.getStreet(),
                    customerDto.getCity(),
                    customerDto.getState(),
                    customerDto.getCountry(),
                    customerDto.getZipcode(),
                    customerDto.getFax(),
                    customerDto.getCustomerCreatedDate());
            System.out.println("Customer Edited Successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
    public void editVendorToDB(AddVendorDto vendorDto) {

        try
        {
            jdbcTemplate.update(sqlQuery.editVendorQuery,
                    vendorDto.getVendorName(),
                    vendorDto.getDescription());
            System.out.println("Vendor Edited Successfully");

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


    public void editCategoryToDB(AddCategoryDto categoryDto) {
        try
        {
            jdbcTemplate.update(sqlQuery.editCategoryQueryy,
                    categoryDto.getCategoryName(),
                    categoryDto.getDescription());
            System.out.println("Category Edited Successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void editBrandToDB(AddBrandDto addBrandDto) {

        try
        {
            jdbcTemplate.update(sqlQuery.editBrandQuery,
                    addBrandDto.getBrandName(),
                    addBrandDto.getBrandDescription());
            System.out.println("Brand Edited Successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
