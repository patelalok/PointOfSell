package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.CustomerHandlerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by asp5045 on 5/18/16.
 */
@RestController
@RequestMapping("")
public class CustomerHandlerController {

    @Autowired
    CustomerHandlerManager customerHandlerManager;

    @RequestMapping(value = "/getCustomerDetails", method = RequestMethod.GET, produces = "application/json")
    public void getCustomerDetails(String phoneNo)
    {
        customerHandlerManager.getCustomerDetailsFromDB(phoneNo);
    }
}
