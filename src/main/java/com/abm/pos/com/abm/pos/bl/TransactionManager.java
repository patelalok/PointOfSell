package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.AddTransactionDto;
import com.abm.pos.com.abm.pos.dto.AddTransactionLineItemDto;
import com.abm.pos.com.abm.pos.dto.AddTransactionPaymentDto;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by asp5045 on 5/20/16.
 */
@Component
public class TransactionManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQuery;

    public void addTransactionToDB(AddTransactionDto addTransactionDto) {

        try
        {
            jdbcTemplate.update(sqlQuery.addTransaction,
                    addTransactionDto.getTransactionDate(),
                    addTransactionDto.getTotalAmount(),
                    addTransactionDto.getTax(),
                    addTransactionDto.getDiscount(),
                    addTransactionDto.getCustomerId(),
                    addTransactionDto.getUserId(),
                    addTransactionDto.getPaymentId(),
                    addTransactionDto.getStatus());
            System.out.println("Transaction Added Successfully");

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void getTransactionDetails(int transactionId) {
        try
        {
            jdbcTemplate.query(sqlQuery.getTransactionDetails,new TransactionManager.TransactionMapper());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }




    private static final class TransactionMapper implements RowMapper<AddTransactionDto>
    {

        @Override
        public AddTransactionDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            AddTransactionDto transaction = new AddTransactionDto();

            transaction.setTransactionId(rs.getInt("TRANSACTION_ID"));
            transaction.setTransactionDate(rs.getString("TRANSACTION_DATE"));
            transaction.setTotalAmount(rs.getString("TOTAL_AMOUNT"));
            transaction.setTax(rs.getString("TAX_AMOUNT"));
            transaction.setDiscount(rs.getString("DISCOUNT_AMOUNT"));
            transaction.setCustomerId(rs.getInt("CUSTOMER_ID"));
            transaction.setUserId(rs.getInt("USER_ID"));
            transaction.setPaymentId(rs.getInt("PAYMENT_ID"));
            transaction.setStatus(rs.getString("STATUS"));

            return transaction;
        }
    }

    public void addTransactionLineItemToDB(AddTransactionLineItemDto addTransactionLineItemDto) {
        try
        {

            jdbcTemplate.update(sqlQuery.addTransactionLineItem,
                    addTransactionLineItemDto.getTransactionId(),
                    addTransactionLineItemDto.getProductId(),
                    addTransactionLineItemDto.getQuantity(),
                    addTransactionLineItemDto.getRetail(),
                    addTransactionLineItemDto.getCost(),
                    addTransactionLineItemDto.getTax(),
                    addTransactionLineItemDto.getDiscount());
            System.out.println("Transaction Line Item Added Successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public void getTransactionLineItemDetails(AddTransactionLineItemDto addTransactionLineItemDto) {
        try
        {
            jdbcTemplate.query(sqlQuery.getTransactionLineItemDetails,new TransactionManager.TransactionLineItemMapper());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }



    private static final class TransactionLineItemMapper implements RowMapper<AddTransactionLineItemDto>
    {

        @Override
        public AddTransactionLineItemDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            AddTransactionLineItemDto lineItem = new AddTransactionLineItemDto();

            lineItem.setTransactionLineItemId(rs.getInt("TRANSACTION_LINE_ITEM_ID"));
            lineItem.setTransactionId(rs.getInt("TRANSACTION_ID"));
            lineItem.setProductId(rs.getInt("PRODUCT_ID"));
            lineItem.setQuantity(rs.getInt("QUANTITY"));
            lineItem.setRetail(rs.getDouble("RETAIL"));
            lineItem.setCost(rs.getDouble("COST"));
            lineItem.setDiscount(rs.getDouble("DISCOUNT"));
            lineItem.setTax(rs.getDouble("TAX"));

            return lineItem;
        }
    }


    public void addTransactionPaymentToDB(AddTransactionPaymentDto transactionPaymentDto) {
        try
        {
            jdbcTemplate.update(sqlQuery.addTransactionPaymentDetail,

                    transactionPaymentDto.getTransactionId(),
                    transactionPaymentDto.getTransactionDate(),
                    transactionPaymentDto.getPaymentId(),
                    transactionPaymentDto.getPaymentAmount());
            System.out.println("Transaction Payment Details Added Successfully");

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void getTransactionPaymentDetails(AddTransactionPaymentDto transactionPaymentDto) {

        try
        {
            jdbcTemplate.query(sqlQuery.getTransactionPaymentDetails,new TransactionManager.TransactionPaymentMapper());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    private static final class TransactionPaymentMapper implements RowMapper<AddTransactionPaymentDto>
    {

        @Override
        public AddTransactionPaymentDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            AddTransactionPaymentDto transPayment = new AddTransactionPaymentDto();

            transPayment.setTransactionPaymentId(rs.getInt("TRANSACTION_PAYMENT_ID"));
            transPayment.setTransactionId(rs.getInt("TRANSACTION_ID"));
            transPayment.setTransactionDate(rs.getString("TRANSACTION_DATE"));
            transPayment.setPaymentId(rs.getInt("PAYMENT_ID"));
            transPayment.setPaymentAmount(rs.getString("PAYMENT_AMOUNT"));

            return transPayment;
        }
    }
    }



