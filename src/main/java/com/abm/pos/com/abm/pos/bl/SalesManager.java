package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.*;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Autowired
    CustomerManager customerManager;


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


        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void editTransaction(TransactionDto transactionDto, String prevTransId) {
        try {

            jdbcTemplate.update(sqlQuery.editTransactionStatus, prevTransId);

            if (null != transactionDto) {
                addTransaction(transactionDto);
                System.out.println("Returned Partial Transaction added returned completely");

            } else {
                System.out.println("Transaction returned completely");
            }


            /*jdbcTemplate.update(sqlQuery.addBlanceToCustomerProfile,
                    transactionDto.getPrevBalance(),
                    transactionDto.getCustomerPhoneNo());
            System.out.println("Customer Balance Edited Successfully");*/

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<TransactionDto> getsalesHistory(String startDate, String endDategit) {
        List<TransactionDto> transactionDto = new ArrayList<>();


        try {
            transactionDto = jdbcTemplate.query(sqlQuery.getTransactionDetails, new TransactionMapperWithOutCustomer(), startDate, endDategit);

            System.out.println("Send Transaction Details Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }

        return transactionDto;
    }


    public List<ReceiptDto> getReceiptDetails(int receiptId) {
        List<ReceiptDto> receiptDtos = new ArrayList<>();


        List<TransactionDto> transactionDtos = new ArrayList<>();
        List<TransactionLineItemDto> transactionLineItemDtos = new ArrayList<>();
        List<CustomerDto> customerDtos = new ArrayList<>();

        ReceiptDto receiptDto = new ReceiptDto();

        try {

            transactionDtos = jdbcTemplate.query(sqlQuery.getTransactionDetailsForReceiptWithoutCustomer, new TransactionMapperWithOutCustomer(), receiptId);
            transactionLineItemDtos = jdbcTemplate.query(sqlQuery.getTransactionLineItemDetails, new TransactionLineItemMapper(), receiptId);

            //Getting customer phone no to check the transaction history
            String custPhoneNo = jdbcTemplate.queryForObject(sqlQuery.getCustomerPhone, new Object[]{receiptId}, String.class);

            //Checking and calling customer db to get all details of the customer for receipt
            if (custPhoneNo != null) {
                customerDtos = jdbcTemplate.query(sqlQuery.getCustomerDetailsForReceipt, new CustomerMapper(), custPhoneNo);
            }

            receiptDto.setTransactionDtoList(transactionDtos);
            receiptDto.setTransactionLineItemDtoList(transactionLineItemDtos);
            receiptDto.setCustomerDtosList(customerDtos);

            receiptDtos.add(receiptDto);
        } catch (Exception e) {
            System.out.println(e);
        }

        return receiptDtos;
    }


    private final class TransactionMapperWithOutCustomer implements RowMapper<TransactionDto> {

        @Override
        public TransactionDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            TransactionDto transaction = new TransactionDto();


            transaction.setTransactionCompId(rs.getInt("TRANSACTION_COMP_ID"));
            DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = null;
            try {
                d = f.parse(rs.getString("TRANSACTION_DATE"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            DateFormat date = new SimpleDateFormat("MM/dd/yyyy");//NEED TO CHECK THIS
            DateFormat time = new SimpleDateFormat("hh:mm:ss");
            //System.out.println("Date: " + date.format(d));
            //System.out.println("Time: " + time.format(d));
            transaction.setTransactionDate(date.format(d));
            transaction.setTransactionTime(time.format(d));
            transaction.setTotalAmount(rs.getDouble("TOTAL_AMOUNT"));
            transaction.setTax(rs.getDouble("TAX_AMOUNT"));


            //Getting sum of discount on line item table and adding into transaction discount to show only total discount.
            String lineItemDiscount = jdbcTemplate.queryForObject(sqlQuery.getDiscountFromLineItem, new Object[]{rs.getInt("TRANSACTION_COMP_ID")}, String.class);

            if (null != lineItemDiscount) {
                double lineItemDiscountDouble = Double.parseDouble(lineItemDiscount);
                //System.out.println(lineItemDiscount);
                transaction.setDiscount(rs.getDouble("DISCOUNT_AMOUNT") + lineItemDiscountDouble);
            } else {

                transaction.setDiscount(rs.getDouble("DISCOUNT_AMOUNT"));
                //System.out.println(lineItemDiscount);
            }


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

    private static final class CustomerMapper implements RowMapper<CustomerDto> {

        @Override
        public CustomerDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            CustomerDto customer = new CustomerDto();

            customer.setFirstName(rs.getString("FIRST_NAME"));
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
            customer.setBalance(rs.getDouble("BALANCE"));

            return customer;
        }
    }


    public void addTransactionLineItemToDB(final List<TransactionLineItemDto> transactionLineItemDto) {

        try {

            jdbcTemplate.batchUpdate(sqlQuery.addTransactionLineItem, new BatchPreparedStatementSetter() {


                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {

                    TransactionLineItemDto transactionLineItemDto1 = transactionLineItemDto.get(i);

                    ps.setInt(1, transactionLineItemDto1.getTransactionCompId());
                    ps.setString(2, transactionLineItemDto1.getTransactionDate());
                    ps.setString(3, transactionLineItemDto1.getTransactionStatus());
                    ps.setInt(4, transactionLineItemDto1.getProductId());

                    int productQuantity = jdbcTemplate.queryForObject(sqlQuery.getProductQuantity, new Object[] {transactionLineItemDto1.getProductId()}, Integer.class);

                    ps.setInt(5, transactionLineItemDto1.getQuantity());

                    int transQuantity = transactionLineItemDto1.getQuantity();

                    //reducing quantity into Stock for transaction
                    productQuantity = productQuantity - transQuantity;

                    jdbcTemplate.update(sqlQuery.updateProductQuantity, productQuantity, transactionLineItemDto1.getProductId());

                    ps.setDouble(6, transactionLineItemDto1.getRetail());
                    ps.setDouble(7, transactionLineItemDto1.getCost());
                    ps.setDouble(8, transactionLineItemDto1.getDiscount());
                    ps.setDouble(9, transactionLineItemDto1.getDiscountPercentage());
                    ps.setDouble(10, transactionLineItemDto1.getRetailWithDis());
                    ps.setDouble(11, transactionLineItemDto1.getTotalProductPrice());

                    System.out.println("Transaction Line Item Added Successfully");
                }

                @Override
                public int getBatchSize() {
                    return transactionLineItemDto.size();
                }
            });
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ediTransactionLineItemToDB(List<TransactionLineItemDto> transactionLineItemDto, String previousTransId) {

        try {

            //Checking if transaction return is complete or partial
            boolean isCompleteReturn = checkReturn(transactionLineItemDto);

            //if there previousTransId = null means this is new transaction no return invovled in this.
            if (null == previousTransId) {

                if (null != transactionLineItemDto) {
                    addTransactionLineItemToDB(transactionLineItemDto);
                } else {
                    System.out.println("transactionLineItemDto is null");
                }

            }
            //If the previousTransId != null then this is return.
            else {
                List<TransactionLineItemDto> lineItemDtoList1 = new ArrayList<>();


                //Doing db call to get Line item details for the previous transaction line item which i need to update and also add the quantity in product.
                lineItemDtoList1 = jdbcTemplate.query(sqlQuery.getTransactionLineItemDetails, new TransactionLineItemMapper(), previousTransId);


                for (int j = 0; j < lineItemDtoList1.size(); j++) {
                    System.out.println(lineItemDtoList1.get(j).getProductId());
                    System.out.println(lineItemDtoList1.get(j).getQuantity());

                    int productQuantity = 0;

                    int productQuantity1 = jdbcTemplate.queryForObject(sqlQuery.getProductQuantity, new Object[]
                            {lineItemDtoList1.get(j).getProductId()}, Integer.class);

                    productQuantity = productQuantity1 + lineItemDtoList1.get(j).getQuantity();

                    System.out.println(productQuantity);

                    jdbcTemplate.update(sqlQuery.updateProductQuantity, productQuantity, lineItemDtoList1.get(j).getProductId());

                    System.out.println("Transaction line item edited successfully");
                }


                for (int i = 0; i < transactionLineItemDto.size(); i++) {
                    System.out.println(transactionLineItemDto.get(i).getTransactionLineItemId());
                    jdbcTemplate.update(sqlQuery.updateLineItemDetailsStatus, transactionLineItemDto.get(i).getTransactionLineItemId());
                }


                if (null != transactionLineItemDto) {
                    addTransactionLineItemToDB(transactionLineItemDto);
                    System.out.println("Added partially returned items successfully");
                }


            }
        } catch (Exception e)

        {
            System.out.println(e);
        }


    }

    private boolean checkReturn(List<TransactionLineItemDto> transactionLineItemDto) {

        boolean isCompleteReturn = false;

        for (int i = 0; i < transactionLineItemDto.size(); i++) {
            if (transactionLineItemDto.get(i).getTransactionStatus() == "returned") {
                isCompleteReturn = true;
                continue;
            } else {
                isCompleteReturn = false;
                break;
            }
        }

        return isCompleteReturn;
    }


    public List<TransactionLineItemDto> getTransactionLineItemDetails(int transactionCompId) {

        List<TransactionLineItemDto> lineItemList = new ArrayList<>();
        try {
            lineItemList = jdbcTemplate.query(sqlQuery.getTransactionLineItemDetails, new TransactionLineItemMapper(), transactionCompId);
        } catch (Exception e) {
            System.out.println(e);
        }
        return lineItemList;
    }

    private final class TransactionLineItemMapper implements RowMapper<TransactionLineItemDto> {

        @Override
        public TransactionLineItemDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            TransactionLineItemDto lineItem = new TransactionLineItemDto();

            lineItem.setTransactionLineItemId(rs.getInt("TRANSACTION_LINE_ITEM_ID"));
            lineItem.setTransactionCompId(rs.getInt("TRANSACTION_COMP_ID"));
            lineItem.setTransactionDate(rs.getString("DATE"));
            lineItem.setProductId(rs.getInt("PRODUCT_ID"));
            lineItem.setProductNumber(jdbcTemplate.queryForObject(sqlQuery.getProductNumber, new Object[]{lineItem.getProductId()}, String.class));
            lineItem.setProductDescription(jdbcTemplate.queryForObject(sqlQuery.getProductDescription, new Object[]{lineItem.getProductId()}, String.class));
            lineItem.setQuantity(rs.getInt("QUANTITY"));
            lineItem.setRetail(rs.getDouble("RETAIL"));
            lineItem.setCost(rs.getDouble("COST"));
            lineItem.setDiscount(rs.getDouble("DISCOUNT"));
            lineItem.setDiscountPercentage(rs.getDouble("DISCOUNT_PERCENTAGE"));
            lineItem.setRetailWithDis(rs.getDouble("RETAILWITHDISCOUNT"));
            lineItem.setTotalProductPrice(rs.getDouble("TOTALPRODUCTPRICE"));

            return lineItem;
        }
    }

    /*public void addTransactionPaymentToDB(TransactionPaymentDto transactionPaymentDto) {
        try {
            jdbcTemplate.update(sqlQuery.addTransactionPaymentDetail,

                    transactionPaymentDto.getTransactionId(),
                    transactionPaymentDto.getTransactionDate(),
                    transactionPaymentDto.getPaymentId(),
                    transactionPaymentDto.getPaymentAmount());
            System.out.println("Transaction Payment Details Added Successfully");

        } catch (Exception e) {
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

    private final class TransactionMapperWithCustomer implements RowMapper<TransactionDto>
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
            transaction.setPrevBalance(rs.getDouble("BALANCE"));

            return transaction;
        }
    }*/
}




