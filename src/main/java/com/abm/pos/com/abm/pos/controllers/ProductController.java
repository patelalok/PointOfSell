package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.ProductManager;
import com.abm.pos.com.abm.pos.dto.AddProductDto;
import com.abm.pos.com.abm.pos.dto.AddVendorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by asp5045 on 5/24/16.
 */
@RestController
@RequestMapping("")
public class ProductController {

    @Autowired
    ProductManager productManager;

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST, consumes = "application/json")
    public void addProduct(@RequestBody AddProductDto productDto) {

        productManager.addProductToDB(productDto);
    }

    @RequestMapping(value = "/editProduct", method = RequestMethod.POST, consumes = "application/json")
    public void editProduct(@RequestBody AddProductDto productDto) {

        productManager.editProductToDB(productDto);
    }

    @RequestMapping(value = "/getProduct", method = RequestMethod.POST, consumes = "application/json")
    public void getProduct(@RequestBody String productNo) {

        productManager.getProductDetails(productNo);
    }

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST, consumes = "application/json")
    public void deleteProduct(@RequestBody String productNo) {

        productManager.deleteProductToDB(productNo);
    }



}

