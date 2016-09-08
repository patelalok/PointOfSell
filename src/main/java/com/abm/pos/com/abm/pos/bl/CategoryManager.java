package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.CategoryDto;
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
                    categoryDto.getDescription(),
                    categoryDto.getCategoryId());

            System.out.println("Category Edited Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<CategoryDto> getCategoryDetails() {

        List<CategoryDto> categoryList = new ArrayList<>();
        try
        {
            categoryList =  jdbcTemplate.query(sqlQuery.getCategoryDetails, new CategoryMapper());
            System.out.println("Send All Category Details Successfully");
        }

        catch (Exception e) {
            System.out.println(e);
        }

        return categoryList;
    }

    private final class CategoryMapper implements RowMapper<CategoryDto>
    {

        @Override
        public CategoryDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            CategoryDto category = new CategoryDto();

            category.setCategoryId(rs.getInt("CATEGORY_ID"));

            int noOfProducts = jdbcTemplate.queryForObject(sqlQuery.getNoOfProductsForCategory, new Object[] {category.getCategoryId()},Integer.class);
            category.setCategoryName(rs.getString("CATEGORY_NAME"));
            category.setDescription(rs.getString("DESCRIPTION"));
            category.setNoOfProducts(noOfProducts);
            category.setFilterValue(rs.getString("CATEGORY_NAME"));

            return category;
        }
    }

    public int deleteCategoryToDB(int categoryId) {

        int result = 0;
        try {


           // int a =  jdbcTemplate.queryForObject(sqlQuery.getCategoryFromProductTable, new Object[]{categoryId}, Integer.class);
            //System.out.println(a);

            result = jdbcTemplate.update(sqlQuery.deleteCategory, categoryId);

        }

        catch (Exception e)
        {
            System.out.println(e);
        }

        return result;
    }



}
