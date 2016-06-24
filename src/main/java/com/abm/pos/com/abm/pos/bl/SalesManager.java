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
                        transactionDto.getSubTotal(),
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
    public List<TransactionDto> getTransactionDetails(String startDate, String endDategit ) {
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
            transaction.setSubTotal(rs.getDouble("SUBTOTAL"));
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
            receiptDto = jdbcTemplate.query(sqlQuery.getTransactionDetailsForReceipt,new TransactionReceiptMapper());
           // lineItemList = jdbcTemplate.query(sqlQuery.getTransactionLineItemDetails,new TransactionLineItemMapper(),rs.getInt("TRANSACTION_COMP_ID"));



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



            ReceiptDto r = new ReceiptDto();

            TransactionDto t = new TransactionDto();




            t.setTransactionCompId(rs.getInt("TRANSACTION_COMP_ID"));
            t.setTransactionDate(rs.getString("TRANSACTION_DATE"));
            t.setTotalAmount(rs.getDouble("TOTAL_AMOUNT"));
            t.setTax(rs.getDouble("TAX_AMOUNT"));
            t.setDiscount(rs.getDouble("DISCOUNT_AMOUNT"));
            t.setCustomerPhoneNo(rs.getString("CUSTOMER_PHONENO"));
            t.setUserId(rs.getInt("USER_ID"));
            String username = jdbcTemplate.queryForObject(sqlQuery.getUsernameFromUser, new Object[] {t.getUserId()},String.class);
            t.setUsername(username);
            t.setCashId(rs.getInt("PAYMENT_ID_CASH"));
            t.setCreditId(rs.getInt("PAYMENT_ID_CREDIT"));
            t.setPaidAmountCash(rs.getDouble("PAID_AMOUNT_CASH"));
            t.setPaidAmountCredit(rs.getDouble("TOTAL_AMOUNT_CREDIT"));
            t.setStatus(rs.getString("STATUS"));
            t.setChangeAmount(rs.getDouble("CHANGE_AMOUNT"));






           /* tl.setProductId(rs.getInt("PRODUCT_ID"));
            String productDescription = jdbcTemplate.queryForObject(sqlQuery.productDescriptionFromProduct, new Object[] {tl.getProductId()},String.class);
            tl.setProductDescription(productDescription);
            tl.setQuantity(rs.getInt("QUANTITY"));
            tl.setRetail(rs.getDouble("RETAIL"));
            tl.setCost(rs.getDouble("COST"));
            tl.setDiscount(rs.getDouble("DISCOUNT"));*/

            return r;
        }
    }


    public void addTransactionLineItemToDB(final List<TransactionLineItemDto> transactionLineItemDto) {

        try {

            jdbcTemplate.batchUpdate(sqlQuery.addTransactionLineItem, new BatchPreparedStatementSetter() {

                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {

                    TransactionLineItemDto transactionLineItemDto1 = transactionLineItemDto.get(i);
                    ps.setInt(1, transactionLineItemDto1.getTransactionCompId());
                    ps.setString(2,transactionLineItemDto1.getDate());
                    ps.setInt(3, transactionLineItemDto1.getProductId());
                    ps.setInt(4, transactionLineItemDto1.getQuantity());
                    ps.setDouble(5, transactionLineItemDto1.getRetail());
                    ps.setDouble(6, transactionLineItemDto1.getCost());
                    ps.setDouble(7, transactionLineItemDto1.getDiscount());
                    ps.setDouble(8,transactionLineItemDto1.getRetailWithDis());
                    ps.setDouble(9,transactionLineItemDto1.getTotalProductPrice());

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
            lineItem.setDate(rs.getString("DATE"));
            lineItem.setProductId(rs.getInt("PRODUCT_ID"));
            lineItem.setQuantity(rs.getInt("QUANTITY"));
            lineItem.setRetail(rs.getDouble("RETAIL"));
            lineItem.setCost(rs.getDouble("COST"));
            lineItem.setDiscount(rs.getDouble("DISCOUNT"));
            lineItem.setRetailWithDis(rs.getDouble("RETAILWITHDISCOUNT"));
            lineItem.setTotalProductPrice(rs.getDouble("TOTALPRODUCTPRICE"));

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





