package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.CategoryDto;
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
public class CategoryManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQuery;

    public void addCategoryToDB(CategoryDto categoryDto) {
        try {
            jdbcTemplate.update(sqlQuery.addCategoryQuery,
                    categoryDto.getCategoryName(),
                    categoryDto.getDescription());
            System.out.println("Category Added Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void editCategoryToDB(CategoryDto categoryDto) {
        try {
            jdbcTemplate.update(sqlQuery.editCategoryQuery,
                    categoryDto.getCategoryName(),
                    categoryDto.getDescription());
            System.out.println("Category Edited Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void getCategoryDetails(CategoryDto categoryDto) {

        try
        {
            jdbcTemplate.query(sqlQuery.getCategoryDetails, new CategoryManager.AddCategoryMapper());
        }

        catch (Exception e) {
            System.out.println(e);
        }
    }

    private static final class AddCategoryMapper implements RowMapper<CategoryDto>
    {

        @Override
        public CategoryDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            CategoryDto category = new CategoryDto();

            category.setCategoryName(rs.getString("CATEGORY_NAME"));
            category.setDescription(rs.getString("DESCRIPTION"));

            return category;
        }
    }

    public void deleteCategoryToDB(String categoryId) {
    }



}
