package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.BrandDto;
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
public class BrandManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQuery;

    public void addBrandToDB(BrandDto brandDto) {
        try
        {
            jdbcTemplate.update(sqlQuery.addBrandQuery,
                    brandDto.getBrandName(),
                    brandDto.getBrandDescription());
            System.out.println("Brand Added Successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void editBrandToDB(BrandDto brandDto) {

        try
        {
            jdbcTemplate.update(sqlQuery.editBrandQuery,
                    brandDto.getBrandName(),
                    brandDto.getBrandDescription(),
                    brandDto.getBrandId());
            System.out.println("Brand Edited Successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public List<BrandDto> getBrandDetails() {

        List<BrandDto> brandList = new ArrayList<>();

        try
        {
            brandList = jdbcTemplate.query(sqlQuery.getBrandDetails, new BrandMapper());
            System.out.println("Send All Brand Details Successfully");
        }

        catch (Exception e) {
            System.out.println(e);
        }
        return brandList;
    }

    private static final class BrandMapper implements RowMapper<BrandDto>
    {

        @Override
        public BrandDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            BrandDto brand = new BrandDto();

            brand.setBrandId(rs.getInt("BRAND_ID"));
            brand.setBrandName(rs.getString("BRAND_NAME"));
            brand.setBrandDescription(rs.getString("DESCRIPTION"));

            return brand;
        }
    }


    public void deleteBrandToDB(BrandDto brandDto) {
    }


}
