package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.CategoryManager;
import com.abm.pos.com.abm.pos.bl.ModelManager;
import com.abm.pos.com.abm.pos.dto.CategoryDto;
import com.abm.pos.com.abm.pos.dto.ModelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by asp5045 on 11/6/16.
 */

@RestController
@RequestMapping(value = "")
@CrossOrigin(origins = {"*"})
public class ModelController {


    @Autowired
    ModelManager modelManager;

    @RequestMapping(value = "/addModel",method = RequestMethod.POST, consumes = "application/json")
    public void addModel(@RequestBody ModelDto modelDto)
    {
        modelManager.addModelToDB(modelDto);
    }

    @RequestMapping(value = "/editModel",method = RequestMethod.POST, consumes = "application/json")
    public void editModel(@RequestBody ModelDto modelDto)
    {
        modelManager.editModelFromDB(modelDto);

    }

    @RequestMapping(value = "/getCategory",method = RequestMethod.GET)
    public List<CategoryDto> getCategoryDetails()
    {
        return modelManager.getModelDetails();
    }

    /*@RequestMapping(value = "/{categoryId}",method = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable int categoryId)
    {
        categoryManager.deleteCategoryToDB(categoryId);
    }*/

    @RequestMapping(value = "/deleteModel" ,method = RequestMethod.POST)
    public ResponseEntity deleteModel(@RequestParam int  categoryId) {

        int result = 0;

        result = modelManager.deleteModelToDB(categoryId);

        if(result ==1)
        {
            return ResponseEntity.ok("Category Deleted Successfully");
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body("Can not delete Category please delete all products in this Category first");
    }
}
