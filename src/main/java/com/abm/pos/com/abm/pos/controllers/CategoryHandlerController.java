package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.CategoryHandlerManager;
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
public class CategoryHandlerController {

    @Autowired
    CategoryHandlerManager categoryHandlerManager;



    @RequestMapping(value = "/addCategory",method = RequestMethod.POST, consumes = "application/json")
    public void addCategory(@RequestBody AddCategoryDto categoryDto)
    {
        try
        {
            categoryHandlerManager.addCategoryToDB(categoryDto);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @RequestMapping(value = "/editCategory",method = RequestMethod.POST, consumes = "application/json")
    public void editCategory(@RequestBody AddCategoryDto categoryDto)
    {
        try
        {
            categoryHandlerManager.editCategoryToDB(categoryDto);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    @RequestMapping(value = "/deleteCategory",method = RequestMethod.POST, consumes = "application/json")
    public void deleteCategory(@RequestBody AddCategoryDto categoryDto)
    {
        try
        {
            categoryHandlerManager.deleteCategoryToDB(categoryDto);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


   /* @RequestMapping(value = "/editProducts",method = RequestMethod.POST, consumes = "application/json")
    public void editProduct(@RequestBody AddProductDto productDto)
    {
        try
        {
            editManager.editProductToDB(productDto);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }*/

}
