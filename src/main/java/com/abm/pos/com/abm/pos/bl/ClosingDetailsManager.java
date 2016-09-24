package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.*;
import com.abm.pos.com.abm.pos.dto.reports.*;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
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

    private BaseFont bfBold;
    private BaseFont bf;
    private int pageNumber = 0;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQueries;

    @Autowired
    ReportManager reportManager;

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

            if (closingDetailsDto.getRegisterId() == 0) {

                jdbcTemplate.update(sqlQueries.addClosingDetails,
                        closingDetailsDto.getUserIdClose(),
                        closingDetailsDto.getReportCash(),
                        closingDetailsDto.getReportCredit(),
                        closingDetailsDto.getReportCheck(),
                        closingDetailsDto.getReportDebit(),
                        closingDetailsDto.getReportTotalAmount(),
                        closingDetailsDto.getCloseCash(),
                        closingDetailsDto.getCloseCredit(),
                        closingDetailsDto.getCloseCheck(),
                        closingDetailsDto.getCloseDebit(),
                        closingDetailsDto.getCloseDate(),
                        closingDetailsDto.getCloseTotalAmount(),
                        closingDetailsDto.getDifferenceCredit(),
                        closingDetailsDto.getDifferenceCash(),
                        closingDetailsDto.getDifferenceCheck(),
                        closingDetailsDto.getDifferenceDebit(),
                        closingDetailsDto.getTotalDifference(),
                        closingDetailsDto.getTotalBusinessAmount(),
                        closingDetailsDto.getTotalTax(),
                        closingDetailsDto.getTotalDiscount(),
                        closingDetailsDto.getTotalProfit(),
                        closingDetailsDto.getTotalMarkup(),
                        closingDetailsDto.getBankDeposit(),
                        closingDetailsDto.getCommission(),
                        closingDetailsDto.getCustomerBalance());

                System.out.println("Closing Details Added Successfully");
            } else {
                jdbcTemplate.update(sqlQueries.editClosingDetails,
                        closingDetailsDto.getUserIdClose(),
                        closingDetailsDto.getReportCash(),
                        closingDetailsDto.getReportCredit(),
                        closingDetailsDto.getReportCheck(),
                        closingDetailsDto.getReportDebit(),
                        closingDetailsDto.getReportTotalAmount(),
                        closingDetailsDto.getCloseCash(),
                        closingDetailsDto.getCloseCredit(),
                        closingDetailsDto.getCloseCheck(),
                        closingDetailsDto.getCloseDebit(),
                        closingDetailsDto.getCloseDate(),
                        closingDetailsDto.getCloseTotalAmount(),
                        closingDetailsDto.getDifferenceCredit(),
                        closingDetailsDto.getDifferenceCash(),
                        closingDetailsDto.getDifferenceCheck(),
                        closingDetailsDto.getDifferenceDebit(),
                        closingDetailsDto.getTotalDifference(),
                        closingDetailsDto.getTotalBusinessAmount(),
                        closingDetailsDto.getTotalTax(),
                        closingDetailsDto.getTotalDiscount(),
                        closingDetailsDto.getTotalProfit(),
                        closingDetailsDto.getTotalMarkup(),
                        closingDetailsDto.getBankDeposit(),
                        closingDetailsDto.getCommission(),
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

            dashboardDto = jdbcTemplate.queryForObject(sqlQueries.getClosingDetailsFromSystemFromTransaction, new TransactionCloseMapper(), startDate, endDate);
            //THIS CALL IS GIVING DATA FROM CASH_REGISTER TABLE BUT THE PROBLEM IS IT DOSE NOT HAVE THE DATA FROM THE SYSTEM ON UI, SO ADDING NOW DB CALL
            closingDetails = jdbcTemplate.query(sqlQueries.getClosingDetailsFromSystem, new ClosingMapper(), startDate, endDate);

            //Getting the discount from the lineitem table to get product level discount.
            String lineItemDiscount = jdbcTemplate.queryForObject(sqlQueries.getDiscountFromLineItemwithDate, new Object[]{startDate, endDate}, String.class);

            String profit = jdbcTemplate.queryForObject(sqlQueries.getPrpfitForCloseRegister, new Object[]{startDate, endDate}, String.class);

            //Here Getting customer balance to handle short or over issue in money for close register reports.
            String customerBalance = jdbcTemplate.queryForObject(sqlQueries.getCustomerBalanceByDate, new Object[]{startDate, endDate}, String.class);


            //this is first call from UI to get close register data cause i dont have any data into cash register table
            if (null != closingDetails && !closingDetails.isEmpty()) {

                //THIS CALL WILL SEND THE SYSTEM DATA TO UI WHICH IS USING TRANSACTION TABLE
                closingDetails.get(0).setReportCash(dashboardDto.getCash());
                closingDetails.get(0).setReportCredit(dashboardDto.getCredit());
                closingDetails.get(0).setReportCheck(dashboardDto.getCheck());
                closingDetails.get(0).setReportDebit(dashboardDto.getDebit());
                closingDetails.get(0).setTotalTax(dashboardDto.getTax());
                closingDetails.get(0).setReportTotalAmount(dashboardDto.getTotal());
                closingDetails.get(0).setCustomerBalance(dashboardDto.getBalance());

                if (null != lineItemDiscount) {

                    double lineItemDiscountDouble = Double.parseDouble(lineItemDiscount);
                    //System.out.println(lineItemDiscount);
                    closingDetails.get(0).setTotalDiscount(dashboardDto.getDiscount() + lineItemDiscountDouble);
                } else {
                    closingDetails.get(0).setTotalDiscount(dashboardDto.getDiscount());
                }

                /*if (null != customerBalance) {
                    double customerBalanceDouble = Double.parseDouble(customerBalance);

                    closingDetails.get(0).setCustomerBalance(customerBalanceDouble - closingDetails.get(0));
                }*/

                if (null != profit) {
                    double profitDouble = Double.parseDouble(profit);
                    closingDetails.get(0).setTotalProfit(profitDouble);
                }

            }


            //This mean user is closing the register 2nd time so call comes here.

            else {
                closingDetails = new ArrayList<>();
                ClosingDetailsDto closingDetailsDto = new ClosingDetailsDto();

                closingDetailsDto.setReportCash(dashboardDto.getCash());
                closingDetailsDto.setReportCredit(dashboardDto.getCredit());
                closingDetailsDto.setReportDebit(dashboardDto.getDebit());
                closingDetailsDto.setReportCheck(dashboardDto.getCheck());
                closingDetailsDto.setTotalTax(dashboardDto.getTax());
                closingDetailsDto.setReportTotalAmount(dashboardDto.getTotal());
                closingDetailsDto.setCustomerBalance(dashboardDto.getBalance());

                if (null != lineItemDiscount) {
                    double lineItemDiscountDouble = Double.parseDouble(lineItemDiscount);
                    //System.out.println(lineItemDiscount);
                    closingDetailsDto.setTotalDiscount(dashboardDto.getDiscount() + lineItemDiscountDouble);
                } else {
                    closingDetailsDto.setTotalDiscount(dashboardDto.getDiscount());
                }
                /*if (null != customerBalance) {
                    double customerBalanceDouble = Double.parseDouble(customerBalance);

                    closingDetailsDto.setCustomerBalance(customerBalanceDouble);
                }*/

                if (null != profit) {
                    double profitDouble = Double.parseDouble(profit);
                    closingDetailsDto.setTotalProfit(profitDouble);
                }


                closingDetails.add(closingDetailsDto);

                return closingDetails;
            }
            System.out.println("Send Closing details Successfully");
        } catch (Exception e) {
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
            closingDto.setDebit(rs.getDouble("DEBIT"));
            closingDto.setTotal(rs.getDouble("TOTAL"));
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
            closingDto.setReportDebit(rs.getDouble("REPORT_DEBIT"));
            closingDto.setReportTotalAmount(rs.getDouble("REPORT_TOTAL_AMOUNT"));
            closingDto.setCloseCash(rs.getDouble("CLOSE_CASH"));
            closingDto.setCloseCredit(rs.getDouble("CLOSE_CREDIT"));
            closingDto.setCloseCheck(rs.getDouble("CLOSE_CHECK"));
            closingDto.setCloseDebit(rs.getDouble("CLOSE_DEBIT"));
            closingDto.setCloseDate(rs.getString("CLOSE_DATE"));
            closingDto.setCloseTotalAmount(rs.getDouble("CLOSE_TOTAL_AMOUNT"));
            closingDto.setDifferenceCredit(rs.getDouble("CREDIT_DIFFERENCE"));
            closingDto.setDifferenceCash(rs.getDouble("CASH_DIFFERENCE"));
            closingDto.setDifferenceDebit(rs.getDouble("DEBIT_DIFFERENCE"));
            closingDto.setDifferenceCheck(rs.getDouble("CHECK_DIFFERENCE"));
            closingDto.setTotalDifference(rs.getDouble("TOTAL_DIFFERENCE"));
            closingDto.setTotalBusinessAmount(rs.getDouble("TOTAL_BUSINESS_AMOUNT"));
            // closingDto.setTotalTax(rs.getDouble("TOTAL_TAX"));
            //closingDto.setTotalDiscount(rs.getDouble("TOTAL_DISCOUNT"));
            // closingDto.setTotalProfit(rs.getDouble("TOTAL_PROFIT"));
            closingDto.setTotalMarkup(rs.getDouble("TOTAL_MARKUP"));
            closingDto.setBankDeposit(rs.getDouble("BANKDEPOSIT"));
            closingDto.setCommission(rs.getDouble("COMISSION"));

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
        double totalDebit;
        double totalTax;
        double totalDiscount;
        double grandTotal;
        double totalProfit;
        int noOfTrans;
        double balance;

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
            hourlyDto.setDebit(rs.getDouble("DEBIT"));
            hourlyDto.setTax(rs.getDouble("TAX"));
            hourlyDto.setDiscount(rs.getDouble("DISCOUNT"));
            hourlyDto.setTotal(rs.getDouble("CREDIT") + rs.getDouble("CASH") + rs.getDouble("CHEC") + rs.getDouble("DEBIT"));
            hourlyDto.setNoOfTrans(rs.getInt("NOOFTRANS"));
            //hourlyDto.setCost(rs.getDouble("COST"));
            // hourlyDto.setRetail(rs.getDouble("RETAIL"));
            hourlyDto.setProfit(rs.getDouble("PROFIT"));
            hourlyDto.setBalance(rs.getDouble("BALANCE"));


            hourlyDtosList.add(hourlyDto);

            hourlyListDto.setHourlyDtoList(hourlyDtosList);

            totalCredit = totalCredit + hourlyDto.getCredit();
            totalCash = totalCash + hourlyDto.getCash();
            totalCheck = totalCheck + hourlyDto.getCheck();
            totalDebit = totalDebit + hourlyDto.getDebit();
            totalTax = totalTax + hourlyDto.getTax();
            totalDiscount = totalDiscount + hourlyDto.getDiscount();
            grandTotal = grandTotal + hourlyDto.getTotal();
            totalProfit = totalProfit + hourlyDto.getProfit();
            noOfTrans = noOfTrans + hourlyDto.getNoOfTrans();
            balance = balance + hourlyDto.getBalance();


            forReportsDto.setTotalCredit(totalCredit);
            forReportsDto.setTotalCash(totalCash);
            forReportsDto.setTotalCheck(totalCheck);
            forReportsDto.setTotalDebit(totalDebit);
            forReportsDto.setTotalTax(totalTax);
            forReportsDto.setTotalDiscount(totalDiscount);
            forReportsDto.setGrandTotal(totalCash + totalCheck + totalCredit + totalDebit);
            forReportsDto.setTotalProfit(totalProfit);
            forReportsDto.setNoOfTrans(noOfTrans);
            forReportsDto.setBalance(balance);

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


        try {
            yearOfMonth = jdbcTemplate.query(sqlQueries.getYearlyTransaction, yearlyMapper, startDate, endDate, startDate, endDate);

            //Need Think of Adding Discount and Profit here
        } catch (Exception e) {
            System.out.println(e);
        }

        return yearlyMapper.yearlyListDto;
    }

    private final class YearlyMapper implements RowMapper<YearlyListDto> {

        double totalCredit;
        double totalCash;
        double totalCheck;
        double totalDebit;
        double totalTax;
        double totalDiscount;
        double grandTotal;
        double totalProfit;
        int noOfTrans;
        //double balance;

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
            yearlyDto.setDebit(rs.getDouble("DEBIT"));
            yearlyDto.setTax(rs.getDouble("TAX"));
            yearlyDto.setDiscount(rs.getDouble("DISCOUNT"));


            //if ()
            yearlyDto.setTotal(rs.getDouble("CASH") + rs.getDouble("CREDIT") + rs.getDouble("CHEC") + rs.getDouble("DEBIT"));
            //yearlyDto.setCost(rs.getDouble("COST"));
            //yearlyDto.setRetail(rs.getDouble("RETAIL"));
            yearlyDto.setProfit(rs.getDouble("PROFIT"));
            yearlyDto.setNoOfTrans(rs.getInt("NOOFTRANS"));
            //yearlyDto.setBalance(rs.getDouble("BALANCE"));


            yearlyDtos.add(yearlyDto);

            yearlyListDto.setYearlyListDtos(yearlyDtos);


            totalCredit = totalCredit + yearlyDto.getCredit();
            totalCash = totalCash + yearlyDto.getCash();
            totalCheck = totalCheck + yearlyDto.getCheck();
            totalDebit = totalDebit + yearlyDto.getDebit();
            totalTax = totalTax + yearlyDto.getTax();
            totalDiscount = totalDiscount + yearlyDto.getDiscount();
            grandTotal = grandTotal + yearlyDto.getTotal();
            totalProfit = totalProfit + yearlyDto.getProfit();
            noOfTrans = noOfTrans + yearlyDto.getNoOfTrans();
            //balance = balance + yearlyDto.getBalance();


            forReportsDto.setTotalCredit(totalCredit);
            forReportsDto.setTotalCash(totalCash);
            forReportsDto.setTotalCheck(totalCheck);
            forReportsDto.setTotalDebit(totalDebit);
            forReportsDto.setTotalTax(totalTax);
            forReportsDto.setTotalDiscount(totalDiscount);
            forReportsDto.setGrandTotal(totalCash + totalCheck + totalCredit + totalDebit);
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
        double totalDebit;
        double totalTax;
        double totalDiscount;
        double grandTotal;
        double totalProfit;
        int noOfTrans;
        double balance;


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
            monthDto.setCash(rs.getDouble("CASH"));
            monthDto.setCredit(rs.getDouble("CREDIT"));
            monthDto.setCheck(rs.getDouble("CHEC"));
            monthDto.setDebit(rs.getDouble("DEBIT"));
            monthDto.setTotal(rs.getDouble("CASH") + rs.getDouble("CREDIT") + rs.getDouble("CHEC") + rs.getDouble("DEBIT"));
            monthDto.setTax(rs.getDouble("TAX"));
            monthDto.setDiscount(rs.getDouble("DISCOUNT"));
            monthDto.setNoOfTrans(rs.getInt("NOOFTRANS"));
            monthDto.setProfit(rs.getDouble("PROFIT"));
            monthDto.setBalance(rs.getDouble("BALANCE"));

            monthDtoList.add(monthDto);

            monthlyListDto.setMonthDtos(monthDtoList);

            totalCredit = totalCredit + monthDto.getCredit();
            totalCash = totalCash + monthDto.getCash();
            totalCheck = totalCheck + monthDto.getCheck();
            totalDebit = totalDebit + monthDto.getDebit();
            totalTax = totalTax + monthDto.getTax();
            totalDiscount = totalDiscount + monthDto.getDiscount();
            grandTotal = grandTotal + monthDto.getTotal();
            totalProfit = totalProfit + monthDto.getProfit();
            noOfTrans = noOfTrans + monthDto.getNoOfTrans();
            balance = balance + monthDto.getBalance();


            forReportsDto.setTotalCredit(totalCredit);
            forReportsDto.setTotalCash(totalCash);
            forReportsDto.setTotalCheck(totalCheck);
            forReportsDto.setTotalDebit(totalDebit);
            forReportsDto.setTotalTax(totalTax);
            forReportsDto.setTotalDiscount(totalDiscount);
            forReportsDto.setGrandTotal(totalCash + totalCheck + totalCredit + totalDebit);
            forReportsDto.setTotalProfit(totalProfit);
            forReportsDto.setNoOfTrans(noOfTrans);
            forReportsDto.setBalance(balance);
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

            if (paidOutDto.getPaidOutId() == 0) {
                jdbcTemplate.update(sqlQueries.addPaidOutDetails,
                        paidOutDto.getPaidOutAmount1(),
                        paidOutDto.getPaidOutAmount2(),
                        paidOutDto.getPaidOutAmount3(),
                        paidOutDto.getPaidOutReason1(),
                        paidOutDto.getPaidOutReason2(),
                        paidOutDto.getPaidOutReason3(),
                        paidOutDto.getPaidOutDate());


                System.out.println("Paid Amount added successfully");
            } else {
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

        try {
            trans = jdbcTemplate.query(sqlQueries.getDailyTransaction, new DailyTransactionMapper(), startDate, endDate);

            //Getting profit = (RETAIL-COST-DISCOUNT/QUANTITY) * QUANTITY and PROFIT - TRANSACTION DISCOUNT(trans.get(0).getDiscount()) then subtracting total Transaction level discount from total profit form the Line item table and then subtracting the profit amount from the Total Transaction level discount.
            double profit = jdbcTemplate.queryForObject(sqlQueries.getPrpfitForCloseRegister, new Object[]{startDate, endDate}, double.class);
            trans.get(0).setProfitAmount(profit - trans.get(0).getDiscount());


            //Getting discount from lineItem table and adding with transaction table discount cause they 2 separate discounts
            double lineItemDiscount = jdbcTemplate.queryForObject(sqlQueries.getDiscountFromLineItemwithDate, new Object[]{startDate, endDate}, double.class);

            trans.get(0).setDiscount(trans.get(0).getDiscount() + lineItemDiscount);


        } catch (Exception e) {
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
            trans.setTotal(rs.getDouble("CASH") + rs.getDouble("CREDIT") + rs.getDouble("SUMCHECK") + rs.getDouble("DEBIT"));
            trans.setCash(rs.getDouble("CASH"));
            trans.setCredit(rs.getDouble("CREDIT"));
            trans.setDebit(rs.getDouble("DEBIT"));
            trans.setCheck(rs.getDouble("SUMCHECK"));
            trans.setTax(rs.getDouble("TAX"));
            trans.setDiscount(rs.getDouble("DISCOUNT"));
            trans.setGrossSale((trans.getTotal()) - trans.getTax());

            return trans;

        }
    }







    public byte[] printClosingDetails(String startDate, String endDate) throws DocumentException {

        Document doc = new Document(PageSize.A4);
        initializeFonts();


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        PdfWriter writer = PdfWriter.getInstance(doc, byteArrayOutputStream);

             List<ClosingDetailsDto> closingDetailsDtoList = new ArrayList<>();
            List<PaidOutDto> paidOutDtoList = new ArrayList<>();
             closingDetailsDtoList = getClosingDetailsToDB(startDate, endDate);
            //Calling getPaid out details to get paid out details
             paidOutDtoList = getPaidOutDetails(startDate, endDate);


        doc.open();

        PdfContentByte cb = writer.getDirectContent();
            boolean beginPage = true;
            int y = 0;
            for(int i=0; i < 100; i++ ){
                if(beginPage){
                    beginPage = false;
                    generateLayout(doc, cb);
                    generateHeader(doc, cb,closingDetailsDtoList, startDate,endDate);
                    y = 615;
                }
                generateClosingDetails(doc, cb, i, y,closingDetailsDtoList,paidOutDtoList);
                }

        doc.close();

        byte[] pdfDataBytes = byteArrayOutputStream.toByteArray();


        return pdfDataBytes;




    }
    private void generateLayout(Document doc, PdfContentByte cb)  {
        try {
            cb.setLineWidth(1f);
           // cb.rectangle(20,60,560,660);
            cb.rectangle(20,60,560,660);

            cb.moveTo(20,690);
            cb.lineTo(580,690);
            cb.moveTo(60,60);
            cb.lineTo(50,710);
            cb.moveTo(150,60);
            cb.lineTo(150,710);
            cb.moveTo(430,60);
            cb.lineTo(430,710);
            cb.stroke();
//            cb.rectangle(20,50,550,600);
//            cb.moveTo(20,630);
//            cb.lineTo(570,630);
//            cb.moveTo(50,50);
//            cb.lineTo(50,650);
//            cb.moveTo(150,50);
//            cb.lineTo(150,650);
//            cb.moveTo(430,50);
//            cb.lineTo(430,650);
//            cb.moveTo(500,50);
//            cb.lineTo(500,650);
//            cb.stroke();
            // Invoice Detail box Text Headings
            createHeadings(cb,50,693,"Payment Types");
            createHeadings(cb,200,693,"From User");
            createHeadings(cb,340,693,"From System");
            createHeadings(cb,480,693,"Difference");
            cb.moveTo(20,660);
            cb.lineTo(580,660);
//
//            cb.moveTo(20,630);
//            cb.lineTo(580,630);
//
//            cb.moveTo(20,600);
//            cb.lineTo(580,600);
//
//            cb.moveTo(20,570);
//            cb.lineTo(580,570);
//
//            cb.moveTo(20,540);
//            cb.lineTo(580,540);
//
//            cb.moveTo(20,510);
//            cb.lineTo(580,510);
//
//            cb.moveTo(20,480);
//            cb.lineTo(580,480);
//
//            cb.moveTo(20,450);
//            cb.lineTo(580,450);
//
//            cb.moveTo(20,420);
//            cb.lineTo(580,420);
//
//            cb.moveTo(20,390);
//            cb.lineTo(580,390);
//
//            cb.moveTo(20,360);
//            cb.lineTo(580,360);
//
//            cb.moveTo(20,330);
//            cb.lineTo(580,330);
//
//            cb.moveTo(20,300);
//            cb.lineTo(580,300);
//
//            cb.moveTo(20,270);
//            cb.lineTo(580,270);
//
//            cb.moveTo(20,240);
//            cb.lineTo(580,240);
//
//            cb.moveTo(20,210);
//            cb.lineTo(580,210);
//
//            cb.moveTo(20,180);
//            cb.lineTo(580,180);
//
//            cb.moveTo(20,150);
//            cb.lineTo(580,150);
            cb.stroke();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    private void generateHeader(Document doc, PdfContentByte cb, List<ClosingDetailsDto> closingDetailsDtos, String startDate, String endDate)  {
        try {


           //S /Users/asp5045/Documents/PointOfSell/src/main/

            Image companyLogo = Image.getInstance("newLogo23.png");
            companyLogo.setAbsolutePosition(235,780);
            companyLogo.scalePercent(25);
            doc.add(companyLogo);


            //createHeadingsAlokTest(cb,235,780,"EXCELL WIRELESS");
            createHeadingsAlokTest(cb,235,750,"Close Register Report");
            createHeadings(cb,20,725,"Date From:");
            createHeadings(cb,450,725,"Today's Profit:");
            DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date sd = null;
            Date ed = null;
            try {
                sd = f.parse(startDate);
                ed = f.parse(endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            DateFormat date = new SimpleDateFormat("MM/dd/yyyy");//NEED TO CHECK THIS
            createHeadings(cb, 65, 725, date.format(sd) + " "+ "To" + " " +date.format(ed));
            if((null != closingDetailsDtos && null != Double.toString(closingDetailsDtos.get(0).getTotalProfit()))) {
                createHeadings(cb, 508, 725, "$" + Double.toString(closingDetailsDtos.get(0).getTotalProfit()));
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    private void generateClosingDetails(Document doc, PdfContentByte cb, int index, int y, List<ClosingDetailsDto> closingDetailsDtos, List<PaidOutDto> paidOutDtos)  {
        DecimalFormat df = new DecimalFormat("0.00");
        try {
            createContent(cb,21,675,"Credit",PdfContentByte.ALIGN_LEFT);
            createContent(cb,21,645,"Debit",PdfContentByte.ALIGN_LEFT);
            createContent(cb,21,615,"Cash",PdfContentByte.ALIGN_LEFT);
            createContent(cb,21,585,"Check",PdfContentByte.ALIGN_LEFT);
            //For Credit
            createContent(cb,152,675,"$" +Double.toString(closingDetailsDtos.get(0).getCloseCredit()),PdfContentByte.ALIGN_LEFT);
            createContent(cb,292,675,"$" +Double.toString(closingDetailsDtos.get(0).getReportCredit()),PdfContentByte.ALIGN_LEFT);
            createContent(cb,432,675,"$" +Double.toString(closingDetailsDtos.get(0).getDifferenceCredit()),PdfContentByte.ALIGN_LEFT);
           // For Debit
            createContent(cb,152,645,"$" +Double.toString(closingDetailsDtos.get(0).getCloseDebit()),PdfContentByte.ALIGN_LEFT);
            createContent(cb,292,645,"$" +Double.toString(closingDetailsDtos.get(0).getReportDebit()),PdfContentByte.ALIGN_LEFT);
            createContent(cb,432,645,"$" +Double.toString(closingDetailsDtos.get(0).getDifferenceDebit()),PdfContentByte.ALIGN_LEFT);
            //For Cash
            createContent(cb,152,615,"$" +Double.toString(closingDetailsDtos.get(0).getCloseCash()),PdfContentByte.ALIGN_LEFT);
            createContent(cb,292,615,"$" +Double.toString(closingDetailsDtos.get(0).getReportCash()),PdfContentByte.ALIGN_LEFT);
            createContent(cb,432,615,"$" +Double.toString(closingDetailsDtos.get(0).getDifferenceCash()),PdfContentByte.ALIGN_LEFT);
            //For Check
            createContent(cb,152,585,"$" +Double.toString(closingDetailsDtos.get(0).getCloseCheck()),PdfContentByte.ALIGN_LEFT);
            createContent(cb,292,585,"$" +Double.toString(closingDetailsDtos.get(0).getReportCheck()),PdfContentByte.ALIGN_LEFT);
            createContent(cb,432,585,"$" +Double.toString(closingDetailsDtos.get(0).getDifferenceCheck()),PdfContentByte.ALIGN_LEFT);
            createContent(cb,21,555,"Paid Out (Sum)",PdfContentByte.ALIGN_LEFT);
            createContent(cb,152,555,"Paid Out-1",PdfContentByte.ALIGN_LEFT);
            createContent(cb,292,555,"Paid Out-2",PdfContentByte.ALIGN_LEFT);
            createContent(cb,432,555,"Paid Out-3",PdfContentByte.ALIGN_LEFT);
            //Adding paid out details from get paid out end point
            createContent(cb,21,520, "$" +Double.toString(paidOutDtos.get(0).getPaidOutAmount1() + paidOutDtos.get(0).getPaidOutAmount2() + paidOutDtos.get(0).getPaidOutAmount3()),PdfContentByte.ALIGN_LEFT);
            createContent(cb,152,520,"$" +Double.toString(paidOutDtos.get(0).getPaidOutAmount1()),PdfContentByte.ALIGN_LEFT);
            createContent(cb,292,520,"$" +Double.toString(paidOutDtos.get(0).getPaidOutAmount2()),PdfContentByte.ALIGN_LEFT);
            createContent(cb,432,520,"$" +Double.toString(paidOutDtos.get(0).getPaidOutAmount3()),PdfContentByte.ALIGN_LEFT);
            createContent(cb,21,490,"Actual Daily Total",PdfContentByte.ALIGN_LEFT);
            createContent(cb,152,490,"Total From User",PdfContentByte.ALIGN_LEFT);
            createContent(cb,292,490,"Total From System",PdfContentByte.ALIGN_LEFT);
            createContent(cb,432,490,"Total Difference",PdfContentByte.ALIGN_LEFT);
            //This just for now need to replace with the real values from service call.
            createContent(cb,21,460,"$" +Double.toString(closingDetailsDtos.get(0).getCloseTotalAmount()),PdfContentByte.ALIGN_LEFT);
            createContent(cb,152,460,"$" +Double.toString(closingDetailsDtos.get(0).getCloseTotalAmount()),PdfContentByte.ALIGN_LEFT);
            createContent(cb,292,460,"$" +Double.toString(closingDetailsDtos.get(0).getReportTotalAmount()),PdfContentByte.ALIGN_LEFT);
            createContent(cb,432,460,"$" +Double.toString(closingDetailsDtos.get(0).getTotalDifference()),PdfContentByte.ALIGN_LEFT);
            createContent(cb,21,430,"Net Sales",PdfContentByte.ALIGN_LEFT);
            createContent(cb,152,430,"Tax",PdfContentByte.ALIGN_LEFT);
            createContent(cb,292,430,"Discount",PdfContentByte.ALIGN_LEFT);
            createContent(cb,432,430,"Gross Sales",PdfContentByte.ALIGN_LEFT);
            //This just for now need to replace with the real values from service call.
            //Net Sales = Total From System - Tax + Sum of all Paid outs because that is part of the sale
            createContent(cb,21,400,"$" +Double.toString(closingDetailsDtos.get(0).getReportTotalAmount() - closingDetailsDtos.get(0).getTotalTax() + paidOutDtos.get(0).getPaidOutAmount1() + paidOutDtos.get(0).getPaidOutAmount2() + paidOutDtos.get(0).getPaidOutAmount3()),PdfContentByte.ALIGN_LEFT);
            createContent(cb,152,400,"$" +Double.toString(closingDetailsDtos.get(0).getTotalTax()),PdfContentByte.ALIGN_LEFT);
            createContent(cb,292,400,"$" +Double.toString(closingDetailsDtos.get(0).getTotalDiscount()),PdfContentByte.ALIGN_LEFT);
            createContent(cb,432,400,"$" +Double.toString(closingDetailsDtos.get(0).getReportTotalAmount() + paidOutDtos.get(0).getPaidOutAmount1() + paidOutDtos.get(0).getPaidOutAmount2() + paidOutDtos.get(0).getPaidOutAmount3()),PdfContentByte.ALIGN_LEFT);
            createContent(cb,21,370,"Customer Balance",PdfContentByte.ALIGN_LEFT);
            createContent(cb,152,370,"Commission",PdfContentByte.ALIGN_LEFT);
            createContent(cb,292,370,"Bank Deposit",PdfContentByte.ALIGN_LEFT);
            createContent(cb,432,370,"Cash In Hand",PdfContentByte.ALIGN_LEFT);
            //This just for now need to replace with the real values from service call.
            createContent(cb,21,340,"$" +Double.toString(closingDetailsDtos.get(0).getCustomerBalance()),PdfContentByte.ALIGN_LEFT);
            createContent(cb,152,340,"$" +Double.toString(closingDetailsDtos.get(0).getCommission()),PdfContentByte.ALIGN_LEFT);
            createContent(cb,292,340,"$" +Double.toString(closingDetailsDtos.get(0).getBankDeposit()),PdfContentByte.ALIGN_LEFT);
            createContent(cb,432,340,"$" +Double.toString(closingDetailsDtos.get(0).getCashInHand()),PdfContentByte.ALIGN_LEFT);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    private void createHeadings(PdfContentByte cb, float x, float y, String text){
        cb.beginText();
        cb.setFontAndSize(bfBold, 15);
        cb.setTextMatrix(x,y);
        cb.showText(text.trim());
        cb.endText();
    }
    private void createHeadingsAlokTest(PdfContentByte cb, float x, float y, String text){
        cb.beginText();
        cb.setFontAndSize(bfBold, 15);
        cb.setTextMatrix(x,y);
        cb.showText(text.trim());
        cb.endText();
    }
    private void createContent(PdfContentByte cb, float x, float y, String text, int align){
        cb.beginText();
        cb.setFontAndSize(bf, 15);
        cb.showTextAligned(align, text.trim(), x , y, 0);
        cb.endText();
    }
    private void initializeFonts() {
        try {
            bfBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
