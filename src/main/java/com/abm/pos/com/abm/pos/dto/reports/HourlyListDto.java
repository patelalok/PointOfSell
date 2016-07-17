package com.abm.pos.com.abm.pos.dto.reports;

import com.abm.pos.com.abm.pos.dto.FinalTotalForReportsDto;
import com.abm.pos.com.abm.pos.dto.HourlyDto;

import java.util.List;

/**
 * Created by asp5045 on 7/16/16.
 */
public class HourlyListDto {


    private List<FinalTotalForReportsDto> finalTotalForReportsDtoList;
    private List<HourlyDto> hourlyDtoList;


    public List<FinalTotalForReportsDto> getFinalTotalForReportsDtoList() {
        return finalTotalForReportsDtoList;
    }

    public void setFinalTotalForReportsDtoList(List<FinalTotalForReportsDto> finalTotalForReportsDtoList) {
        this.finalTotalForReportsDtoList = finalTotalForReportsDtoList;
    }

    public List<HourlyDto> getHourlyDtoList() {
        return hourlyDtoList;
    }

    public void setHourlyDtoList(List<HourlyDto> hourlyDtoList) {
        this.hourlyDtoList = hourlyDtoList;
    }



}
