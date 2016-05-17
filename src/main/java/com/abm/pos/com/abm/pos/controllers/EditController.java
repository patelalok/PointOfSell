package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.AddManager;
import com.abm.pos.com.abm.pos.bl.EditManager;
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
public class EditController {

    @Autowired
    EditManager editManager;


    @RequestMapping(value = "/editProducts",method = RequestMethod.POST, consumes = "application/json")
    public void editProduct(@RequestBody AddProductDto productDto)
    {
        try
        {
            editManager.editProductToDB(productDto);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @RequestMapping(value = "/editCustomers",method = RequestMethod.POST, consumes = "application/json")
    public void editCustomer(@RequestBody AddCustomerDto customerDto)
    {
        try
        {
            editManager.editCustomerToDB(customerDto);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @RequestMapping(value = "/editVendor",method = RequestMethod.POST, consumes = "application/json")
    public void editVendor(@RequestBody AddVendorDto vendorDto)
    {
        try
        {
            editManager.editVendorToDB(vendorDto);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


    @RequestMapping(value = "/editCategory",method = RequestMethod.POST, consumes = "application/json")
    public void editCategory(@RequestBody AddCategoryDto categoryDto)
    {
        try
        {
            editManager.editCategoryToDB(categoryDto);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    @RequestMapping(value = "/editBrand",method = RequestMethod.POST, consumes = "application/json")
    public void editCategory(@RequestBody AddBrandDto addBrandDto)
    {
        try
        {
            editManager.editBrandToDB(addBrandDto);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

}
