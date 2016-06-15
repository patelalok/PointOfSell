package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.BrandManager;
import com.abm.pos.com.abm.pos.dto.BrandDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by asp5045 on 5/24/16.
 */
@RestController
@RequestMapping("")
public class BrandController {

    @Autowired
    BrandManager brandManager;

    @RequestMapping(value = "/addBrand",method = RequestMethod.POST, consumes = "application/json")
    public void addBrand(@RequestBody BrandDto brandDto)
    {
        brandManager.addBrandToDB(brandDto);
    }

    @RequestMapping(value = "/editBrand",method = RequestMethod.POST, consumes = "application/json")
    public void editBrand(@RequestBody BrandDto brandDto)
    {
        brandManager.editBrandToDB(brandDto);
    }

    @RequestMapping(value = "/getBrand",method = RequestMethod.GET)
    public List<BrandDto> getBrand()
    {

       return brandManager.getBrandDetails();
    }

   /* @RequestMapping(method = RequestMethod.DELETE, value = "/{brandId}")
    public void deleteVendor(@PathVariable int  brandId) {

        brandManager.deleteBrandFromDB(brandId);
    }*/
}
