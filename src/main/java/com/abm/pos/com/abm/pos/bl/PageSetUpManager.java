package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.PageSetUpDto;
import com.abm.pos.com.abm.pos.dto.ProductDto;
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

    private final class SetupDetailsMapper implements RowMapper<PageSetUpDto> {

        @Override
        public PageSetUpDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            PageSetUpDto setUpDto = new PageSetUpDto();

            setUpDto.setId(rs.getInt("GET_PAGE_SETUP_DETAILS_ID"));
            setUpDto.setTax(rs.getInt("TAX"));
            setUpDto.setStoreAddress(rs.getString("STORE_ADDRESS"));
            setUpDto.setStoreLogo(rs.getString("STORE_LOGO"));

            return setUpDto;
        }
    }

}