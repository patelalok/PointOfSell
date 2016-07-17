package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.ReportManager;
import com.abm.pos.com.abm.pos.dto.reports.CommonComparisonDto;
import com.abm.pos.com.abm.pos.dto.reports.CommonInventoryDto;
import com.abm.pos.com.abm.pos.dto.reports.Top50ItemsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by asp5045 on 6/21/16.
 */

@RestController
@RequestMapping("")

public class ReportController {

    @Autowired
    ReportManager reportManager;

    @RequestMapping(value = "/getTop50Items",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Top50ItemsDto> getTop50Items(@RequestParam String startDate, String endDate)
    {
        return reportManager.getTop50Items(startDate,endDate);
    }

    @RequestMapping(value = "/getSalesByCategory",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CommonComparisonDto> getSalesByCategory(@RequestParam String startDate, String endDate)
    {
        return reportManager.getSalesByCategory(startDate,endDate);
    }

    @RequestMapping(value = "/getSalesByVendor",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CommonComparisonDto> getSalesByVendor(@RequestParam String startDate, String endDate)
    {
        return reportManager.getSalesByVendor(startDate,endDate);
    }

    @RequestMapping(value = "/getSalesByBrand",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CommonComparisonDto> getSalesByBrand(@RequestParam String startDate, String endDate)
    {
        return reportManager.getSalesByBrand(startDate,endDate);
    }

    @RequestMapping(value = "/getSalesByProduct",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CommonComparisonDto> getSalesByProduct(@RequestParam String startDate, String endDate)
    {
        return reportManager.getSalesByProduct(startDate,endDate);
    }

    @RequestMapping(value = "/getSalesByUser",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CommonComparisonDto> getSalesByUser(@RequestParam String startDate, String endDate)
    {
        return reportManager.getSalesByUser(startDate,endDate);
    }

    @RequestMapping(value = "/getSalesByCustomer",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CommonComparisonDto> getSalesByCustomer(@RequestParam String startDate, String endDate)
    {
        return reportManager.getSalesByCustomer(startDate,endDate);
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
