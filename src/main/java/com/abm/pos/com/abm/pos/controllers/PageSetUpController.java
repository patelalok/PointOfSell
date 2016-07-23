package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.PageSetUpManager;
import com.abm.pos.com.abm.pos.dto.PageSetUpDto;
import com.abm.pos.com.abm.pos.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public List<ProductDto> readExcelSheet() {

        return pageSetUpManager.readExcelSheet();
    }

    @RequestMapping(value = "/editTax", method = RequestMethod.POST, consumes = "application/json")
    public void addProduct(@RequestBody PageSetUpDto pageSetUpDto) {

        pageSetUpManager.editPageSetupDetails(pageSetUpDto);
    }

    @RequestMapping(value = "/getPageSetUpDetails", method = RequestMethod.GET)
    public List<PageSetUpDto> getProduct() {

        return pageSetUpManager.getPageSetUpDetails();
    }

}
