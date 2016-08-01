package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.ClosingDetailsManager;
import com.abm.pos.com.abm.pos.dto.*;
import com.abm.pos.com.abm.pos.dto.reports.HourlyListDto;
import com.abm.pos.com.abm.pos.dto.reports.YearlyListDto;
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
    ClosingDetailsManager closingDetailsManager;


    @RequestMapping(value = "/addClosingDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addClosingDetails(@RequestBody ClosingDetailsDto closingDetailsDto)
    {
        closingDetailsManager.addClosingDetailsToDB(closingDetailsDto);
    }


    @RequestMapping(value = "/getClosingDetails", method = RequestMethod.GET, produces = "application/json")
    public List<ClosingDetailsDto> getClosingDetails(@RequestParam String startDate, @RequestParam String endDate)
    {
        return closingDetailsManager.getClosingDetailsToDB(startDate,endDate);
    }


    @RequestMapping(value = "/addPaidOut", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addPaidOut(@RequestBody PaidOutDto paidOutDto)
    {
        closingDetailsManager.addPaidOut(paidOutDto);
    }


    @RequestMapping(value = "/getPaidOut", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PaidOutDto> getPaidOut(@RequestParam String startDate, @RequestParam String endDate)
    {
        return closingDetailsManager.getPaidOutDetails(startDate,endDate);
    }

    @RequestMapping(value = "/editPaidOut", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void editPaidOut(@RequestBody PaidOutDto paidOutDto)
    {
        closingDetailsManager.editPaidOut(paidOutDto);
    }

    @RequestMapping(value = "/getHourlyTransactionDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HourlyListDto getHourlyTransactionDetails(@RequestParam String startDate, @RequestParam String endDate)
    {
        return closingDetailsManager.getHourlyTransactionDetails(startDate,endDate);
    }

    @RequestMapping(value = "/getDailyTransactionDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DailyTransactionDto> getDailyTransactionDetails(@RequestParam String startDate, @RequestParam String endDate)
    {
        return closingDetailsManager.getDailyTransactionDetails(startDate,endDate);
    }
    @RequestMapping(value = "/getWeeklyTransactionDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WeekDto> getWeeklyTransactionDetails(@RequestParam String startDate, @RequestParam String endDate)
    {
        return closingDetailsManager.getWeeklyTransactionDetails(startDate,endDate);
    }

    @RequestMapping(value = "/getMonthlyTransactionDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public MonthlyListDto getMonthlyTransactionDetails(@RequestParam String startDate, @RequestParam String endDate)
    {
        return closingDetailsManager.getMonthlyTransactionDetails(startDate,endDate);
    }

    @RequestMapping(value = "/getYearlyTransactionDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public YearlyListDto getYearlyTransactionDetails(@RequestParam String startDate, @RequestParam String endDate)
    {
        return closingDetailsManager.getYearlyTransactionDetails(startDate,endDate);
    }


}
