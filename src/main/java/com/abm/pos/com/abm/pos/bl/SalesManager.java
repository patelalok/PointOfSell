package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.ReceiptDto;
import com.abm.pos.com.abm.pos.dto.TransactionDto;
import com.abm.pos.com.abm.pos.dto.TransactionLineItemDto;
import com.abm.pos.com.abm.pos.dto.TransactionPaymentDto;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
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

        try {
           // if (transactionDto.getPaymentIdMulty() == 0 && transactionDto.getPaidAmountMulty() == 0) {
                jdbcTemplate.update(sqlQuery.addTransaction,
                        transactionDto.getTransactionCompId(),
                        transactionDto.getTransactionDate(),
                        transactionDto.getTotalAmount(),
                        transactionDto.getTax(),
                        transactionDto.getDiscount(),
                        transactionDto.getCustomerPhoneNo(),
                        transactionDto.getUserId(),
                        transactionDto.getCashId(),
                        transactionDto.getStatus(),
                        transactionDto.getPaidAmountCash(),
                        transactionDto.getChangeAmount(),
                        transactionDto.getCreditId(),
                        transactionDto.getPaidAmountCredit());

                System.out.println("Transaction Added Successfully");
            }

        catch (Exception e)
        {
            System.out.println(e);
        }

    }
    public List<TransactionDto> getTransactionDetails(String startDate, String endDate) {
        List<TransactionDto> transactionDto = new ArrayList<>();

        try {
            transactionDto = jdbcTemplate.query(sqlQuery.getTransactionDetails, new TransactionMapper());
            System.out.println("Send Transaction Details Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }

        return transactionDto;
    }


    private final class TransactionMapper implements RowMapper<TransactionDto>
    {

        @Override
        public TransactionDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            TransactionDto transaction = new TransactionDto();

            transaction.setTransactionCompId(rs.getInt("TRANSACTION_COMP_ID"));
            transaction.setTransactionDate(rs.getString("TRANSACTION_DATE"));
            StringTokenizer at = new StringTokenizer(transaction.getTransactionDate());
            String date = at.nextToken();
            String time = at.nextToken();
            System.out.println(date);
            System.out.println(time);
            transaction.setTotalAmount(rs.getDouble("TOTAL_AMOUNT"));
            transaction.setTax(rs.getDouble("TAX_AMOUNT"));
            transaction.setDiscount(rs.getDouble("DISCOUNT_AMOUNT"));
            transaction.setCustomerPhoneNo(rs.getString("CUSTOMER_PHONENO"));
            transaction.setUserId(rs.getInt("USER_ID"));
            String username = jdbcTemplate.queryForObject(sqlQuery.getUsernameFromUser, new Object[] {transaction.getUserId()},String.class);
            transaction.setUsername(username);
            transaction.setCashId(rs.getInt("PAYMENT_ID_CASH"));
            transaction.setCreditId(rs.getInt("PAYMENT_ID_CREDIT"));
            transaction.setPaidAmountCash(rs.getDouble("PAID_AMOUNT_CASH"));
            transaction.setPaidAmountCredit(rs.getDouble("TOTAL_AMOUNT_CREDIT"));
            transaction.setStatus(rs.getString("STATUS"));
            transaction.setChangeAmount(rs.getDouble("CHANGE_AMOUNT"));

            return transaction;
        }
    }

    public List<ReceiptDto> getReceiptDetails(int receiptId)
    {
        List<ReceiptDto> receiptDto = new ArrayList<>();


        try
        {
            receiptDto = jdbcTemplate.query(sqlQuery.getTransactionDetailsForReceipt,new TransactionReceiptMapper(),receiptId );

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return receiptDto;
    }
    private final class TransactionReceiptMapper implements RowMapper<ReceiptDto>
    {

        @Override
        public ReceiptDto mapRow(ResultSet rs, int rowNum) throws SQLException
        {

            ReceiptDto receipt = new ReceiptDto();

            receipt.setTransactionCompId(rs.getInt("TRANSACTION_COMP_ID"));
            receipt.setTransactionDate(rs.getString("TRANSACTION_DATE"));
            receipt.setTotalAmount(rs.getDouble("TOTAL_AMOUNT"));
            receipt.setTax(rs.getDouble("TAX_AMOUNT"));
            receipt.setTransactionDiscount(rs.getDouble("DISCOUNT_AMOUNT"));
            receipt.setCustomerPhoneNo(rs.getString("CUSTOMER_PHONENO"));
            receipt.setUserId(rs.getInt("USER_ID"));
            String username = jdbcTemplate.queryForObject(sqlQuery.getUsernameFromUser, new Object[] {receipt.getUserId()},String.class);
            receipt.setUsername(username);
            receipt.setPaymentId(rs.getInt("PAYMENT_ID"));
            receipt.setStatus(rs.getString("STATUS"));
            receipt.setPaidAmount(rs.getDouble("PAID_AMOUNT"));
            receipt.setChangeAmount(rs.getDouble("CHANGE_AMOUNT"));
            receipt.setPaymentIdMulty(rs.getInt("PAYMENT__ID_MULTY"));
            receipt.setPaidAmountMulty(rs.getDouble("TOTAL_AMOUNT_MULTY"));


            receipt.setProductId(rs.getInt("PRODUCT_ID"));
            String productDescription = jdbcTemplate.queryForObject(sqlQuery.productDescriptionFromProduct, new Object[] {receipt.getUserId()},String.class);
            receipt.setProductDescription(productDescription);
            receipt.setUsername(username);
            receipt.setQuantity(rs.getInt("QUANTITY"));
            receipt.setRetail(rs.getDouble("RETAIL"));
            receipt.setCost(rs.getDouble("COST"));
            receipt.setProductDiscount(rs.getDouble("DISCOUNT"));

            return receipt;
        }
    }


    public void addTransactionLineItemToDB(final List<TransactionLineItemDto> transactionLineItemDto) {

        try {

            jdbcTemplate.batchUpdate(sqlQuery.addTransactionLineItem, new BatchPreparedStatementSetter() {

                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {

                    TransactionLineItemDto transactionLineItemDto1 = transactionLineItemDto.get(i);
                    ps.setInt(1, transactionLineItemDto1.getTransactionCompId());
                    ps.setInt(2, transactionLineItemDto1.getProductId());
                    ps.setInt(3, transactionLineItemDto1.getQuantity());
                    ps.setDouble(4, transactionLineItemDto1.getRetail());
                    ps.setDouble(5, transactionLineItemDto1.getCost());
                    ps.setDouble(6, transactionLineItemDto1.getDiscount());

                    System.out.println("Transaction Line Item Added Successfully");
                }

                @Override
                public int getBatchSize() {
                    return transactionLineItemDto.size();
                }
            });
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


    public List<TransactionLineItemDto> getTransactionLineItemDetails(int  transactionCompId) {

        List<TransactionLineItemDto> lineItemList = new ArrayList<>();
        try
        {
            lineItemList = jdbcTemplate.query(sqlQuery.getTransactionLineItemDetails,new TransactionLineItemMapper(),transactionCompId);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return lineItemList;
    }

    private static final class TransactionLineItemMapper implements RowMapper<TransactionLineItemDto>
    {

        @Override
        public TransactionLineItemDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            TransactionLineItemDto lineItem = new TransactionLineItemDto();

            lineItem.setTransactionLineItemId(rs.getInt("TRANSACTION_LINE_ITEM_ID"));
            lineItem.setTransactionCompId(rs.getInt("TRANSACTION_COMP_ID"));
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





