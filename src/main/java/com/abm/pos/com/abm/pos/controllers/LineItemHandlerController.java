package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.LineItemHandlerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by asp5045 on 5/18/16.
 */
@RestController
@RequestMapping("")
public class LineItemHandlerController {

    @Autowired
    LineItemHandlerManager lineItemHandlerManager;

    @RequestMapping(value = "/getProductDetails",method = RequestMethod.GET, produces = "application/json")
    public void getProductForLineItem(String productId)
    {
        lineItemHandlerManager.getProductDetails(productId);

    }

}

