package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.PageSetUpManager;
import com.abm.pos.com.abm.pos.dto.CustomerDto;
import com.abm.pos.com.abm.pos.dto.MultyAddProductDto;
import com.abm.pos.com.abm.pos.dto.PageSetUpDto;
import com.abm.pos.com.abm.pos.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by asp5045 on 7/14/16.
 */

@RestController
@RequestMapping("")
public class PageSetUpController {

    @Autowired
    PageSetUpManager pageSetUpManager;


    @RequestMapping(value = "/readExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MultyAddProductDto> readExcelSheet() {

        return pageSetUpManager.readExcelSheet();
    }

    @RequestMapping(value = "/editPageSetUpDetails", method = RequestMethod.POST)
    public void editPageSetupDetails(@RequestBody PageSetUpDto pageSetUpDto) {

        pageSetUpManager.editPageSetupDetails(pageSetUpDto);
    }

    @RequestMapping(value = "/getPageSetUpDetails", method = RequestMethod.GET)
    public List<PageSetUpDto> getProduct() {

        return pageSetUpManager.getPageSetUpDetails();
    }

    @RequestMapping(value = "/addMultipleProducts", method = RequestMethod.POST, consumes = "application/json")
    public void addProduct(@RequestBody List<MultyAddProductDto> productDto) {

        pageSetUpManager.addProductToDB(productDto);
    }

    @RequestMapping(value = "/addMultyCustomer",method = RequestMethod.POST, consumes = "application/json")
    public void addMultyCustomer(@RequestBody List<CustomerDto> customerDto)
    {
        pageSetUpManager.addMultyCustomer(customerDto);
    }

    @RequestMapping(value = "/getLicenceKey", method = RequestMethod.GET)
    public boolean getLicenceKey(@RequestParam String licenceKey) {

        return pageSetUpManager.getLicenceKey(licenceKey);
    }


}
