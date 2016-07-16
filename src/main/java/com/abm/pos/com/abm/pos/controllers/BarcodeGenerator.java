package com.abm.pos.com.abm.pos.controllers;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.impl.upcean.UPCA;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
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

    @RequestMapping(value="/getpdf", method= RequestMethod.GET)
    public void getBarcode(@RequestParam String ProductName, @RequestParam double price, @RequestParam int noOfBarcode) throws IOException, DocumentException {

        Code39Bean bean = new Code39Bean();
        final int dpi = 150;
        //String name = "Running shoes";
       // String price = "$12.99";

        //Configure the barcode generator
        bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); //makes the narrow bar, width exactly one pixel
        bean.setWideFactor(3);
        bean.doQuietZone(false);

        //Open output file
        File outputFile = new File("/Users/asp5045/Downloads/out1.png");
        OutputStream out = new FileOutputStream(outputFile);

        try {

            //Set up the canvas provider for monochrome PNG output
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                    out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);


            //Generate the barcode
            bean.generateBarcode(canvas,ProductName + price);


            //Signal end of generation
            canvas.finish();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally {
            out.close();
        }


        Document document = new Document();

            FileOutputStream file = new FileOutputStream("/Users/asp5045/Downloads/AddImageExample2.pdf");
            PdfWriter writer = PdfWriter.getInstance(document, file);




            document.open();

            //document.add(new Paragraph("Image Example"));

            //Add Image
            Image image1 = Image.getInstance("/Users/asp5045/Downloads/out1.png");
            //Fixed Positioning
            image1.setAbsolutePosition(10f, 770f);
            //Scale to new height and new width of image
            image1.scaleAbsolute(100, 50);
            //Add to document

            for(int i = 0; i <= noOfBarcode; i++) {
                document.add(image1);
            }


            //String imageUrl = "http://www.eclipse.org/xtend/images/java8_logo.png";
            //Image image2 = Image.getInstance(new URL(imageUrl));
            //document.add(image2);

            document.close();
            writer.close();



    }


}

