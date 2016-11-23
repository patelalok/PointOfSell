package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.WebMenuManager;
import com.abm.pos.com.abm.pos.dto.MenuDto;
import com.abm.pos.com.abm.pos.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by asp5045 on 11/17/16.
 */

@RestController
@RequestMapping("")
@CrossOrigin(origins = {"*"})
public class WebMenuController {

    @Autowired
    WebMenuManager webMenuManager;

    @RequestMapping(value = "/getWebMenu", method = RequestMethod.GET)
    public MenuDto getWebMenu() {

        return webMenuManager.getWebMenu();
    }

}
