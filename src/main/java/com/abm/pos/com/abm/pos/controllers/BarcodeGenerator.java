package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.BarcodeManager;
import com.itextpdf.text.BadElementException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import org.apache.commons.io.IOUtils;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.impl.upcean.UPCA;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
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
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
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

    Document document = new Document();

    @RequestMapping(value="/getpdf", method= RequestMethod.GET,produces = "application/pdf")
    public ResponseEntity<InputStreamResource> getBarcode(@RequestParam String productName, @RequestParam double price, @RequestParam int noOfBarcode) throws IOException, DocumentException
    {
        Image img1 = Image.getInstance("Screen Shot 2016-07-11 at 1.49.18 PM.png");
        try {

            Code39Bean bean = new Code39Bean();
            final int dpi = 150;

            //Configure the barcode generator
            bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); //makes the narrow bar, width exactly one pixel
            bean.setWideFactor(3);
            bean.doQuietZone(false);

            //Open output file
            File outputFile = new File("Screen Shot 2016-07-11 at 1.49.18 PM.png");
            OutputStream out = new FileOutputStream(outputFile);
            try {

                //Set up the canvas provider for monochrome PNG output
                BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                        out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);


                //Generate the barcode
                bean.generateBarcode(canvas, productName + price);


                //Signal end of generation
                canvas.finish();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                out.close();
            }


            PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream("AddImageExample.pdf"));
            document.open();

            PdfPTable table = new PdfPTable(5); // 3 columns.

            table.setWidthPercentage(100); //Width 100%
            // table.setSpacingBefore(10f); //Space before table
            //  table.setSpacingAfter(24f); //Space after table

            //Set Column widths
            float[] columnWidths = {10f, 10f, 10f, 10f, 10f};
            table.setWidths(columnWidths);

            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);

            document.add(table);

            document.close();
            writer.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


        String fileName = "AddTableExample.pdf";
        ClassPathResource pdfFile = new ClassPathResource(fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
        headers.add("Access-Control-Allow-Headers", "Content-Type");
        headers.add("Content-Disposition", "filename=" + fileName);
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");



       headers.setContentLength(pdfFile.contentLength());
        ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
              new InputStreamResource(pdfFile.getInputStream()), headers, HttpStatus.OK);

        return response;





    }



}

