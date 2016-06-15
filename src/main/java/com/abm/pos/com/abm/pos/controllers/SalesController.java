package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.SalesManager;
import com.abm.pos.com.abm.pos.dto.TransactionDto;
import com.abm.pos.com.abm.pos.dto.TransactionLineItemDto;
import com.abm.pos.com.abm.pos.dto.TransactionPaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/getSalesHistory", method = RequestMethod.GET, produces = "application/json")
    public List<TransactionDto> getTransactionFromDB(@RequestParam String startDate)
    {
        return salesManager.getTransactionDetails(startDate);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addTransactionLineItem", produces = "application/json")
    public void addTransactionLineItem(TransactionLineItemDto transactionLineItemDto)
    {
        salesManager.addTransactionLineItemToDB(transactionLineItemDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getTransactionLineItem", produces = "application/json")
    public void getTransactionLineItem(TransactionLineItemDto transactionLineItemDto)
    {
        salesManager.getTransactionLineItemDetails(transactionLineItemDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addTransactionPaymentDetails", produces = "application/json")
    public void addTransactionPayment(TransactionPaymentDto transactionPaymentDto)
    {
        salesManager.addTransactionPaymentToDB(transactionPaymentDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getTransactionPaymentDetails", produces = "application/json")
    public void getTransactionPayment(TransactionPaymentDto transactionPaymentDto)
    {
        salesManager.getTransactionPaymentDetails(transactionPaymentDto);
    }
}
