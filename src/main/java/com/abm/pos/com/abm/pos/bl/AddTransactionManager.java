package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.AddTransactionDto;
import com.abm.pos.com.abm.pos.dto.AddTransactionLineItemDto;
import com.abm.pos.com.abm.pos.dto.AddTransactionPaymentDto;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by asp5045 on 5/20/16.
 */
@Component
public class AddTransactionManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SQLQueries sqlQueries;

    public void addTransactionToDB(AddTransactionDto addTransactionDto) {

        try
        {
            jdbcTemplate.update(sqlQueries.addTransaction,
                    addTransactionDto.getTransactionDate(),
                    addTransactionDto.getTotalAmount(),
                    addTransactionDto.getTax(),
                    addTransactionDto.getDiscount(),
                    addTransactionDto.getCustomerId(),
                    addTransactionDto.getUserId(),
                    addTransactionDto.getPaymentId());
            System.out.println("Transaction Added Successfully");

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void addTransactionLineItemToDB(AddTransactionLineItemDto addTransactionLineItemDto) {
        try
        {
            jdbcTemplate.update(sqlQueries.addTransactionLineItem,
                    addTransactionLineItemDto.getTransactionId(),
                    addTransactionLineItemDto.getProductId(),
                    addTransactionLineItemDto.getQuantity(),
                    addTransactionLineItemDto.getRetail(),
                    addTransactionLineItemDto.getCost(),
                    addTransactionLineItemDto.getTax(),
                    addTransactionLineItemDto.getDiscount());
            System.out.println("Transaction Line Item Added Successfully");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void addTransactionPaymentToDB(AddTransactionPaymentDto transactionPaymentDto) {
        try
        {
            jdbcTemplate.update(sqlQueries.addTransactionPaymentDetail,

                    transactionPaymentDto.getTransactionId(),
                    transactionPaymentDto.getTransactionDate(),
                    transactionPaymentDto.getPaymentId(),
                    transactionPaymentDto.getPaymentAmount());
            System.out.println("Transaction Payment Details Added Successfully");

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
