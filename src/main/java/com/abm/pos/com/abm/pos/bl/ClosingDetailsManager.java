package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.*;
import com.abm.pos.com.abm.pos.dto.reports.HourlyListDto;
import com.abm.pos.com.abm.pos.dto.reports.YearlyDto;
import com.abm.pos.com.abm.pos.dto.reports.YearlyListDto;
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

    public List<ClosingDetailsDto> getClosingDetailsToDB(String startDate, String endDate) {

        List<ClosingDetailsDto> closingDetails = new ArrayList<>();

        try {
            closingDetails = jdbcTemplate.query(sqlQueries.getClosingDetailsFromSystem, new ClosingMapper(), startDate, endDate);
        } catch (Exception e) {
            System.out.println(e);
        }

        return closingDetails;
    }

    private final class ClosingMapper implements RowMapper<ClosingDetailsDto> {

        @Override
        public ClosingDetailsDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            ClosingDetailsDto closingDto = new ClosingDetailsDto();

            // closingDto.setUserIdClose(rs.getInt("USER_ID_CLOSE"));
            closingDto.setReportCash(rs.getDouble("CASH"));
            closingDto.setReportCredit(rs.getDouble("CREDIT"));
            closingDto.setReportCheck(rs.getDouble("CHECKAMOUNT"));
            closingDto.setReportTotalAmount(rs.getDouble("TOTAL"));
            /*closingDto.setCloseCash(rs.getDouble("CLOSE_CASH"));
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
            closingDto.setTotalMarkup(rs.getDouble("TOTAL_MARKUP"));*/

            return closingDto;
        }
    }


    public HourlyListDto getHourlyTransactionDetails(String startDate, String endDate) {

        List<HourlyListDto> hourlyList = new ArrayList<>();

        HourlyMapper mapper = new HourlyMapper();


        try {
            hourlyList = jdbcTemplate.query(sqlQueries.getHourlyTransactions, mapper, startDate, endDate);

        } catch (Exception e) {
            System.out.println(e);
        }
        return mapper.hourlyListDto;

    }

    private final class HourlyMapper implements RowMapper<HourlyListDto> {

        double totalCredit;
        double totalCash;
        double totalCheck;
        double totalTax;
        double totalDiscount;
        double grandTotal;
        double totalProfit;
        int noOfTrans;

        HourlyListDto hourlyListDto = new HourlyListDto();

        List<HourlyDto> hourlyDtosList = new ArrayList<>();

        FinalTotalForReportsDto forReportsDto = new FinalTotalForReportsDto();

        List<FinalTotalForReportsDto> finalTotalForReportsDtos = new ArrayList<>();


        @Override
        public HourlyListDto mapRow(ResultSet rs, int rowNum) throws SQLException {


            FinalTotalForReportsDto forReportsDto = new FinalTotalForReportsDto();

            HourlyDto hourlyDto = new HourlyDto();

            List<FinalTotalForReportsDto> finalTotalForReportsDtos = new ArrayList<>();

            hourlyDto.setHour(rs.getInt("HOUR"));
            hourlyDto.setCredit(rs.getDouble("CREDIT"));
            hourlyDto.setCash(rs.getDouble("CASH"));
            hourlyDto.setCheck(rs.getDouble("CHEC"));
            hourlyDto.setTax(rs.getDouble("TAX"));
            hourlyDto.setDiscount(rs.getDouble("DISCOUNT"));
            hourlyDto.setTotal(rs.getDouble("TOTAL"));
            hourlyDto.setCost(rs.getDouble("COST"));
            hourlyDto.setRetail(rs.getDouble("RETAIL"));
            hourlyDto.setProfit(rs.getDouble("PROFIT"));
            hourlyDto.setNoOfTrans(rs.getInt("NOOFTRANS"));

            hourlyDtosList.add(hourlyDto);

            hourlyListDto.setHourlyDtoList(hourlyDtosList);

            totalCredit = totalCredit + hourlyDto.getCredit();
            totalCash = totalCash + hourlyDto.getCash();
            totalCheck = totalCheck + hourlyDto.getCheck();
            totalTax = totalTax + hourlyDto.getTax();
            totalDiscount = totalDiscount + hourlyDto.getDiscount();
            grandTotal = grandTotal + hourlyDto.getTotal();
            totalProfit = totalProfit + hourlyDto.getProfit();
            noOfTrans = noOfTrans + hourlyDto.getNoOfTrans();



            forReportsDto.setTotalCredit(totalCredit);
            forReportsDto.setTotalCash(totalCash);
            forReportsDto.setTotalCheck(totalCheck);
            forReportsDto.setTotalTax(totalTax);
            forReportsDto.setTotalDiscount(totalDiscount);
            forReportsDto.setGrandTotal(grandTotal);
            forReportsDto.setTotalProfit(totalProfit);
            forReportsDto.setNoOfTrans(noOfTrans);
            forReportsDto.setAvgBasketSize(12.99);

            finalTotalForReportsDtos.add(forReportsDto);

            hourlyListDto.setFinalTotalForReportsDtoList(finalTotalForReportsDtos);

            return hourlyListDto;
        }
    }

    public YearlyListDto getYearlyTransactionDetails(String startDate, String endDate) {

        List<YearlyListDto> yearOfMonth = new ArrayList<>();

        YearlyMapper yearlyMapper = new YearlyMapper();


        try
        {
            yearOfMonth = jdbcTemplate.query(sqlQueries.getYearlyTransaction, yearlyMapper,startDate,endDate);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return yearlyMapper.yearlyListDto;
    }

    private final class YearlyMapper implements RowMapper<YearlyListDto> {

        double totalCredit;
        double totalCash;
        double totalCheck;
        double totalTax;
        double totalDiscount;
        double grandTotal;
        double totalProfit;
        int noOfTrans;

        YearlyListDto yearlyListDto = new YearlyListDto();

        List<YearlyDto> yearlyDtos = new ArrayList<>();

        @Override
        public YearlyListDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            FinalTotalForReportsDto forReportsDto = new FinalTotalForReportsDto();

            YearlyDto yearlyDto = new YearlyDto();

            List<FinalTotalForReportsDto> finalTotalForReportsDtos = new ArrayList<>();


            yearlyDto.setMonthName(rs.getString("NameOfMonth"));
            yearlyDto.setCredit(rs.getDouble("CREDIT"));
            yearlyDto.setCash(rs.getDouble("CASH"));
            yearlyDto.setCheck(rs.getDouble("CHEC"));
            yearlyDto.setTax(rs.getDouble("TAX"));
            yearlyDto.setDiscount(rs.getDouble("DISCOUNT"));
            yearlyDto.setTotal(rs.getDouble("TOTAL"));
            yearlyDto.setCost(rs.getDouble("COST"));
            yearlyDto.setRetail(rs.getDouble("RETAIL"));
            yearlyDto.setProfit(rs.getDouble("PROFIT"));
            yearlyDto.setNoOfTrans(rs.getInt("NOOFTRANS"));

            yearlyDtos.add(yearlyDto);

            yearlyListDto.setYearlyListDtos(yearlyDtos);


            totalCredit = totalCredit + yearlyDto.getCredit();
            totalCash = totalCash + yearlyDto.getCash();
            totalCheck = totalCheck + yearlyDto.getCheck();
            totalTax = totalTax + yearlyDto.getTax();
            totalDiscount = totalDiscount + yearlyDto.getDiscount();
            grandTotal = grandTotal + yearlyDto.getTotal();
            totalProfit = totalProfit + yearlyDto.getProfit();
            noOfTrans = noOfTrans + yearlyDto.getNoOfTrans();



            forReportsDto.setTotalCredit(totalCredit);
            forReportsDto.setTotalCash(totalCash);
            forReportsDto.setTotalCheck(totalCheck);
            forReportsDto.setTotalTax(totalTax);
            forReportsDto.setTotalDiscount(totalDiscount);
            forReportsDto.setGrandTotal(grandTotal);
            forReportsDto.setTotalProfit(totalProfit);
            forReportsDto.setNoOfTrans(noOfTrans);
            forReportsDto.setAvgBasketSize(12.99);

            finalTotalForReportsDtos.add(forReportsDto);

            yearlyListDto.setFinalTotalForReportsDtos(finalTotalForReportsDtos);

            return yearlyListDto;
        }
    }

    public MonthlyListDto getMonthlyTransactionDetails(String startDate, String endDate) {

        List<MonthlyListDto> monthlyListDtos = new ArrayList<>();
        MonthlyDetailsMapper mapper = new MonthlyDetailsMapper();
        try {
            monthlyListDtos = jdbcTemplate.query(sqlQueries.getMonthlyTransDetails, mapper, startDate, endDate);

        } catch (Exception e) {
            System.out.println(e);
        }

        return mapper.monthlyListDto;

    }

    private final class MonthlyDetailsMapper implements RowMapper<MonthlyListDto> {

        double totalCredit;
        double totalCash;
        double totalCheck;
        double totalTax;
        double totalDiscount;
        double grandTotal;
        double totalProfit;
        int noOfTrans;

        MonthlyListDto monthlyListDto = new MonthlyListDto();

        List<MonthDto> monthDtoList = new ArrayList<>();

        FinalTotalForReportsDto forReportsDto = new FinalTotalForReportsDto();

        List<FinalTotalForReportsDto> finalTotalForReportsDtos = new ArrayList<>();

        @Override
        public MonthlyListDto mapRow(ResultSet rs, int rowNum) throws SQLException {


            FinalTotalForReportsDto forReportsDto = new FinalTotalForReportsDto();

            MonthDto monthDto = new MonthDto();

            List<FinalTotalForReportsDto> finalTotalForReportsDtos = new ArrayList<>();

            monthDto.setDate(rs.getString("DATE"));
            monthDto.setCash(rs.getDouble("SUM_CASH"));
            monthDto.setCredit(rs.getDouble("SUM_CREDIT"));
            monthDto.setTotal(rs.getDouble("TOTAL"));
            monthDto.setTax(rs.getDouble("SUM_TAX"));
            monthDto.setDiscount(rs.getDouble("DISCOUNT"));
            monthDto.setCost(rs.getDouble("COST"));
            monthDto.setRetail(rs.getDouble("RETAIL"));
            monthDto.setProfit(rs.getDouble("PROFIT"));
            monthDto.setNoOfTrans(rs.getInt("NOOFTRANS"));

            monthDtoList.add(monthDto);

            monthlyListDto.setMonthDtos(monthDtoList);

            totalCredit = totalCredit + monthDto.getCredit();
            totalCash = totalCash + monthDto.getCash();
            totalCheck = totalCheck + monthDto.getCheck();
            totalTax = totalTax + monthDto.getTax();
            totalDiscount = totalDiscount + monthDto.getDiscount();
            grandTotal = grandTotal + monthDto.getTotal();
            totalProfit = totalProfit + monthDto.getProfit();
            noOfTrans = noOfTrans + monthDto.getNoOfTrans();


                forReportsDto.setTotalCredit(totalCredit);
                forReportsDto.setTotalCash(totalCash);
                forReportsDto.setTotalCheck(totalCheck);
                forReportsDto.setTotalTax(totalTax);
                forReportsDto.setTotalDiscount(totalDiscount);
                forReportsDto.setGrandTotal(grandTotal);
                forReportsDto.setTotalProfit(totalProfit);
                forReportsDto.setNoOfTrans(noOfTrans);
                forReportsDto.setAvgBasketSize(14.99);

                finalTotalForReportsDtos.add(forReportsDto);

                monthlyListDto.setFinalTotalForReportsDtos(finalTotalForReportsDtos);


            return monthlyListDto;

        }
    }


    public List<WeekDto> getWeeklyTransactionDetails(String startDate, String endDate) {


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

        weeks.add(0, weekDto);

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


    public void addPaidOut(PaidOutDto paidOutDto) {

        try {
            jdbcTemplate.update(sqlQueries.addPaidOutDetails,
                    paidOutDto.getPaidOut1(),
                    paidOutDto.getPaidOut2(),
                    paidOutDto.getPaidOut3(),
                    paidOutDto.getReason1(),
                    paidOutDto.getReason2(),
                    paidOutDto.getReason3(),
                    paidOutDto.getPaidOutDate());

            System.out.println("Paid Amount added successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<PaidOutDto> getPaidOutDetails(String startDate, String endDate) {

        List<PaidOutDto> paidDto = new ArrayList<>();

        try {
            paidDto = jdbcTemplate.query(sqlQueries.getPaidOutDetails, new PaidOutMapper(), startDate, endDate);
            System.out.println("Send Paid Amount added successfully");
        } catch (Exception e) {
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


    // public


// GET DAILY TRANSACTION FROM DB

    public List<DailyTransactionDto> getDailyTransactionDetails(String startDate, String endDate) {

        List<DailyTransactionDto> trans = new ArrayList<>();
        DailyTransactionDto d = new DailyTransactionDto();

        try
        {
            trans.add(d);
            trans = jdbcTemplate.query(sqlQueries.getDailyTransaction, new DailyTransactionMapper(), startDate, endDate);

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

           // trans.setDate("2016-06-22");
            trans.setNoOfTransactions(rs.getInt("NOOFTRANS"));
            trans.setAvgTotal(rs.getDouble("AVGTOTAL"));
            trans.setTotal(rs.getDouble("TOTAL"));
            trans.setCash(rs.getDouble("CASH"));
            trans.setCredit(rs.getDouble("CREDIT"));
            trans.setCheck(rs.getDouble("SUMCHECK"));
            trans.setTax(rs.getDouble("TAX"));
            trans.setDiscount(rs.getDouble("DISCOUNT"));
            trans.setGrossSale((trans.getTotal()) - trans.getTax());
            trans.setProfitAmount(rs.getDouble("PROFIT"));

            return trans;

        }
    }
}

