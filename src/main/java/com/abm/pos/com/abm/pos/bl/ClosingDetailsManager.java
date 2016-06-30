package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.ClosingDetailsDto;
import com.abm.pos.com.abm.pos.dto.DailyTransactionDto;
import com.abm.pos.com.abm.pos.dto.PaidOutDto;
import com.abm.pos.com.abm.pos.dto.WeekDto;
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

    public List<WeekDto> getMontlyTransactionDetails(String startDate, String endDate, int month) {


        List<WeekDto> week = new ArrayList<>();

        try
        {

        }
        catch (Exception e)
        {

        }

        return week;

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

