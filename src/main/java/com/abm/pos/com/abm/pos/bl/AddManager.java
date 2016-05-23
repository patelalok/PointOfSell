package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.*;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by asp5045 on 5/9/16.
 */
@Component
public class AddManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQuery;

    public  void addProductToDB(AddProductDto productDto) {

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
                    productDto.getRetailPrice(),
                    productDto.getQuantity(),
                    productDto.getMinProductQuantity(),
                    productDto.getReturnRule(),
                    productDto.getImage(),
                    productDto.getCreatedDate());
            System.out.println("Product Added Successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

    public  void addCustomerToDB(AddCustomerDto customerDto) {

        try
        {
            jdbcTemplate.update(sqlQuery.addCustomerQuery,
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
            System.out.println("Customer Added Successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
    public void addVendorToDB(AddVendorDto vendorDto) {

        try
        {
            jdbcTemplate.update(sqlQuery.addVendorQuery,
                    vendorDto.getVendorName(),
                    vendorDto.getDescription());
            System.out.println("Vendor Added Successfully");

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


    public void addCategoryToDB(AddCategoryDto categoryDto) {
        try
        {
            jdbcTemplate.update(sqlQuery.addCategoryQuery,
                    categoryDto.getCategoryName(),
                    categoryDto.getDescription());
            System.out.println("Category Added Successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void addBrandToDB(AddBrandDto addBrandDto) {
        try
        {
            jdbcTemplate.update(sqlQuery.addBrandQuery,
                    addBrandDto.getBrandName(),
                    addBrandDto.getBrandDescription());
            System.out.println("Brand Added Successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
