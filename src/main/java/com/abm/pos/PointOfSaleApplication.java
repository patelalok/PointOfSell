package com.abm.pos;

import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.image.BufferedImage;
import java.io.*;

import static java.lang.System.out;

@SpringBootApplication
public class PointOfSaleApplication {

	public static void main(String[] args) throws IOException {
        SpringApplication.run(PointOfSaleApplication.class, args);


}

	}

