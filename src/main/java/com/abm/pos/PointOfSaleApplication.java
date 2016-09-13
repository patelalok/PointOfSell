package com.abm.pos;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

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

        generateInvoice.createPDF();

    }

    private void createPDF (){

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

            for(int i=0; i < 100; i++ ){
                if(beginPage){
                    beginPage = false;
                    generateLayout(doc, cb);
                    generateHeader(doc, cb);
                    y = 615;
                }
                generateDetail(doc, cb, i, y);
                y = y - 15;
                if(y < 50){
                    printPageNumber(cb);
                    doc.newPage();
                    beginPage = true;
                }
            }
            printPageNumber(cb);

        }
        catch (DocumentException dex)
        {
            dex.printStackTrace();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (doc != null)
            {
                doc.close();
            }
            if (docWriter != null)
            {
                docWriter.close();
            }
        }
    }

    private void generateLayout(Document doc, PdfContentByte cb)  {

        try {

            cb.setLineWidth(1f);

            // Invoice Header box layout
//            cb.rectangle(420,700,150,60);
//            cb.moveTo(420,720);
//            cb.lineTo(570,720);
//            cb.moveTo(420,740);
//            cb.lineTo(570,740);
//            cb.moveTo(480,700);
//            cb.lineTo(480,760);
//            cb.stroke();
//
//            // Invoice Header box Text Headings
////            createHeadings(cb,422,743,"Account No.");
////            createHeadings(cb,422,723,"Invoice No.");
////            createHeadings(cb,422,703,"Invoice Date");

            // Invoice Detail box layout
            cb.rectangle(20,50,550,600);
            cb.moveTo(20,630);
            cb.lineTo(570,630);
            cb.moveTo(50,50);
            cb.lineTo(50,650);
            cb.moveTo(150,50);
            cb.lineTo(150,650);
            cb.moveTo(430,50);
            cb.lineTo(430,650);
            cb.moveTo(500,50);
            cb.lineTo(500,650);
            cb.stroke();


            createHeadings(cb,22,633,"Qty");
            createHeadings(cb,52,633,"Item Number");
            createHeadings(cb,152,633,"Item Description");
            createHeadings(cb,432,633,"Price");
            createHeadings(cb,502,633,"Ext Price");





        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    private void generateHeader(Document doc, PdfContentByte cb)  {

        try {

//            createHeadings(cb,200,750,"Company Name");
//            createHeadings(cb,200,735,"Address Line 1");
//            createHeadings(cb,200,720,"Address Line 2");
//            createHeadings(cb,200,705,"City, State - ZipCode");
//            createHeadings(cb,200,690,"Country");
//
//            createHeadings(cb,482,743,"ABC0001");
//            createHeadings(cb,482,723,"123456");
//            createHeadings(cb,482,703,"09/26/2012");

        }

        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    private void generateDetail(Document doc, PdfContentByte cb, int index, int y)  {
        DecimalFormat df = new DecimalFormat("0.00");

        try {

            createContent(cb,48,y,String.valueOf(index+1),PdfContentByte.ALIGN_RIGHT);
            createContent(cb,52,y, "ITEM" + String.valueOf(index+1),PdfContentByte.ALIGN_LEFT);
            createContent(cb,152,y, "Product Description - SIZE " + String.valueOf(index+1),PdfContentByte.ALIGN_LEFT);

            double price = Double.valueOf(df.format(Math.random() * 10));
            double extPrice = price * (index+1) ;
            createContent(cb,498,y, df.format(price),PdfContentByte.ALIGN_RIGHT);
            createContent(cb,568,y, df.format(extPrice),PdfContentByte.ALIGN_RIGHT);

        }

        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    private void createHeadings(PdfContentByte cb, float x, float y, String text){


        cb.beginText();
        cb.setFontAndSize(bfBold, 8);
        cb.setTextMatrix(x,y);
        cb.showText(text.trim());
        cb.endText();

    }

    private void printPageNumber(PdfContentByte cb){


        cb.beginText();
        cb.setFontAndSize(bfBold, 8);
        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Page No. " + (pageNumber+1), 570 , 25, 0);
        cb.endText();

        pageNumber++;

    }

    private void createContent(PdfContentByte cb, float x, float y, String text, int align){


        cb.beginText();
        cb.setFontAndSize(bf, 8);
        cb.showTextAligned(align, text.trim(), x , y, 0);
        cb.endText();

    }

    private void initializeFonts(){


        try {
            bfBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
            doc.addTitle("Close Register Report");
            doc.setPageSize(PageSize.A4);

            doc.open();
            PdfContentByte cb = docWriter.getDirectContent();

            boolean beginPage = true;
            int y = 0;

            for (int i = 0; i < 100; i++) {
                if (beginPage) {
                    beginPage = false;
                    generateLayout(doc, cb);
                    generateHeader(doc, cb);
                    y = 615;
                }
                generateClosingDetails(doc, cb, i, y);
                y = y - 15;
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

//            // Invoice Header box layout
//            cb.rectangle(420,700,150,60);
//            cb.moveTo(420,720);
//            cb.lineTo(570,720);
//            cb.moveTo(420,740);
//            cb.lineTo(570,740);
//            cb.moveTo(480,700);
//            cb.lineTo(480,760);
//            cb.stroke();

//            // Invoice Header box Text Headings
//            createHeadings(cb,422,743,"Account No.");
//            createHeadings(cb,422,723,"Invoice No.");
//            createHeadings(cb,422,703,"Invoice Date");

            // Invoice Detail box layout
            //cb.rectangle(20,50,550,600);
            cb.rectangle(20, 50, 560, 660);
            // cb.moveTo(20,630);
            cb.moveTo(20, 690);
            cb.lineTo(580, 690);
            cb.moveTo(150, 150);
            cb.lineTo(150, 710);
            cb.moveTo(290, 150);
            cb.lineTo(290, 710);
            cb.moveTo(430, 150);
            cb.lineTo(430, 710);
            cb.stroke();

            // Invoice Detail box Text Headings
            createHeadings(cb, 50, 693, "Payment Types");
            createHeadings(cb, 200, 693, "From User");
            createHeadings(cb, 340, 693, "From System");
            createHeadings(cb, 480, 693, "Difference");

            cb.moveTo(20, 660);
            cb.lineTo(580, 660);

            cb.moveTo(20, 630);
            cb.lineTo(580, 630);

            cb.moveTo(20, 600);
            cb.lineTo(580, 600);

            cb.moveTo(20, 570);
            cb.lineTo(580, 570);

            cb.moveTo(20, 540);
            cb.lineTo(580, 540);

            cb.moveTo(20, 510);
            cb.lineTo(580, 510);

            cb.moveTo(20, 480);
            cb.lineTo(580, 480);

            cb.moveTo(20, 450);
            cb.lineTo(580, 450);

            cb.moveTo(20, 420);
            cb.lineTo(580, 420);

            cb.moveTo(20, 390);
            cb.lineTo(580, 390);

            cb.moveTo(20, 360);
            cb.lineTo(580, 360);

            cb.moveTo(20, 330);
            cb.lineTo(580, 330);

            cb.stroke();

            createHeadings(cb, 480, 693, "Difference");


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void generateHeader(Document doc, PdfContentByte cb) {

        try {

            createHeadingsAlokTest(cb, 220, 815, "Close Register Report");
            createHeadings(cb, 20, 780, "Date:");
            createHeadings(cb, 450, 780, "Today's Profit:");


            createHeadings(cb, 40, 780, "09/26/2012");
            createHeadings(cb, 508, 780, "$40,000");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

//    private void generateDetail(Document doc, PdfContentByte cb, int index, int y)  {
//        DecimalFormat df = new DecimalFormat("0.00");
//
//        try {
//
//            createContent(cb,48,y,String.valueOf(index+1),PdfContentByte.ALIGN_RIGHT);
//            createContent(cb,52,y, "ITEM" + String.valueOf(index+1),PdfContentByte.ALIGN_LEFT);
//            createContent(cb,152,y, "Product Description - SIZE " + String.valueOf(index+1),PdfContentByte.ALIGN_LEFT);
//
//            double price = Double.valueOf(df.format(Math.random() * 10));
//            double extPrice = price * (index+1) ;
//            createContent(cb,498,y, df.format(price),PdfContentByte.ALIGN_RIGHT);
//            createContent(cb,568,y, df.format(extPrice),PdfContentByte.ALIGN_RIGHT);
//
//        }
//
//        catch (Exception ex){
//            ex.printStackTrace();
//        }
//
//    }

    private void generateClosingDetails(Document doc, PdfContentByte cb, int index, int y) {
        DecimalFormat df = new DecimalFormat("0.00");

        try {

            createContent(cb, 21, 675, "Credit", PdfContentByte.ALIGN_LEFT);
            createContent(cb, 21, 645, "Debit", PdfContentByte.ALIGN_LEFT);
            createContent(cb, 21, 615, "Cash", PdfContentByte.ALIGN_LEFT);
            createContent(cb, 21, 585, "Check", PdfContentByte.ALIGN_LEFT);

            createContent(cb, 21, 555, "Paid Out (Sum)", PdfContentByte.ALIGN_LEFT);
            createContent(cb, 152, 555, "Paid Out-1", PdfContentByte.ALIGN_LEFT);
            createContent(cb, 292, 555, "Paid Out-2", PdfContentByte.ALIGN_LEFT);
            createContent(cb, 432, 555, "Paid Out-3", PdfContentByte.ALIGN_LEFT);


            //This just for now need to replace with the real values from service call.
            createContent(cb, 21, 520, "Paid Out (Sum)", PdfContentByte.ALIGN_LEFT);
            createContent(cb, 152, 520, "Paid Out-1", PdfContentByte.ALIGN_LEFT);
            createContent(cb, 292, 520, "Paid Out-2", PdfContentByte.ALIGN_LEFT);
            createContent(cb, 432, 520, "Paid Out-3", PdfContentByte.ALIGN_LEFT);

            createContent(cb, 21, 490, "Actual Daily Total", PdfContentByte.ALIGN_LEFT);
            createContent(cb, 152, 490, "Total From User", PdfContentByte.ALIGN_LEFT);
            createContent(cb, 292, 490, "Total From System", PdfContentByte.ALIGN_LEFT);
            createContent(cb, 432, 490, "Total Difference", PdfContentByte.ALIGN_LEFT);

            //This just for now need to replace with the real values from service call.
            createContent(cb, 21, 460, "Actual Daily Total", PdfContentByte.ALIGN_LEFT);
            createContent(cb, 152, 460, "Total From User", PdfContentByte.ALIGN_LEFT);
            createContent(cb, 292, 460, "Total From System", PdfContentByte.ALIGN_LEFT);
            createContent(cb, 432, 460, "Total Difference", PdfContentByte.ALIGN_LEFT);

            createContent(cb, 21, 430, "Net Sales", PdfContentByte.ALIGN_LEFT);
            createContent(cb, 152, 430, "Tax", PdfContentByte.ALIGN_LEFT);
            createContent(cb, 292, 430, "Discount", PdfContentByte.ALIGN_LEFT);
            createContent(cb, 432, 430, "Gross Sales", PdfContentByte.ALIGN_LEFT);


            //This just for now need to replace with the real values from service call.
            createContent(cb, 21, 400, "Net Sales", PdfContentByte.ALIGN_LEFT);
            createContent(cb, 152, 400, "Tax", PdfContentByte.ALIGN_LEFT);
            createContent(cb, 292, 400, "Discount", PdfContentByte.ALIGN_LEFT);
            createContent(cb, 432, 400, "Gross Sales", PdfContentByte.ALIGN_LEFT);


            createContent(cb, 21, 370, "Customer Balance", PdfContentByte.ALIGN_LEFT);
            createContent(cb, 152, 370, "Commission", PdfContentByte.ALIGN_LEFT);
            createContent(cb, 292, 370, "Bank Deposit", PdfContentByte.ALIGN_LEFT);
            createContent(cb, 432, 370, "Cash In Hand", PdfContentByte.ALIGN_LEFT);

            //This just for now need to replace with the real values from service call.
            createContent(cb, 21, 340, "Customer Balance", PdfContentByte.ALIGN_LEFT);
            createContent(cb, 152, 340, "Commission", PdfContentByte.ALIGN_LEFT);
            createContent(cb, 292, 340, "Bank Deposit", PdfContentByte.ALIGN_LEFT);
            createContent(cb, 432, 340, "Cash In Hand", PdfContentByte.ALIGN_LEFT);


//            createContent(cb,48,y,String.valueOf(index+1),PdfContentByte.ALIGN_RIGHT);
//            createContent(cb,52,y, "ITEM" + String.valueOf(index+1),PdfContentByte.ALIGN_LEFT);
//            createContent(cb,152,y, "Product Description - SIZE " + String.valueOf(index+1),PdfContentByte.ALIGN_LEFT);
//
//            double price = Double.valueOf(df.format(Math.random() * 10));
//            double extPrice = price * (index+1) ;
//            createContent(cb,498,y, df.format(price),PdfContentByte.ALIGN_RIGHT);
//            createContent(cb,568,y, df.format(extPrice),PdfContentByte.ALIGN_RIGHT);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void createHeadings(PdfContentByte cb, float x, float y, String text) {


        cb.beginText();
        cb.setFontAndSize(bfBold, 8);
        cb.setTextMatrix(x, y);
        cb.showText(text.trim());
        cb.endText();

    }

    private void createHeadingsAlokTest(PdfContentByte cb, float x, float y, String text) {


        cb.beginText();
        cb.setFontAndSize(bfBold, 12);
        cb.setTextMatrix(x, y);
        cb.showText(text.trim());
        cb.endText();

    }

    private void printPageNumber(PdfContentByte cb) {


        cb.beginText();
        cb.setFontAndSize(bfBold, 8);
        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Page No. " + (pageNumber + 1), 570, 25, 0);
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
}







