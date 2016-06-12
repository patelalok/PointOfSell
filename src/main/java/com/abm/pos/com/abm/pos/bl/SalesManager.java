package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.TransactionDto;
import com.abm.pos.com.abm.pos.dto.TransactionLineItemDto;
import com.abm.pos.com.abm.pos.dto.TransactionPaymentDto;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by asp5045 on 6/12/16.
 */

@Component
public class SalesManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQuery;

    public void addTransaction(TransactionDto transactionDto) {

        try
        {
            jdbcTemplate.update(sqlQuery.addTransaction,
                    transactionDto.getTransactionDate(),
                    transactionDto.getTotalAmount(),
                    transactionDto.getTax(),
                    transactionDto.getDiscount(),
                    transactionDto.getCustomerId(),
                    transactionDto.getUserId(),
                    transactionDto.getPaymentId(),
                    transactionDto.getStatus(),
                    transactionDto.getPaidAmount(),
                    transactionDto.getChangeAmount());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }



    public List<TransactionDto> getTransactionDetails(String startDate, String endDate)
    {
        List<TransactionDto> transactionDto = new ArrayList<>();

        try
        {
            transactionDto = jdbcTemplate.query(sqlQuery.getTransactionDetails, new TransactionMapper(), startDate,endDate);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return transactionDto;
    }

    private static final class TransactionMapper implements RowMapper<TransactionDto>
    {

        @Override
        public TransactionDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            TransactionDto transaction = new TransactionDto();

            transaction.setTransactionId(rs.getInt("TRANSACTION_ID"));
            transaction.setTransactionDate(rs.getString("TRANSACTION_DATE"));
           StringTokenizer at = new StringTokenizer(transaction.getTransactionDate());
            String date = at.nextToken();
            String time = at.nextToken();
            System.out.println(date);
            System.out.println(time);
            transaction.setTotalAmount(rs.getDouble("TOTAL_AMOUNT"));
            transaction.setTax(rs.getDouble("TOTAL_AMOUNT"));
            transaction.setDiscount(rs.getDouble("DISCOUNT_AMOUNT"));
            transaction.setCustomerId(rs.getInt("CUSTOMER_ID"));
            transaction.setUserId(rs.getInt("USER_ID"));
            transaction.setPaymentId(rs.getInt("PAYMENT_ID"));
            transaction.setStatus(rs.getString("STATUS"));
            transaction.setTotalAmount(rs.getDouble("PAID_AMOUNT"));
            transaction.setChangeAmount(rs.getDouble("CHANGE_AMOUNT"));


            return transaction;
        }
    }

    public void addTransactionLineItemToDB(TransactionLineItemDto transactionLineItemDto) {
        try
        {

            jdbcTemplate.update(sqlQuery.addTransactionLineItem,
                    transactionLineItemDto.getTransactionId(),
                    transactionLineItemDto.getProductId(),
                    transactionLineItemDto.getQuantity(),
                    transactionLineItemDto.getRetail(),
                    transactionLineItemDto.getCost(),
                    transactionLineItemDto.getDiscount());
            System.out.println("Transaction Line Item Added Successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


    public void getTransactionLineItemDetails(TransactionLineItemDto transactionLineItemDto) {
        try
        {
            jdbcTemplate.query(sqlQuery.getTransactionLineItemDetails,new TransactionLineItemMapper());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    private static final class TransactionLineItemMapper implements RowMapper<TransactionLineItemDto>
    {

        @Override
        public TransactionLineItemDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            TransactionLineItemDto lineItem = new TransactionLineItemDto();

            lineItem.setTransactionLineItemId(rs.getInt("TRANSACTION_LINE_ITEM_ID"));
            lineItem.setTransactionId(rs.getInt("TRANSACTION_ID"));
            lineItem.setProductId(rs.getInt("PRODUCT_ID"));
            lineItem.setQuantity(rs.getInt("QUANTITY"));
            lineItem.setRetail(rs.getDouble("RETAIL"));
            lineItem.setCost(rs.getDouble("COST"));
            lineItem.setDiscount(rs.getDouble("DISCOUNT"));

            return lineItem;
        }
    }

    public void addTransactionPaymentToDB(TransactionPaymentDto transactionPaymentDto) {
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


        public void getTransactionPaymentDetails(TransactionPaymentDto transactionPaymentDto) {
            try
            {
                jdbcTemplate.query(sqlQuery.getTransactionPaymentDetails,new TransactionPaymentMapper());
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }

    private static final class TransactionPaymentMapper implements RowMapper<TransactionPaymentDto>
    {

        @Override
        public TransactionPaymentDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            TransactionPaymentDto transPayment = new TransactionPaymentDto();

            transPayment.setTransactionPaymentId(rs.getInt("TRANSACTION_PAYMENT_ID"));
            transPayment.setTransactionId(rs.getInt("TRANSACTION_ID"));
            transPayment.setTransactionDate(rs.getString("TRANSACTION_DATE"));
            transPayment.setPaymentId(rs.getInt("PAYMENT_ID"));
            transPayment.setPaymentAmount(rs.getString("PAYMENT_AMOUNT"));

            return transPayment;
        }
    }



}





