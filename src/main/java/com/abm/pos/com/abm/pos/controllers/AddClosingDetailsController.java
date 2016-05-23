package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.AddClosingDetailsManager;
import com.abm.pos.com.abm.pos.dto.AddClosingDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by asp5045 on 5/20/16.
 */
@RestController
@RequestMapping("")
public class AddClosingDetailsController {
    @Autowired
    AddClosingDetailsManager addClosingDetailsManager;

    @RequestMapping(value = "/addClosingDetails", method = RequestMethod.POST, produces = "application/json")
    public void addClosingDetails(@RequestBody AddClosingDetailsDto addClosingDetailsDto)
    {
        addClosingDetailsManager.addClosingDetailsToDB(addClosingDetailsDto);
    }
}
