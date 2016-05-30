package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.AddBrandDto;
import com.abm.pos.com.abm.pos.dto.AddCategoryDto;
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
public class BrandManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQuery;

    public void addBrandToDB(AddBrandDto addBrandDto) {
        try
        {
            jdbcTemplate.update(sqlQuery.addBrandQuery,
                    addBrandDto.getBrandName(),
                    addBrandDto.getBrandDescription());
            System.out.println("Brand Added Successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void editBrandToDB(AddBrandDto addBrandDto) {

        try
        {
            jdbcTemplate.update(sqlQuery.editBrandQuery,
                    addBrandDto.getBrandName(),
                    addBrandDto.getBrandDescription());
            System.out.println("Brand Edited Successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void getBrandDetails(AddBrandDto addBrandDto) {

        try
        {
            jdbcTemplate.query(sqlQuery.getBrandDetails, new BrandManager.AddBrandMapper());
        }

        catch (Exception e) {
            System.out.println(e);
        }
    }

    private static final class AddBrandMapper implements RowMapper<AddBrandDto>
    {

        @Override
        public AddBrandDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            AddBrandDto brand = new AddBrandDto();

            brand.setBrandName(rs.getString("BRAND_ID"));
            brand.setBrandDescription(rs.getString("DESCRIPTION"));

            return brand;
        }
    }


    public void deleteBrandToDB(AddBrandDto addBrandDto) {
    }


}
