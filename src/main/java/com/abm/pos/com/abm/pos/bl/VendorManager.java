package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.VendorDto;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asp5045 on 5/24/16.
 */
@Component
public class VendorManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQuery;

    public void addVendorToDB(VendorDto vendorDto) {

        try
        {
            jdbcTemplate.update(sqlQuery.addVendorQuery,
                    vendorDto.getVendorName(),
                    vendorDto.getDescription(),
                    vendorDto.getCommision());
            System.out.println("Vendor Added Successfully");

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void editVendorToDB(VendorDto vendorDto) {

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

    public List<VendorDto> getVendorDetails() {

        List<VendorDto> vendorList = new ArrayList<>();

        try
        {
            vendorList = jdbcTemplate.query(sqlQuery.getVendorDetails, new VendorMapper());
            System.out.println("Send All Vendor Details Successfully.");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return vendorList;

    }
        private static final class VendorMapper implements RowMapper<VendorDto>
        {

            @Override
            public VendorDto mapRow(ResultSet rs, int rowNum) throws SQLException {

                VendorDto vendor = new VendorDto();

                vendor.setVendorId(rs.getInt("VENDOR_ID"));
                vendor.setVendorName(rs.getString("VENDOR_NAME"));
                vendor.setDescription(rs.getString("DESCRIPTION"));
                vendor.setCommision(rs.getString("COMMISION"));

                return vendor;
            }
        }

    public void deleteVendorToDB(String vendorId) {
    }


}
