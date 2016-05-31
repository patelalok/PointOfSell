package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.CustomerManager;
import com.abm.pos.com.abm.pos.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by asp5045 on 5/18/16.
 */
@RestController
@RequestMapping("")
public class CustomerController {

    @Autowired
    CustomerManager customerManager;

    @RequestMapping(value = "/addCustomer",method = RequestMethod.POST, consumes = "application/json")
    public void addCustomer(@RequestBody CustomerDto customerDto)
    {
        customerManager.addCustomerToDB(customerDto);
    }

    @RequestMapping(value = "/editCustomer",method = RequestMethod.POST, consumes = "application/json")
    public void editCustomer(@RequestBody CustomerDto customerDto)
    {
        customerManager.editCustomerToDB(customerDto);
    }

    @RequestMapping(value = "/getCustomerDetail", method = RequestMethod.GET, produces = "application/json")
    public void getCustomerDetails(String phoneNo)
    {
        customerManager.getCustomerDetailsFromDB(phoneNo);
    }

    @RequestMapping(value = "/deleteCustomer",method = RequestMethod.POST, consumes = "application/json")
    public void editDelete(@RequestBody String phoneNo)
    {
        customerManager.deleteCustomerToDB(phoneNo);
    }


}
