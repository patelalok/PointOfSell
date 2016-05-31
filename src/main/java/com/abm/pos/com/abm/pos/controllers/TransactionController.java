package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.TransactionManager;
import com.abm.pos.com.abm.pos.dto.TransactionDto;
import com.abm.pos.com.abm.pos.dto.TransactionLineItemDto;
import com.abm.pos.com.abm.pos.dto.TransactionPaymentDto;
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
    public void addTransactionDetails(@RequestBody TransactionDto transactionDto)
    {
        transactionManager.addTransactionToDB(transactionDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getTransaction", produces = "application/json")
    public void getTransactionDetails(@RequestBody int transactionId)
    {
        transactionManager.getTransactionDetails(transactionId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addTransactionLineItem", produces = "application/json")
    public void addTransactionLineItem(TransactionLineItemDto transactionLineItemDto)
    {
        transactionManager.addTransactionLineItemToDB(transactionLineItemDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getTransactionLineItem", produces = "application/json")
    public void getTransactionLineItem(TransactionLineItemDto transactionLineItemDto)
    {
        transactionManager.getTransactionLineItemDetails(transactionLineItemDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addTransactionPaymentDetails", produces = "application/json")
    public void addTransactionPayment(TransactionPaymentDto transactionPaymentDto)
    {
        transactionManager.addTransactionPaymentToDB(transactionPaymentDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getTransactionPaymentDetails", produces = "application/json")
    public void getTransactionPayment(TransactionPaymentDto transactionPaymentDto)
    {
        transactionManager.getTransactionPaymentDetails(transactionPaymentDto);
    }



}
