package com.abm.pos.com.abm.pos.dto;

import java.util.List;

/**
 * Created by asp5045 on 7/15/16.
 */
public class MonthlyDto {


    private List<MonthDto> monthDtos;
    private List<FinalTotalForReportsDto> finalTotalForReportsDtos;

    public List<FinalTotalForReportsDto> getFinalTotalForReportsDtos() {
        return finalTotalForReportsDtos;
    }

    public void setFinalTotalForReportsDtos(List<FinalTotalForReportsDto> finalTotalForReportsDtos) {
        this.finalTotalForReportsDtos = finalTotalForReportsDtos;
    }

    public List<MonthDto> getMonthDtos() {
        return monthDtos;
    }

    public void setMonthDtos(List<MonthDto> monthDtos) {
        this.monthDtos = monthDtos;
    }

}
