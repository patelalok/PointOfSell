package com.abm.pos.com.abm.pos.controllers;

import com.abm.pos.com.abm.pos.bl.OrderManager;
import com.abm.pos.com.abm.pos.dto.ModelDto;
import com.abm.pos.com.abm.pos.dto.OrderDto;
import com.abm.pos.com.abm.pos.dto.TransactionLineItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by apatel2 on 6/25/17.
 */

@RestController
@RequestMapping(value = "")
@CrossOrigin(origins = {"*"})
public class OrderController {

    @Autowired
    private OrderManager orderManager;

    @RequestMapping(value = "/createOrder",method = RequestMethod.POST, consumes = "application/json")
    public void createOrder(@RequestBody OrderDto orderDto)
    {
        orderManager.createOrder(orderDto);
    }

    @RequestMapping(value = "/createOrderDetails",method = RequestMethod.POST, consumes = "application/json")
    public void createOrderDetails(@RequestBody List<OrderDto> orderDtoList)
    {
        orderManager.createOrderDetails(orderDtoList);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUniqueOrderId", produces = "application/json")
    public int getUniqueOrderId()
    {
        return orderManager.getUniqueOrderId() + 1;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getOrderDetailsById", produces = "application/json")
    public List<OrderDto> getOrderDetailsById(@RequestParam int  orderId)
    {
        return orderManager.getOrderDetailsById(orderId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllOrders", produces = "application/json")
    public List<OrderDto> getAllOrders(@RequestParam String startDate, @RequestParam String endDate)
    {
        return orderManager.getAllOrders(startDate, endDate);
    }

    @RequestMapping(value = "/UpdateQuantityByOrderDetails",method = RequestMethod.POST, consumes = "application/json")
    public void UpdateQuantityByOrderDetails(@RequestBody List<OrderDto> orderDtoList)
    {
        orderManager.UpdateQuantityByOrderDetails(orderDtoList);
    }
}
