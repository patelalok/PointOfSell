package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.AddCategoryDto;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by asp5045 on 5/24/16.
 */
public class CategoryHandlerManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQuery;

    public void addCategoryToDB(AddCategoryDto categoryDto) {
        try
        {
            jdbcTemplate.update(sqlQuery.addCategoryQuery,
                    categoryDto.getCategoryName(),
                    categoryDto.getDescription());
            System.out.println("Category Added Successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void editCategoryToDB(AddCategoryDto categoryDto) {
        try
        {
            jdbcTemplate.update(sqlQuery.editCategoryQuery,
                    categoryDto.getCategoryName(),
                    categoryDto.getDescription());
            System.out.println("Category Edited Successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void deleteCategoryToDB(AddCategoryDto categoryDto) {
    }



}
