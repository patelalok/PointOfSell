package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.AddClosingDetailsDto;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by asp5045 on 5/20/16.
 */
@Component
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
                    addClosingDetailsDto.getOpenAmount(),
                    addClosingDetailsDto.getReportCash(),
                    addClosingDetailsDto.getReportCredit(),
                    addClosingDetailsDto.getReportTotalAmount(),
                    addClosingDetailsDto.getCloseCash(),
                    addClosingDetailsDto.getCloseCredit(),
                    addClosingDetailsDto.getCloseDate(),
                    addClosingDetailsDto.getCloseTotalAmount(),
                    addClosingDetailsDto.getDifferenceCash(),
                    addClosingDetailsDto.getDifferenceCredit(),
                    addClosingDetailsDto.getTotalDifference(),
                    addClosingDetailsDto.getTotalBusinessAmount());

            System.out.println("Closing Details Added Successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
}

