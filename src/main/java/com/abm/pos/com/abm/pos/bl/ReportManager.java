package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.reports.*;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
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
 * Created by asp5045 on 7/4/16.
 */

@Component
public class ReportManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQueries;

    private BaseFont bfBold;
    private BaseFont bf;
    private int pageNumber = 0;
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

    public byte[] printSaleByCommonName(String startDate, String endDate, int noOfReportType) throws DocumentException {

        Document doc = new Document(PageSize.A4);
        initializeFontsForCommonReports();

        String reportName = null;


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        PdfWriter writer = PdfWriter.getInstance(doc, byteArrayOutputStream);


        CommonComparisonTotalDto commonComparisonDtos = new CommonComparisonTotalDto();

        //Checking which kind of report is this.
        if (noOfReportType == 1) {
            commonComparisonDtos = getSalesByCategory(startDate, endDate);
        } else if (noOfReportType == 2) {
            commonComparisonDtos = getSalesByVendor(startDate, endDate);
        } else if (noOfReportType == 3) {
            commonComparisonDtos = getSalesByBrand(startDate, endDate);
        } else if (noOfReportType == 4) {
            commonComparisonDtos = getSalesByProduct(startDate, endDate);
        } else if (noOfReportType == 5) {
            commonComparisonDtos = getSalesByUser(startDate, endDate);
        } else if (noOfReportType == 6) {
            commonComparisonDtos = getSalesByCustomer(startDate, endDate);
        } else if (noOfReportType == 7) {
            commonComparisonDtos = getTop50Items(startDate, endDate);
        }


        //USING noOfReportType Identifying which type of report ui is asking.

        //1 = byCategory
        //2 = byVendor
        //3 = byBrand


        doc.open();

        PdfContentByte cb = writer.getDirectContent();

        boolean beginPage = true;
        int y = 0;


        for (int i = 0; i < commonComparisonDtos.getCommonComparisonDtos().size(); i++) {
            if (beginPage) {
                beginPage = false;
                generateLayout(doc, cb, noOfReportType);
                generateHeader(doc, cb, startDate, endDate, noOfReportType);
                y = 570;
            }
            generateDetail(doc, cb, i, y, commonComparisonDtos);
            y = y - 40;
            if (y < 60) {
                printPageNumber(cb);
                doc.newPage();
                beginPage = true;
            }
        }
        generateTotalDetail(doc, cb, 0, y, commonComparisonDtos);
        printPageNumber(cb);

        doc.close();

        byte[] pdfDataBytes = byteArrayOutputStream.toByteArray();



        return pdfDataBytes;



    }

    private void generateLayout(Document doc, PdfContentByte cb, int noOfReportType) {

        try {

            cb.setLineWidth(1f);


            // Invoice Detail box layout
            cb.rectangle(20, 50, 550, 580);
            cb.moveTo(20, 590);
            cb.lineTo(570, 590);
//            cb.moveTo(50, 50);
//            cb.lineTo(50, 650);
//            cb.moveTo(150, 50);
//            cb.lineTo(150, 650);
//            cb.moveTo(430, 50);
//            cb.lineTo(430, 650);
//            cb.moveTo(500, 50);
//            cb.lineTo(500, 650);
            cb.stroke();

            // Invoice Detail box Text Headings

            //Checking which kind of report is this.
            if (noOfReportType == 1) {
                createHeadingsForCommonReports(cb, 23, 605, "Category Name");
                createHeadingsForCommonReports(cb, 240, 730, "Sales By Category Report");
            } else if (noOfReportType == 2) {
                createHeadingsForCommonReports(cb, 23, 605, "Vendor Name");
                createHeadingsForCommonReports(cb, 240, 730, "Sales By Vendor Report");
            } else if (noOfReportType == 3) {
                createHeadingsForCommonReports(cb, 23, 605, "Brand Name");
                createHeadingsForCommonReports(cb, 240, 730, "Sales By Brand Report");
            } else if (noOfReportType == 4) {
                createHeadingsForCommonReports(cb, 23, 605, "Product Name");
                createHeadingsForCommonReports(cb, 240, 730, "Sales By Product Report");
            } else if (noOfReportType == 5) {
                createHeadingsForCommonReports(cb, 23, 605, "Employee Name");
                createHeadingsForCommonReports(cb, 240, 730, "Sales By Employee Report");
            } else if (noOfReportType == 6) {
                createHeadingsForCommonReports(cb, 23, 605, "Customer Name");
                createHeadingsForCommonReports(cb, 240, 730, "Sales By Customer Report");
            } else if (noOfReportType == 7) {
                createHeadingsForCommonReports(cb, 23, 605, "Product Name");
                createHeadingsForCommonReports(cb, 240, 730, "Top 50 Products Sale Report");
            }

            createHeadingsForCommonReports(cb, 205, 605, "Quantity");
            createHeadingsForCommonReports(cb, 284, 605, "Discount");
            createHeadingsForCommonReports(cb, 369, 605, "Total Sales");
            createHeadingsForCommonReports(cb, 462, 605, "Tax");
            createHeadingsForCommonReports(cb, 519, 605, "Profit");


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    private void createHeadingsForCompanyName(PdfContentByte cb, float x, float y, String text){


        cb.beginText();
        cb.setFontAndSize(bfBold, 12);
        cb.setTextMatrix(x,y);
        cb.showText(text.trim());
        cb.endText();

    }

    private void generateHeader(Document doc, PdfContentByte cb, String startDate, String endDate, int noOfReportType) {

        try {

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

            String a = date.format(sd);
            String b = date.format(ed);

            if (a.equals(b)) {
                createHeadingsForCompanyName(cb, 20, 660, "Date:" + date.format(sd));
            } else {
                createHeadingsForCompanyName(cb, 20, 660, "Date:" + a + " " + "To" + " " + b);
            }


            createHeadingsForCompanyName(cb, 265, 770, "Excell Wireless");


            //createHeadings(cb, 240, 730, "Sales By Category Report");


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void generateDetail(Document doc, PdfContentByte cb, int index, int y, CommonComparisonTotalDto commonComparisonTotalDto) {
        DecimalFormat df = new DecimalFormat("0.00");

        try {

            if (null != commonComparisonTotalDto && commonComparisonTotalDto.getCommonComparisonDtos().size() >= 1) {

                createForCommonReportsContent(cb, 23, y, commonComparisonTotalDto.getCommonComparisonDtos().get(index).getCommanName(), 0);
                createForCommonReportsContent(cb, 205, y, "$" + df.format(commonComparisonTotalDto.getCommonComparisonDtos().get(index).getQuantity()), 0);
                createForCommonReportsContent(cb, 284, y, "$" + df.format(commonComparisonTotalDto.getCommonComparisonDtos().get(index).getDiscount()), 0);

                createForCommonReportsContent(cb, 369, y, "$" + df.format(commonComparisonTotalDto.getCommonComparisonDtos().get(index).getSalesTotal()), 0);
                createForCommonReportsContent(cb, 462, y, "$" + df.format(commonComparisonTotalDto.getCommonComparisonDtos().get(index).getTax()), 0);
                createForCommonReportsContent(cb, 519, y, "$" + df.format(commonComparisonTotalDto.getCommonComparisonDtos().get(index).getProfitAmount()), 0);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    //Here I am printing Total values
    private void generateTotalDetail(Document doc, PdfContentByte cb, int index, int y, CommonComparisonTotalDto commonComparisonTotalDto) {
        DecimalFormat df = new DecimalFormat("0.00");

        try {

            if (null != commonComparisonTotalDto && commonComparisonTotalDto.getFinalTotalForCommonComparisonDtos().size() == 1) {
                createContentForTotal(cb, 23, y, "TOTAL", 0);
                createContentForTotal(cb, 205, y, "$" + df.format(commonComparisonTotalDto.getFinalTotalForCommonComparisonDtos().get(0).getTotalQuantity()), 0);
                createContentForTotal(cb, 284, y, "$" + df.format(commonComparisonTotalDto.getFinalTotalForCommonComparisonDtos().get(0).getTotalDiscount()), 0);
                createContentForTotal(cb, 369, y, "$" + df.format(commonComparisonTotalDto.getFinalTotalForCommonComparisonDtos().get(0).getTotalSales()), 0);
                createContentForTotal(cb, 462, y, "$" + df.format(commonComparisonTotalDto.getFinalTotalForCommonComparisonDtos().get(0).getTotalTax()), 0);
                createContentForTotal(cb, 519, y, "$" + df.format(commonComparisonTotalDto.getFinalTotalForCommonComparisonDtos().get(0).getTotalProfit()), 0);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void createHeadingsForCommonReports(PdfContentByte cb, float x, float y, String text) {


        cb.beginText();
        cb.setFontAndSize(bfBold, 12);
        cb.setTextMatrix(x, y);
        cb.showText(text.trim());
        cb.endText();

    }

    private void printPageNumber(PdfContentByte cb) {


        cb.beginText();
        cb.setFontAndSize(bfBold, 8);
        cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "Page No. " + (pageNumber + 1), 570, 25, 0);
        cb.endText();

        pageNumber++;

    }

    private void createForCommonReportsContent(PdfContentByte cb, float x, float y, String text, int align) {


        cb.beginText();
        cb.setFontAndSize(bf, 12);
        cb.showTextAligned(align, text.trim(), x, y, 0);
        cb.endText();

    }

    private void createContentForTotal(PdfContentByte cb, float x, float y, String text, int align) {


        cb.beginText();
        cb.setFontAndSize(bfBold, 12);
        cb.showTextAligned(align, text.trim(), x, y, 0);
        cb.endText();

    }

    private void initializeFontsForCommonReports() {


        try {
            bfBold = BaseFont.createFont(BaseFont.TIMES_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
