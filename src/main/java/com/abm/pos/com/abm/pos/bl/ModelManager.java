package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.CategoryDto;
import com.abm.pos.com.abm.pos.dto.ModelDto;
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
 * Created by asp5045 on 11/6/16.
 */
@Component
public class ModelManager {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQuery;

    public void addModelToDB(ModelDto modelDto) {
        try {
            jdbcTemplate.update(sqlQuery.addModelQuery,
                    modelDto.getModelName(),
                    modelDto.getDescription());

            System.out.println("Model Added Successfully");

        } catch (Exception e) {

            System.out.println(e);
        }
    }

    public void editModelFromDB(ModelDto modelDto) {
        try {
            jdbcTemplate.update(sqlQuery.editModelQuery,
                    modelDto.getModelName(),
                    modelDto.getDescription(),
                    modelDto.getModelId());

            System.out.println("Model Edited Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<ModelDto> getModelDetails() {
        List<ModelDto> modelDtoList = new ArrayList<>();
        try {
            modelDtoList = jdbcTemplate.query(sqlQuery.getModelDetails, new ModelManager.ModelMapper());
            System.out.println("Send All Models Details Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }

        return modelDtoList;
    }

    private final class ModelMapper implements RowMapper<ModelDto>
    {

    @Override
    public ModelDto mapRow(ResultSet rs, int rowNum) throws SQLException {

        ModelDto modelDto = new ModelDto();

        modelDto.setModelId(rs.getInt("ID"));

        int noOfProducts = jdbcTemplate.queryForObject(sqlQuery.getNoOfProductsForModel, new Object[]{modelDto.getModelId()}, Integer.class);
        modelDto.setModelName(rs.getString("NAME"));
        modelDto.setDescription(rs.getString("DESCRIPTION"));
        modelDto.setNoOfProducts(noOfProducts);

        return modelDto;
    }

}


    public int deleteModelToDB(int modelId) {
        int result = 0;
        try {


            // int a =  jdbcTemplate.queryForObject(sqlQuery.getCategoryFromProductTable, new Object[]{categoryId}, Integer.class);
            //System.out.println(a);

            result = jdbcTemplate.update(sqlQuery.deleteModel, modelId);

        }

        catch (Exception e)
        {
            System.out.println(e);
        }

        return result;
    }
    }


