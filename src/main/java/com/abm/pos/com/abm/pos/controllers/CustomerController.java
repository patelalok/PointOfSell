package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.CustomerManager;
import com.abm.pos.com.abm.pos.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by asp5045 on 5/18/16.
 */
@RestController
@RequestMapping("")
@CrossOrigin(origins = {"*"})
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
    public List<CustomerDto> getCustomerDetails()
    {
        return customerManager.getCustomerDetailsFromDB();
    }

    @RequestMapping(value = "/getCustomerBalance", method = RequestMethod.GET, produces = "application/json")
    public String getCustomerBalance(@RequestParam String phoneNo)
    {
        return customerManager.getCustomerBalance(phoneNo);
    }

    @RequestMapping(value = "/deleteCustomer",method = RequestMethod.POST)
    public ResponseEntity editDelete(@RequestParam int custId)
    {
        int result = 0;

        result = customerManager.deleteCustomerToDB(custId);

        if(result == 1)
        {
            return ResponseEntity.ok("Customer Deleted Successfully");
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body("Can not delete Customer, This customer has some sales records.");
    }


}
