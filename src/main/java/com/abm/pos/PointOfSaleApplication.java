package com.abm.pos;

import com.itextpdf.text.Image;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Calendar;

import static java.lang.System.out;

@SpringBootApplication
public class PointOfSaleApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(PointOfSaleApplication.class, args);

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, 6);
		cal.set(Calendar.DAY_OF_MONTH, 28);
		int start = cal.get(Calendar.WEEK_OF_MONTH);
		System.out.println(start);

		Document document = new Document();
			try {
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("AddImageExample2.pdf"));
				document.open();
				//document.add(new Paragraph("Image Example"));

				//Add Image
				Image image1 = Image.getInstance("out.png");
				//Fixed Positioning
				image1.setAbsolutePosition(10f, 770f);
				//Scale to new height and new width of image
				image1.scaleAbsolute(100, 50);
				//Add to document

				for(int i = 0; i<=3; i++) {
					document.add(image1);
				}

				//String imageUrl = "http://www.eclipse.org/xtend/images/java8_logo.png";
				//Image image2 = Image.getInstance(new URL(imageUrl));
				//document.add(image2);

				document.close();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}


		}
	}





