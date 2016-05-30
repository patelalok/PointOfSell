package com.abm.pos.com.abm.pos.controllers;

import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.impl.upcean.UPCA;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    public void getBarcode() throws IOException {


        Code39Bean bean = new Code39Bean();

        final int dpi = 150;

        //Configure the barcode generator
        bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); //makes the narrow bar, width exactly one pixel
        bean.setWideFactor(3);
        bean.doQuietZone(false);

        //Open output file
        File outputFile = new File("resources"+"/"+"images"+"/"+"out.png");
        OutputStream out = new FileOutputStream(outputFile);

        try {

            //Set up the canvas provider for monochrome PNG output
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                    out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

            //Generate the barcode
            bean.generateBarcode(canvas, "Hello World");

            //Signal end of generation
            canvas.finish();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}
