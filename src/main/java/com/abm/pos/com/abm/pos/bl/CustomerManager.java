package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.CustomerDto;
import com.abm.pos.com.abm.pos.dto.CustomerResponse;
import com.abm.pos.com.abm.pos.dto.MultyAddProductDto;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by asp5045 on 5/18/16.
 */
@Service
public class CustomerManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQuery;

    public  void addCustomerToDB(CustomerDto customerDto) {

        try
        {
            jdbcTemplate.update(sqlQuery.addCustomerQuery,
                    customerDto.getOnlyFirstName(),
                    customerDto.getLastName(),
                    customerDto.getPhoneNo(),
                    customerDto.getEmail(),
                    customerDto.getTaxId(),
                    customerDto.getDateOfBirth(),
                    customerDto.getCustomerType(),
                    customerDto.getGender(),
                    customerDto.getStreet(),
                    customerDto.getCity(),
                    customerDto.getState(),
                    customerDto.getCountry(),
                    customerDto.getZipcode(),
                    customerDto.getFax(),
                    customerDto.getCustomerCreatedDate(),
                    customerDto.getCompanyName());
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
                    customerDto.getTaxId(),
                    customerDto.getDateOfBirth(),
                    customerDto.getCustomerType(),
                    customerDto.getGender(),
                    customerDto.getStreet(),
                    customerDto.getCity(),
                    customerDto.getState(),
                    customerDto.getCountry(),
                    customerDto.getZipcode(),
                    customerDto.getFax(),
                    customerDto.getCompanyName(),
                    customerDto.getCustomerId(),
                    customerDto.getOldPhoneNo());


            System.out.println("Customer Edited Successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

    public List<CustomerDto> getCustomerDetailsFromDB() {

        List<CustomerDto> customerDto = new ArrayList<>();

        try
        {
            customerDto = jdbcTemplate.query(sqlQuery.getCustomerDetails,new CustomerMapper());
            System.out.println("Send Customer Details Successfully");
        }

        catch (Exception e)
        {
            System.out.println(e);
        }

        return customerDto;
    }




    private final class CustomerMapper implements RowMapper<CustomerDto>
    {

        @Override
        public CustomerDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            CustomerDto customer = new CustomerDto();

            customer.setCustomerId(rs.getInt("CUSTOMER_ID"));
            customer.setOnlyFirstName(rs.getString("FIRST_NAME"));
            customer.setFirstName(rs.getString("FIRST_NAME") + "  " +rs.getString("LAST_NAME"));
            customer.setLastName(rs.getString("LAST_NAME"));
            customer.setPhoneNo(rs.getString("PHONE_NO"));
            customer.setEmail(rs.getString("EMAIL"));
            customer.setDateOfBirth(rs.getString("DATEOFBIRTH"));
            customer.setCustomerType(rs.getString("CUSTOMER_TYPE"));
            customer.setGender(rs.getString("GENDER"));
            customer.setStreet(rs.getString("STREET"));
            customer.setCity(rs.getString("CITY"));
            customer.setState(rs.getString("STATE"));
            customer.setCountry(rs.getString("COUNTRY"));
            customer.setZipcode(rs.getString("ZIPCODE"));
            customer.setFax(rs.getString("FAX"));
//            customer.setCustomerCreatedDate(rs.getString("CUSTOMER_CREATE_DATE"));
            customer.setBalance(rs.getDouble("BALANCE"));
            customer.setTaxId(rs.getString("TAX_ID"));
            customer.setCompanyName(rs.getString("COMPANY_NAME"));


           // String totalSpending = jdbcTemplate.queryForObject(sqlQuery.getCustomersLast12MonthSpend, new Object[] {rs.getString("PHONE_NO")}, String.class);

//            if(null != totalSpending)
//            {
//                customer.setLast12MonthsSpend(Double.parseDouble(totalSpending));
//            }
//            else
//            {
//                customer.setLast12MonthsSpend(0.0);
//            }

            //This code is to get last year date from current date.
            /*DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Calendar cal = Calendar.getInstance();

            String currentDate = df.format(cal.getTime());
            System.out.println("Test" + currentDate);

            System.out.println("Today : " + df.format(cal.getTime()));


            // Substract 1 year from the calendar
            cal.add(Calendar.YEAR, -1);
            System.out.println("1 year ago: " + df.format(cal.getTime()));

            String last12MonthDate = df.format(cal.getTime());*/




            return customer;
        }
    }



    public CustomerResponse getCustomerBalance(String phoneNo) {


        CustomerResponse customerResponses = new CustomerResponse();
        try
        {
            customerResponses  = (jdbcTemplate.queryForObject(sqlQuery.getCustomerBalanceAndNotes, new CustomerResponseMapper(), phoneNo));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return customerResponses;
    }

    private final class CustomerResponseMapper implements RowMapper<CustomerResponse>
    {

        @Override
        public CustomerResponse mapRow(ResultSet rs, int rowNum) throws SQLException {

            CustomerResponse customerResponse = new CustomerResponse();

            customerResponse.setBalance(rs.getString("BALANCE"));
            customerResponse.setNotes(rs.getString("CUSTOMER_NOTE"));

            return customerResponse;
        }
    }


    public int deleteCustomerToDB(int custId) {

        int result = 0;

        try {
            result = jdbcTemplate.update(sqlQuery.deleteCustomer,custId);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return result;

    }
}
