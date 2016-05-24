package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.BrandHandlerManager;
import com.abm.pos.com.abm.pos.dto.AddBrandDto;
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
public class BrandHandlerController {

    @Autowired
    BrandHandlerManager brandHandlerManager;

    @RequestMapping(value = "/addBrand",method = RequestMethod.POST, consumes = "application/json")
    public void addBrand(@RequestBody AddBrandDto addBrandDto)
    {
        try
        {
            brandHandlerManager.addBrandToDB(addBrandDto);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


    @RequestMapping(value = "/editBrand",method = RequestMethod.POST, consumes = "application/json")
    public void editBrand(@RequestBody AddBrandDto addBrandDto)
    {
        try
        {
            brandHandlerManager.editBrandToDB(addBrandDto);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


    @RequestMapping(value = "/deleteBrand",method = RequestMethod.POST, consumes = "application/json")
    public void deleteBrand(@RequestBody AddBrandDto addBrandDto)
    {
        try
        {
            brandHandlerManager.deleteBrandToDB(addBrandDto);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }





}
