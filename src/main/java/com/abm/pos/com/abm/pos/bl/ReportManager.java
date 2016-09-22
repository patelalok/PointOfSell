package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.reports.*;
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
 * Created by asp5045 on 7/4/16.
 */

@Component
public class ReportManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQueries;

//    JdbcTemplate jdbcTemplate = new JdbcTemplate();
//    SQLQueries sqlQueries = new SQLQueries();


    public CommonComparisonTotalDto getTop50Items(String startDate, String endDate) {

        List<CommonComparisonTotalDto> commonComparisonDtos = new ArrayList<>();

        SalesCategoryManager mapper = new SalesCategoryManager();

        try
        {
            commonComparisonDtos = jdbcTemplate.query(sqlQueries.getTop50Items, mapper, startDate,endDate);

        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return mapper.commonComparisonTotalDto;

    }




   /* private final class Top50Mapper implements RowMapper<Top50ItemsDto>
    {
        @Override
        public Top50ItemsDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            Top50ItemsDto top50 = new Top50ItemsDto();

            top50.setProductNo(rs.getString("PRODUCT_NO"));
            top50.setProductName(rs.getString("DESCRIPTION"));
            top50.setQuantity(rs.getInt("QUANTITY"));
            top50.setProductSumRetailPrice(rs.getDouble("RETAIL"));
            top50.setProductSumCostPrice(rs.getDouble("COST"));

            double profitAmount = top50.getProductSumRetailPrice()-top50.getProductSumCostPrice();
            System.out.println("1----"+ profitAmount);
            profitAmount = profitAmount * top50.getQuantity();
            System.out.println("2----"+ profitAmount);
            top50.setProductProfitAmount(profitAmount);
            System.out.println("3----"+top50.getProductProfitAmount());

            int test =+ top50.getQuantity();

            if( rs.isLast())
            {
                top50.setRetailPrice(test);
                System.out.println(test);
                System.out.println(top50.getRetailPrice());
                System.out.println("test");
            }
            return top50;
        }
    }*/

    public CommonComparisonTotalDto getSalesByCategory(String startDate, String endDate) {

        List<CommonComparisonTotalDto> commonComparisonDtos = new ArrayList<>();

        SalesCategoryManager mapper = new SalesCategoryManager();

        try
        {
            commonComparisonDtos = jdbcTemplate.query(sqlQueries.getSalesCategoryDetails, mapper, startDate, endDate);

            //String lineItemDiscount = jdbcTemplate.queryForObject(sqlQueries.getDiscountFromLineItemwithDate, new Object[]{startDate,endDate}, String.class);

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return mapper.commonComparisonTotalDto;

    }

    public CommonComparisonTotalDto getSalesByVendor(String startDate, String endDate) {
        List<CommonComparisonTotalDto> commonComparisonDtos = new ArrayList<>();
        SalesCategoryManager mapper = new SalesCategoryManager();


        try
        {
            commonComparisonDtos = jdbcTemplate.query(sqlQueries.getSalesVendorDetails,mapper, startDate, endDate);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return mapper.commonComparisonTotalDto;
    }

    public CommonComparisonTotalDto getSalesByBrand(String startDate, String endDate) {
        List<CommonComparisonTotalDto> commonComparisonDtos = new ArrayList<>();
        SalesCategoryManager mapper = new SalesCategoryManager();

        try
        {
            commonComparisonDtos = jdbcTemplate.query(sqlQueries.getSalesBrandDetails, mapper, startDate, endDate);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return mapper.commonComparisonTotalDto;
    }

    public CommonComparisonTotalDto getSalesByProduct(String startDate, String endDate) {
        List<CommonComparisonTotalDto> commonComparisonDtos = new ArrayList<>();
        SalesCategoryManager mapper = new SalesCategoryManager();

        try
        {
            commonComparisonDtos = jdbcTemplate.query(sqlQueries.getSalesProductDetails, mapper, startDate, endDate);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return mapper.commonComparisonTotalDto;
    }

    public CommonComparisonTotalDto getSalesByUser(String startDate, String endDate) {
        List<CommonComparisonTotalDto> commonComparisonDtos = new ArrayList<>();
        SalesCategoryManager mapper = new SalesCategoryManager();
        try
        {
            commonComparisonDtos = jdbcTemplate.query(sqlQueries.getSalesByUser, mapper, startDate, endDate);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return mapper.commonComparisonTotalDto;
    }

    public CommonComparisonTotalDto getSalesByCustomer(String startDate, String endDate) {
        List<CommonComparisonTotalDto> commonComparisonDtos = new ArrayList<>();
        SalesCategoryManager mapper = new SalesCategoryManager();

        try
        {
            commonComparisonDtos = jdbcTemplate.query(sqlQueries.getSalesbyCustomer, mapper, startDate, endDate);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return mapper.commonComparisonTotalDto;

    }



    private final class SalesCategoryManager implements RowMapper<CommonComparisonTotalDto>
    {
        int totalQuantity;
        double totalDiscount;
        double totalSales;
        double totalTax;
        double totalProfit;
        double totalMarkUp;
        double totalPer;

        CommonComparisonTotalDto commonComparisonTotalDto = new CommonComparisonTotalDto();

        List<CommonComparisonDto> commonComparisonDtosList = new ArrayList<>();


        @Override
        public CommonComparisonTotalDto mapRow(ResultSet rs, int rowNum) throws SQLException {


            FinalTotalForCommonComparisonDto finalTotalForCommonComparisonDto = new FinalTotalForCommonComparisonDto();

            CommonComparisonDto commonComparisonDto = new CommonComparisonDto();

            List<FinalTotalForCommonComparisonDto> finalTotalForCommonComparisonDtosList = new ArrayList<>();


            commonComparisonDto.setCommanName(rs.getString("COMMON_NAME"));
            commonComparisonDto.setSalesTotal(rs.getDouble("SALESTOTAL"));
            commonComparisonDto.setTax(rs.getDouble("TAX"));
            commonComparisonDto.setQuantity(rs.getInt("QUANTITY"));
            commonComparisonDto.setProfitAmount(rs.getDouble("PROFIT"));
            commonComparisonDto.setDiscount(rs.getDouble("DISCOUNT"));

            commonComparisonDtosList.add(commonComparisonDto);


            commonComparisonTotalDto.setCommonComparisonDtos(commonComparisonDtosList);

            totalQuantity = totalQuantity + commonComparisonDto.getQuantity();
            totalDiscount = totalDiscount + commonComparisonDto.getDiscount();
            totalSales = totalSales + commonComparisonDto.getSalesTotal();
            totalTax = totalTax + commonComparisonDto.getTax();
            totalProfit = totalProfit + commonComparisonDto.getProfitAmount();


            finalTotalForCommonComparisonDto.setTotalQuantity(totalQuantity);
            finalTotalForCommonComparisonDto.setTotalDiscount(totalDiscount);
            finalTotalForCommonComparisonDto.setTotalSales(totalSales);
            finalTotalForCommonComparisonDto.setTotalTax(totalTax);
            finalTotalForCommonComparisonDto.setTotalProfit(totalProfit);


            finalTotalForCommonComparisonDtosList.add(finalTotalForCommonComparisonDto);

            commonComparisonTotalDto.setFinalTotalForCommonComparisonDtos(finalTotalForCommonComparisonDtosList);




            //commonComparisonDto.setCostPrice(rs.getDouble("COST"));
            //commonComparisonDto.setRetailPrice(rs.getDouble("RETAIL"));



            return commonComparisonTotalDto;
        }
    }

    public CommonInvetoryTotalDto getInventoryByCategory() {

        List<CommonInvetoryTotalDto> commonInvetoryTotalDtos = new ArrayList<>();

        InventoryMapper mapper = new InventoryMapper();

        try
        {
            commonInvetoryTotalDtos = jdbcTemplate.query(sqlQueries.getInventoryByCategory, mapper);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return mapper.commonInvetoryTotalDto;

    }

    private final class InventoryMapper implements RowMapper<CommonInvetoryTotalDto>
    {
        int totalQuantity;
        double totalCost;
        double totalRetail;
        double totalAvg;

        CommonInvetoryTotalDto commonInvetoryTotalDto = new CommonInvetoryTotalDto();

        List<CommonInventoryDto> commonComparisonDtosList = new ArrayList<>();

        @Override
        public CommonInvetoryTotalDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            FinalTotalForInventoryDto finalTotalForInventoryDto = new FinalTotalForInventoryDto();

            CommonInventoryDto inventoryDto = new CommonInventoryDto();

            List<FinalTotalForInventoryDto> finalTotalForInventoryDtoList = new ArrayList<>();


            inventoryDto.setCommonName(rs.getString("COMMON_NAME"));
            inventoryDto.setNoOfProducts(rs.getInt("NOOFPRODUCTS"));
            inventoryDto.setCost(rs.getDouble("COST"));
            inventoryDto.setRetail(rs.getDouble("RETAIL"));
            inventoryDto.setMargin(rs.getDouble("MARGIN"));


            commonComparisonDtosList.add(inventoryDto);

            commonInvetoryTotalDto.setCommonInventoryDtos(commonComparisonDtosList);


            totalQuantity = totalQuantity + inventoryDto.getNoOfProducts();
            totalCost = totalCost + inventoryDto.getCost();
            totalRetail = totalRetail + inventoryDto.getRetail();

            finalTotalForInventoryDto.setTotalQuantity(totalQuantity);
            finalTotalForInventoryDto.setTotalCost(totalCost);
            finalTotalForInventoryDto.setTotalRetail(totalRetail);

            //I NEED TO FIX THIS SOME HOW
            finalTotalForInventoryDto.setAvgMargin(0.0);

            finalTotalForInventoryDtoList.add(finalTotalForInventoryDto);

            commonInvetoryTotalDto.setFinalTotalForInventoryDtos(finalTotalForInventoryDtoList);

            return commonInvetoryTotalDto;
        }
    }

    public CommonInvetoryTotalDto getInventoryByVendor() {

        List<CommonInvetoryTotalDto> commonInvetoryTotalDtos = new ArrayList<>();

        InventoryMapper mapper = new InventoryMapper();

        try
        {
            commonInvetoryTotalDtos = jdbcTemplate.query(sqlQueries.getInventoryByVendor, mapper);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return mapper.commonInvetoryTotalDto;


    }

    public CommonInvetoryTotalDto getInventoryByBrand() {

        List<CommonInvetoryTotalDto> commonInvetoryTotalDtos = new ArrayList<>();

        InventoryMapper mapper = new InventoryMapper();

        try {
            commonInvetoryTotalDtos = jdbcTemplate.query(sqlQueries.getInventoryByBrand, mapper);
        } catch (Exception e) {
            System.out.println(e);
        }

        return mapper.commonInvetoryTotalDto;
    }

}
