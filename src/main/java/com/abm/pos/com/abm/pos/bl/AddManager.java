package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.util.SQLQueries;
import com.abm.pos.com.abm.pos.dto.AddCustomerDto;
import com.abm.pos.com.abm.pos.dto.AddProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by asp5045 on 5/9/16.
 */
public class AddManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQuery;

    public  void addProductToDB(AddProductDto productDto) {

        try
        {
            jdbcTemplate.update(sqlQuery.addProductQuery,productDto.getProductId(),productDto.getVendorId(),productDto.getDescription(),productDto.getCategoryId()
                    ,productDto.getUpcCode(),productDto.getAltNo(),productDto.getCostPrice(),productDto.getRetailPrice(),productDto.getQuantity(),productDto.getMinProductQuantity()
                    ,productDto.getReturnRule(),productDto.getImage());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

    public  void addCustomerToDB(AddCustomerDto customerDto) {

        try
        {
            jdbcTemplate.update(sqlQuery.addProductQuery,customerDto.getFirstName(),customerDto.getLastName(),customerDto.getPhoneNo(),customerDto.getEmail(),
                    customerDto.getDateOfBirth(),customerDto.getGender(),customerDto.getAptNo(),customerDto.getStreet(),customerDto.getCity(),customerDto.getState(),
                    customerDto.getCountry(),customerDto.getZipcode(),customerDto.getFax(),customerDto.getImage());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
}
