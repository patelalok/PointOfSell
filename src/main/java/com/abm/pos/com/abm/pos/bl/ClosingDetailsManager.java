package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.*;
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
 * Created by asp5045 on 5/20/16.
 */
@Component
public class ClosingDetailsManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQueries;


    public void addClosingDetailsToDB(ClosingDetailsDto closingDetailsDto) {

        try {


            jdbcTemplate.update(sqlQueries.addClosingDetails,
                    closingDetailsDto.getUserIdClose(),
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
                    closingDetailsDto.getTotalBusinessAmount(),
                    closingDetailsDto.getTotalTax(),
                    closingDetailsDto.getTotalDiscount(),
                    closingDetailsDto.getTotalProfit(),
                    closingDetailsDto.getTotalMarkup());

            System.out.println("Closing Details Added Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //GET CLOSING DETAILS FROM DB WITH DATE

    public List<ClosingDetailsDto> getClosingDetailsToDB(String startDate,String endDate) {

        List<ClosingDetailsDto> closingDetails = new ArrayList<>();

        try {
            closingDetails = jdbcTemplate.query(sqlQueries.getClosingDetails, new ClosingMapper(), startDate,endDate);
        } catch (Exception e) {
            System.out.println(e);
        }

        return closingDetails;
    }

    public List<MonthDto> getMontlyTransactionDetails(String startDate, String endDate, int month) {


        List<MonthDto> monthDtos = new ArrayList<>();

        try
        {
            monthDtos = jdbcTemplate.query(sqlQueries.getMonthlyTransDetails, new MonthlyDetailsMapper(), startDate,endDate);

        }
        catch (Exception e)
        {
                System.out.println(e);
        }

        return monthDtos;

    }

    public List<WeekDto> getWeeklyTransactionDetails(String startDate, String endDate, int month) {


        List<WeekDto> weeks = new ArrayList<>();


            WeekDto weekDto = new WeekDto();

            weekDto.setCash1(12.99);
            weekDto.setCash2(12.99);
            weekDto.setCash3(12.99);
            weekDto.setCash4(12.99);
            weekDto.setCash5(12.99);

            weekDto.setCredit1(11.99);
            weekDto.setCredit2(11.99);
            weekDto.setCredit3(11.99);
            weekDto.setCredit4(11.99);
            weekDto.setCredit5(11.99);

            weekDto.setTotal1(24.00);
            weekDto.setTotal2(24.00);
            weekDto.setTotal3(24.00);
            weekDto.setTotal4(24.00);
            weekDto.setTotal5(24.00);

            weekDto.setWeekAvg(134.99);

            weeks.add(0,weekDto);

            //weeks = jdbcTemplate.query(sqlQueries.getWeeklyTransDetails, new WeeklyDetailsMapper(), startDate,endDate);

        return weeks;
    }

    private final class WeeklyDetailsMapper implements RowMapper<WeekDto> {

        @Override
        public WeekDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            WeekDto weekDto = new WeekDto();

           /* weekDto.setWeek1(12.99);
            weekDto.setWeek2(13.99);
            weekDto.setWeek3(14.99);
            weekDto.setWeek4(15.99);
            weekDto.setWeek5(16.99);
            weekDto.setWeekAvg(134.99);*/
            return weekDto;

        }
    }

    private final class MonthlyDetailsMapper implements RowMapper<MonthDto> {

        @Override
        public MonthDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            MonthDto monthDto = new MonthDto();

            monthDto.setDate(rs.getString("DATE"));
            monthDto.setSumCash(rs.getDouble("SUM_CASH"));
            monthDto.setSumCredit(rs.getDouble("SUM_CREDIT"));
            monthDto.setTotal(rs.getDouble("TOTAL"));
            monthDto.setSumTax(rs.getDouble("SUM_TAX"));
            monthDto.setDiscount(rs.getDouble("DISCOUNT"));

            return monthDto;

        }
    }


    public void addPaidOut(PaidOutDto paidOutDto) {

        try
        {
            jdbcTemplate.update(sqlQueries.addPaidOutDetails,
                    paidOutDto.getPaidOut1(),
                    paidOutDto.getPaidOut2(),
                    paidOutDto.getPaidOut3(),
                    paidOutDto.getReason1(),
                    paidOutDto.getReason2(),
                    paidOutDto.getReason3(),
                    paidOutDto.getPaidOutDate());

            System.out.println("Paid Amount added successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public List<PaidOutDto> getPaidOutDetails(String startDate, String endDate) {

        List<PaidOutDto> paidDto = new ArrayList<>();

        try
        {
            paidDto = jdbcTemplate.query(sqlQueries.getPaidOutDetails, new PaidOutMapper(), startDate,endDate);
            System.out.println("Send Paid Amount added successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return paidDto;
    }
    private final class PaidOutMapper implements RowMapper<PaidOutDto> {

        @Override
        public PaidOutDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            PaidOutDto paidOut = new PaidOutDto();

            paidOut.setPaidOut1(rs.getDouble("PAIDOUT1"));
            paidOut.setPaidOut2(rs.getDouble("PAIDOUT2"));
            paidOut.setPaidOut3(rs.getDouble("PAIDOUT3"));
            paidOut.setReason1(rs.getString("REASON1"));
            paidOut.setReason2(rs.getString("REASON2"));
            paidOut.setReason3(rs.getString("REASON3"));
            paidOut.setPaidOutDate(rs.getString("DATE"));

            return paidOut;

        }
    }


// JUST MAPPER TO MAP RESPONSE FROM DB

    private final class ClosingMapper implements RowMapper<ClosingDetailsDto> {

        @Override
        public ClosingDetailsDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            ClosingDetailsDto closingDto = new ClosingDetailsDto();

            closingDto.setUserIdClose(rs.getInt("USER_ID_CLOSE"));
            closingDto.setReportCash(rs.getDouble("REPORT_CASH"));
            closingDto.setReportCredit(rs.getDouble("REPORT_CREDIT"));
            closingDto.setReportTotalAmount(rs.getDouble("REPORT_TOTAL_AMOUNT"));
            closingDto.setCloseCash(rs.getDouble("CLOSE_CASH"));
            closingDto.setCloseCredit(rs.getDouble("CLOSE_CREDIT"));
            closingDto.setCloseDate(rs.getString("CLOSE_DATE"));
            closingDto.setCloseTotalAmount(rs.getDouble("CLOSE_TOTAL_AMOUNT"));
            closingDto.setDifferenceCredit(rs.getDouble("CREDIT_DIFFERENCE"));
            closingDto.setDifferenceCash(rs.getDouble("CASH_DIFFERENCE"));
            closingDto.setTotalDifference(rs.getDouble("TOTAL_DIFFERENCE"));
            closingDto.setTotalBusinessAmount(rs.getDouble("TOTAL_BUSINESS_AMOUNT"));
            closingDto.setTotalTax(rs.getDouble("TOTAL_TAX"));
            closingDto.setTotalDiscount(rs.getDouble("TOTAL_DISCOUNT"));
            closingDto.setTotalProfit(rs.getDouble("TOTAL_PROFIT"));
            closingDto.setTotalMarkup(rs.getDouble("TOTAL_MARKUP"));

            return closingDto;
        }
    }

   // public


// GET DAILY TRANSACTION FROM DB

    public List<DailyTransactionDto> getDailyTransactionDetails(String startDate, String endDate) {

        List<DailyTransactionDto> trans = new ArrayList<>();

        try
        {
            trans = jdbcTemplate.query(sqlQueries.getDailyTransaction, new DailyTransactionMapper(), startDate,endDate);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return trans;
    }

// JUST MAPPER TO MAP RESPONSE FROM DB

    private final class DailyTransactionMapper implements RowMapper<DailyTransactionDto> {

        @Override
        public DailyTransactionDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            DailyTransactionDto trans = new DailyTransactionDto();

            trans.setNoOfTransactions(rs.getInt("count(TRANSACTION_COMP_ID)"));
            trans.setAvgTotal(rs.getDouble("AVG(TOTAL_AMOUNT)"));
            trans.setCash(rs.getDouble("sum(PAID_AMOUNT_CASH)"));
            trans.setCredit(rs.getDouble("sum(TOTAL_AMOUNT_CREDIT)"));
            trans.setTax(rs.getDouble("sum(TAX_AMOUNT)"));
            trans.setDiscount(rs.getDouble("sum(DISCOUNT_AMOUNT)"));
            trans.setTotal(trans.getCash() + trans.getCredit() + trans.getTax());
            trans.setGrossSale((trans.getTotal()) - trans.getTax());
           // String a = jdbcTemplate.query(sqlQueries.getDailyProfit, new Object[]{startDate,endDate});
            //TODO WRITE LOGIC OF PROFIT HERE
            trans.setProfitAmount(0.0);

            return trans;

        }
    }
}

