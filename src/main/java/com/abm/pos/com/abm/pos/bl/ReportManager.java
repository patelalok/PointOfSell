package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.MonthlyListDto;
import com.abm.pos.com.abm.pos.dto.ReceiptDto;
import com.abm.pos.com.abm.pos.dto.WeekDto;
import com.abm.pos.com.abm.pos.dto.reports.*;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
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
import java.util.*;

/**
 * Created by asp5045 on 7/4/16.
 */

@Component
public class ReportManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQueries;

    @Autowired
    ClosingDetailsManager closingDetailsManager;

    @Autowired
    SalesManager salesManager;

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
                createHeadingsForCommonReportsName(cb, 200, 730, "Sales By Category Report");
            } else if (noOfReportType == 2) {
                createHeadingsForCommonReports(cb, 23, 605, "Vendor Name");
                createHeadingsForCommonReportsName(cb, 200, 730, "Sales By Vendor Report");
            } else if (noOfReportType == 3) {
                createHeadingsForCommonReports(cb, 23, 605, "Brand Name");
                createHeadingsForCommonReportsName(cb, 200, 730, "Sales By Brand Report");
            } else if (noOfReportType == 4) {
                createHeadingsForCommonReports(cb, 23, 605, "Product Name");
                createHeadingsForCommonReportsName(cb, 200, 730, "Sales By Product Report");
            } else if (noOfReportType == 5) {
                createHeadingsForCommonReports(cb, 23, 605, "Employee Name");
                createHeadingsForCommonReportsName(cb, 200, 730, "Sales By Employee Report");
            } else if (noOfReportType == 6) {
                createHeadingsForCommonReports(cb, 23, 605, "Customer Name");
                createHeadingsForCommonReportsName(cb, 200, 730, "Sales By Customer Report");
            } else if (noOfReportType == 7) {
                createHeadingsForCommonReports(cb, 23, 605, "Product Name");
                createHeadingsForCommonReportsName(cb, 180, 730, "Top 50 Products Sale Report");
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


            Image companyLogo = Image.getInstance("logo.png");
            companyLogo.setAbsolutePosition(235,760);
            companyLogo.scalePercent(15);
            doc.add(companyLogo);
           // createHeadingsForCompanyName(cb, 265, 770, "Excell Wireless");


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
                createForCommonReportsContent(cb, 205, y,       df.format(commonComparisonTotalDto.getCommonComparisonDtos().get(index).getQuantity()), 0);
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
                createContentForTotal(cb, 205, y,       df.format(commonComparisonTotalDto.getFinalTotalForCommonComparisonDtos().get(0).getTotalQuantity()), 0);
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
    private void createHeadingsForCommonReportsName(PdfContentByte cb, float x, float y, String text) {


        cb.beginText();
        cb.setFontAndSize(bfBold, 20);
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

    public byte[] printInventorySummaryByCommonNames(int reportNo) throws DocumentException {

        Document doc = new Document(PageSize.A4);
        initializeFontsForCommonReports();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        PdfWriter writer = PdfWriter.getInstance(doc, byteArrayOutputStream);

        CommonInvetoryTotalDto commonComparisonDtos = new CommonInvetoryTotalDto();

        //Checking which kind of report is this.

        //USING noOfReportType Identifying which type of report ui is asking.

        //1 = byCategory
        //2 = byVendor
        //3 = byBrand
        if (reportNo == 1) {
            commonComparisonDtos = getInventoryByCategory();
        } else if (reportNo == 2) {
            commonComparisonDtos = getInventoryByVendor();
        } else if (reportNo == 3) {
            commonComparisonDtos = getInventoryByBrand();
        }

        doc.open();

        PdfContentByte cb = writer.getDirectContent();

        boolean beginPage = true;
        int y = 0;

        for (int i = 0; i < commonComparisonDtos.getCommonInventoryDtos().size(); i++) {
            if (beginPage) {
                beginPage = false;
                generateLayoutForInventory(doc, cb, reportNo);
                generateHeaderForInventory(doc, cb,reportNo);
                y = 570;
            }
            generateDetailForInventory(doc, cb, i, y, commonComparisonDtos);
            y = y - 40;
            if (y < 60) {
                printPageNumber(cb);
                doc.newPage();
                beginPage = true;
            }
        }
        generateTotalDetailForInventorty(doc, cb, 0, y, commonComparisonDtos);
        printPageNumber(cb);

        doc.close();

        byte[] pdfDataBytes = byteArrayOutputStream.toByteArray();



        return pdfDataBytes;

    }

    private void generateLayoutForInventory(Document doc, PdfContentByte cb, int reportNo) {

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
            if (reportNo == 1) {
                createHeadingsForCommonReports(cb, 23, 605, "Category Name");
                createHeadingsForCommonReportsName(cb, 168, 730, "Inventory By Category Report");
            } else if (reportNo == 2) {
                createHeadingsForCommonReports(cb, 23, 605, "Vendor Name");
                createHeadingsForCommonReportsName(cb, 175, 730, "Inventory By Vendor Report");
            } else if (reportNo == 3) {
                createHeadingsForCommonReports(cb, 23, 605, "Brand Name");
                createHeadingsForCommonReportsName(cb, 175, 730, "Inventory By Brand Report");
            }


            createHeadingsForCommonReports(cb, 220, 605, "Quantity");
            createHeadingsForCommonReports(cb, 330, 605, "Cost");
            createHeadingsForCommonReports(cb, 420, 605, "Retail");
            createHeadingsForCommonReports(cb, 510, 605, "Margin");


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void generateTotalDetailForInventorty(Document doc, PdfContentByte cb, int index, int y, CommonInvetoryTotalDto commonComparisonDtos) {

        DecimalFormat df = new DecimalFormat("0.00");

        try {

            if (null != commonComparisonDtos && commonComparisonDtos.getFinalTotalForInventoryDtos().size() == 1) {
                createContentForTotalInventory(cb, 23, y, "TOTAL", 0);
                createContentForTotalInventory(cb, 220, y,       df.format(commonComparisonDtos.getFinalTotalForInventoryDtos().get(0).getTotalQuantity()), 0);
                createContentForTotalInventory(cb, 330, y, "$" + df.format(commonComparisonDtos.getFinalTotalForInventoryDtos().get(0).getTotalCost()), 0);
                createContentForTotalInventory(cb, 420, y, "$" + df.format(commonComparisonDtos.getFinalTotalForInventoryDtos().get(0).getTotalRetail()), 0);
                createContentForTotalInventory(cb, 605, y, "$" + df.format(commonComparisonDtos.getFinalTotalForInventoryDtos().get(0).getAvgMargin()), 0);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void generateDetailForInventory(Document doc, PdfContentByte cb, int index, int y, CommonInvetoryTotalDto commonComparisonDtos) {

        DecimalFormat df = new DecimalFormat("0.00");

        try {

            if (null != commonComparisonDtos && commonComparisonDtos.getCommonInventoryDtos().size() >= 1) {

                createForCommonReportsContentForInventory(cb, 23, y, commonComparisonDtos.getCommonInventoryDtos().get(index).getCommonName(), 0);
                createForCommonReportsContentForInventory(cb, 220, y,       df.format(commonComparisonDtos.getCommonInventoryDtos().get(index).getNoOfProducts()), 0);
                createForCommonReportsContentForInventory(cb, 330, y, "$" + df.format(commonComparisonDtos.getCommonInventoryDtos().get(index).getCost()), 0);
                createForCommonReportsContentForInventory(cb, 420, y, "$" + df.format(commonComparisonDtos.getCommonInventoryDtos().get(index).getRetail()), 0);
                createForCommonReportsContentForInventory(cb, 510, y,       df.format(commonComparisonDtos.getCommonInventoryDtos().get(index).getMargin()) + "%", 0);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void generateHeaderForInventory(Document doc, PdfContentByte cb, int reportNo) {

        try {

            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            Date dateobj = new Date();

            createHeadingsForCompanyName(cb, 20, 660, "Date:" + df.format(dateobj));

            Image companyLogo = Image.getInstance("/assets/images/final-logo.png");
            companyLogo.setAbsolutePosition(235,760);
            companyLogo.scalePercent(15);
            doc.add(companyLogo);
            // createHeadingsForCompanyName(cb, 265, 770, "Excell Wireless");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }

    private void createForCommonReportsContentForInventory(PdfContentByte cb, float x, float y, String text, int align) {


        cb.beginText();
        cb.setFontAndSize(bf, 12);
        cb.showTextAligned(align, text.trim(), x, y, 0);
        cb.endText();

    }

    private void createContentForTotalInventory(PdfContentByte cb, float x, float y, String text, int align) {


        cb.beginText();
        cb.setFontAndSize(bfBold, 12);
        cb.showTextAligned(align, text.trim(), x, y, 0);
        cb.endText();

    }

    public byte[] printYearlySalesReport(String startDate, String endDate, int reportNo) throws DocumentException {

        Document doc = new Document(PageSize.A4);
        initializeFontsForCommonReports();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        PdfWriter writer = PdfWriter.getInstance(doc, byteArrayOutputStream);

        YearlyListDto yearlyListDto = new YearlyListDto();

        MonthlyListDto monthlyListDto = new MonthlyListDto();



        HourlyListDto hourlyListDto = new HourlyListDto();




        //yearlyListDto = closingDetailsManager.getYearlyTransactionDetails(startDate,endDate);


        doc.open();

        PdfContentByte cb = writer.getDirectContent();

        boolean beginPage = true;
        int y = 0;

        if(reportNo==1)
        {
            yearlyListDto = closingDetailsManager.getYearlyTransactionDetails(startDate,endDate);
            for (int i = 0; i < yearlyListDto.getYearlyListDtos().size(); i++) {
                if (beginPage) {
                beginPage = false;
                generateLayoutForYearlySales(doc, cb,reportNo);
                generateHeaderForYearlySales(doc, cb);
                y = 570;
            }
            generateDetailForYearlySales(doc, cb, i, y, yearlyListDto);

            y = y - 40;
            if (y < 60) {
                printPageNumber(cb);
                doc.newPage();
                beginPage = true;
            }
        }


        }
        else if(reportNo ==2)
        {
            monthlyListDto = closingDetailsManager.getMonthlyTransactionDetails(startDate,endDate);
            for (int i = 0; i < monthlyListDto.getMonthDtos().size(); i++) {
                if (beginPage) {
                    beginPage = false;
                    generateLayoutForYearlySales(doc, cb,reportNo);
                    generateHeaderForYearlySales(doc, cb);
                    y = 570;
                }
                generateDetailForMonthlySales(doc, cb, i, y, monthlyListDto);
                y = y - 40;
                if (y < 60) {
                    printPageNumber(cb);
                    doc.newPage();
                    beginPage = true;
                }
            }

        }

        else if(reportNo ==3)
        {
            //This is left for week
        }


        else if(reportNo ==4)
        {
            hourlyListDto = closingDetailsManager.getHourlyTransactionDetails(startDate,endDate);

            for (int i = 0; i < hourlyListDto.getHourlyDtoList().size(); i++) {
                if (beginPage) {
                    beginPage = false;
                    generateLayoutForYearlySales(doc, cb, reportNo);
                    generateHeaderForYearlySales(doc, cb);
                    y = 570;
                }

                generateDetailForHourlySales(doc, cb, i, y, hourlyListDto);
                y = y - 40;
                if (y < 60) {
                    printPageNumber(cb);
                    doc.newPage();
                    beginPage = true;
                }
            }



        }

        if(reportNo==1) {
            generateTotalDetailForYearlySales(doc, cb, 0, y, yearlyListDto);
        }
        else if(reportNo ==2)
        {
            generateTotalDetailForMonthlySales(doc, cb, 0, y, monthlyListDto);
        }
        else if(reportNo ==3)
        {
            //This is left for week
        }
        else if(reportNo ==4)
        {
            generateTotalDetailForHourlySales(doc, cb, 0, y, hourlyListDto);
        }

        printPageNumber(cb);

        doc.close();

        byte[] pdfDataBytes = byteArrayOutputStream.toByteArray();



        return pdfDataBytes;
    }

    private void generateLayoutForYearlySales(Document doc, PdfContentByte cb, int reportNo) {




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

            if(reportNo==1) {
                createHeadingsForCommonReports(cb, 23, 605, "Month");
                createHeadingsForCommonReportsName(cb, 200, 730, "Yearly Sales Report");
            }
            else if(reportNo ==2)
            {
                createHeadingsForCommonReports(cb, 23, 605, "Date");
                createHeadingsForCommonReportsName(cb, 200, 730, "Monthly Sales Report");
            }
            else if(reportNo ==3)
            {
                createHeadingsForCommonReports(cb, 23, 605, "Week");
                createHeadingsForCommonReportsName(cb, 200, 730, "Weekly Sales Report");
            }
            else if(reportNo ==4)
            {
                createHeadingsForCommonReports(cb, 23, 605, "Hour");
                createHeadingsForCommonReportsName(cb, 200, 730, "Hourly Sales Report");
            }





            createHeadingsForCommonReports(cb, 110, 605, "Debit");
            createHeadingsForCommonReports(cb, 165, 605, "Credit");
            createHeadingsForCommonReports(cb, 230, 605, "Cash");
            createHeadingsForCommonReports(cb, 285, 605, "Check");

            createHeadingsForCommonReports(cb, 340, 605, "Tax");
            createHeadingsForCommonReports(cb, 380, 605, "Discount");
            createHeadingsForCommonReports(cb, 450, 605, "Profit");
            createHeadingsForCommonReports(cb, 510, 605, "Total");


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

        private void generateHeaderForYearlySales(Document doc, PdfContentByte cb) {

            try {

                DateFormat df = new SimpleDateFormat("dd/MM/yy");
                Date dateobj = new Date();

                createHeadingsForCompanyName(cb, 20, 660, "Date:" + df.format(dateobj));

                Image companyLogo = Image.getInstance("logo.png");
                companyLogo.setAbsolutePosition(225,760);
                companyLogo.scalePercent(15);
                doc.add(companyLogo);
                // createHeadingsForCompanyName(cb, 265, 770, "Excell Wireless");

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    private void generateDetailForYearlySales(Document doc, PdfContentByte cb, int index, int y, YearlyListDto yearlyListDto) {

        DecimalFormat df = new DecimalFormat("0.00");

        try {

            if (null != yearlyListDto && yearlyListDto.getYearlyListDtos().size() >= 1) {

                createForCommonReportsContentForInventory(cb, 23, y, yearlyListDto.getYearlyListDtos().get(index).getMonthName(), 0);
                createForCommonReportsContentForInventory(cb, 110, y, df.format(yearlyListDto.getYearlyListDtos().get(index).getDebit()), 0);
                createForCommonReportsContentForInventory(cb, 165, y, df.format(yearlyListDto.getYearlyListDtos().get(index).getCredit()), 0);
                createForCommonReportsContentForInventory(cb, 230, y,df.format(yearlyListDto.getYearlyListDtos().get(index).getCash()), 0);
                createForCommonReportsContentForInventory(cb, 285, y, df.format(yearlyListDto.getYearlyListDtos().get(index).getCheck()), 0);
                createForCommonReportsContentForInventory(cb, 340, y, df.format(yearlyListDto.getYearlyListDtos().get(index).getTax()), 0);
                createForCommonReportsContentForInventory(cb, 380, y, df.format(yearlyListDto.getYearlyListDtos().get(index).getDiscount()), 0);
                createForCommonReportsContentForInventory(cb, 450, y,df.format(yearlyListDto.getYearlyListDtos().get(index).getProfit()), 0);
                createForCommonReportsContentForInventory(cb, 510, y, df.format(yearlyListDto.getYearlyListDtos().get(index).getTotal()), 0);


            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void generateDetailForMonthlySales(Document doc, PdfContentByte cb, int index, int y, MonthlyListDto monthlyListDto) {

        DecimalFormat df = new DecimalFormat("0.00");

        try {

            if (null != monthlyListDto && monthlyListDto.getMonthDtos().size() >= 1) {

                createForCommonReportsContentForInventory(cb, 23, y, monthlyListDto.getMonthDtos().get(index).getDate(), 0);
                createForCommonReportsContentForInventory(cb, 110, y, df.format(monthlyListDto.getMonthDtos().get(index).getDebit()), 0);
                createForCommonReportsContentForInventory(cb, 165, y, df.format(monthlyListDto.getMonthDtos().get(index).getCredit()), 0);
                createForCommonReportsContentForInventory(cb, 230, y,df.format(monthlyListDto.getMonthDtos().get(index).getCash()), 0);
                createForCommonReportsContentForInventory(cb, 285, y, df.format(monthlyListDto.getMonthDtos().get(index).getCheck()), 0);
                createForCommonReportsContentForInventory(cb, 340, y, df.format(monthlyListDto.getMonthDtos().get(index).getTax()), 0);
                createForCommonReportsContentForInventory(cb, 380, y, df.format(monthlyListDto.getMonthDtos().get(index).getDiscount()), 0);
                createForCommonReportsContentForInventory(cb, 450, y,df.format(monthlyListDto.getMonthDtos().get(index).getProfit()), 0);
                createForCommonReportsContentForInventory(cb, 510, y, df.format(monthlyListDto.getMonthDtos().get(index).getTotal()), 0);


            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void generateDetailForHourlySales(Document doc, PdfContentByte cb, int index, int y, HourlyListDto hourlyListDto) {

        DecimalFormat df = new DecimalFormat("0.00");

        try {

            if (null != hourlyListDto && hourlyListDto.getHourlyDtoList().size() >= 1) {

                createForCommonReportsContentForInventory(cb, 23, y, String.valueOf(hourlyListDto.getHourlyDtoList().get(index).getHour()), 0);
                createForCommonReportsContentForInventory(cb, 110, y, df.format(hourlyListDto.getHourlyDtoList().get(index).getDebit()), 0);
                createForCommonReportsContentForInventory(cb, 165, y, df.format(hourlyListDto.getHourlyDtoList().get(index).getCredit()), 0);
                createForCommonReportsContentForInventory(cb, 230, y,df.format(hourlyListDto.getHourlyDtoList().get(index).getCash()), 0);
                createForCommonReportsContentForInventory(cb, 285, y, df.format(hourlyListDto.getHourlyDtoList().get(index).getCheck()), 0);
                createForCommonReportsContentForInventory(cb, 340, y, df.format(hourlyListDto.getHourlyDtoList().get(index).getTax()), 0);
                createForCommonReportsContentForInventory(cb, 380, y, df.format(hourlyListDto.getHourlyDtoList().get(index).getDiscount()), 0);
                createForCommonReportsContentForInventory(cb, 450, y,df.format(hourlyListDto.getHourlyDtoList().get(index).getProfit()), 0);
                createForCommonReportsContentForInventory(cb, 510, y, df.format(hourlyListDto.getHourlyDtoList().get(index).getTotal()), 0);


            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void generateTotalDetailForYearlySales(Document doc, PdfContentByte cb, int index, int y, YearlyListDto yearlyListDto) {

        DecimalFormat df = new DecimalFormat("0.00");

        try {

            if (null != yearlyListDto && yearlyListDto.getFinalTotalForReportsDtos().size() == 1) {
                createContentForTotalInventory(cb, 23, y, "TOTAL", 0);
                createContentForTotalInventory(cb, 110, y,       df.format(yearlyListDto.getFinalTotalForReportsDtos().get(0).getTotalDebit()), 0);
                createContentForTotalInventory(cb, 165, y,       df.format(yearlyListDto.getFinalTotalForReportsDtos().get(0).getTotalCredit()), 0);
                createContentForTotalInventory(cb, 230, y,       df.format(yearlyListDto.getFinalTotalForReportsDtos().get(0).getTotalCash()), 0);
                createContentForTotalInventory(cb, 285, y,       df.format(yearlyListDto.getFinalTotalForReportsDtos().get(0).getTotalCheck()), 0);
                createContentForTotalInventory(cb, 340, y,       df.format(yearlyListDto.getFinalTotalForReportsDtos().get(0).getTotalTax()), 0);
                createContentForTotalInventory(cb, 380, y,       df.format(yearlyListDto.getFinalTotalForReportsDtos().get(0).getTotalDiscount()), 0);
                createContentForTotalInventory(cb, 450, y,       df.format(yearlyListDto.getFinalTotalForReportsDtos().get(0).getTotalProfit()), 0);
                createContentForTotalInventory(cb, 510, y,       df.format(yearlyListDto.getFinalTotalForReportsDtos().get(0).getGrandTotal()), 0);


            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void generateTotalDetailForMonthlySales(Document doc, PdfContentByte cb, int index, int y, MonthlyListDto monthlyListDto) {

        DecimalFormat df = new DecimalFormat("0.00");

        try {

            if (null != monthlyListDto && monthlyListDto.getFinalTotalForReportsDtos().size() == 1) {
                createContentForTotalInventory(cb, 23, y, "TOTAL", 0);
                createContentForTotalInventory(cb, 110, y,       df.format(monthlyListDto.getFinalTotalForReportsDtos().get(0).getTotalDebit()), 0);
                createContentForTotalInventory(cb, 165, y,       df.format(monthlyListDto.getFinalTotalForReportsDtos().get(0).getTotalCredit()), 0);
                createContentForTotalInventory(cb, 230, y,       df.format(monthlyListDto.getFinalTotalForReportsDtos().get(0).getTotalCash()), 0);
                createContentForTotalInventory(cb, 285, y,       df.format(monthlyListDto.getFinalTotalForReportsDtos().get(0).getTotalCheck()), 0);
                createContentForTotalInventory(cb, 340, y,       df.format(monthlyListDto.getFinalTotalForReportsDtos().get(0).getTotalTax()), 0);
                createContentForTotalInventory(cb, 380, y,       df.format(monthlyListDto.getFinalTotalForReportsDtos().get(0).getTotalDiscount()), 0);
                createContentForTotalInventory(cb, 450, y,       df.format(monthlyListDto.getFinalTotalForReportsDtos().get(0).getTotalProfit()), 0);
                createContentForTotalInventory(cb, 510, y,       df.format(monthlyListDto.getFinalTotalForReportsDtos().get(0).getGrandTotal()), 0);


            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void generateTotalDetailForHourlySales(Document doc, PdfContentByte cb, int index, int y, HourlyListDto hourlyListDto) {

        DecimalFormat df = new DecimalFormat("0.00");

        try {

            if (null != hourlyListDto && hourlyListDto.getFinalTotalForReportsDtoList().size() == 1) {
                createContentForTotalInventory(cb, 23, y, "TOTAL", 0);
                createContentForTotalInventory(cb, 110, y,       df.format(hourlyListDto.getFinalTotalForReportsDtoList().get(0).getTotalDebit()), 0);
                createContentForTotalInventory(cb, 165, y,       df.format(hourlyListDto.getFinalTotalForReportsDtoList().get(0).getTotalCredit()), 0);
                createContentForTotalInventory(cb, 230, y,       df.format(hourlyListDto.getFinalTotalForReportsDtoList().get(0).getTotalCash()), 0);
                createContentForTotalInventory(cb, 285, y,       df.format(hourlyListDto.getFinalTotalForReportsDtoList().get(0).getTotalCheck()), 0);
                createContentForTotalInventory(cb, 340, y,       df.format(hourlyListDto.getFinalTotalForReportsDtoList().get(0).getTotalTax()), 0);
                createContentForTotalInventory(cb, 380, y,       df.format(hourlyListDto.getFinalTotalForReportsDtoList().get(0).getTotalDiscount()), 0);
                createContentForTotalInventory(cb, 450, y,       df.format(hourlyListDto.getFinalTotalForReportsDtoList().get(0).getTotalProfit()), 0);
                createContentForTotalInventory(cb, 510, y,       df.format(hourlyListDto.getFinalTotalForReportsDtoList().get(0).getGrandTotal()), 0);


            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    public byte[] printDetailedHistorySales(String startDate, String endDate) throws DocumentException {

        Document doc = new Document(PageSize.A4);
        initializeFontsForCommonReports();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        PdfWriter writer = PdfWriter.getInstance(doc, byteArrayOutputStream);



        doc.open();

        PdfContentByte cb = writer.getDirectContent();

       List<Integer> transactionIds =  getTransactionId(startDate,endDate);

        List<ReceiptDto> receiptDtos = new ArrayList<>();
        int i = 0;
        if(null != transactionIds)
        {
            boolean beginPage = true;

            int y = 0;
            int x = 0;
            int z = 0;

            for(int c = 0; c <= 0; c++)
            {
                if (beginPage) {
                    beginPage = false;

                     y = 605;
                     x = 565;
                     z = 525;

                    generateHeaderForYearlySales(doc, cb);
                }

                generateLayoutForDetailedSales(doc, cb,y);

                receiptDtos =  salesManager.getReceiptDetails(14);
                generateDetailForDetailedSales(doc, cb, i, y-40, receiptDtos);

                generateLineItemLayoutForDetailedSales(doc, cb,y-70);

                for(int m = 0; m < receiptDtos.get(0).getTransactionLineItemDtoList().size(); m++ )
                {
                    generateLineItemValuesForDetailedSales(doc, cb,y-100, receiptDtos,m);
                    y = y-25;
                }
                generatePaymentLayoutForDetailedSales(doc, cb,y-100);

//                y = y - 120;
//
//
//
//
//
//
//
//
//                x = x -80;
//
//                z = z- 120;

                if (y < 60) {
                    printPageNumber(cb);
                    doc.newPage();
                    beginPage = true;
                }
                i++;

                System.out.println(c);
            }
        }

        printPageNumber(cb);

        doc.close();

        byte[] pdfDataBytes = byteArrayOutputStream.toByteArray();



        return pdfDataBytes;


    }

    private void generateLineItemValuesForDetailedSales(Document doc, PdfContentByte cb, int y, List<ReceiptDto> receiptDtosList, int m) {

        DecimalFormat df = new DecimalFormat("0.00");

        if(null != receiptDtosList) {

            createForCommonReportsContentForInventory(cb, 5, y, receiptDtosList.get(0).getTransactionLineItemDtoList().get(m).getProductDescription(), 0);
            createForCommonReportsContentForInventory(cb, 350, y, df.format(receiptDtosList.get(0).getTransactionLineItemDtoList().get(m).getRetail()), 0);
            createForCommonReportsContentForInventory(cb, 420, y, df.format(receiptDtosList.get(0).getTransactionLineItemDtoList().get(m).getDiscount()), 0);
            createForCommonReportsContentForInventory(cb, 480, y, df.format(receiptDtosList.get(0).getTransactionLineItemDtoList().get(m).getQuantity()), 0);
            createForCommonReportsContentForInventory(cb, 550, y, df.format(receiptDtosList.get(0).getTransactionLineItemDtoList().get(m).getTotalProductPrice()), 0);



        }


    }

    private void generatePaymentLayoutForDetailedSales(Document doc, PdfContentByte cb, int y) {

        createHeadingsForCommonReports(cb, 5, y, "Cash");
        createHeadingsForCommonReports(cb, 172, y, "Credit");
        createHeadingsForCommonReports(cb, 309, y, "Debit");
        createHeadingsForCommonReports(cb, 446, y, "Check");


    }

    private void generateLineItemLayoutForDetailedSales(Document doc, PdfContentByte cb, int y) {


        createHeadingsForCommonReports(cb, 5, y, "Description");
        createHeadingsForCommonReports(cb, 350, y, "Retail");
        createHeadingsForCommonReports(cb, 420, y, "Discount");
        createHeadingsForCommonReports(cb, 480, y, "Quantity");
        createHeadingsForCommonReports(cb, 550, y, "Total");

    }


    private void generateDetailForDetailedSales(Document doc, PdfContentByte cb, int i, int y, List<ReceiptDto> receiptDto) {

        DecimalFormat df = new DecimalFormat("0.00");

        try {

            if (null != receiptDto) {

                createForCommonReportsContentForInventory(cb, 5, y, df.format(receiptDto.get(0).getTransactionDtoList().get(0).getTransactionCompId()), 0);
                createForCommonReportsContentForInventory(cb, 73, y, (receiptDto.get(0).getTransactionDtoList().get(0).getTransactionDate()), 0);
                createForCommonReportsContentForInventory(cb, 183, y, (receiptDto.get(0).getTransactionDtoList().get(0).getUsername()), 0);
                createForCommonReportsContentForInventory(cb, 245, y, (receiptDto.get(0).getTransactionDtoList().get(0).getCustomerName()), 0);
                createForCommonReportsContentForInventory(cb, 310, y, df.format(receiptDto.get(0).getTransactionDtoList().get(0).getTax()), 0);
                createForCommonReportsContentForInventory(cb, 350, y, df.format(receiptDto.get(0).getTransactionDtoList().get(0).getDiscount()), 0);
                createForCommonReportsContentForInventory(cb, 420, y, df.format(receiptDto.get(0).getTransactionDtoList().get(0).getTotalQuantity()), 0);
                createForCommonReportsContentForInventory(cb, 480, y, (receiptDto.get(0).getTransactionDtoList().get(0).getStatus()), 0);
                createForCommonReportsContentForInventory(cb, 550, y, df.format(receiptDto.get(0).getTransactionDtoList().get(0).getTotalAmount()), 0);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    private void generateLayoutForDetailedSales(Document doc, PdfContentByte cb, int y) {

        try {

            cb.setLineWidth(1f);

            // Invoice Detail box layout
            cb.rectangle(2, 50, 590, 580);
            cb.moveTo(2, 590);
            cb.lineTo(570, 590);

            cb.stroke();


            createHeadingsForCommonReportsName(cb, 200, 730, "Detailed Sales History");

            createHeadingsForCommonReports(cb, 5, y, "Sales Id");
            createHeadingsForCommonReports(cb, 73, y, "Date/Time");
            createHeadingsForCommonReports(cb, 183, y, "Csr");
            createHeadingsForCommonReports(cb, 245, y, "Name");
            createHeadingsForCommonReports(cb, 310, y, "Tax");
            createHeadingsForCommonReports(cb, 350, y, "Discount");
            createHeadingsForCommonReports(cb, 420, y, "Quantity");
            createHeadingsForCommonReports(cb, 480, y, "Status");
            createHeadingsForCommonReports(cb, 550, y, "Total");






        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Integer> getTransactionId(String startDate, String endDate)
    {

           List<Integer> transactionIdLists = new ArrayList<>();

        try
        {
             transactionIdLists = jdbcTemplate.queryForList(sqlQueries.getTransactionIds, new Object[] {startDate, endDate}, Integer.class);



        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return transactionIdLists;

    }

}

