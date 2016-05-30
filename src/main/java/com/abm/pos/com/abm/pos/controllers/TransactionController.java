package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.TransactionManager;
import com.abm.pos.com.abm.pos.dto.AddTransactionDto;
import com.abm.pos.com.abm.pos.dto.AddTransactionLineItemDto;
import com.abm.pos.com.abm.pos.dto.AddTransactionPaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by asp5045 on 5/20/16.
 */
@RestController
@RequestMapping("")
public class TransactionController {

   // @Autowired
    TransactionManager  transactionManager = new TransactionManager();

    @RequestMapping(method = RequestMethod.POST, value = "/addTransaction", produces = "application/json")
    public void addTransactionDetails(@RequestBody AddTransactionDto addTransactionDto)
    {
        transactionManager.addTransactionToDB(addTransactionDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getTransaction", produces = "application/json")
    public void getTransactionDetails(@RequestBody int transactionId)
    {
        transactionManager.getTransactionDetails(transactionId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addTransactionLineItem", produces = "application/json")
    public void addTransactionLineItem(AddTransactionLineItemDto addTransactionLineItemDto)
    {
        transactionManager.addTransactionLineItemToDB(addTransactionLineItemDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getTransactionLineItem", produces = "application/json")
    public void getTransactionLineItem(AddTransactionLineItemDto addTransactionLineItemDto)
    {
        transactionManager.getTransactionLineItemDetails(addTransactionLineItemDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addTransactionPaymentDetails", produces = "application/json")
    public void addTransactionPayment(AddTransactionPaymentDto transactionPaymentDto)
    {
        transactionManager.addTransactionPaymentToDB(transactionPaymentDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getTransactionPaymentDetails", produces = "application/json")
    public void getTransactionPayment(AddTransactionPaymentDto transactionPaymentDto)
    {
        transactionManager.getTransactionPaymentDetails(transactionPaymentDto);
    }



}
