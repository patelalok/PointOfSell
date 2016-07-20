package com.abm.pos.com.abm.pos.bl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/**
 * Created by asp5045 on 7/18/16.
 */

@Component
public class BarcodeManager {

    Response response;

    public static final String DEST = "/Users/asp5045/Desktop/out.png";
    public static final String IMG1 = "/Users/asp5045/Desktop/out.png";
    public static final String IMG2 = "resources/images/dog.bmp";
    public static final String IMG3 = "resources/images/fox.bmp";
}



       /* Image img1 = Image.getInstance("Screen Shot 2016-07-11 at 1.49.18 PM.png");
        try {

            Code39Bean bean = new Code39Bean();
            final int dpi = 150;

            //Configure the barcode generator
            bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); //makes the narrow bar, width exactly one pixel
            bean.setWideFactor(3);
            bean.doQuietZone(false);

            //Open output file
            File outputFile = new File("Screen Shot 2016-07-11 at 1.49.18 PM.png");
            OutputStream out = new FileOutputStream(outputFile);
            try {

                //Set up the canvas provider for monochrome PNG output
                BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                        out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);


                //Generate the barcode
                bean.generateBarcode(canvas, productName + price);


                //Signal end of generation
                canvas.finish();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                out.close();
            }


            PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream("AddImageExample.pdf"));
            document.open();

            PdfPTable table = new PdfPTable(5); // 3 columns.

            table.setWidthPercentage(100); //Width 100%
            // table.setSpacingBefore(10f); //Space before table
            //  table.setSpacingAfter(24f); //Space after table

            //Set Column widths
            float[] columnWidths = {10f, 10f, 10f, 10f, 10f};
            table.setWidths(columnWidths);

            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);

            document.add(table);

            document.close();
            writer.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

return document;
    }
}*/
        /*Document document = new Document();

        try {


            FileOutputStream file = new FileOutputStream("/Users/asp5045/Downloads/AddImageExample2.pdf");
            PdfWriter writer = PdfWriter.getInstance(document, file);

            document.open();

            addImage(document, productName, price);

            //document.add(new Paragraph("Image Example"));

            //Add Image
            Image image1 = Image.getInstance("/Users/asp5045/Downloads/out1.png");
            //Fixed Positioning
            image1.setAbsolutePosition(10f, 770f);
            //Scale to new height and new width of image
            image1.scaleAbsolute(100, 50);
            //Add to document

            for (int i = 0; i <= noOfBarcode; i++) {
                document.add(image1);
            }

            document.close();
            writer.close();


        }
        catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return document;
    }*/


        //String name = "Running shoes";
        // String price = "$12.99";


   /* private void addImage(Document document, String productName,double price) throws IOException, DocumentException {

        Code39Bean bean = new Code39Bean();
        final int dpi = 150;

        //Configure the barcode generator
        bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); //makes the narrow bar, width exactly one pixel
        bean.setWideFactor(3);
        bean.doQuietZone(false);

        //Open output file
        File outputFile = new File("/Users/asp5045/Downloads/out1.png");
        OutputStream out = new FileOutputStream(outputFile);
        PdfPTable table = new PdfPTable(3);


        try {

            //Set up the canvas provider for monochrome PNG output
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                    out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);


            //Generate the barcode
            bean.generateBarcode(canvas,productName + price);


            //Signal end of generation
            canvas.finish();
          document.add(table);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally {
            out.close();
        }

    }*/

 /* PdfPTable table1 = new PdfPTable(5); // 3 columns.

            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(7.5f); //Space before table
            table.setSpacingAfter(7.5f); //Space after table

            //Set Column widths
            float[] columnWidths1 = {1.5f, 1.5f, 1.5f, 1.5f, 1.5f};
            table.setWidths(columnWidths);

            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);

            PdfPTable table2 = new PdfPTable(5); // 3 columns.

            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(7.5f); //Space before table
            table.setSpacingAfter(7.5f); //Space after table

            //Set Column widths
            float[] columnWidths2 = {1.5f, 1.5f, 1.5f, 1.5f, 1.5f};
            table.setWidths(columnWidths);

            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);

            PdfPTable table3 = new PdfPTable(5); // 3 columns.

            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(7.5f); //Space before table
            table.setSpacingAfter(7.5f); //Space after table

            //Set Column widths
            float[] columnWidths3 ={ 1.5f, 1.5f, 1.5f, 1.5f, 1.5f};
            table.setWidths(columnWidths);

            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);

            PdfPTable table4 = new PdfPTable(5); // 3 columns.

            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(7.5f); //Space before table
            table.setSpacingAfter(7.5f); //Space after table
            //Set Column widths
            float[] columnWidths4 = { 1.5f, 1.5f, 1.5f, 1.5f, 1.5f};
            table.setWidths(columnWidths);

            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);

            PdfPTable table5 = new PdfPTable(5); // 3 columns.

            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(7.5f); //Space before table
            table.setSpacingAfter(7.5f); //Space after table

            //Set Column widths
            float[] columnWidths5 = { 1.5f, 1.5f, 1.5f, 1.5f, 1.5f};
            table.setWidths(columnWidths);

            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);


            PdfPTable table6 = new PdfPTable(5); // 3 columns.

            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(7.5f); //Space before table
            table.setSpacingAfter(7.5f); //Space after table

            //Set Column widths
            float[] columnWidths6 = { 1.5f, 1.5f, 1.5f, 1.5f, 1.5f};
            table.setWidths(columnWidths);

            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);


            PdfPTable table7 = new PdfPTable(5); // 3 columns.

            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(7.5f); //Space before table
            table.setSpacingAfter(7.5f); //Space after table

            //Set Column widths
            float[] columnWidths7 = { 1.5f, 1.5f, 1.5f, 1.5f, 1.5f};
            table.setWidths(columnWidths);

            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);


            PdfPTable table8 = new PdfPTable(5); // 3 columns.

            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(7.5f); //Space before table
            table.setSpacingAfter(7.5f); //Space after table

            //Set Column widths
            float[] columnWidths8 = { 1.5f, 1.5f, 1.5f, 1.5f, 1.5f};
            table.setWidths(columnWidths);

            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);


            PdfPTable table9 = new PdfPTable(5); // 3 columns.

            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(7.5f); //Space before table
            table.setSpacingAfter(7.5f); //Space after table

            //Set Column widths
            float[] columnWidths9 = { 1.5f, 1.5f, 1.5f, 1.5f, 1.5f};
            table.setWidths(columnWidths);

            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);
            table.addCell(img1);

*/




