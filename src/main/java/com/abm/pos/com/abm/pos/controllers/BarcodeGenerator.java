package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.BarcodeManager;

import com.abm.pos.com.abm.pos.dto.FinalTotalForReportsDto;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import java.io.*;

/**
 * Created by asp5045 on 5/23/16.
 */
@RestController
@RequestMapping("")
public class BarcodeGenerator {

    @Autowired
    BarcodeManager barcodeManager;

    @Autowired
    ServletContext context;


    @RequestMapping(value= "/getPdf", method = RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity<InputStreamResource> getBarcode(@RequestParam String productName,@RequestParam double price, @RequestParam int noOfBarcode) throws IOException, DocumentException {
        System.out.println(productName + price + noOfBarcode);

        barcodeManager.getPdf(productName,price,noOfBarcode);

        ClassPathResource pdfFile = new ClassPathResource("downloads/AddTableExample2.pdf");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
        headers.add("Access-Control-Allow-Headers", "Content-Type");
        headers.add("Content-Disposition", "filename=" + "AddTableExample1.pdf");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        headers.setContentLength(pdfFile.contentLength());
        ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
                new InputStreamResource(pdfFile.getInputStream()), headers, HttpStatus.OK);

        return response;

    }
}



