package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.AddTransactionManager;
import com.abm.pos.com.abm.pos.dto.AddTransactionDto;
import com.abm.pos.com.abm.pos.dto.AddTransactionLineItemDto;
import com.abm.pos.com.abm.pos.dto.AddVendorDto;
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
public class AddTransactionController {

    @Autowired
    AddTransactionManager addTransactionManager;

    @RequestMapping(method = RequestMethod.POST, value = "/addTransaction", produces = "application/json")
    public void addTransactionDetails(@RequestBody AddTransactionDto addTransactionDto)
    {
        addTransactionManager.addTransactionToDB(addTransactionDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addTransactionLineItem", produces = "application/json")
    public void AddTransactionLineItem(AddTransactionLineItemDto addTransactionLineItemDto)
    {
        addTransactionManager.addTransactionLineItemToDB(addTransactionLineItemDto);
    }


}
