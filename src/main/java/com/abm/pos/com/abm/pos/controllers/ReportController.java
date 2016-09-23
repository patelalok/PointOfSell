package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.BarcodeManager;
import com.abm.pos.com.abm.pos.bl.ReportManager;
import com.abm.pos.com.abm.pos.dto.reports.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by asp5045 on 6/21/16.
 */

@RestController
@RequestMapping("")
@CrossOrigin(origins = {"*"})
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
    public CommonInvetoryTotalDto getInventoryByCategory()
    {
        return reportManager.getInventoryByCategory();
    }

   @RequestMapping(value = "/getInventoryByVendor",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonInvetoryTotalDto getInventoryByVendor()
    {
        return reportManager.getInventoryByVendor();
    }



    @RequestMapping(value = "/getInventoryByBrand",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonInvetoryTotalDto getInventoryByBrand()
    {
        return reportManager.getInventoryByBrand();
    }


    @RequestMapping(value= "/printSaleByCategory", method = RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity<byte[]> getPrintClosingDetails(@RequestParam String startDate, @RequestParam String endDate, @RequestParam int reportNo) throws IOException, DocumentException {
        //System.out.println(productName + price + noOfBarcode);

        byte[] pdfDataBytes = reportManager.printSaleByCommonName(startDate,endDate,reportNo);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
        headers.add("Access-Control-Allow-Headers", "Content-Type");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdfDataBytes, headers, HttpStatus.OK);
        return response;
    }
}
