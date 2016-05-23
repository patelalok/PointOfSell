package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.AddCustomerDto;
import com.abm.pos.com.abm.pos.dto.AddProductDto;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by asp5045 on 5/18/16.
 */
@Component
public class CustomerHandlerManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQueries;


    public void getCustomerDetailsFromDB(String phoneNo) {

        try
        {
            jdbcTemplate.query(sqlQueries.getCustomerDetails,new CustomerMapper());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

    private static final class CustomerMapper implements RowMapper<AddCustomerDto>
    {

        @Override
        public AddCustomerDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            AddCustomerDto customer = new AddCustomerDto();

            customer.setFirstName(rs.getString("FIRST_NAME"));
            customer.setLastName(rs.getString("LAST_NAME"));
            customer.setPhoneNo(rs.getString("PHONE_NO"));
            customer.setEmail(rs.getString("EMAIL"));
            customer.setDateOfBirth(rs.getString("DATEOFBIRTH"));
            customer.setGender(rs.getString("GENDER"));
            customer.setAptNo(rs.getString("APT_NO"));
            customer.setStreet(rs.getString("STREET"));
            customer.setCity(rs.getString("CITY"));
            customer.setState(rs.getString("STATE"));
            customer.setCountry(rs.getString("COUNTRY"));
            customer.setZipcode(rs.getString("ZIPCODE"));
            customer.setFax(rs.getString("FAX"));
            customer.setCustomerCreatedDate(rs.getString("CUSTOMER_CREATE_DATE"));

            return customer;
        }
    }
}
