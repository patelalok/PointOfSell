package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.CustomerDto;
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
public class CustomerManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQuery;

    public  void addCustomerToDB(CustomerDto customerDto) {

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

    public  void editCustomerToDB(CustomerDto customerDto) {

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
                    customerDto.getFax());


            System.out.println("Customer Edited Successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

    public void getCustomerDetailsFromDB(String phoneNo) {

        try
        {
            jdbcTemplate.query(sqlQuery.getCustomerDetails,new CustomerMapper());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

    private static final class CustomerMapper implements RowMapper<CustomerDto>
    {

        @Override
        public CustomerDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            CustomerDto customer = new CustomerDto();

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




    public void deleteCustomerToDB(String phoneNo) {

    }
}
