package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.*;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

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

    private BaseFont bfBold;
    private BaseFont bf;
    private int pageNumber = 0;

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
                    transactionDto.getStatus(),
                    transactionDto.getPaidAmountCash(),
                    transactionDto.getChangeAmount(),
                    transactionDto.getPaidAmountCredit(),
                    transactionDto.getPaidAmountCheck(),
                    transactionDto.getTransCreditId(),
                    transactionDto.getLast4Digits(),
                    transactionDto.getPrevBalance(),
                    transactionDto.getBalance(),
                    transactionDto.getPaidAmountDebit(),
                    transactionDto.getReceiptNote(),
                    transactionDto.getTransactionNote());

            if(null != transactionDto.getStatus() && transactionDto.getStatus().equals("c")) {

                jdbcTemplate.update(sqlQuery.updateBalanceToCustomerProfile,
                        transactionDto.getBalance(),
                        transactionDto.getTransactionDate(),
                        transactionDto.getCustomerPhoneNo());
                System.out.println("Customer Balance Added Successfully");
            }


            System.out.println("Transaction Added Successfully");


        } catch (Exception e) {
            System.out.println(e);
        }

    }


    public void editTransaction(TransactionDto transactionDto, String previousTransId) {
        try {


            //So here I am adding the transaction with negative values because this will balance out with the previous transaction.

            //This is not neccecary but just doing in case i need on future.
            //jdbcTemplate.update(sqlQuery.updateTransactionStatus, previousTransId);

          //  System.out.println("Update the status successfully");

            if(null != transactionDto &&  transactionDto.getPrevBalance() != 0.0)
            {
                //Here getting the previous balance of the customer and adding back the customer cause this is return  so he will get his previous balance back
                //and this is not right that why need to add back the customer.
                String previousBalance = jdbcTemplate.queryForObject(sqlQuery.getPreviousBalance, new Object[]{previousTransId}, String.class);

                String customerPhoneNo = jdbcTemplate.queryForObject(sqlQuery.getCustomerPhoneNo, new Object[]{previousTransId}, String.class);

                if (null != customerPhoneNo && null != previousBalance) {


                    jdbcTemplate.update(sqlQuery.updateBlanceToCustomerProfileWithoutDate, previousBalance, customerPhoneNo);
                    System.out.println("Customer balance updated successfully with with complete return");
                }
                //Here I am adding the return transaction into db with negative values from ui.
                addTransaction(transactionDto);

                System.out.println("that was complete return");
            }

            else {
                //Just adding partial transaction as new transaction in db with negative values.


                //This the case when customer has not previous balance but intial transaction was made as partial payment
                // and now user is returning this transaction so we need to make the balance of the transaction as 0.0 because  that has not any previous balance.
                if(null != transactionDto && transactionDto.getBalance() != 0.0 && null != transactionDto.getCustomerPhoneNo())
                {
                    jdbcTemplate.update(sqlQuery.updateBlanceToCustomerProfileWithoutDate, 0.0,  transactionDto.getCustomerPhoneNo());
                    addTransaction(transactionDto);
                }

                if(null != transactionDto) {
                    addTransaction(transactionDto);
                }

                System.out.println("that was partial return");
            }


           /* TransactionDto transactionDtoPrevious = new TransactionDto();


            //Getting the previous Transaction details
            transactionDtoPrevious =  jdbcTemplate.queryForObject(sqlQuery.getTransactionDetailsForReceiptWithoutCustomer, new TransactionMapperForReturn(),transactionDto.getTransactionCompId());



            //After getting previous transaction details now subtracting new values from old vlaue and storing into db.
            //For both scenario partial return and full return.

            jdbcTemplate.update(sqlQuery.editTransactionStatus,
                    transactionDto.getTransactionDate(),
                    transactionDto.getTotalAmount() - transactionDtoPrevious.getTotalAmount(),
                    transactionDto.getTax() - transactionDtoPrevious.getTax(),
                    transactionDto.getDiscount() - transactionDtoPrevious.getDiscount(),
                    transactionDto.getSubTotal() - transactionDtoPrevious.getSubTotal(),
                    transactionDto.getTotalQuantity() - transactionDtoPrevious.getTotalQuantity(),
                    transactionDto.getUserId(),
                    transactionDto.getCashId(),
                    transactionDto.getStatus(),
                    transactionDto.getPaidAmountCash(),
                    transactionDto.getChangeAmount(),
                    transactionDto.getCreditId(),
                    transactionDto.getPaidAmountCredit(),
                    transactionDto.getPaidAmountCheck(),
                    transactionDto.getTransCreditId(),
                    transactionDto.getLast4Digits(),
                    transactionDto.getTransactionCompId());*/


            /*jdbcTemplate.update(sqlQuery.UpdateBlanceToCustomerProfile,
                    transactionDto.getPrevBalance(),
                    transactionDto.getCustomerPhoneNo());
            System.out.println("Customer Balance Edited Successfully");*/

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    private final class TransactionMapperForReturn implements RowMapper<TransactionDto> {

        @Override
        public TransactionDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            TransactionDto transaction = new TransactionDto();


            transaction.setTransactionCompId(rs.getInt("TRANSACTION_COMP_ID"));
            transaction.setTransactionDate(rs.getString("TRANSACTION_DATE"));
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
            transaction.setPaidAmountCash(rs.getDouble("PAID_AMOUNT_CASH"));
            transaction.setPaidAmountCredit(rs.getDouble("TOTAL_AMOUNT_CREDIT"));
            transaction.setPaidAmountCheck(rs.getDouble("TOTAL_AMOUNT_CHECK"));
            transaction.setPaidAmountDebit(rs.getDouble("PAID_AMOUNT_DEBIT"));
            transaction.setStatus(rs.getString("STATUS"));
            transaction.setChangeAmount(rs.getDouble("CHANGE_AMOUNT"));
            transaction.setPrevBalance(rs.getDouble("PREVIOUS_BALANCE"));
            transaction.setBalance(rs.getDouble("BALANCE"));
            transaction.setReceiptNote(rs.getString("RECEIPT_NOTE"));
            transaction.setTransactionNote(rs.getString("TRANSACTION_NOTE"));

            //  transaction.setPrevBalance(rs.getDouble("BALANCE"));

            return transaction;
        }
    }

    public List<TransactionDto> getsalesHistory(String startDate, String endDate) {
        List<TransactionDto> transactionDto = new ArrayList<>();


        try {
            transactionDto = jdbcTemplate.query(sqlQuery.getTransactionDetails, new TransactionMapperWithOutCustomer(), startDate, endDate);

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
                transaction.setLineItemDiscount(lineItemDiscountDouble);
            } else {

                transaction.setDiscount(rs.getDouble("DISCOUNT_AMOUNT"));
                //System.out.println(lineItemDiscount);
            }


            transaction.setSubTotal(rs.getDouble("SUBTOTAL"));
            transaction.setTotalQuantity(rs.getInt("TOTALQUANTITY"));
            transaction.setCustomerPhoneNo(rs.getString("CUSTOMER_PHONENO"));

            if (null == rs.getString("CUSTOMER_PHONENO") || rs.getString("CUSTOMER_PHONENO").isEmpty()) {

                transaction.setCustomerName("");


            } else {
                //getting first and last name of customer to show on the sales history
                String firstName = jdbcTemplate.queryForObject(sqlQuery.getFirstName, new Object[]{rs.getString("CUSTOMER_PHONENO")}, String.class);
                String lastName = jdbcTemplate.queryForObject(sqlQuery.getLastName, new Object[]{rs.getString("CUSTOMER_PHONENO")}, String.class);

                //merging first and last name.
                transaction.setCustomerName(firstName + " " + lastName);
            }


            transaction.setUserId(rs.getInt("USER_ID"));
            String username = jdbcTemplate.queryForObject(sqlQuery.getUsernameFromUser, new Object[]{transaction.getUserId()}, String.class);
            transaction.setUsername(username);

            if (rs.getDouble("BALANCE") == 0 && rs.getString("STATUS").equals("c")) {
                transaction.setPaidAmountCash(rs.getDouble("PAID_AMOUNT_CASH") + rs.getDouble("CHANGE_AMOUNT"));
            } else {
                transaction.setPaidAmountCash(rs.getDouble("PAID_AMOUNT_CASH"));
            }
            transaction.setPaidAmountCredit(rs.getDouble("TOTAL_AMOUNT_CREDIT"));
            transaction.setPaidAmountCheck(rs.getDouble("TOTAL_AMOUNT_CHECK"));
            transaction.setPaidAmountDebit(rs.getDouble("PAID_AMOUNT_DEBIT"));
            transaction.setStatus(rs.getString("STATUS"));
           if(rs.getString("STATUS").equals("c")) {
               transaction.setChangeAmount(rs.getDouble("CHANGE_AMOUNT"));
           }
            transaction.setPrevBalance(rs.getDouble("PREVIOUS_BALANCE"));
            transaction.setBalance(rs.getDouble("BALANCE"));
            transaction.setReceiptNote(rs.getString("RECEIPT_NOTE"));
            transaction.setTransactionNote(rs.getString("TRANSACTION_NOTE"));


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
            customer.setCompanyName(rs.getString("COMPANY_NAME"));
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
                    ps.setString(4, transactionLineItemDto1.getProductNumber());

                    int productQuantity = jdbcTemplate.queryForObject(sqlQuery.getProductQuantity, new Object[]{transactionLineItemDto1.getProductNumber()}, Integer.class);

                    ps.setInt(5, transactionLineItemDto1.getQuantity());

                    int transQuantity = transactionLineItemDto1.getQuantity();

                    //reducing quantity into Stock for transaction
                    productQuantity = productQuantity - transQuantity;

                    int productId = jdbcTemplate.queryForObject(sqlQuery.getProductId, new Object[]{transactionLineItemDto1.getProductNumber()}, Integer.class);

                    jdbcTemplate.update(sqlQuery.updateProductQuantity, productQuantity, productId);

                    //Here I am checking is this phone return or not and if yes then i need to add that phone back to inventory.
                    if(null != transactionLineItemDto1.getImeiNo() && (transactionLineItemDto1.getTransactionStatus().equals("r") || transactionLineItemDto1.getTransactionStatus().equals("p")))
                    {
                        try
                        {
                            jdbcTemplate.update(sqlQuery.addPhoneDetailsAsProduct,
                                    transactionLineItemDto1.getProductNumber(),
                                    transactionLineItemDto1.getImeiNo(),
                                     Math.abs(transactionLineItemDto1.getCost()),
                                    Math.abs(transactionLineItemDto1.getRetail()),
                                    0,
                                    transactionLineItemDto1.getTransactionDate());

                            //jdbcTemplate.update(sqlQuery.updateProductQuantity, productQuantity + transQuantity * -1, productId);
                        System.out.println("This is Phone Return");
                            //System.out.println(Math.abs(transQuantity));
                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }

                    }

                    ps.setDouble(6, transactionLineItemDto1.getRetail());
                    ps.setDouble(7, transactionLineItemDto1.getCost());
                    ps.setDouble(8, transactionLineItemDto1.getDiscount());
                    ps.setDouble(9, transactionLineItemDto1.getDiscountPercentage());
                    ps.setDouble(10, transactionLineItemDto1.getRetailWithDis());
                    ps.setDouble(11, transactionLineItemDto1.getTotalProductPrice());
                    ps.setDouble(12, transactionLineItemDto1.getTotalProductPriceWithTax());
                    ps.setString(13, transactionLineItemDto1.getImeiNo());

                    //Checking is this product is phone,  product has phone id or not if yes then that means this is phone sale so i need to remove IMEI No form Phone Table
                    // And also need to check that it should be complete transaction not a return thats why i am checking the status FLAG.
                    if (transactionLineItemDto1.getPhoneId() != 0 && transactionLineItemDto1.getTransactionStatus().equals("c")) {
                        jdbcTemplate.update(sqlQuery.deleteImeiDetailsFromPhone, transactionLineItemDto1.getPhoneId());
                        System.out.println("This is phone sale: Delete IMEI Successfully!!" + transactionLineItemDto1.getImeiNo());
                    }



                }

                @Override
                public int getBatchSize() {
                    return transactionLineItemDto.size();
                }
            });

            System.out.println("Transaction Line Item Added Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ediTransactionLineItemToDB(List<TransactionLineItemDto> transactionLineItemDto, String previousTransId) {

        try {
            //Here I am just adding transaction lineitem from the regulat add lineitem call and ui gonna send me quantity negative and i am doing
            //Subtraction so it will become positive and added to the stock.

            if(null != transactionLineItemDto) {
                addTransactionLineItemToDB(transactionLineItemDto);
            }

        } catch (Exception e)

        {
            System.out.println(e);
        }


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
            lineItem.setProductNumber(rs.getString("PRODUCT_NO"));
            lineItem.setProductDescription(jdbcTemplate.queryForObject(sqlQuery.getProductDescription, new Object[]{lineItem.getProductNumber()}, String.class));
            lineItem.setQuantity(rs.getInt("QUANTITY"));
            lineItem.setRetail(rs.getDouble("RETAIL"));
            lineItem.setCost(rs.getDouble("COST"));
            lineItem.setDiscount(rs.getDouble("DISCOUNT"));
            lineItem.setDiscountPercentage(rs.getDouble("DISCOUNT_PERCENTAGE"));
            lineItem.setRetailWithDis(rs.getDouble("RETAILWITHDISCOUNT"));
            lineItem.setTotalProductPrice(rs.getDouble("TOTALPRODUCTPRICE"));
            lineItem.setTotalProductPriceWithTax(rs.getDouble("TOTAL_PRODUCT_PRICE_WITH_TAX"));
            lineItem.setImeiNo(rs.getString("IMEI_NO"));

            return lineItem;
        }
    }

    //THIS WILL GIVE LAST TRANSACTION COMP ID WHICH HELP UI TO GENERATE NEXT ID
    public int getLastTransactionId() {

        int lastTransactionID = jdbcTemplate.queryForObject(sqlQuery.getLastTransactionId, new Object[]{}, Integer.class);

        return lastTransactionID;
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
            transaction.setAddTax(rs.getDouble("TAX_AMOUNT"));
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

    public byte[] getReceiptDetailsAlok(int receiptId) throws DocumentException {

         initializeFonts();

        java.util.List<ReceiptDto> receiptDtos = new ArrayList<>();

        receiptDtos = getReceiptDetails(receiptId);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();

        PdfContentByte cb = writer.getDirectContent();

        boolean beginPage = true;
        int y = 0;

        for (int i = 0; i < receiptDtos.get(0).getTransactionLineItemDtoList().size(); i++) {

            if (beginPage) {
                beginPage = false;
                generateLayout(document, cb);
                generateHeader(document, cb);
                y = 680;
            }
            generateDetail(document, cb, i, y, receiptDtos);
            y = y - 15;
            if (y < 50) {
               // printPageNumber(cb);
                document.newPage();
                beginPage = true;
            }


        }
       // printPageNumber(cb);
        generatTransactionDetails(document, cb, 0, y, receiptDtos);




            /*byte[] pdfDataBytes = baos.toByteArray();

            return pdfDataBytes;*/



//        cb.rectangle(150, 50, 250, 650);
//        cb.rectangle(150, 50, 250, 630);
//        cb.moveTo(150, 655);
//        cb.lineTo(400, 655);
//        cb.stroke();

//            createHeadings(cb, 1, 685, "Date:" + " " + "2016-12-12");
//            createHeadings(cb, 72, 685, "Time:" + " " + "23:59:59");
//            createHeadings(cb, 142, 685, "CSR:" + " " + "Alok");
//            createHeadings(cb, 192, 685, "Sale Id:" + " " + 100);
//
//
//            createHeadings(cb, 1, 665, "Item Number");
//            createHeadings(cb, 72, 665, "Item Description");
//            createHeadings(cb,215 , 665, "Price");

//
//        cb.rectangle(150, 50, 250, 650);
//        cb.rectangle(150, 50, 250, 630);
//        cb.moveTo(150, 655);
//        cb.lineTo(400, 655);
//        cb.stroke();

           // createContent(cb,48,400,String.valueOf(1),PdfContentByte.ALIGN_RIGHT);

            document.close();

            byte[] pdfDataBytes = byteArrayOutputStream.toByteArray();



            return pdfDataBytes;


    }


    private void generateLayout(Document doc, PdfContentByte cb) {

        try {

           cb.setLineWidth(0f);

            // Invoice Header box layout
            // cb.rectangle(150,50,250,650);


           // cb.stroke();

            // Invoice Header box Text Headings


            // Invoice Detail box layout

            cb.rectangle(0, 50, 200, 690);
            cb.rectangle(0, 50, 200, 670);
            cb.moveTo(1, 695);
            cb.lineTo(201, 695);

//            cb.moveTo(50,50);
//            cb.lineTo(50,650);
//            cb.moveTo(150,50);
//            cb.lineTo(150,650);
//            cb.moveTo(430,50);
//            cb.lineTo(430,650);
//            cb.moveTo(500,50);
//            cb.lineTo(500,650);
            cb.stroke();

            // Invoice Detail box Text Headings


            createHeadings(cb, 1, 725, "Date:" + " " + "2016-12-12");
            createHeadings(cb, 55, 725, "Time:" + " " + "23:59:59");
            createHeadings(cb, 110, 725, "CSR:" + " " + "Alok");
            createHeadings(cb, 155, 725, "Sale Id:" + " " + 100);
//
//
            createHeadings(cb, 1, 703, "Item Number");
            createHeadings(cb, 55, 703, "Item Description");
            createHeadings(cb, 165, 703, "Price");


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void createHeadings(PdfContentByte cb, float x, float y, String text){


        cb.beginText();
        cb.setFontAndSize(bfBold, 6);
        cb.setTextMatrix(x,y);
        cb.showText(text.trim());
        cb.endText();

    }
    private void createHeadingsForAddress(PdfContentByte cb, float x, float y, String text){


        cb.beginText();
        cb.setFontAndSize(bfBold, 8);
        cb.setTextMatrix(x,y);
        cb.showText(text.trim());
        cb.endText();

    }

    private void generateHeader(Document doc, PdfContentByte cb) {

        try {

            createStoreNameHeadings(cb, 35, 810, "Map Wireless World Inc");//197
            createHeadingsForAddress(cb, 60, 795, "926 Montreal Road");//238
            createHeadingsForAddress(cb, 75, 785, "Suite-16");//255
            createHeadingsForAddress(cb, 60, 775, "Clarkston, GA 30021");//238
            createHeadingsForAddress(cb, 72, 765, "470-428-4284");//250

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void generateDetail(Document doc, PdfContentByte cb, int index, int y, List<ReceiptDto> receiptDtos) {
        DecimalFormat df = new DecimalFormat("0.00");

        try {

            createContent(cb, 1, y, receiptDtos.get(0).getTransactionLineItemDtoList().get(index).getProductNumber(), PdfContentByte.ALIGN_LEFT);
            createContent(cb, 55, y, receiptDtos.get(0).getTransactionLineItemDtoList().get(index).getProductDescription(), PdfContentByte.ALIGN_LEFT);
            createContent(cb, 180, y, df.format(receiptDtos.get(0).getTransactionLineItemDtoList().get(index).getTotalProductPrice()), PdfContentByte.ALIGN_RIGHT);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void generatTransactionDetails(Document doc, PdfContentByte cb, int index, int y, List<ReceiptDto> receiptDtos) {
        DecimalFormat df = new DecimalFormat("0.00");

        try {

            createContentForTotal(cb, 120, y - 30, "Sub Total" + "          " + receiptDtos.get(0).getTransactionDtoList().get(0).getSubTotal(), PdfContentByte.ALIGN_LEFT);
            createContentForTotal(cb, 120, y - 45, "Tax" + " " + "7%" + "      " + "        " + receiptDtos.get(0).getTransactionDtoList().get(0).getTax(), PdfContentByte.ALIGN_LEFT);
            createContentForTotal(cb, 120, y - 60, "Total" + "   " + "      " + "        " + receiptDtos.get(0).getTransactionDtoList().get(0).getTotalAmount(), PdfContentByte.ALIGN_LEFT);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }



    private void createStoreNameHeadings(PdfContentByte cb, float x, float y, String text) {


        cb.beginText();
        cb.setFontAndSize(bfBold, 12);
        cb.setTextMatrix(x, y);
        cb.showText(text.trim());
        cb.endText();

    }
//
//    private void printPageNumber(PdfContentByte cb) {
//
//
//        cb.beginText();
//        cb.setFontAndSize(bfBold, 8);
//        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Page No. " + (pageNumber + 1), 570, 25, 0);
//        cb.endText();
//
//        pageNumber++;
//
//    }

    private void createContent(PdfContentByte cb, float x, float y, String text, int align) {


        cb.beginText();
        cb.setFontAndSize(bf, 6);
        cb.showTextAligned(align, text.trim(), x, y, 0);
        cb.endText();

    }

    private void createContentForTotal(PdfContentByte cb, float x, float y, String text, int align) {


        cb.beginText();
        cb.setFontAndSize(bf, 8);
        cb.showTextAligned(align, text.trim(), x, y, 0);
        cb.endText();

    }



    private void initializeFonts() {


        try {
            bfBold = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public void editReceiptNote(int transactionId, String receiptNote, String transactionNote) {

        try {
            jdbcTemplate.update(sqlQuery.editTransactionNote, receiptNote, transactionNote, transactionId);
        } catch (Exception e) {
            System.out.println(e);


        }
    }
}






