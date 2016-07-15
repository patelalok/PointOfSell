package com.abm.pos.com.abm.pos.dto.reports;

import com.abm.pos.com.abm.pos.dto.FinalTotalForReportsDto;

import java.util.List;

/**
 * Created by asp5045 on 7/10/16.
 */
public class YearlyListDto {

    private List<FinalTotalForReportsDto> finalTotalForReportsDtos;
    private List<YearlyDto> yearlyListDtos;



    public List<FinalTotalForReportsDto> getFinalTotalForReportsDtos() {
        return finalTotalForReportsDtos;
    }

    public void setFinalTotalForReportsDtos(List<FinalTotalForReportsDto> finalTotalForReportsDtos) {
        this.finalTotalForReportsDtos = finalTotalForReportsDtos;
    }

    public List<YearlyDto> getYearlyListDtos() {
        return yearlyListDtos;
    }

    public void setYearlyListDtos(List<YearlyDto> yearlyListDtos) {
        this.yearlyListDtos = yearlyListDtos;
    }




}
