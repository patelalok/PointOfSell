package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.BarcodeManager;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.impl.upcean.UPCA;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by asp5045 on 5/23/16.
 */
@RestController
@RequestMapping("")
public class BarcodeGenerator {

    @Autowired
    BarcodeManager barcodeManager;

    @RequestMapping(value="/getpdf", method= RequestMethod.GET,produces = "application/pdf")
    public Document getBarcode(@RequestParam String productName, @RequestParam double price, @RequestParam int noOfBarcode) throws IOException, DocumentException
    {
       return barcodeManager.getPdf(productName,price,noOfBarcode);

    }



}

