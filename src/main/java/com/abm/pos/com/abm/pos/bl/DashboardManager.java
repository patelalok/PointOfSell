package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.DashboardDto;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asp5045 on 6/15/16.
 */
@Component
public class DashboardManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQueries;

//
//    public List<DashboardDto> getDashboardDetails(String startDateCurrent, String endDateCurrent, String startDateLast, String endDateLast) {
//
//        List<DashboardDto> dashboardDtos = new ArrayList<>();
//        try
//        {
//            dashboardDtos = jdbcTemplate.query(sqlQueries.getDashboardDetailsForMonths, new DashMonthMapper(), startDateCurrent,endDateCurrent,startDateCurrent,endDateCurrent);
//
//
//            //HERE I NEED TO ADD THE LINE ITEM DISCOUNT CAUSE RIGHT NOW ITS SHOWING ONLY TRANSACTION DISCOUNT
//
//        }
//        catch (Exception e)
//        {
//            System.out.println(e);
//        }
//
//        return dashboardDtos;
//    }



    private final class DashMonthMapper implements RowMapper<DashboardDto> {

        @Override
        public DashboardDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            DashboardDto dashboardDto = new DashboardDto();

            dashboardDto.setNameOfMonth(rs.getString("NameOfMonth"));
            dashboardDto.setCredit(rs.getDouble("CREDIT"));
            dashboardDto.setCash(rs.getDouble("CASH"));
            dashboardDto.setTax(rs.getDouble("TAX"));
            dashboardDto.setDiscount(rs.getDouble("DISCOUNT"));
            dashboardDto.setTotal(rs.getDouble("TOTAL"));
            dashboardDto.setNoOfTrans(rs.getDouble("NOOFTRANS"));
            dashboardDto.setProfit(rs.getDouble("PROFIT"));





            return dashboardDto;
        }
    }

}
