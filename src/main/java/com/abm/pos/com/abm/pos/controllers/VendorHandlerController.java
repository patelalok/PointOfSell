package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.VendorHandlerManager;
import com.abm.pos.com.abm.pos.dto.*;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.io.*;

import static java.lang.System.out;

/**
 * Created by asp5045 on 5/17/16.
 */

@RestController
@RequestMapping(value = "")
public class VendorHandlerController {

    @Autowired
    VendorHandlerManager vendorHandlerManager;

    @RequestMapping(value = "/addVendor",method = RequestMethod.POST, consumes = "application/json")
    public void addVendor(@RequestBody AddVendorDto vendorDto)
    {
        try {
            final int dpi = 150;
            Code39Bean bean = new Code39Bean();
            bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); //makes the narrow bar, width exactly one pixel
            bean.setWideFactor(3);
            bean.doQuietZone(false);

            File outputFile = new File("resources" + "/" + "out.png");
            OutputStream out = new FileOutputStream(outputFile);
           // addManager.addProductToDB(productDto);


            //Set up the canvas provider for monochrome PNG output
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                    out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

            //Generate the barcode
            bean.generateBarcode(canvas, "Hello World");

            //Signal end of generation
            canvas.finish();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }


    @RequestMapping(value = "/editVendor",method = RequestMethod.POST, consumes = "application/json")
    public void editVendor(@RequestBody AddVendorDto vendorDto)
    {
        try
        {
            vendorHandlerManager.editVendorToDB(vendorDto);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }



    @RequestMapping(value = "/deleteVendor",method = RequestMethod.POST, consumes = "application/json")
    public void editDelete(@RequestBody AddVendorDto vendorDto)
    {
        try
        {
            vendorHandlerManager.deleteVendorToDB(vendorDto);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


    /*public  void editProductToDB(AddProductDto productDto) {

        try
        {
            jdbcTemplate.update(sqlQuery.editProductQuery,
                    productDto.getProductNo(),
                    productDto.getCategoryId(),
                    productDto.getVendorId(),
                    productDto.getBrandId(),
                    productDto.getAltNo(),
                    productDto.getDescription(),
                    productDto.getCostPrice(),
                    productDto.getRetailPrice(),
                    productDto.getQuantity(),
                    productDto.getMinProductQuantity(),
                    productDto.getReturnRule(),
                    productDto.getImage(),
                    productDto.getCreatedDate());
            System.out.println("Product Edited Successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
       }
*/
    }




