package com.abm.pos.com.abm.pos.dto.reports;

import com.abm.pos.com.abm.pos.dto.reports.CommonInventoryDto;
import com.abm.pos.com.abm.pos.dto.reports.FinalTotalForInventoryDto;
import java.util.List;

/**
 * Created by asp5045 on 9/21/16.
 */
public class CommonInvetoryTotalDto {

    private List<FinalTotalForInventoryDto> finalTotalForInventoryDtos;
    private List<CommonInventoryDto> commonInventoryDtos;

    public List<FinalTotalForInventoryDto> getFinalTotalForInventoryDtos() {
        return finalTotalForInventoryDtos;
    }

    public void setFinalTotalForInventoryDtos(List<FinalTotalForInventoryDto> finalTotalForInventoryDtos) {
        this.finalTotalForInventoryDtos = finalTotalForInventoryDtos;
    }

    public List<CommonInventoryDto> getCommonInventoryDtos() {
        return commonInventoryDtos;
    }

    public void setCommonInventoryDtos(List<CommonInventoryDto> commonInventoryDtos) {
        this.commonInventoryDtos = commonInventoryDtos;
    }
}
