package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.AddClosingDetailsDto;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by asp5045 on 5/20/16.
 */
public class AddClosingDetailsManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQueries;


    public void addClosingDetailsToDB(AddClosingDetailsDto addClosingDetailsDto) {

        try
        {
            jdbcTemplate.update(sqlQueries.addClosingDetails,
                    addClosingDetailsDto.getUserId(),
                    addClosingDetailsDto.getOpenDate(),
                    addClosingDetailsDto.getIntialAmount(),
                    addClosingDetailsDto.getReportCash(),
                    addClosingDetailsDto.getReportCredit(),
                    addClosingDetailsDto.getCloseCash(),
                    addClosingDetailsDto.getCloseCredit(),
                    addClosingDetailsDto.getCloseDate(),
                    addClosingDetailsDto.getDifferenceCash(),
                    addClosingDetailsDto.getDifferenceCredit(),
                    addClosingDetailsDto.getTotalDifference(),
                    addClosingDetailsDto.getTotalAmount());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
}

