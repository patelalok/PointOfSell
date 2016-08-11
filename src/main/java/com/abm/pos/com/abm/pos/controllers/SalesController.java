package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.SalesManager;
import com.abm.pos.com.abm.pos.dto.ReceiptDto;
import com.abm.pos.com.abm.pos.dto.TransactionDto;
import com.abm.pos.com.abm.pos.dto.TransactionLineItemDto;
import com.abm.pos.com.abm.pos.dto.TransactionPaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by asp5045 on 6/12/16.
 */
@RestController
@RequestMapping("")
public class SalesController {

    @Autowired
    SalesManager salesManager;


    @RequestMapping(value = "/addTransaction", method = RequestMethod.POST, consumes = "application/json")
    public void addTransactionToDB(@RequestBody TransactionDto transactionDto)
    {
        salesManager.addTransaction(transactionDto);
    }

    @RequestMapping(value = "/editTransaction", method = RequestMethod.POST, consumes = "application/json")
    public void editTransactionToDB(@RequestBody TransactionDto transactionDto, @RequestParam String previousTransId)
    {
        salesManager.editTransaction(transactionDto,previousTransId);
    }

    @RequestMapping(value = "/getSalesHistory", method = RequestMethod.GET, produces = "application/json")
    public List<TransactionDto> getTransactionFromDB(@RequestParam String startDate, @RequestParam String endDate)
    {
        return salesManager.getsalesHistory(startDate,endDate);
    }


    @RequestMapping(value = "/getReceiptDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReceiptDto> getReceiptDetails(@RequestParam int receiptId)
    {
       return salesManager.getReceiptDetails(receiptId);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/addTransactionLineItem", consumes = "application/json")
    public void addTransactionLineItem(@RequestBody List<TransactionLineItemDto> transactionLineItemDto)
    {
        salesManager.addTransactionLineItemToDB(transactionLineItemDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editTransactionLineItem", consumes = "application/json")
    public void ediTransactionLineItem(@RequestBody List<TransactionLineItemDto> transactionLineItemDto,@RequestParam String previousTransId)
    {
        salesManager.ediTransactionLineItemToDB(transactionLineItemDto,previousTransId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getTransactionLineItem", produces = "application/json")
    public List<TransactionLineItemDto> getTransactionLineItem(@RequestParam int  transactionCompId)
    {
        return salesManager.getTransactionLineItemDetails(transactionCompId);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/getLastTransactionId", produces = "application/json")
    public int getTransactionLineItem()
    {
        return salesManager.getLastTransactionId();
    }


   /* @RequestMapping(method = RequestMethod.POST, value = "/addTransactionPaymentDetails", produces = "application/json")
    public void addTransactionPayment(TransactionPaymentDto transactionPaymentDto)
    {
        salesManager.addTransactionPaymentToDB(transactionPaymentDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getTransactionPaymentDetails", produces = "application/json")
    public void getTransactionPayment(TransactionPaymentDto transactionPaymentDto)
    {
        salesManager.getTransactionPaymentDetails(transactionPaymentDto);
    }*/
}
