package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.*;
import com.abm.pos.com.abm.pos.dto.reports.HourlyListDto;
import com.abm.pos.com.abm.pos.dto.reports.YearlyDto;
import com.abm.pos.com.abm.pos.dto.reports.YearlyListDto;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by asp5045 on 7/31/16.
 */

@Component
public class ClosingDetailsManager {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQueries;

    public static final String FONT = "resources/fonts/OpenSans-Regular.ttf";
    public static final String FONTB = "resources/fonts/OpenSans-Bold.ttf";
    protected Font font10;
    protected Font font10b;
    protected Font font12;
    protected Font font12b;
    protected Font font14;
    public void addClosingDetailsToDB(ClosingDetailsDto closingDetailsDto) {

        //Checking if register_id is 0 that means closing details is inserting first time and if not then i need to edit it.
        try {

            if (closingDetailsDto.getRegisterId() == 0)
            {

                jdbcTemplate.update(sqlQueries.addClosingDetails,
                        closingDetailsDto.getUserIdClose(),
                        closingDetailsDto.getReportCash(),
                        closingDetailsDto.getReportCredit(),
                        closingDetailsDto.getReportCheck(),
                        closingDetailsDto.getReportTotalAmount(),
                        closingDetailsDto.getCloseCash(),
                        closingDetailsDto.getCloseCredit(),
                        closingDetailsDto.getCloseDate(),
                        closingDetailsDto.getCloseTotalAmount(),
                        closingDetailsDto.getDifferenceCredit(),
                        closingDetailsDto.getDifferenceCash(),
                        closingDetailsDto.getTotalDifference(),
                        closingDetailsDto.getTotalBusinessAmount(),
                        closingDetailsDto.getTotalTax(),
                        closingDetailsDto.getTotalDiscount(),
                        closingDetailsDto.getTotalProfit(),
                        closingDetailsDto.getTotalMarkup(),
                        closingDetailsDto.getBankDeposit(),
                        closingDetailsDto.getCustomerBalance());

                System.out.println("Closing Details Added Successfully");
            }

            else
            {
                jdbcTemplate.update(sqlQueries.editClosingDetails,
                        closingDetailsDto.getUserIdClose(),
                        closingDetailsDto.getReportCash(),
                        closingDetailsDto.getReportCredit(),
                        closingDetailsDto.getReportCheck(),
                        closingDetailsDto.getReportTotalAmount(),
                        closingDetailsDto.getCloseCash(),
                        closingDetailsDto.getCloseCredit(),
                        closingDetailsDto.getCloseDate(),
                        closingDetailsDto.getCloseTotalAmount(),
                        closingDetailsDto.getDifferenceCredit(),
                        closingDetailsDto.getDifferenceCash(),
                        closingDetailsDto.getTotalDifference(),
                        closingDetailsDto.getTotalBusinessAmount(),
                        closingDetailsDto.getTotalTax(),
                        closingDetailsDto.getTotalDiscount(),
                        closingDetailsDto.getTotalProfit(),
                        closingDetailsDto.getTotalMarkup(),
                        closingDetailsDto.getBankDeposit(),
                        closingDetailsDto.getCustomerBalance(),
                        closingDetailsDto.getRegisterId());

                System.out.println("Closing Details Edited Successfully");

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public List<ClosingDetailsDto> getClosingDetailsToDB(String startDate, String endDate) {

        List<ClosingDetailsDto> closingDetails = new ArrayList<>();
        DashboardDto dashboardDto = new DashboardDto();

        // I NEED TO CHANGE CLOSING DETAISL TO OBJECT INSTADE OF ARRY LIST CAUSE IT DOESNT MAKE ANY SENSE.

        try {

            //GETTING CURRENT DATE

            dashboardDto = jdbcTemplate.queryForObject(sqlQueries.getClosingDetailsFromSystemFromTransaction, new TransactionCloseMapper(), startDate,endDate);
            //THIS CALL IS GIVING DATA FROM CASH_REGISTER TABLE BUT THE PROBLEM IS IT DOSE NOT HAVE THE DATA FROM THE SYSTEM ON UI, SO ADDING NOW DB CALL
            closingDetails = jdbcTemplate.query(sqlQueries.getClosingDetailsFromSystem, new ClosingMapper(), startDate, endDate);

            //Getting the discount from the lineitem table to get product level discount.
            String lineItemDiscount = jdbcTemplate.queryForObject(sqlQueries.getDiscountFromLineItemwithDate, new Object[]{startDate,endDate}, String.class);

            String profit = jdbcTemplate.queryForObject(sqlQueries.getPrpfitForCloseRegister, new Object[] {startDate,endDate}, String.class);

            //Here Getting customer balance to handle short or over issue in money for close register reports.
            String customerBalance = jdbcTemplate.queryForObject(sqlQueries.getCustomerBalanceByDate, new Object[] {startDate,endDate},String.class);


            //Here getting the sum of all paid for particular day and then adding into daily total so i can match paidout and total amount.
            //String totalPaidout = jdbcTemplate.queryForObject(sqlQueries.getSumOfPaidOut, new Object[] {startDate,endDate}, String.class);



            //this is first call from UI to get close register data cause i dont have any data into cash register table
            if(null != closingDetails && !closingDetails.isEmpty()) {

                //THIS CALL WILL SEND THE SYSTEM DATA TO UI WHICH IS USING TRANSACTION TABLE
                closingDetails.get(0).setReportCash(dashboardDto.getCash());
                closingDetails.get(0).setReportCredit(dashboardDto.getCredit());
                closingDetails.get(0).setReportCheck(dashboardDto.getCheck());
                closingDetails.get(0).setTotalTax(dashboardDto.getTax());
                closingDetails.get(0).setReportTotalAmount(dashboardDto.getTotal());

                if (null != lineItemDiscount) {

                    double lineItemDiscountDouble = Double.parseDouble(lineItemDiscount);
                    //System.out.println(lineItemDiscount);
                    closingDetails.get(0).setTotalDiscount(dashboardDto.getDiscount() + lineItemDiscountDouble);
                }
                else {
                    closingDetails.get(0).setTotalDiscount(dashboardDto.getDiscount());
                }

                if(null != customerBalance)
                {
                    double customerBalanceDouble = Double.parseDouble(customerBalance);

                    closingDetails.get(0).setCustomerBalance(customerBalanceDouble);
                }

                if(null != profit)
                {
                    double profitDouble = Double.parseDouble(customerBalance);
                    closingDetails.get(0).setTotalProfit(profitDouble);
                }

            }



            //This mean user is closing the register 2nd time so call comes here.

        else{
                closingDetails = new ArrayList<>();
                ClosingDetailsDto closingDetailsDto= new ClosingDetailsDto();

                closingDetailsDto.setReportCash(dashboardDto.getCash());
                closingDetailsDto.setReportCredit(dashboardDto.getCredit());
                closingDetailsDto.setReportCheck(dashboardDto.getCheck());
                closingDetailsDto.setTotalTax(dashboardDto.getTax());
                closingDetailsDto.setReportTotalAmount(dashboardDto.getTotal());

                if (null != lineItemDiscount) {
                    double lineItemDiscountDouble = Double.parseDouble(lineItemDiscount);
                    //System.out.println(lineItemDiscount);
                    closingDetailsDto.setTotalDiscount(dashboardDto.getDiscount() + lineItemDiscountDouble);
                } else {
                    closingDetailsDto.setTotalDiscount(dashboardDto.getDiscount());
                }
                if(null != customerBalance)
                {
                    double customerBalanceDouble = Double.parseDouble(customerBalance);

                    closingDetailsDto.setCustomerBalance(customerBalanceDouble);
                }

                if(null != profit)
                {
                    double profitDouble = Double.parseDouble(profit);
                    closingDetailsDto.setTotalProfit(profitDouble);
                }


                closingDetails.add(closingDetailsDto);

                return closingDetails;
            }
                System.out.println("Send Closing details Successfully");
        }
            catch (Exception e) {
                System.out.println(e);
        }

        return closingDetails;
    }




    //I AM JUST USING DASHBOARD DTO TO NOT TO CREATE DUPLICATE DTO
    private final class TransactionCloseMapper implements RowMapper<DashboardDto> {

        @Override
        public DashboardDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            DashboardDto closingDto = new DashboardDto();

            closingDto.setCash(rs.getDouble("CASH"));
            closingDto.setCredit(rs.getDouble("CREDIT"));
            closingDto.setCheck(rs.getDouble("CHECKAMOUNT"));
            closingDto.setTotal(rs.getDouble("TOTAL") - rs.getDouble("BALANCE"));
            closingDto.setTax(rs.getDouble("TAX"));
            closingDto.setDiscount(rs.getDouble("DISCOUNT"));
            closingDto.setBalance(rs.getDouble("BALANCE"));


            //closingDto.setProfit(rs.getDouble("PROFIT"));

            return closingDto;
        }
    }

    private final class ClosingMapper implements RowMapper<ClosingDetailsDto> {

        @Override
        public ClosingDetailsDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            ClosingDetailsDto closingDto = new ClosingDetailsDto();

            closingDto.setRegisterId(rs.getInt("REGISTER_ID"));
            closingDto.setReportCash(rs.getDouble("REPORT_CASH"));
            closingDto.setReportCredit(rs.getDouble("REPORT_CREDIT"));
            closingDto.setReportCheck(rs.getDouble("REPORT_CHECK"));
            closingDto.setReportTotalAmount(rs.getDouble("REPORT_TOTAL_AMOUNT"));
            closingDto.setCloseCash(rs.getDouble("CLOSE_CASH"));
            closingDto.setCloseCredit(rs.getDouble("CLOSE_CREDIT"));
            closingDto.setCloseDate(rs.getString("CLOSE_DATE"));
            closingDto.setCloseTotalAmount(rs.getDouble("CLOSE_TOTAL_AMOUNT"));
            closingDto.setDifferenceCredit(rs.getDouble("CREDIT_DIFFERENCE"));
            closingDto.setDifferenceCash(rs.getDouble("CASH_DIFFERENCE"));
            closingDto.setTotalDifference(rs.getDouble("TOTAL_DIFFERENCE"));
            closingDto.setTotalBusinessAmount(rs.getDouble("TOTAL_BUSINESS_AMOUNT"));
            // closingDto.setTotalTax(rs.getDouble("TOTAL_TAX"));
            //closingDto.setTotalDiscount(rs.getDouble("TOTAL_DISCOUNT"));
            // closingDto.setTotalProfit(rs.getDouble("TOTAL_PROFIT"));
            closingDto.setTotalMarkup(rs.getDouble("TOTAL_MARKUP"));
            closingDto.setBankDeposit(rs.getDouble("BANKDEPOSIT"));

            return closingDto;
        }
    }

    public HourlyListDto getHourlyTransactionDetails(String startDate, String endDate) {

        List<HourlyListDto> hourlyList = new ArrayList<>();

        HourlyMapper mapper = new HourlyMapper();

        try {

            hourlyList = jdbcTemplate.query(sqlQueries.getHourlyTransactions, mapper, startDate, endDate, startDate, endDate);



            //jdbcTemplate.query(sqlQueries.getHorlyProfit);




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
            hourlyDto.setNoOfTrans(rs.getInt("NOOFTRANS"));
            //hourlyDto.setCost(rs.getDouble("COST"));
           // hourlyDto.setRetail(rs.getDouble("RETAIL"));
            hourlyDto.setProfit(rs.getDouble("PROFIT"));


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

    private final class HourlyProfitMapper implements RowMapper<HourlyDto> {


        @Override
        public HourlyDto mapRow(ResultSet rs, int rowNum) throws SQLException {


            HourlyDto hourlyDto = new HourlyDto();

            hourlyDto.setProfit(rs.getDouble("PROFIT"));

            return hourlyDto;
        }
    }



    public YearlyListDto getYearlyTransactionDetails(String startDate, String endDate) {

        List<YearlyListDto> yearOfMonth = new ArrayList<>();

        YearlyMapper yearlyMapper = new YearlyMapper();


        try
        {
            yearOfMonth = jdbcTemplate.query(sqlQueries.getYearlyTransaction, yearlyMapper,startDate,endDate, startDate, endDate);

            //Need Think of Adding Discount and Profit here
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
        double balance;

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
            //yearlyDto.setCost(rs.getDouble("COST"));
            //yearlyDto.setRetail(rs.getDouble("RETAIL"));
            yearlyDto.setProfit(rs.getDouble("PROFIT"));
            yearlyDto.setNoOfTrans(rs.getInt("NOOFTRANS"));
            yearlyDto.setBalance(rs.getDouble("BALANCE"));

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
            balance = balance + yearlyDto.getBalance();



            forReportsDto.setTotalCredit(totalCredit);
            forReportsDto.setTotalCash(totalCash);
            forReportsDto.setTotalCheck(totalCheck);
            forReportsDto.setTotalTax(totalTax);
            forReportsDto.setTotalDiscount(totalDiscount);
            forReportsDto.setGrandTotal(grandTotal - balance);
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
            monthlyListDtos = jdbcTemplate.query(sqlQueries.getMonthlyTransDetails, mapper, startDate, endDate, startDate, endDate);

            //Need Think of Adding Discount and Profit here

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

            if(paidOutDto.getPaidOutId() == 0) {
                jdbcTemplate.update(sqlQueries.addPaidOutDetails,
                        paidOutDto.getPaidOutAmount1(),
                        paidOutDto.getPaidOutAmount2(),
                        paidOutDto.getPaidOutAmount3(),
                        paidOutDto.getPaidOutReason1(),
                        paidOutDto.getPaidOutReason2(),
                        paidOutDto.getPaidOutReason3(),
                        paidOutDto.getPaidOutDate());


                System.out.println("Paid Amount added successfully");
            }

            else
            {
                editPaidOut(paidOutDto);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<PaidOutDto> getPaidOutDetails(String startDate, String endDate) {

        List<PaidOutDto> paidDto = new ArrayList<>();

        try {
            paidDto = jdbcTemplate.query(sqlQueries.getPaidOutDetails, new PaidOutMapper(), startDate, endDate);
            System.out.println("Send Paid Amount successfully");
        } catch (Exception e) {
            System.out.println(e);
        }

        return paidDto;
    }

    private final class PaidOutMapper implements RowMapper<PaidOutDto> {

        double sum;

        @Override
        public PaidOutDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            PaidOutDto paidOut = new PaidOutDto();

            paidOut.setPaidOutId(rs.getInt("PAIDOUT_ID"));
            paidOut.setPaidOutAmount1(rs.getDouble("PAIDOUT1"));
            paidOut.setPaidOutAmount2(rs.getDouble("PAIDOUT2"));
            paidOut.setPaidOutAmount3(rs.getDouble("PAIDOUT3"));
            paidOut.setPaidOutReason1(rs.getString("REASON1"));
            paidOut.setPaidOutReason2(rs.getString("REASON2"));
            paidOut.setPaidOutReason3(rs.getString("REASON3"));

            //Adding all paid out and sending sum of it to ui
            paidOut.setSumPaidOut(paidOut.getPaidOutAmount1() + paidOut.getPaidOutAmount2() + paidOut.getPaidOutAmount3());
            paidOut.setPaidOutDate(rs.getString("DATE"));


            return paidOut;

        }
    }

    public void editPaidOut(PaidOutDto paidOutDto) {

        try {
            jdbcTemplate.update(sqlQueries.editPaidOutDetails,
                    paidOutDto.getPaidOutAmount1(),
                    paidOutDto.getPaidOutAmount2(),
                    paidOutDto.getPaidOutAmount3(),
                    paidOutDto.getPaidOutReason1(),
                    paidOutDto.getPaidOutReason2(),
                    paidOutDto.getPaidOutReason3(),
                    paidOutDto.getPaidOutDate(),
                    paidOutDto.getPaidOutId());

            System.out.println("Paid Amount Edit successfully");
        } catch (Exception e) {
            System.out.println(e);
        }

    }
// GET DAILY TRANSACTION FROM DB

    public List<DailyTransactionDto> getDailyTransactionDetails(String startDate, String endDate) {

        List<DailyTransactionDto> trans = new ArrayList<>();
        //DailyTransactionDto d = new DailyTransactionDto();

        try
        {
            trans = jdbcTemplate.query(sqlQueries.getDailyTransaction, new DailyTransactionMapper(), startDate, endDate);

            //Getting profit = (RETAIL-COST-DISCOUNT/QUANTITY) * QUANTITY and PROFIT - TRANSACTION DISCOUNT(trans.get(0).getDiscount()) then subtracting total Transaction level discount from total profit form the Line item table and then subtracting the profit amount from the Total Transaction level discount.
            double profit = jdbcTemplate.queryForObject(sqlQueries.getPrpfitForCloseRegister, new Object[] {startDate,endDate}, double.class);
            trans.get(0).setProfitAmount(profit - trans.get(0).getDiscount());
            //System.out.println(profit);
           // System.out.println(profit-trans.get(0).getDiscount());

            //Getting discount from lineItem table and adding with transaction table discount cause they 2 separate discounts
            double lineItemDiscount = jdbcTemplate.queryForObject(sqlQueries.getDiscountFromLineItemwithDate, new Object[]{startDate,endDate}, double.class);
           // System.out.println(lineItemDiscount + trans.get(0).getDiscount());
            trans.get(0).setDiscount(trans.get(0).getDiscount() + lineItemDiscount);
          //  System.out.println(trans.get(0).getDiscount());
          //  System.out.println(lineItemDiscount);






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

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            trans.setDate(dateFormat.format(date));
            System.out.println(dateFormat.format(date));
            trans.setNoOfTransactions(rs.getInt("NOOFTRANS"));
            trans.setAvgTotal(rs.getDouble("AVGTOTAL"));
            trans.setTotal(rs.getDouble("TOTAL"));
            trans.setCash(rs.getDouble("CASH"));
            trans.setCredit(rs.getDouble("CREDIT"));
            trans.setCheck(rs.getDouble("SUMCHECK"));
            trans.setTax(rs.getDouble("TAX"));
            trans.setDiscount(rs.getDouble("DISCOUNT"));
            trans.setGrossSale((trans.getTotal()) - trans.getTax());

            return trans;

        }
    }

    public void printClosingDetails(String startDate, String endDate) {

       /* SalesManager salesManager = new SalesManager();
        List<ReceiptDto> receiptDtos = new ArrayList<>();

        receiptDtos = salesManager.getReceiptDetails(1);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);



        PdfPCell seller = getPartyAddress("From:",*/


    }

    public PdfPCell getPartyAddress(String who, String name,
                                    String line1, String line2, String countryID, String postcode, String city) {
        PdfPCell cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.addElement(new Paragraph(who, font12b));
        cell.addElement(new Paragraph(name, font12));
        cell.addElement(new Paragraph(line1, font12));
        cell.addElement(new Paragraph(line2, font12));
        cell.addElement(new Paragraph(
                String.format("%s-%s %s", countryID, postcode, city), font12));
        return cell;
    }

    public String getLineItemDiscount(String startDate, String endDate)
    {
        String lineItemDiscount = jdbcTemplate.queryForObject(sqlQueries.getDiscountFromLineItemwithDate, new Object[]{startDate,endDate}, String.class);

        return lineItemDiscount;
    }

}