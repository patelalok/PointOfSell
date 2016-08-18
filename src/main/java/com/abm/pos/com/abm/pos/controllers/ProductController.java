package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.ProductManager;
import com.abm.pos.com.abm.pos.dto.ProductDto;
import com.abm.pos.com.abm.pos.dto.ProductNoAndAltNoDTO;
import com.abm.pos.com.abm.pos.dto.RelatedProductDto;
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

        productManager.addProductToDB(productDto);
    }

    @RequestMapping(value = "/addRelatedProduct", method = RequestMethod.POST, consumes = "application/json")
    public void addRelatedProduct(@RequestBody List<RelatedProductDto> relatedProductDtosList) {

        productManager.addRelatedProduct(relatedProductDtosList);
    }

    /*@RequestMapping(value = "/editRelatedProduct", method = RequestMethod.POST, consumes = "application/json")
    public void editRelatedProduct(@RequestBody List<RelatedProductDto> relatedProductDtosList) {

        productManager.editRelatedProduct(relatedProductDtosList);
    }*/

    @RequestMapping(value = "/editProduct", method = RequestMethod.POST, consumes = "application/json")
    public void editProduct(@RequestBody ProductDto productDto) {

        productManager.editProductToDB(productDto);
    }

    @RequestMapping(value = "/getProduct", method = RequestMethod.GET)
    public List<ProductDto> getProduct() {

        return productManager.getProductDetails();
    }

    @RequestMapping(value = "/getRelatedProduct", method = RequestMethod.GET, produces = "application/json")
    public List<ProductDto> getRelatedProduct(@RequestParam String productNo) {

        return productManager.getRelatedProduct(productNo);
    }



    @RequestMapping(value = "/getProductHistory", method = RequestMethod.GET)
    public List<TransactionLineItemDto> getProductHistory(@RequestParam int productId) {

        return productManager.getProductHistoryFromDB(productId);
    }

    @RequestMapping(value = "/getProductNoAndAltNo", method = RequestMethod.GET)
    public List<ProductNoAndAltNoDTO> getProductNoAndAltNo() {

        return productManager.getProductNoAndAltNo();
    }


    @RequestMapping(value = "/deleteProduct", method = RequestMethod.DELETE, consumes = "application/json")
    public void deleteProduct(@RequestParam String productId) {

        productManager.deleteProductToDB(productId);

        System.out.println("Product Deleted Successfully !!!");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getLastProductNo", produces = "application/json")
    public long getLastProductNo()
    {
        return productManager.getLastProductNo();
    }



}

