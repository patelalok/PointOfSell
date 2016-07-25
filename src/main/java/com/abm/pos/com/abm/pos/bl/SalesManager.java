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
import java.util.*;

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
                jdbcTemplate.update(sqlQuery.addTransaction,
                        transactionDto.getTransactionCompId(),
                        transactionDto.getTransactionDate(),
                        transactionDto.getTotalAmount(),
                        transactionDto.getTax(),
                        transactionDto.getDiscount(),
                        transactionDto.getSubTotal(),
                        transactionDto.getTotalQuantity(),
                        transactionDto.getCustomerPhoneNo(),
                        transactionDto.getUserId(),
                        transactionDto.getCashId(),
                        transactionDto.getStatus(),
                        transactionDto.getPaidAmountCash(),
                        transactionDto.getChangeAmount(),
                        transactionDto.getCreditId(),
                        transactionDto.getPaidAmountCredit(),
                        transactionDto.getPaidAmountCheck(),
                        transactionDto.getTransCreditId(),
                        transactionDto.getLast4Digits());

                        jdbcTemplate.update(sqlQuery.addBlanceToCustomerProfile,
                                transactionDto.getPrevBalance(),
                                transactionDto.getCustomerPhoneNo());
                System.out.println("Customer Balance Added Successfully");
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
            transactionDto = jdbcTemplate.query(sqlQuery.getTransactionDetails, new TransactionMapper(),startDate,endDategit);
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
                transaction.setTotalAmount(rs.getDouble("TOTAL_AMOUNT"));
                transaction.setTax(rs.getDouble("TAX_AMOUNT"));
                transaction.setDiscount(rs.getDouble("DISCOUNT_AMOUNT"));
                transaction.setSubTotal(rs.getDouble("SUBTOTAL"));
                transaction.setTotalQuantity(rs.getInt("TOTALQUANTITY"));
                transaction.setCustomerPhoneNo(rs.getString("CUSTOMER_PHONENO"));
                transaction.setUserId(rs.getInt("USER_ID"));
                String username = jdbcTemplate.queryForObject(sqlQuery.getUsernameFromUser, new Object[]{transaction.getUserId()}, String.class);
                transaction.setUsername(username);
                transaction.setCashId(rs.getInt("PAYMENT_ID_CASH"));
                transaction.setCreditId(rs.getInt("PAYMENT_ID_CREDIT"));
                transaction.setPaidAmountCash(rs.getDouble("PAID_AMOUNT_CASH"));
                transaction.setPaidAmountCredit(rs.getDouble("TOTAL_AMOUNT_CREDIT"));
                transaction.setStatus(rs.getString("STATUS"));
                transaction.setChangeAmount(rs.getDouble("CHANGE_AMOUNT"));
              //  transaction.setPrevBalance(rs.getDouble("BALANCE"));

            return transaction;
        }
    }

    public List<ReceiptDto> getReceiptDetails(int receiptId)
    {
       List<ReceiptDto> receiptDtos = new ArrayList<>();

        List<TransactionLineItemDto> transactionLineItemDtos = new ArrayList<>();
        List<TransactionDto> transactionDtos = new ArrayList<>();

        ReceiptDto receiptDto = new ReceiptDto();

        try
        {

            transactionDtos = jdbcTemplate.query(sqlQuery.getTransactionDetailsForReceipt, new TransactionMapper(),receiptId);
            transactionLineItemDtos = jdbcTemplate.query(sqlQuery.getTransactionLineItemDetails,new TransactionLineItemMapper(),receiptId);

            receiptDto.setTransactionDtoList(transactionDtos);
            receiptDto.setTransactionLineItemDtoList(transactionLineItemDtos);

            receiptDtos.add(receiptDto);

        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return receiptDtos;
    }



    public void addTransactionLineItemToDB(final List<TransactionLineItemDto> transactionLineItemDto) {

        try {

            jdbcTemplate.batchUpdate(sqlQuery.addTransactionLineItem, new BatchPreparedStatementSetter() {



                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {

                    TransactionLineItemDto transactionLineItemDto1 = transactionLineItemDto.get(i);

                    ps.setInt(1, transactionLineItemDto1.getTransactionCompId());
                    ps.setString(2,transactionLineItemDto1.getTransactionDate());
                    ps.setInt(3, transactionLineItemDto1.getProductId());

                    int productQuantity = jdbcTemplate.queryForObject(sqlQuery.getProductQuantity, new Object[]
                            {transactionLineItemDto1.getProductId()}, Integer.class);

                    //System.out.println("Quantity: "+productQuantity);

                    ps.setInt(4, transactionLineItemDto1.getQuantity());

                    int transQuantity = transactionLineItemDto1.getQuantity();

                    //System.out.println("transQuantity:"+transQuantity);

                    productQuantity = productQuantity - transQuantity;


                    jdbcTemplate.update(sqlQuery.updateProductQuantity, productQuantity,transactionLineItemDto1.getProductId());


                   // System.out.println("DONE:)");

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

    private  final class TransactionLineItemMapper implements RowMapper<TransactionLineItemDto>
    {

        @Override
        public TransactionLineItemDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            TransactionLineItemDto lineItem = new TransactionLineItemDto();

            lineItem.setTransactionLineItemId(rs.getInt("TRANSACTION_LINE_ITEM_ID"));
            lineItem.setTransactionCompId(rs.getInt("TRANSACTION_COMP_ID"));
            lineItem.setTransactionDate(rs.getString("DATE"));
            lineItem.setProductId(rs.getInt("PRODUCT_ID"));
            lineItem.setProductDescription(jdbcTemplate.queryForObject(sqlQuery.getProductDescription, new Object[]{lineItem.getProductId()}, String.class));
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





