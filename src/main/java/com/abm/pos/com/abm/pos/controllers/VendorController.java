package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.VendorManager;
import com.abm.pos.com.abm.pos.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by asp5045 on 5/17/16.
 */

@RestController
@RequestMapping(value = "")
@CrossOrigin(origins = {"*"})
public class VendorController {

    @Autowired
    VendorManager vendorManager;

    @RequestMapping(value = "/addVendor", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity addVendor(@RequestBody VendorDto vendorDto){

        vendorManager.addVendorToDB(vendorDto);
        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(value = "/editVendor", method = RequestMethod.POST, consumes = "application/json")
    public void editVendor(@RequestBody VendorDto vendorDto) {

        vendorManager.editVendorToDB(vendorDto);
    }

    @RequestMapping(value = "/getVendor", method = RequestMethod.GET)
    public List<VendorDto> getVendor() {

        return  vendorManager.getVendorDetails();
    }

    @RequestMapping(value = "/deleteVendor", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity deleteVendor(@RequestParam int  vendorId) {

        int result = 0;
       result =  vendorManager.deleteVendorToDB(vendorId);

        if(result ==1)
        {
            return ResponseEntity.ok("Vendor Delete Successfully");
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body("Can not delete vendor please delete all products in this vendor first");

    }
}

