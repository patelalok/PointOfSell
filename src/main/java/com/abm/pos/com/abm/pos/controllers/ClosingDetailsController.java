package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.ClosingDetailsManager;
import com.abm.pos.com.abm.pos.dto.ClosingDetailsDto;
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
public class ClosingDetailsController {
    @Autowired
    ClosingDetailsManager addClosingDetailsManager;

    @RequestMapping(value = "/addClosingDetails", method = RequestMethod.POST, produces = "application/json")
    public void addClosingDetails(@RequestBody ClosingDetailsDto closingDetailsDto)
    {
        addClosingDetailsManager.addClosingDetailsToDB(closingDetailsDto);
    }
}
