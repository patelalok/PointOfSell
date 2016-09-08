package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.BrandManager;
import com.abm.pos.com.abm.pos.dto.BrandDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by asp5045 on 5/24/16.
 */
@RestController
@RequestMapping("")
@CrossOrigin(origins = {"*"})
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

    @RequestMapping(value = "/deleteBrand" ,method = RequestMethod.POST)
    public ResponseEntity deleteVendor(@RequestParam int  brandId) {

        int result = 0;
        result = brandManager.deleteBrandFromDB(brandId);

        if(result ==1)
        {
            return ResponseEntity.ok("Brand Deleted Successfully");
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body("Can not delete brand please delete all products in this brand first");
    }
}
