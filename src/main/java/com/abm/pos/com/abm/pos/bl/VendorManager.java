package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.AddProductDto;
import com.abm.pos.com.abm.pos.dto.AddVendorDto;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by asp5045 on 5/24/16.
 */
@Component
public class VendorManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQuery;

    public void addVendorToDB(AddVendorDto vendorDto) {

        try
        {
            jdbcTemplate.update(sqlQuery.addVendorQuery,
                    vendorDto.getVendorName(),
                    vendorDto.getDescription());
            System.out.println("Vendor Added Successfully");

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void editVendorToDB(AddVendorDto vendorDto) {

        try
        {
            jdbcTemplate.update(sqlQuery.editVendorQuery,
                    vendorDto.getVendorName(),
                    vendorDto.getDescription());
            System.out.println("Vendor Edited Successfully");

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void getVendorDetails(String vendorId) {

        try {
            jdbcTemplate.query(sqlQuery.getVendorDetails, new VendorManager.AddVenderMapper());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
        private static final class AddVenderMapper implements RowMapper<AddVendorDto>
        {

            @Override
            public AddVendorDto mapRow(ResultSet rs, int rowNum) throws SQLException {

                AddVendorDto vendor = new AddVendorDto();

                vendor.setVendorName(rs.getString("VENDOR_NAME"));
                vendor.setDescription(rs.getString("DESCRIPTION"));

                return vendor;
            }
        }

    public void deleteVendorToDB(String vendorId) {
    }


}
