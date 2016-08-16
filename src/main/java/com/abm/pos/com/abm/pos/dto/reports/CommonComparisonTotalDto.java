package com.abm.pos.com.abm.pos.dto.reports;

import java.util.List;

/**
 * Created by asp5045 on 8/16/16.
 */
public class CommonComparisonTotalDto {

    private List<FinalTotalForCommonComparisonDto> finalTotalForCommonComparisonDtos;
    private List<CommonComparisonDto> commonComparisonDtos;

    public List<CommonComparisonDto> getCommonComparisonDtos() {
        return commonComparisonDtos;
    }

    public void setCommonComparisonDtos(List<CommonComparisonDto> commonComparisonDtos) {
        this.commonComparisonDtos = commonComparisonDtos;
    }

    public List<FinalTotalForCommonComparisonDto> getFinalTotalForCommonComparisonDtos() {
        return finalTotalForCommonComparisonDtos;
    }

    public void setFinalTotalForCommonComparisonDtos(List<FinalTotalForCommonComparisonDto> finalTotalForCommonComparisonDtos) {
        this.finalTotalForCommonComparisonDtos = finalTotalForCommonComparisonDtos;
    }

}
