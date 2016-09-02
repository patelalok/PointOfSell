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

        String pdfFilename = "test.pdf";
        PointOfSaleApplication generateInvoice = new PointOfSaleApplication();

       // generateInvoice.createPDF(pdfFilename);

    }

   /* private void createPDF (String pdfFilename){

        Document doc = new Document();
        PdfWriter docWriter = null;
        initializeFonts();

        try {

            docWriter = PdfWriter.getInstance(doc , new FileOutputStream("/Users/asp5045/Documents/PointOfSell/src/main/resources/AddImageExample.pdf"));
            doc.addAuthor("betterThanZero");
            doc.addCreationDate();
            doc.addProducer();
            doc.addCreator("MySampleCode.com");
            doc.addTitle("Invoice");
            doc.setPageSize(PageSize.A2);

            doc.open();
            PdfContentByte cb = docWriter.getDirectContent();

            boolean beginPage = true;
            int y = 0;

            for(int i=0; i < 100; i++ ){
                if(beginPage){
                    beginPage = false;
                    generateLayout(doc, cb);
                    generateHeader(doc, cb);
                    y = 651;
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
            cb.rectangle(420,700,150,60);
            cb.moveTo(420,720);
            cb.lineTo(570,720);
            cb.moveTo(420,740);
            cb.lineTo(570,740);
            cb.moveTo(480,700);
            cb.lineTo(480,760);
            cb.stroke();

            // Invoice Header box Text Headings
            createHeadings(cb,422,743,"Account No.");
            createHeadings(cb,422,723,"Invoice No.");
            createHeadings(cb,422,703,"Invoice Date");

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

            // Invoice Detail box Text Headings
            createHeadings(cb,22,633,"Qty");
            createHeadings(cb,52,633,"Item Number");
            createHeadings(cb,152,633,"Item Description");
            createHeadings(cb,432,633,"Price");
            createHeadings(cb,502,633,"Ext Price");

            //add the images
            Image companyLogo = Image.getInstance("images/olympics_logo.gif");
            companyLogo.setAbsolutePosition(25,700);
            companyLogo.scalePercent(25);
            doc.add(companyLogo);

        }

        catch (DocumentException dex){
            dex.printStackTrace();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    private void generateHeader(Document doc, PdfContentByte cb)  {

        try {

            createHeadings(cb,200,750,"Company Name");
            createHeadings(cb,200,735,"Address Line 1");
            createHeadings(cb,200,720,"Address Line 2");
            createHeadings(cb,200,705,"City, State - ZipCode");
            createHeadings(cb,200,690,"Country");

            createHeadings(cb,482,743,"ABC0001");
            createHeadings(cb,482,723,"123456");
            createHeadings(cb,482,703,"09/26/2012");

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



       /* Document doc = new Document();
        PdfWriter docWriter = null;

        DecimalFormat df = new DecimalFormat("0.00");

        try {

            //special font sizes
            Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
            Font bf12 = new Font(Font.FontFamily.TIMES_ROMAN, 12);

            //file path

            docWriter = PdfWriter.getInstance(doc , new FileOutputStream("HelloWorld-Table.pdf"));

            //document header attributes
            doc.addAuthor("betterThanZero");
            doc.addCreationDate();
            doc.addProducer();
            doc.addCreator("MySampleCode.com");
            doc.addTitle("Report with Column Headings");
            doc.setPageSize(PageSize.LETTER);

            //open document
            doc.open();

            //create a paragraph
            Paragraph paragraph = new Paragraph("iText ® is a library that allows you to create and " +
                    "manipulate PDF documents. It enables developers looking to enhance web and other " +
                    "applications with dynamic PDF document generation and/or manipulation.");


            //specify column widths
            float[] columnWidths = {1.5f, 2f, 5f, 2f};
            //create PDF table with the given widths
            PdfPTable table = new PdfPTable(columnWidths);
            // set table width a percentage of the page width
            table.setWidthPercentage(90f);

            //insert column headings
            insertCell(table, "Order No", Element.ALIGN_RIGHT, 1, bfBold12);
            insertCell(table, "Account No", Element.ALIGN_LEFT, 1, bfBold12);
            insertCell(table, "Account Name", Element.ALIGN_LEFT, 1, bfBold12);
            insertCell(table, "Order Total", Element.ALIGN_RIGHT, 1, bfBold12);
            table.setHeaderRows(1);

            //insert an empty row
            insertCell(table, "", Element.ALIGN_LEFT, 4, bfBold12);
            //create section heading by cell merging
            insertCell(table, "New York Orders ...", Element.ALIGN_LEFT, 4, bfBold12);
            double orderTotal, total = 0;

            //just some random data to fill
            for(int x=1; x<5; x++){

                insertCell(table, "10010" + x, Element.ALIGN_RIGHT, 1, bf12);
                insertCell(table, "ABC00" + x, Element.ALIGN_LEFT, 1, bf12);
                insertCell(table, "This is Customer Number ABC00" + x, Element.ALIGN_LEFT, 1, bf12);

                orderTotal = Double.valueOf(df.format(Math.random() * 1000));
                total = total + orderTotal;
                insertCell(table, df.format(orderTotal), Element.ALIGN_RIGHT, 1, bf12);

            }
            //merge the cells to create a footer for that section
            insertCell(table, "New York Total...", Element.ALIGN_RIGHT, 3, bfBold12);
            insertCell(table, df.format(total), Element.ALIGN_RIGHT, 1, bfBold12);

            //repeat the same as above to display another location
            insertCell(table, "", Element.ALIGN_LEFT, 4, bfBold12);
            insertCell(table, "California Orders ...", Element.ALIGN_LEFT, 4, bfBold12);
            orderTotal = 0;

            for(int x=1; x<7; x++){

                insertCell(table, "20020" + x, Element.ALIGN_RIGHT, 1, bf12);
                insertCell(table, "XYZ00" + x, Element.ALIGN_LEFT, 1, bf12);
                insertCell(table, "This is Customer Number XYZ00" + x, Element.ALIGN_LEFT, 1, bf12);

                orderTotal = Double.valueOf(df.format(Math.random() * 1000));
                total = total + orderTotal;
                insertCell(table, df.format(orderTotal), Element.ALIGN_RIGHT, 1, bf12);

            }
            insertCell(table, "California Total...", Element.ALIGN_RIGHT, 3, bfBold12);
            insertCell(table, df.format(total), Element.ALIGN_RIGHT, 1, bfBold12);

            //add the PDF table to the paragraph
            paragraph.add(table);
            // add the paragraph to the document
            doc.add(paragraph);

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
            if (doc != null){
                //close the document
                doc.close();
            }
            if (docWriter != null){
                //close the writer
                docWriter.close();
            }
        }*/




    }


//    private static void insertCell(PdfPTable table, String text, int align, int colspan, Font font){
//
//        //create a new cell with the specified Text and Font
//        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
//        //set the cell alignment
//        cell.setHorizontalAlignment(align);
//        //set the cell column span in case you want to merge two or more cells
//        cell.setColspan(colspan);
//        //in case there is no text and you wan to create an empty row
//        if(text.trim().equalsIgnoreCase("")){
//            cell.setMinimumHeight(10f);
//        }
//        //add the call to the table
//        table.addCell(cell);
//
//    }






