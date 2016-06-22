package com.abm.pos.com.abm.pos.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by asp5045 on 6/21/16.
 */

@RestController
@RequestMapping("")
public class ReportController {
    @RequestMapping(value = "/getDailyReports",method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void getDailyReports()
    {

    }
}
