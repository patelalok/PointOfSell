package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.ProductManager;
import com.abm.pos.com.abm.pos.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by asp5045 on 5/24/16.
 */
@RestController
@RequestMapping("")
@CrossOrigin(origins = {"*"})
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

    @RequestMapping(value = "/getProductPriceByCustomer", method = RequestMethod.GET)
    public List<ProductPriceByCustomerDto> getProductPriceForCustomer(@RequestParam String phoneNo) {

        return productManager.getProductPriceForCustomer(phoneNo);
    }

    @RequestMapping(value = "/getEcommerceProductsByCategory", method = RequestMethod.GET)
    public List<ProductEcomerceDto> getEcommerceProductsByCategory(@RequestParam int category_Id) {

        return productManager.getEcommerceProductsByCategory(category_Id);
    }

    @RequestMapping(value = "/getEcommerceProductsByBrand", method = RequestMethod.GET)
    public List<ProductEcomerceDto> getEcommerceProductsByBrand(@RequestParam int brand_Id, @RequestParam int model_Id) {

        return productManager.getEcommerceProductsByBrand(brand_Id,model_Id);
    }


    @RequestMapping(value = "/getRelatedProduct", method = RequestMethod.GET, produces = "application/json")
    public List<ProductDto> getRelatedProduct(@RequestParam String productNo) {

        return productManager.getRelatedProduct(productNo);
    }

    @RequestMapping(value = "/getRelatedProductForProductPage", method = RequestMethod.GET, produces = "application/json")
    public List<ProductDto> getRelatedProductForProductPage(@RequestParam String productNo) {

        return productManager.getRelatedProductForProductPage(productNo);
    }

    @RequestMapping(value = "/getProductHistory", method = RequestMethod.GET)
    public List<TransactionLineItemDto> getProductHistory(@RequestParam String productNo, @RequestParam String startDate, @RequestParam String endDate) {

        return productManager.getProductHistoryFromDB(productNo,startDate,endDate);
    }

    @RequestMapping(value = "/getProductNoAndAltNo", method = RequestMethod.GET)
    public List<ProductNoAndAltNoDTO> getProductNoAndAltNo() {

        return productManager.getProductNoAndAltNo();
    }


    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response> deleteProduct(@RequestParam String productId) {

        Response  response = new Response();

       int result =  productManager.deleteProductToDB(productId);

        if(result == 1)
        {

            System.out.println("Product Deleted Successfully !!!");



            response.setStatusMessege("Product Deleted Successfully");

            return ResponseEntity.ok(response);
        }



        return ResponseEntity.status(HttpStatus.CONFLICT).
                body(response);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getLastProductNo", produces = "application/json")
    public long getLastProductNo()
    {
        return productManager.getLastProductNo();
    }

    @RequestMapping(value = "/deleteRelatedProduct", method = RequestMethod.POST, consumes = "application/json")
    public void deleteRelatedProduct(@RequestParam String relatedProductId) {

        productManager.deleteRelatedProduct(relatedProductId);

        System.out.println("Product Deleted Successfully !!!");
    }

    @RequestMapping(value = "/LowStockProductDetails", method = RequestMethod.GET)
    public List<ProductDto> getLowStockProductDetails() {

        return productManager.getLowStockProductDetails();
    }

    @RequestMapping(value = "/addIMEINo", method = RequestMethod.POST, consumes = "application/json")
    public void addIMEINo(@RequestBody PhoneDto phoneDto) {

        productManager.addIMEINo(phoneDto);
    }

    @RequestMapping(value = "/editIMEINo", method = RequestMethod.POST, consumes = "application/json")
    public void editIMEINo(@RequestBody PhoneDto phoneDto) {

        productManager.editIMEINo(phoneDto);
    }


    @RequestMapping(value = "/getPhoneDetails", method = RequestMethod.GET)
    public List<ProductDto> getPhoneDetails(@RequestParam String productNo) {

        return productManager.getPhoneDetails(productNo);
    }

    @RequestMapping(value = "/deleteImei", method = RequestMethod.POST)
    public void deleteImei(@RequestParam String phoneId, @RequestParam String productId) {

        productManager.deleteImei(phoneId,productId);
    }



}

