package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.*;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import org.apache.poi.ss.formula.functions.Value;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by asp5045 on 7/14/16.
 */

@Component
public class PageSetUpManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQueries;

    public void editPageSetupDetails(PageSetUpDto pageSetUpDto) {

        try {
            jdbcTemplate.update(sqlQueries.editPageSetUpDetails,
                    pageSetUpDto.getTax(),
                    pageSetUpDto.getStoreAddress(),
                    pageSetUpDto.getStoreLogo(),
                    pageSetUpDto.getFooterReceipt(),
                    pageSetUpDto.getStoreEmail(),
                    pageSetUpDto.getId());


            System.out.println("PageSetUp Details Edited Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public List<PageSetUpDto> getPageSetUpDetails() {

        List<PageSetUpDto> pageSetUpDto = new ArrayList<>();
        try {
            pageSetUpDto = jdbcTemplate.query(sqlQueries.getPageSetUpDetails, new SetupDetailsMapper());
        } catch (Exception e) {
            System.out.println(e);
        }


        return pageSetUpDto;
    }

            final class SetupDetailsMapper implements RowMapper<PageSetUpDto> {

                @Override
                public PageSetUpDto mapRow(ResultSet rs, int rowNum) throws SQLException {

                    PageSetUpDto setUpDto = new PageSetUpDto();

                    setUpDto.setId(rs.getInt("GET_PAGE_SETUP_DETAILS_ID"));
                    setUpDto.setTax(rs.getInt("TAX"));
                    setUpDto.setStoreAddress(rs.getString("STORE_ADDRESS"));
                    setUpDto.setStoreLogo(rs.getString("STORE_LOGO"));
                    setUpDto.setFooterReceipt(rs.getString("FOOTER_RECEIPT"));
                    setUpDto.setStoreEmail(rs.getString("STORE_EMAIL"));

                    return setUpDto;
                }
            }


    public void addProductToDB(final List<MultyAddProductDto> multyAddProductDtos) {
        try {

            jdbcTemplate.batchUpdate(sqlQueries.addProductQuery, new BatchPreparedStatementSetter() {



                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {

                    MultyAddProductDto multyAddProductDto = multyAddProductDtos.get(i);

                    ps.setString(1, multyAddProductDto.getProductNo());
                    ps.setString(2, multyAddProductDto.getCategoryId());
                    ps.setString(3, multyAddProductDto.getVendorId());
                    ps.setString(4, multyAddProductDto.getBrandId());
                    ps.setString(5, multyAddProductDto.getAltNo());
                    ps.setString(6, multyAddProductDto.getDescription());
                    ps.setString(7, multyAddProductDto.getCostPrice());
                    ps.setString(8, multyAddProductDto.getMarkup());
                    ps.setString(9, multyAddProductDto.getRetailPrice());
                    ps.setString(10, multyAddProductDto.getQuantity());
                    ps.setInt(11, multyAddProductDto.getMinProductQuantity());
                    ps.setString(12, multyAddProductDto.getReturnRule());
                    ps.setString(13, multyAddProductDto.getImage());
                    ps.setString(14, multyAddProductDto.getCreatedDate());
                    ps.setString(15, multyAddProductDto.getImeiNo());
                    ps.setString(16, multyAddProductDto.getTax());



                    System.out.println("All Products Added Successfully");


                }

                @Override
                public int getBatchSize() {
                    return multyAddProductDtos.size();
                }
            });
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


    public void addMultyCustomer(final List<CustomerDto> customerDto) {

        try {

            jdbcTemplate.batchUpdate(sqlQueries.addMultyCustomerQuery, new BatchPreparedStatementSetter() {



                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {

                    CustomerDto customerDto1 = customerDto.get(i);

                    ps.setString(1, customerDto1.getFirstName());
                    ps.setString(2, customerDto1.getLastName());
                    ps.setString(3, customerDto1.getPhoneNo());
                    ps.setString(4, customerDto1.getEmail());
                    ps.setString(5, customerDto1.getTaxId());
                    ps.setString(6, customerDto1.getCompanyName());

                    System.out.println("All Customers Added Successfully");

                }

                @Override
                public int getBatchSize() {
                    return customerDto.size();
                }
            });
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }




    public List<MultyAddProductDto> readExcelSheet() {

        List<MultyAddProductDto> multyAddProductDtos = new ArrayList<>();


        FileInputStream fis = null;
        try {
            fis = new FileInputStream("/Users/asp5045/Desktop/SimplyPrepadenurbhai.xlsx");

            // Using XSSF for xlsx format, for xls use HSSF
            Workbook workbook = new XSSFWorkbook(fis);

            int numberOfSheets = workbook.getNumberOfSheets();

            //looping over each workbook sheet
            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                Iterator rowIterator = sheet.iterator();

                //iterating over each row
                while (rowIterator.hasNext()) {

                    MultyAddProductDto product = new MultyAddProductDto();
                    Row row = (Row) rowIterator.next();
                    Iterator cellIterator = row.cellIterator();

                    //Iterating over each cell (column wise)  in a particular row.
                    while (cellIterator.hasNext()) {

                        Cell cell = (Cell) cellIterator.next();

                        //The Cell Containing String will is name.
                        if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
                            if (cell.getColumnIndex() == 1) {
                                product.setDescription(cell.getStringCellValue());




                            }
                            else if (cell.getColumnIndex() == 0) {
                                product.setProductNo(cell.getStringCellValue());
                            }
                            //The Cell Containing numeric value will contain marks
                        } else if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                            //Cell with index 1 contains marks in upc

                            //Cell with index 2 contains marks in quantity
                            if (cell.getColumnIndex() == 2) {
                                product.setQuantity(String.valueOf(cell.getNumericCellValue()));
                            }
                            //Cell with index 3 contains marks in cost
                            else if (cell.getColumnIndex() == 3) {
                                product.setCostPrice(String.valueOf(cell.getNumericCellValue()));
                            }
                            //Cell with index 3 contains marks in retail
                            else if (cell.getColumnIndex() == 4) {
                                product.setRetailPrice(String.valueOf(cell.getNumericCellValue()));
                            }
                            //Cell with index 3 contains marks in category
                            else if (cell.getColumnIndex() == 5) {
                                product.setCategoryId(String.valueOf(cell.getNumericCellValue()));
                            }
                            //Cell with index 3 contains marks in brand
                            else if (cell.getColumnIndex() == 6) {
                                product.setBrandId(String.valueOf(cell.getNumericCellValue()));
                            }
                            //Cell with index 3 contains marks in vendor
                            else if (cell.getColumnIndex() == 7) {
                                product.setVendorId(String.valueOf(cell.getNumericCellValue()));
                            }
                        }
                    }
                    //end iterating a row, add all the elements of a row in list
                    multyAddProductDtos.add(product);
                }
            }

            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return multyAddProductDtos;
    }

}




