package com.abm.pos;

import com.abm.pos.com.abm.pos.bl.ReportManager;
import com.abm.pos.com.abm.pos.dto.reports.CommonComparisonTotalDto;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

@SpringBootApplication
@EnableAutoConfiguration
public class PointOfSaleApplication {

    private BaseFont bfBold;
    private BaseFont bf;
    private int pageNumber = 0;


    public static void main(String[] args) throws IOException, DocumentException {

        SpringApplication.run(PointOfSaleApplication.class, args);

        PointOfSaleApplication generateInvoice = new PointOfSaleApplication();

        //generateInvoice.createPDF();

    }


}

   /* private void createPDF() {

        Document doc = new Document();
        PdfWriter docWriter = null;
        initializeFonts();

        try {

            docWriter = PdfWriter.getInstance(doc, new FileOutputStream("/Users/asp5045/Documents/PointOfSell/src/main/resources/AddImageExample.pdf"));
            doc.addAuthor("betterThanZero");
            doc.addCreationDate();
            doc.addProducer();
            doc.addCreator("MySampleCode.com");
            doc.addTitle("Invoice");
            doc.setPageSize(PageSize.LETTER);

            doc.open();
            PdfContentByte cb = docWriter.getDirectContent();

            boolean beginPage = true;
            int y = 0;


            CommonComparisonTotalDto commonComparisonTotalDto = new CommonComparisonTotalDto();

            ReportManager r = new ReportManager();
            commonComparisonTotalDto =  r.getSalesByCategory("2016-01-31 00:00:00","2016-12-31 23:59:59");

            for (int i = 0; i <2; i++) {
                if (beginPage) {
                    beginPage = false;
                    generateLayout(doc, cb);
                    generateHeader(doc, cb);
                    y = 570;
                }
                generateDetail(doc, cb, i, y,commonComparisonTotalDto);
                y = y - 25;
                if (y < 50) {
                    printPageNumber(cb);
                    doc.newPage();
                    beginPage = true;
                }
            }
            printPageNumber(cb);

        } catch (DocumentException dex) {
            dex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (doc != null) {
                doc.close();
            }
            if (docWriter != null) {
                docWriter.close();
            }
        }
    }

    private void generateLayout(Document doc, PdfContentByte cb) {

        try {

            cb.setLineWidth(1f);


            // Invoice Detail box layout
            cb.rectangle(20, 50, 550, 580);
            cb.moveTo(20, 600);
            cb.lineTo(570, 600);
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
            createHeadings(cb, 22, 603, "Category Name");
            createHeadings(cb, 205, 603, "Quantity");
            createHeadings(cb, 290, 603, "Discount");
            createHeadings(cb, 375, 603, "Total Sales");
            createHeadings(cb, 465, 603, "Tax");
            createHeadings(cb, 530, 603, "Profit");




        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void generateHeader(Document doc, PdfContentByte cb) {

        try {


            createHeadings(cb, 20, 660, "Date:");
            createHeadings(cb, 265, 750, "Excell Wireless");
            createHeadings(cb, 240, 710, "Sales By Category Report");



        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void generateDetail(Document doc, PdfContentByte cb, int index, int y, CommonComparisonTotalDto commonComparisonTotalDto) {
        DecimalFormat df = new DecimalFormat("0.00");

        try {

            createContent(cb, 90, y,commonComparisonTotalDto.getCommonComparisonDtos().get(index).getCommanName(), PdfContentByte.ALIGN_RIGHT);
            createContent(cb, 225, y,df.format(commonComparisonTotalDto.getCommonComparisonDtos().get(index).getQuantity()), PdfContentByte.ALIGN_CENTER);
            createContent(cb, 310, y, df.format(commonComparisonTotalDto.getCommonComparisonDtos().get(index).getDiscount()), PdfContentByte.ALIGN_CENTER);

            createContent(cb, 400, y, df.format(commonComparisonTotalDto.getCommonComparisonDtos().get(index).getSalesTotal()), PdfContentByte.ALIGN_CENTER);
            createContent(cb, 475, y, df.format(commonComparisonTotalDto.getCommonComparisonDtos().get(index).getTax()), PdfContentByte.ALIGN_CENTER);
            createContent(cb, 550, y, df.format(commonComparisonTotalDto.getCommonComparisonDtos().get(index).getProfitAmount()), PdfContentByte.ALIGN_CENTER);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void createHeadings(PdfContentByte cb, float x, float y, String text) {


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

    private void createContent(PdfContentByte cb, float x, float y, String text, int align) {


        cb.beginText();
        cb.setFontAndSize(bf, 8);
        cb.showTextAligned(align, text.trim(), x, y, 0);
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


    }*/







