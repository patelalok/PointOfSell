package com.abm.pos.com.abm.pos.bl;

import com.abm.pos.com.abm.pos.dto.OrderDto;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apatel2 on 6/25/17.
 */

@Component
public class OrderManager {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SQLQueries sqlQueries;

    public void createOrder(OrderDto orderDto) {

        try {


            jdbcTemplate.update(
                    sqlQueries.addOrder,
                    orderDto.getOrderId(),
                    orderDto.getLastUpdatedOrderDate(),
                    orderDto.getUsername(),
                    orderDto.getVendorName(),
                    orderDto.getVendorId());

            System.out.println("Order Created Successfully!!!");
        }

        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void createOrderDetails(List<OrderDto> orderDto) {

        try {
            jdbcTemplate.batchUpdate(sqlQueries.addOrderDetails, new BatchPreparedStatementSetter() {


                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {

                    OrderDto orderDto1 = orderDto.get(i);

                    ps.setInt(1, orderDto1.getOrderId());
                    ps.setString(2, orderDto1.getProductNo());
                    ps.setDouble(3, orderDto1.getCostPrice());
                    ps.setDouble(4, orderDto1.getRetailPrice());
                    ps.setInt(5, orderDto1.getQuantity());

                }

                @Override
                public int getBatchSize() {
                    return orderDto.size();
                }
            });

            System.out.println("OrderDetails Added Successfully");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<OrderDto> getOrderDetailsById(int orderId) {

        List<OrderDto> orderDtoList = new ArrayList<>();

        try {
            orderDtoList = jdbcTemplate.query(sqlQueries.getOrderDetailsById, new OrderDetailsMapper(), orderId);

            System.out.println("Send Order Details Successfully For OrderId ---> "+ orderId);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return orderDtoList;


    }

    public int getUniqueOrderId() {

        return jdbcTemplate.queryForObject(sqlQueries.getLastOrderId, new Object[]{}, Integer.class);
    }

    public void UpdateQuantityByOrderDetails(List<OrderDto> orderDtoList) {

        try {
            jdbcTemplate.batchUpdate(sqlQueries.UpdateQuantityByOrderDetails, new BatchPreparedStatementSetter() {


                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {

                    OrderDto orderDto1 = orderDtoList.get(i);

                    ps.setInt(1, orderDto1.getQuantity());
                    ps.setDouble(2, orderDto1.getCostPrice());
                    ps.setDouble(3, orderDto1.getRetailPrice());
                    ps.setString(4, orderDto1.getProductNo());

                }

                @Override
                public int getBatchSize() {
                    return orderDtoList.size();
                }
            });

            System.out.println("Quantity  Updated Successfully by Order Number");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<OrderDto> getAllOrders(String startDate, String endDate) {

        List<OrderDto> orderDtoList = new ArrayList<>();

        try {

            orderDtoList = jdbcTemplate.query(sqlQueries.getAllOrdersByDate, new OrderMapper(), startDate, endDate);

        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return orderDtoList;
    }


    private final class OrderDetailsMapper implements RowMapper<OrderDto>
    {

        @Override
        public OrderDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            OrderDto orderDto = new OrderDto();

            orderDto.setOrderId(rs.getInt("ORDER_ID"));
            orderDto.setProductNo(rs.getString("PRODUCT_NO"));
            orderDto.setCostPrice(rs.getDouble("COST_PRICE"));
            orderDto.setRetailPrice(rs.getDouble("RETAIL_PRICE"));
            orderDto.setQuantity(rs.getInt("QUANTITY"));

            return orderDto;
        }
    }

    private final class OrderMapper implements RowMapper<OrderDto>
    {

        @Override
        public OrderDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            OrderDto orderDto = new OrderDto();

            orderDto.setOrderId(rs.getInt("ORDER_ID"));
            orderDto.setLastUpdatedOrderDate(rs.getString("LASTUPDATED_ORDER_DATE"));
            orderDto.setUsername(rs.getString("ORDER_CREATED_BY"));
            orderDto.setVendorName(rs.getString("VENDOR_NAME"));
            orderDto.setVendorId(rs.getInt("VENDOR_ID"));

            return orderDto;
        }
    }
}
