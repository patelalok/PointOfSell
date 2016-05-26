package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.VendorManager;
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
public class VendorController {

    @Autowired
    VendorManager vendorManager;

    @RequestMapping(value = "/addVendor", method = RequestMethod.POST, consumes = "application/json")
    public void addVendor(@RequestBody AddVendorDto vendorDto) {
        vendorManager.addVendorToDB(vendorDto);

    }

    @RequestMapping(value = "/editVendor", method = RequestMethod.POST, consumes = "application/json")
    public void editVendor(@RequestBody AddVendorDto vendorDto) {

            vendorManager.editVendorToDB(vendorDto);
    }

    @RequestMapping(value = "/getVendor", method = RequestMethod.POST, consumes = "application/json")
    public void getVendor(@RequestBody String  vendorId) {

        vendorManager.getVendorDetails(vendorId);
    }

    @RequestMapping(value = "/deleteVendor", method = RequestMethod.POST, consumes = "application/json")
    public void deleteVendor(@RequestBody String  vendorId) {

            vendorManager.deleteVendorToDB(vendorId);
    }
}



