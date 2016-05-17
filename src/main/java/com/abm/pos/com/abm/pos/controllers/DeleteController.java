package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.DeleteManager;
import com.abm.pos.com.abm.pos.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by asp5045 on 5/17/16.
 */

@RestController
@RequestMapping(value = "")
public class DeleteController {

    @Autowired
    DeleteManager deleteManager;


    @RequestMapping(value = "/deleteProducts",method = RequestMethod.POST, consumes = "application/json")
    public void editProduct(@RequestBody AddProductDto productDto)
    {
        try
        {
            deleteManager.deleteProductToDB(productDto);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @RequestMapping(value = "/deleteCustomers",method = RequestMethod.POST, consumes = "application/json")
    public void editCustomer(@RequestBody AddCustomerDto customerDto)
    {
        try
        {
            deleteManager.deleteCustomerToDB(customerDto);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @RequestMapping(value = "/deleteVendor",method = RequestMethod.POST, consumes = "application/json")
    public void editVendor(@RequestBody AddVendorDto vendorDto)
    {
        try
        {
            deleteManager.deleteVendorToDB(vendorDto);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


    @RequestMapping(value = "/deleteCategory",method = RequestMethod.POST, consumes = "application/json")
    public void editCategory(@RequestBody AddCategoryDto categoryDto)
    {
        try
        {
            deleteManager.deleteCategoryToDB(categoryDto);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    @RequestMapping(value = "/deleteBrand",method = RequestMethod.POST, consumes = "application/json")
    public void editCategory(@RequestBody AddBrandDto addBrandDto)
    {
        try
        {
            deleteManager.deleteBrandToDB(addBrandDto);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

}
