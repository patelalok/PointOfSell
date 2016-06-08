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

                /*List<Integer> brandId = new ArrayList<Integer>();
                brandId.add(rs.getInt("BRAND_ID"));

            for(int a : brandId)
            {
                System.out.println(a);
                jdbcTemplate.query(sqlQuery.getNoOfProducts, new BrandMapper());*/

                brand.setBrandId(rs.getInt("BRAND_ID"));
            int ab = brand.getBrandId();
             int noOfProduct;
            Object[] parameters = new Object[] {new Integer(ab)};

            List l = jdbcTemplate.queryForList("SELECT COUNT(*) from PRODUCT where BRAND_ID = ? ",
                    parameters);
            String alok = l.toString();

                    System.out.println(alok);

                brand.setBrandName(rs.getString("BRAND_NAME"));
                brand.setBrandDescription(rs.getString("DESCRIPTION"));









            return brand;
        }

        private void getNoOfProduct(int a) {


        }
    }


    public void deleteBrandToDB(BrandDto brandDto) {
    }


}
