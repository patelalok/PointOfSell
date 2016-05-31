package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.ClosingDetailsDto;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by asp5045 on 5/20/16.
 */
@Component
public class ClosingDetailsManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQueries;


    public void addClosingDetailsToDB(ClosingDetailsDto closingDetailsDto) {

        try
        {
            jdbcTemplate.update(sqlQueries.addClosingDetails,
                    closingDetailsDto.getUserId(),
                    closingDetailsDto.getOpenDate(),
                    closingDetailsDto.getOpenAmount(),
                    closingDetailsDto.getReportCash(),
                    closingDetailsDto.getReportCredit(),
                    closingDetailsDto.getReportTotalAmount(),
                    closingDetailsDto.getCloseCash(),
                    closingDetailsDto.getCloseCredit(),
                    closingDetailsDto.getCloseDate(),
                    closingDetailsDto.getCloseTotalAmount(),
                    closingDetailsDto.getDifferenceCash(),
                    closingDetailsDto.getDifferenceCredit(),
                    closingDetailsDto.getTotalDifference(),
                    closingDetailsDto.getTotalBusinessAmount());

            System.out.println("Closing Details Added Successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
}

