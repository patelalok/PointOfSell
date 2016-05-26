package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.UserManager;
import com.abm.pos.com.abm.pos.dto.AddUserDto;
import com.abm.pos.com.abm.pos.dto.AddVendorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by asp5045 on 5/25/16.
 */
@RestController
@RequestMapping("")
public class UserController {

    @Autowired
    UserManager userManager;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST, consumes = "application/json")
    public void addVendor(@RequestBody AddUserDto userDto) {
        userManager.addUserToDB(userDto);

    }
}
