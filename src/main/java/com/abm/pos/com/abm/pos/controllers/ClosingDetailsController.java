package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.ClosingDetailsManager;
import com.abm.pos.com.abm.pos.dto.*;
import com.abm.pos.com.abm.pos.dto.reports.YearlyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by asp5045 on 5/20/16.
 */
@RestController
@RequestMapping("")
public class ClosingDetailsController {
    @Autowired
    ClosingDetailsManager addClosingDetailsManager;


    @RequestMapping(value = "/addClosingDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addClosingDetails(@RequestBody ClosingDetailsDto closingDetailsDto)
    {
        addClosingDetailsManager.addClosingDetailsToDB(closingDetailsDto);
    }


    @RequestMapping(value = "/getClosingDetails", method = RequestMethod.GET, produces = "application/json")
    public List<ClosingDetailsDto> getClosingDetails(@RequestParam String startDate, @RequestParam String endDate)
    {
        return addClosingDetailsManager.getClosingDetailsToDB(startDate,endDate);
    }


    @RequestMapping(value = "/addPaidOut", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addPaidOut(@RequestBody PaidOutDto paidOutDto)
    {
        addClosingDetailsManager.addPaidOut(paidOutDto);
    }


    @RequestMapping(value = "/getPaidOut", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PaidOutDto> getPaidOut(@RequestParam String startDate, @RequestParam String endDate)
    {
        return addClosingDetailsManager.getPaidOutDetails(startDate,endDate);
    }

    @RequestMapping(value = "/getHourlyTransactionDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HourlyTransactionDto> getHourlyTransactionDetails(@RequestParam String startDate, @RequestParam String endDate)
    {
        return addClosingDetailsManager.getHourlyTransactionDetails(startDate,endDate);
    }

    @RequestMapping(value = "/getDailyTransactionDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DailyTransactionDto> getDailyTransactionDetails(@RequestParam String startDate, @RequestParam String endDate)
    {
        return addClosingDetailsManager.getDailyTransactionDetails(startDate,endDate);
    }
    @RequestMapping(value = "/getWeeklyTransactionDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WeekDto> getWeeklyTransactionDetails(@RequestParam String startDate, @RequestParam String endDate)
    {
        return addClosingDetailsManager.getWeeklyTransactionDetails(startDate,endDate);
    }

    @RequestMapping(value = "/getMonthlyTransactionDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MonthDto> getMonthlyTransactionDetails(@RequestParam String startDate, @RequestParam String endDate)
    {
        return addClosingDetailsManager.getMonthlyTransactionDetails(startDate,endDate);
    }

    @RequestMapping(value = "/getYearlyTransactionDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<YearlyDto> getYearlyTransactionDetails(@RequestParam String startDate, @RequestParam String endDate)
    {
        return addClosingDetailsManager.getYearlyTransactionDetails(startDate,endDate);
    }


}
