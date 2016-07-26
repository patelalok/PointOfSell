package com.abm.pos;

import com.itextpdf.text.BadElementException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class PointOfSaleApplication {

	public static void main(String[] args) throws IOException, BadElementException {
		SpringApplication.run(PointOfSaleApplication.class, args);

		}
	}





