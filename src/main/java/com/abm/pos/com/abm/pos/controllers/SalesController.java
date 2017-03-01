package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.SalesManager;
import com.abm.pos.com.abm.pos.dto.ReceiptDto;
import com.abm.pos.com.abm.pos.dto.TransactionDto;
import com.abm.pos.com.abm.pos.dto.TransactionLineItemDto;
import com.abm.pos.com.abm.pos.dto.TransactionPaymentDto;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by asp5045 on 6/12/16.
 */
@RestController
@RequestMapping("")
@CrossOrigin(origins = {"*"})
public class SalesController {

    @Autowired
    SalesManager salesManager;


    @RequestMapping(value = "/addTransaction", method = RequestMethod.POST, consumes = "application/json")
    public void addTransactionToDB(@RequestBody TransactionDto transactionDto)
    {
        salesManager.addTransaction(transactionDto);
    }

    @RequestMapping(value = "/editTransaction", method = RequestMethod.POST, consumes = "application/json")
    public void editTransactionToDB(@RequestBody (required = false)TransactionDto transactionDto , @RequestParam String previousTransId)
    {
        salesManager.editTransaction(transactionDto,previousTransId);
    }

    @RequestMapping(value = "/editReceiptNote", method = RequestMethod.POST, consumes = "application/json")
    public void editReceiptNote(@RequestParam int transactionId, @RequestParam String receiptNote, @RequestParam String transactionNote)
    {
        salesManager.editReceiptNote(transactionId,receiptNote, transactionNote);
    }

    @RequestMapping(value = "/getSalesHistory", method = RequestMethod.GET, produces = "application/json")
    public List<TransactionDto> getTransactionFromDB(@RequestParam String startDate, @RequestParam String endDate)
    {
        return salesManager.getsalesHistory(startDate,endDate);
    }


    @RequestMapping(value = "/getReceiptDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReceiptDto> getReceiptDetails(@RequestParam int receiptId)
    {
       return salesManager.getReceiptDetails(receiptId);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/addTransactionLineItem", consumes = "application/json")
    public void addTransactionLineItem(@RequestBody List<TransactionLineItemDto> transactionLineItemDto, @RequestParam String phoneNo)
    {
        salesManager.addTransactionLineItemToDB(transactionLineItemDto, phoneNo);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addProductPriceByCustomer", consumes = "application/json")
    public void addProductPriceByCustomer(@RequestBody List<TransactionLineItemDto> transactionLineItemDto, @RequestParam String phoneNo)
    {
        salesManager.addProductPriceByCustomer(transactionLineItemDto, phoneNo);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editTransactionLineItem", consumes = "application/json")
    public void ediTransactionLineItem(@RequestBody (required = false) List<TransactionLineItemDto> transactionLineItemDto,@RequestParam String previousTransId)
    {
        salesManager.ediTransactionLineItemToDB(transactionLineItemDto,previousTransId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getTransactionLineItem", produces = "application/json")
    public List<TransactionLineItemDto> getTransactionLineItem(@RequestParam int  transactionCompId)
    {
        return salesManager.getTransactionLineItemDetails(transactionCompId);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/getLastTransactionId", produces = "application/json")
    public int getTransactionLineItem()
    {
        return salesManager.getLastTransactionId();
    }


    @RequestMapping(value= "/getReceiptDetailsForThermalPrint", method = RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity<byte[]> getPrintClosingDetails(@RequestParam int receiptId, HttpServletResponse httpServletResponse) throws IOException, DocumentException {
        //System.out.println(productName + price + noOfBarcode);

        byte[] pdfDataBytes = salesManager.getReceiptDetailsAlok(receiptId);


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

