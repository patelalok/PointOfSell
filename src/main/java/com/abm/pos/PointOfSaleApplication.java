package com.abm.pos;

import com.abm.pos.com.abm.pos.bl.ReportManager;
import com.abm.pos.com.abm.pos.bl.SalesManager;
import com.abm.pos.com.abm.pos.dto.ReceiptDto;
import com.abm.pos.com.abm.pos.dto.reports.CommonComparisonTotalDto;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import com.itextpdf.text.*;
import com.itextpdf.text.List;
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
import java.util.*;

@SpringBootApplication
@EnableAutoConfiguration
public class PointOfSaleApplication {

    public static void main(String[] args) throws IOException, DocumentException {

        SpringApplication.run(PointOfSaleApplication.class, args);
    }
}








