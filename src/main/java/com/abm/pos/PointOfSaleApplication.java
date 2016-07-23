package com.abm.pos;

import com.itextpdf.text.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.pdf.PdfWriter;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Calendar;
import java.util.Iterator;

import static java.lang.System.out;

@SpringBootApplication
public class PointOfSaleApplication {

	public static void main(String[] args) throws IOException, BadElementException {
		SpringApplication.run(PointOfSaleApplication.class, args);

		String excelFilePath = "/Users/asp5045/Desktop/Test.xlsx";
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();

		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();

				switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						System.out.print(cell.getStringCellValue());
						break;
//					case Cell.CELL_TYPE_BOOLEAN:
//						System.out.print(cell.getBooleanCellValue());
//						break;
//					case Cell.CELL_TYPE_NUMERIC:
//						System.out.print(cell.getNumericCellValue());
//						break;
				}

			}
			System.out.println();
		}

		workbook.close();
		inputStream.close();


		/*Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, 6);
		cal.set(Calendar.DAY_OF_MONTH, 28);
		int start = cal.get(Calendar.WEEK_OF_MONTH);
		System.out.println(start);*/

		/*Document document = new Document();
			try {
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("/Users/asp5045/Downloads/AddImageExample2.pdf"));
				document.open();
				//document.add(new Paragraph("Image Example"));

				//Add Image
				Image image1 = Image.getInstance("out1.png");
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
			}*/


		}
	}





