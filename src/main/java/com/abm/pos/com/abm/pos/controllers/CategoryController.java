package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.CategoryManager;
import com.abm.pos.com.abm.pos.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by asp5045 on 5/17/16.
 */
@RestController
@RequestMapping(value = "")
public class CategoryController {

    @Autowired
    CategoryManager categoryManager;

    @RequestMapping(value = "/addCategory",method = RequestMethod.POST, consumes = "application/json")
    public void addCategory(@RequestBody CategoryDto categoryDto)
    {
            categoryManager.addCategoryToDB(categoryDto);
    }

    @RequestMapping(value = "/editCategory",method = RequestMethod.POST, consumes = "application/json")
    public void editCategory(@RequestBody CategoryDto categoryDto)
    {
        categoryManager.editCategoryToDB(categoryDto);
    }

    @RequestMapping(value = "/getCategory",method = RequestMethod.POST, consumes = "application/json")
    public void getCategoryDetails(@RequestBody CategoryDto categoryDto)
    {
        categoryManager.getCategoryDetails(categoryDto);
    }

    @RequestMapping(value = "/deleteCategory",method = RequestMethod.POST, consumes = "application/json")
    public void deleteCategory(@RequestBody String categoryId)
    {
        categoryManager.deleteCategoryToDB(categoryId);
    }

}
