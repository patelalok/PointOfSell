package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.DashboardManager;
import com.abm.pos.com.abm.pos.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by asp5045 on 6/15/16.
 */

@RestController
@RequestMapping("")
public class DashboardController
{
    @Autowired
    DashboardManager dashboardManager;

    @RequestMapping(value = "/getDashboardDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addProduct(@RequestBody ProductDto productDto) {

        dashboardManager.getDashboardDetails();
    }

}
