package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.AddBrandDto;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by asp5045 on 5/24/16.
 */
public class BrandHandlerManager {

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

    public void deleteBrandToDB(AddBrandDto addBrandDto) {
    }
}
