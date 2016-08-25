package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.DashboardManager;
import com.abm.pos.com.abm.pos.dto.DashboardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by asp5045 on 6/15/16.
 */

@RestController
@RequestMapping("")
@CrossOrigin(origins = {"*"})
public class DashboardController
{
    @Autowired
    DashboardManager dashboardManager;



    @RequestMapping(value = "/getDashBoardDetailByMonth", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DashboardDto> getDashBoardDetailByMonth(@RequestParam String startDateCurrent, @RequestParam String endDateCurrent , @RequestParam String startDateLast, @RequestParam String endDateLast)
    {
        return dashboardManager.getDashboardDetails(startDateCurrent,endDateCurrent,startDateLast,endDateLast);
    }


}
