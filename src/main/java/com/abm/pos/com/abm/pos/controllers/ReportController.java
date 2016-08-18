package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.BarcodeManager;
import com.abm.pos.com.abm.pos.bl.ReportManager;
import com.abm.pos.com.abm.pos.dto.reports.CommonComparisonDto;
import com.abm.pos.com.abm.pos.dto.reports.CommonComparisonTotalDto;
import com.abm.pos.com.abm.pos.dto.reports.CommonInventoryDto;
import com.abm.pos.com.abm.pos.dto.reports.Top50ItemsDto;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Created by asp5045 on 6/21/16.
 */

@RestController
@RequestMapping("")

public class ReportController {

    @Autowired
    ReportManager reportManager;

    @Autowired
    BarcodeManager barcodeManager;

    @RequestMapping(value = "/getTop50Items",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonComparisonTotalDto getTop50Items(@RequestParam String startDate, String endDate)
    {
        return reportManager.getTop50Items(startDate,endDate);
    }

    @RequestMapping(value = "/getSalesByCategory",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonComparisonTotalDto getSalesByCategory(@RequestParam String startDate, String endDate)
    {
        return reportManager.getSalesByCategory(startDate,endDate);
    }

    @RequestMapping(value = "/getSalesByVendor",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonComparisonTotalDto getSalesByVendor(@RequestParam String startDate, String endDate)
    {
        return reportManager.getSalesByVendor(startDate,endDate);
    }

    @RequestMapping(value = "/getSalesByBrand",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonComparisonTotalDto getSalesByBrand(@RequestParam String startDate, String endDate)
    {
        return reportManager.getSalesByBrand(startDate,endDate);
    }

    @RequestMapping(value = "/getSalesByProduct",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonComparisonTotalDto getSalesByProduct(@RequestParam String startDate, String endDate)
    {
        return reportManager.getSalesByProduct(startDate,endDate);
    }

    @RequestMapping(value = "/getSalesByUser",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonComparisonTotalDto getSalesByUser(@RequestParam String startDate, String endDate)
    {
        return reportManager.getSalesByUser(startDate,endDate);
    }

    @RequestMapping(value = "/getSalesByCustomer",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonComparisonTotalDto getSalesByCustomer(@RequestParam String startDate, String endDate) {
        return reportManager.getSalesByCustomer(startDate, endDate);
    }

    @RequestMapping(value = "/getInventoryByCategory",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CommonInventoryDto> getInventoryByCategory()
    {
        return reportManager.getInventoryByCategory();
    }

   @RequestMapping(value = "/getInventoryByVendor",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CommonInventoryDto> getInventoryByVendor()
    {
        return reportManager.getInventoryByVendor();
    }



    @RequestMapping(value = "/getInventoryByBrand",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CommonInventoryDto> getInventoryByBrand()
    {
        return reportManager.getInventoryByBrand();
    }






}
