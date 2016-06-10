package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.ProductManager;
import com.abm.pos.com.abm.pos.dto.ProductDto;
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

        productManager.addProductToDB(productDto);
    }

    @RequestMapping(value = "/editProduct", method = RequestMethod.POST, consumes = "application/json")
    public void editProduct(@RequestBody ProductDto productDto) {

        productManager.editProductToDB(productDto);
    }

    @RequestMapping(value = "/getProduct", method = RequestMethod.GET)
    public List<ProductDto> getProduct() {

        return productManager.getProductDetails();
    }

    @RequestMapping(value = "/getProductForProductPage", method = RequestMethod.GET)
    public List<ProductDto> getProductForProductPage(@PathVariable int brandId) {

        return null;// productManager.getProductDetailsForProductPage(brandId,categoryId,vendorId);
    }

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST, consumes = "application/json")
    public void deleteProduct(@RequestBody String productNo) {

        productManager.deleteProductToDB(productNo);
    }



}

