package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.UserManager;
import com.abm.pos.com.abm.pos.dto.UserDto;
import com.abm.pos.com.abm.pos.dto.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by asp5045 on 5/25/16.
 */
@RestController
@RequestMapping("")
public class UserController {

    @Autowired
    UserManager userManager;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST, consumes = "application/json")
    public void addUser(@RequestBody UserDto userDto) {

        userManager.addUserToDB(userDto);
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.POST, consumes = "application/json")
    public void editUser(@RequestBody UserDto userDto) {

        userManager.editUserToDB(userDto);

    }

    @RequestMapping(value = "/getUserLoginDetails", method = RequestMethod.GET)
    public UserLogin getUserLoginDetails(@RequestParam String username, @RequestParam String password) {

        return userManager.getUserLoginDetails(username,password);

    }

    @RequestMapping(value = "/getUserDetails", method = RequestMethod.GET, produces = "application/json")
    public List<UserDto> getUserDetails() {

        return userManager.getUserDetails();

    }




}
