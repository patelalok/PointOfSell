package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.BrandDto;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.coyote.http11.Constants.a;

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

    private final class BrandMapper implements RowMapper<BrandDto>
    {

        @Override
        public BrandDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            BrandDto brand = new BrandDto();

            brand.setBrandId(rs.getInt("BRAND_ID"));

            int noOfProducts = jdbcTemplate.queryForObject(sqlQuery.getNoOfProductsForBrand, new Object[] {brand.getBrandId()},Integer.class);

            brand.setBrandName(rs.getString("BRAND_NAME"));
            brand.setBrandDescription(rs.getString("DESCRIPTION"));
            brand.setNoOfProducts(noOfProducts);
            brand.setFilterValue(rs.getString("BRAND_NAME"));

            return brand;
        }

        private void getNoOfProduct(int a) {


        }
    }


    public void deleteBrandFromDB(int  brandId) {

        try {
            int a =  jdbcTemplate.queryForObject(sqlQuery.getBrandFromProductTable, new Object[]{brandId}, Integer.class);
            System.out.println(a);

            if(a == 0)
            {
                jdbcTemplate.update(sqlQuery.deleteBrand, brandId);
                System.out.println("Brand deleted successfully");

            }
            else
            {
                System.out.println("This Brand is associate with product so can not delete it.");

            }
        }

        catch (Exception e)
        {
            System.out.println(e);
        }
    }


}
