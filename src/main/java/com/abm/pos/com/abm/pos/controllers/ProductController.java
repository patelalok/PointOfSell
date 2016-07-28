package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.ProductManager;
import com.abm.pos.com.abm.pos.dto.ProductDto;
import com.abm.pos.com.abm.pos.dto.TransactionLineItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by asp5045 on 5/24/16.
 */
@RestController
@RequestMapping("")
public class ProductController {

    @Autowired
    ProductManager productManager;

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST, consumes = "application/json")
    public void addProduct(@RequestBody ProductDto productDto) {

        //productManager.addProductToDB(productDto);
    }

    @RequestMapping(value = "/editProduct", method = RequestMethod.POST, consumes = "application/json")
    public void editProduct(@RequestBody ProductDto productDto) {

        productManager.editProductToDB(productDto);
    }

    @RequestMapping(value = "/getProduct", method = RequestMethod.GET)
    public List<ProductDto> getProduct() {

        return productManager.getProductDetails();
    }

    @RequestMapping(value = "/getProductHistory", method = RequestMethod.GET)
    public List<TransactionLineItemDto> getProductHistory(@RequestParam int productId) {

        return productManager.getProductHistoryFromDB(productId);
    }

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST, consumes = "application/json")
    public void deleteProduct(@RequestBody String productNo) {

        productManager.deleteProductToDB(productNo);
    }



}

